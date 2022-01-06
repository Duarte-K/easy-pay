package com.example.easypay.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.easypay.model.PayModel;

import java.util.UUID;

import cielo.orders.domain.CheckoutRequest;
import cielo.orders.domain.Credentials;
import cielo.orders.domain.Order;
import cielo.orders.domain.ResultOrders;
import cielo.sdk.order.OrderManager;
import cielo.sdk.order.ServiceBindListener;
import cielo.sdk.order.payment.Payment;
import cielo.sdk.order.payment.PaymentCode;
import cielo.sdk.order.payment.PaymentError;
import cielo.sdk.order.payment.PaymentListener;

public class APILio {
    private Order order;
    private OrderManager orderManager;
    private ResultOrders resultOrders;


    public void payment(PayModel payment) {
        CheckoutRequest request;
        final PaymentCode paymentCode;

        if(payment.getPayOption().equals("CRÉDITO") && payment.getInstallments() == 1){
            paymentCode = PaymentCode.CREDITO_AVISTA;
        }else if(payment.getPayOption().equals("CRÉDITO") && payment.getInstallments() > 1){
            paymentCode = PaymentCode.CREDITO_PARCELADO_LOJA;
        }else{
            paymentCode = PaymentCode.DEBITO_AVISTA;
        }
        request = new CheckoutRequest.Builder()
                .orderId(order.getId())
                .amount(order.getPrice())
                .installments(payment.getInstallments())
                .paymentCode(paymentCode)
                .build();

        orderManager.checkoutOrder(request, new PaymentListener() {
            @Override
            public void onStart() {
                Log.d("SDKClient", "o pagamento iniciou");
            }

            @Override
            public void onPayment(Order order) {
                Log.d("SDKClient", "o pagamento foi realizado");
            }

            @Override
            public void onCancel() {
                Log.d("SDKClient", "operação cancelada");
            }

            @Override
            public void onError(PaymentError paymentError) {
                Log.d("SDKClient", "erro no pagamento");
            }
        });
    }

    public ResultOrders getResultOrders() {
        return resultOrders;
    }

    public void setResultOrders(ResultOrders resultOrders) {
        this.resultOrders = resultOrders;
    }

    public void initSDK(Context context) {
        /*======================= Credenciais ======================*/
        Credentials credentials = new Credentials("BkX665M4vQAkGhL23LFbWuAYjt9loQj1RBIpNa5sw7vIlTAWqA", "SrhFWLwcYGpmJHxHHME8vBHvgWUH3qIgEGl0P35JPw0xZKexhu");
        /*======================= Criando OrderManager ======================*/
        orderManager = new OrderManager(credentials, context);
        ServiceBindListener serviceBindListener = new ServiceBindListener() {

            @Override
            public void onServiceBoundError(Throwable throwable) {
                Log.e("onServiceBoundError", throwable.getMessage());
                //Ocorreu um erro ao tentar se conectar com o serviço OrderManager
            }

            @Override
            public void onServiceBound() {
                Log.e("onServiceBound", "Conectado");
                setResultOrders(orderManager.retrieveOrders(200, 0));
                //Você deve garantir que sua aplicação se conectou com a LIO a partir desse listener
                //A partir desse momento você pode utilizar as funções do OrderManager, caso contrário uma exceção será lançada.
            }

            @Override
            public void onServiceUnbound() {
                Log.e("onServiceUnBound", "Desconectado");
                // O serviço foi desvinculado
            }
        };

        orderManager.bind(context, serviceBindListener);
    }

    public void initOrder() throws Exception{
        order = orderManager.createDraftOrder(String.valueOf(UUID.randomUUID()));
    }

    public void addItemToPay(PayModel value)  {
        //order.addItem();
    }

    public void placeOrder() {
        orderManager.placeOrder(order);
    }

    public void unBind() {
        orderManager.unbind();
    }

}



