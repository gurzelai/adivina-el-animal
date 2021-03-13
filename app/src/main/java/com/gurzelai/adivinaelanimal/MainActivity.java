package com.gurzelai.adivinaelanimal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button jugar, instrucciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jugar = (Button) findViewById(R.id.jugar);
        jugar.setOnClickListener(view -> abrirIntentJuego());
        instrucciones = (Button) findViewById(R.id.instrucciones);
        instrucciones.setOnClickListener(view -> abrirIntentInstrucciones());
        abrirIntentJuego();
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