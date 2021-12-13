package com.example.easypay.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.easypay.R;
import com.example.easypay.model.PayModel;
import com.example.easypay.utils.MoneyTextWatcher;


public class DescriptionFragment extends Fragment {

    private Button btnEnviar;
    private PayModel pay;
    private EditText description, value;


    public DescriptionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_description, container, false);
        btnEnviar = view.findViewById(R.id.btn_enviar);
        description = view.findViewById(R.id.tx_Description);
        value = view.findViewById(R.id.tx_Value);
        pay = new PayModel();

        value.addTextChangedListener(new MoneyTextWatcher(value));

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InstallmentsFragment installmentsFragment = new InstallmentsFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_conteudo, installmentsFragment);
                transaction.commit();
            }
        });
        return view;
    }

    public void check(){
        String descpay = description.getText().toString();
        String valuePay = value.getText().toString();

        if(descpay.equals("")){
            description.setError("Preencha o campo para continuar");
        }else if(valuePay.equals("")){
            value.setError("Preencha o campo para continuar");
        }else{
            String valueFormat = value.getText().toString().substring(2);
            float valueFloat = 0;
            if(valueFormat.length() > 6){
                valueFloat =  Float.parseFloat(valueFormat.replaceAll("\\.", "").replaceAll(",", "."));
            }else{
                valueFloat =  Float.parseFloat(valueFormat.replaceAll(",", "."));
            }
            pay.setDescription(descpay);
            pay.setPayValue(valueFloat);

        }

    }
}