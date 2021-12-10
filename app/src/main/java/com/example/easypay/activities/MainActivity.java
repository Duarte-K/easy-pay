package com.example.easypay.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.easypay.R;
import com.example.easypay.fragments.DescriptionFragment;

public class MainActivity extends AppCompatActivity {

    private DescriptionFragment descriptionFragment;
    TextView txTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        descriptionFragment = new DescriptionFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_conteudo, descriptionFragment);
        transaction.commit();
    }
}