package com.gurzelai.adivinaelanimal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Instrucciones extends AppCompatActivity {

    Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones);

        FirebaseAnalytics analytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString("a", "Ha entrado en informacion");
        analytics.logEvent("a", bundle);

        volver = (Button) findViewById(R.id.boton);
        volver.setOnClickListener(view -> finish());
    }
}