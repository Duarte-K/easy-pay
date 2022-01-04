package com.example.easypay.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.easypay.BuildConfig;
import com.example.easypay.R;
import com.example.easypay.model.PayModel;

import java.util.UUID;

import cielo.orders.domain.Credentials;
import cielo.orders.domain.Item;
import cielo.orders.domain.Order;
import cielo.orders.domain.ResultOrders;
import cielo.sdk.order.OrderManager;
import cielo.sdk.order.ServiceBindListener;


public class InstallmentsFragment extends Fragment {
    private Button btnCancel;
    private Button btnFinish;
    private ResultOrders resultOrders;
    public InstallmentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        PayModel str = (PayModel) this.getArguments().getSerializable("payment");
        View view = inflater.inflate(R.layout.fragment_installments, container, false);

        btnCancel = view.findViewById(R.id.btn_cancel);
        btnFinish = view.findViewById(R.id.btn_finish);


        Credentials credentials = new Credentials("BkX665M4vQAkGhL23LFbWuAYjt9loQj1RBIpNa5sw7vIlTAWqA", "SrhFWLwcYGpmJHxHHME8vBHvgWUH3qIgEGl0P35JPw0xZKexhu");

        OrderManager orderManager = new OrderManager(credentials, getActivity());

        ServiceBindListener serviceBindListener = new ServiceBindListener() {
            @Override
            public void onServiceBoundError(Throwable throwable) {
                Log.e("onServiceBoundError", throwable.getMessage());
                //Ocorreu um erro ao tentar se conectar com o serviço OrderManager
            }

            @Override
            public void onServiceBound() {
                Log.e("onServiceBound", "Conectado");
                setResultOrders(orderManager.retrieveOrders(200,0));
                //Você deve garantir que sua aplicação se conectou com a LIO a partir desse listener
                //A partir desse momento você pode utilizar as funções do OrderManager, caso contrário uma exceção será lançada.
            }

            @Override
            public void onServiceUnbound() {
                Log.e("onServiceUnBound", "Desconectado");
                // O serviço foi desvinculado
            }
        };

        orderManager.bind(getContext(), serviceBindListener);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DescriptionFragment descriptionFragment = new DescriptionFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_conteudo, descriptionFragment);
                transaction.commit();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Order order = orderManager.createDraftOrder(String.valueOf(UUID.randomUUID()));
        //order.addItem(str.getPayValue(), str.getDescription(), str.getPayOption());

        return view;
    }

    public ResultOrders getResultOrders() {
        return resultOrders;
    }

    public void setResultOrders(ResultOrders resultOrders) {
        this.resultOrders = resultOrders;
    }

}