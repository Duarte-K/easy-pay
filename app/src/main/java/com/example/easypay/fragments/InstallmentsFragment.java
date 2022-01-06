package com.example.easypay.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.easypay.utils.APILio;
import com.example.easypay.R;
import com.example.easypay.model.PayModel;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;


public class InstallmentsFragment extends Fragment {
    private Button btnFinish;
    private Locale ptBr = new Locale("pt","BR");
    private APILio apiLio = new APILio();
    private RadioButton parcel1,parcel2,parcel3,parcel4,parcel5,parcel6,parcel7,parcel8,parcel9,parcel10,parcel11,parcel12;
    private PayModel str;


    public InstallmentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_installments, container, false);
        str = (PayModel) this.getArguments().getSerializable("payment");
        Log.i("dada",""+str.getPayOption()+","+str.getDescription()+""+str.getPayValue());
        btnFinish = view.findViewById(R.id.btn_finish);
        parcel1 = view.findViewById(R.id.rb_parcel1);
        parcel2 = view.findViewById(R.id.rb_parcel2);
        parcel3 = view.findViewById(R.id.rb_parcel3);
        parcel4 = view.findViewById(R.id.rb_parcel4);
        parcel5 = view.findViewById(R.id.rb_parcel5);
        parcel6 = view.findViewById(R.id.rb_parcel6);
        parcel7 = view.findViewById(R.id.rb_parcel7);
        parcel8 = view.findViewById(R.id.rb_parcel8);
        parcel9 = view.findViewById(R.id.rb_parcel9);
        parcel10 = view.findViewById(R.id.rb_parcel10);
        parcel11 = view.findViewById(R.id.rb_parcel11);
        parcel12 = view.findViewById(R.id.rb_parcel12);
        apiLio.initSDK(getContext());
        checkParcel();


        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    apiLio.initOrder();
                    apiLio.addItemToPay(str);
                    apiLio.placeOrder();
                    apiLio.payment(str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    public void checkParcel(){
        if (str.getPayValue() >= 10) {
            if (str.getPayValue() / 2 >= 5) {
                parcel2.setVisibility(View.VISIBLE);
                float value = str.getPayValue() / 2;
                parcel2.setText("2x de " + NumberFormat.getCurrencyInstance(ptBr).format(value));

            } else {
                if (parcel2.isChecked()) {
                    parcel1.setChecked(true);
                }
                parcel2.setVisibility(View.GONE);
            }
            if (str.getPayValue() / 3 >= 5) {
                parcel3.setVisibility(View.VISIBLE);
                float value = str.getPayValue() / 3;
                parcel3.setText("3x de " + NumberFormat.getCurrencyInstance(ptBr).format(value));
            } else {
                if (parcel3.isChecked()) {
                    parcel1.setChecked(true);
                }
                parcel3.setVisibility(View.GONE);
            }
            if (str.getPayValue() / 4 >= 5) {
                parcel4.setVisibility(View.VISIBLE);
                float value = str.getPayValue() / 4;
                parcel4.setText("4x de " + NumberFormat.getCurrencyInstance(ptBr).format(value));
            } else {
                if (parcel4.isChecked()) {
                    parcel1.setChecked(true);
                }
                parcel4.setVisibility(View.GONE);
            }
            if (str.getPayValue() / 5 >= 5) {
                parcel5.setVisibility(View.VISIBLE);
                float value = str.getPayValue() / 5;
                parcel5.setText("5x de " + NumberFormat.getCurrencyInstance(ptBr).format(value));
            } else {
                if (parcel5.isChecked()) {
                    parcel1.setChecked(true);
                }
                parcel5.setVisibility(View.GONE);
            }
            if (str.getPayValue() / 6 >= 5) {
                parcel6.setVisibility(View.VISIBLE);
                float value = str.getPayValue() / 6;
                parcel6.setText("6x de " + NumberFormat.getCurrencyInstance(ptBr).format(value));
            } else {
                if (parcel6.isChecked()) {
                    parcel1.setChecked(true);
                }
                parcel6.setVisibility(View.GONE);
            }
            if (str.getPayValue() / 7 >= 5) {
                parcel7.setVisibility(View.VISIBLE);
                float value = str.getPayValue() / 7;
                parcel7.setText("7x de " + NumberFormat.getCurrencyInstance(ptBr).format(value));
            } else {
                if (parcel7.isChecked()) {
                    parcel1.setChecked(true);
                }
                parcel7.setVisibility(View.GONE);
            }
            if (str.getPayValue() / 8 >= 5) {
                parcel8.setVisibility(View.VISIBLE);
                float value = str.getPayValue() / 8;
                parcel8.setText("8x de " + NumberFormat.getCurrencyInstance(ptBr).format(value));
            } else {
                if (parcel8.isChecked()) {
                    parcel1.setChecked(true);
                }
                parcel8.setVisibility(View.GONE);
            }
            if (str.getPayValue() / 9 >= 5) {
                parcel9.setVisibility(View.VISIBLE);
                float value = str.getPayValue() / 9;
                parcel9.setText("9x de " + NumberFormat.getCurrencyInstance(ptBr).format(value));
            } else {
                if (parcel9.isChecked()) {
                    parcel1.setChecked(true);
                }
                parcel9.setVisibility(View.GONE);
            }
            if (str.getPayValue() / 10 >= 5) {
                parcel10.setVisibility(View.VISIBLE);
                float value = str.getPayValue() / 10;
                parcel10.setText("10x de " + NumberFormat.getCurrencyInstance(ptBr).format(value));
            } else {
                if (parcel10.isChecked()) {
                    parcel1.setChecked(true);
                }
                parcel10.setVisibility(View.GONE);
            }
            if (str.getPayValue() / 11 >= 5) {
                parcel11.setVisibility(View.VISIBLE);
                float value = str.getPayValue() / 11;
                parcel11.setText("11x de " + NumberFormat.getCurrencyInstance(ptBr).format(value));
            } else {
                if (parcel11.isChecked()) {
                    parcel1.setChecked(true);
                }
                parcel11.setVisibility(View.GONE);
            }
            if (str.getPayValue() / 12 >= 5) {
                parcel12.setVisibility(View.VISIBLE);
                float value = str.getPayValue() / 12;
                parcel12.setText("12x de " + NumberFormat.getCurrencyInstance(ptBr).format(value));
            } else {
                if (parcel12.isChecked()) {
                    parcel1.setChecked(true);
                }
                parcel12.setVisibility(View.GONE);
            }
        }else{
            if(!parcel1.isChecked()){
                parcel1.setChecked(true);
            }
            parcel2.setVisibility(View.GONE);
            parcel3.setVisibility(View.GONE);
            parcel4.setVisibility(View.GONE);
            parcel5.setVisibility(View.GONE);
            parcel6.setVisibility(View.GONE);
            parcel7.setVisibility(View.GONE);
            parcel8.setVisibility(View.GONE);
            parcel9.setVisibility(View.GONE);
            parcel10.setVisibility(View.GONE);
            parcel11.setVisibility(View.GONE);
            parcel12.setVisibility(View.GONE);
        }
    }
}
