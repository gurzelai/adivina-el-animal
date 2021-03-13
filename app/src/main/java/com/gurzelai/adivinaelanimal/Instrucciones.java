package com.gurzelai.adivinaelanimal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class Instrucciones extends AppCompatActivity {

    Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones);

        volver = (Button) findViewById(R.id.boton);
        volver.setOnClickListener(view -> finish());
    }
}