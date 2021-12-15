package com.example.easypay.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easypay.R;
import com.example.easypay.model.PayModel;
import com.example.easypay.utils.MoneyTextWatcher;

import java.time.temporal.TemporalAccessor;

import cielo.orders.domain.Credentials;
import cielo.orders.domain.ResultOrders;
import cielo.sdk.order.OrderManager;
import cielo.sdk.order.ServiceBindListener;


public class DescriptionFragment extends Fragment {

    private Button btnEnviar;
    private PayModel pay;
    private EditText description, value;
    private RadioGroup rgOption;
    private String rbOpt;
    private ResultOrders resultOrders;



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
        rgOption = view.findViewById(R.id.rg_payOption);
        pay = new PayModel();

        value.addTextChangedListener(new MoneyTextWatcher(value));


        rgOption.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_credito){
                    rbOpt = ((RadioButton) view.findViewById(rgOption.getCheckedRadioButtonId())).getText().toString();
                    pay.setPayOption(rbOpt);
                }else{
                    rbOpt = ((RadioButton) view.findViewById(rgOption.getCheckedRadioButtonId())).getText().toString();
                    pay.setPayOption(rbOpt);
                }

            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
                if (check()){
                    Bundle args = new Bundle();
                    args.putSerializable("payment", pay);
                    InstallmentsFragment installmentsFragment = new InstallmentsFragment();
                    installmentsFragment.setArguments(args);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_conteudo, installmentsFragment);
                    transaction.commit();
                }
            }
        });
        return view;
    }

    public boolean check(){
        String descpay = description.getText().toString();
        String valuePay = value.getText().toString();

        if(descpay.equals("")){
            description.setError("Preencha o campo para continuar");
            return false;
        }else if(valuePay.equals("")){
            value.setError("Preencha o campo para continuar");
            return false;
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
        return true;
    }
}
