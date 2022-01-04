package com.example.easypay;

import android.telephony.TelephonyManager;
import android.util.Log;

import cielo.orders.domain.CheckoutRequest;
import cielo.orders.domain.Credentials;
import cielo.orders.domain.Order;
import cielo.orders.domain.ResultOrders;
import cielo.sdk.order.OrderManager;
import cielo.sdk.order.payment.Payment;
import cielo.sdk.order.payment.PaymentCode;
import cielo.sdk.order.payment.PaymentError;
import cielo.sdk.order.payment.PaymentListener;

public class APILio {
    private Order order;
    private OrderManager orderManager;
    private ResultOrders resultOrders;
    private Credentials credentials;

    public void payment(Payment payment){
        CheckoutRequest request;
        final PaymentCode paymentCode;

        /*if(payment.getPaymentFormat().equals("CRÉDITO") && payment.getNumberParcel() == 1){
            paymentCode = PaymentCode.CREDITO_AVISTA;
        }else if(payment.getPaymentFormat().equals("CRÉDITO") && payment.getNumberParcel > 1){
            paymentCode = PaymentCode.CREDITO_PARCELADO_LOJA;
        }else{
            paymentCode = PaymentCode.DEBITO_AVISTA;
        }

        if(payment.getNumberParcel() == 1){
            request = new CheckoutRequest.Builder()
                    .orderId(order.getId())
                    .amount(order.getPrice())
                    .paymentCode(paymentCode)
                    .build();
        }else{
            request = new CheckoutRequest.Builder()
                    .orderId(order.getId())
                    .amount(order.getPrice())
                    .installments(payment.getNumberParcel())
                    .paymentCode(paymentCode)
                    .build();
        }
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
        });*/
    }
}


