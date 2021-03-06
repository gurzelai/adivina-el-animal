package com.gurzelai.adivinaelanimal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class Acierto extends AppCompatActivity {

    GifImageView gifImageView;
    MediaPlayer mp;
    Button atras;
    TextView puntos;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acierto);

        int puntosActuales = getIntent().getIntExtra("puntos", 0);
        String nombreDelAnimal = getIntent().getStringExtra("nombre del animal");
        puntos = findViewById(R.id.puntos);
        puntos.setText(puntos.getText().toString() + puntosActuales);
        TextView respuesta = findViewById(R.id.tvRespuesta);
        String respuestaCorrecta = respuesta.getText().toString() + "<b>"+ nombreDelAnimal.toUpperCase() +"</b>";
        respuesta.setText(HtmlCompat.fromHtml(respuestaCorrecta, Typeface.BOLD));
        gifImageView = findViewById(R.id.gif);
        atras = findViewById(R.id.atras);
        atras.setOnClickListener(view -> cerrar());
        Random r = new Random();

        if (puntosActuales % 10 == 0 && getIntent().getIntExtra("vidas", 0) < 3)
            mostrarDialogoSumarVida(puntosActuales);
        gifImageView.setImageResource(getResources().getIdentifier("aplausos" + (r.nextInt(8)), "drawable", getPackageName()));
        mp = MediaPlayer.create(getApplicationContext(), getResources().getIdentifier("correcto", "raw", getPackageName()));
        mp.start();
    }

    private void cerrar() {
        if (mp.isPlaying()) mp.stop();
        finish();
    }

    private void mostrarDialogoSumarVida(int puntos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("??UNA VIDA M??S!");
        builder.setIcon(R.drawable.corazon);
        builder.setMessage("Acabas de consegir " + puntos + " puntos.\nTendr??s una vida m??s cada 10 puntos.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}