package com.gurzelai.adivinaelanimal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    Button jugar, instrucciones, ajustes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jugar = (Button) findViewById(R.id.jugar);
        jugar.setOnClickListener(view -> abrirIntentJuego());
        instrucciones = (Button) findViewById(R.id.instrucciones);
        instrucciones.setOnClickListener(view -> abrirIntentInstrucciones());
        ajustes = findViewById(R.id.ajustes);
        ajustes.setOnClickListener(view -> abrirIntentConfiguracion());
    }

    private void abrirIntentConfiguracion() {
        Intent intent = new Intent(getApplicationContext(), Configuracion.class);
        startActivity(intent);
    }

    private void abrirIntentInstrucciones() {
        Intent intent = new Intent(getApplicationContext(), Instrucciones.class);
        startActivity(intent);
    }

    private void abrirIntentJuego() {
        Intent intent = new Intent(getApplicationContext(), Juego.class);
        startActivity(intent);
    }
}