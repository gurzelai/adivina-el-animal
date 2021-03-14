package com.gurzelai.adivinaelanimal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.BuddhistCalendar;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Juego extends AppCompatActivity {

    String[] animales = {"burro", "cabra", "canario", "gabiota", "lobo", "tigre"};
    Random generador;
    ImageButton uno, dos, tres, cuatro;
    MediaPlayer mp;
    String correcto;
    List<String> animalesSelect;
    FloatingActionButton boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        getSupportActionBar().setTitle("Adivina el animal");

        uno = findViewById(R.id.imagen1);
        dos =  findViewById(R.id.imagen2);
        tres = findViewById(R.id.imagen3);
        cuatro = findViewById(R.id.imagen4);
        boton = findViewById(R.id.boton);
        boton.setOnClickListener(view -> volverAReproducir());
        inicializar();
        onlisten();
    }

    private void volverAReproducir() {
        if (!mp.isPlaying()) {
            mp.start();
        }
    }

    private void onlisten() {
        uno.setOnClickListener(view -> comprobar(animalesSelect.get(0)));
        dos.setOnClickListener(view -> comprobar(animalesSelect.get(1)));
        tres.setOnClickListener(view -> comprobar(animalesSelect.get(2)));
        cuatro.setOnClickListener(view -> comprobar(animalesSelect.get(3)));
    }

    private void comprobar(String s) {
        if (s.equals(correcto)) {
            mp.stop();
            FirebaseAnalytics analytics = FirebaseAnalytics.getInstance(this);
            Bundle bundle = new Bundle();
            bundle.putString("a", "b");
            analytics.logEvent("c", bundle);
            Intent intent = new Intent(getApplicationContext(), Acierto.class);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onStop() {
        mp.stop();
        mp.release();
        super.onStop();
    }

    private void inicializar() {
        generador = new Random();
        String an;
        animalesSelect = new ArrayList<>();
        while (animalesSelect.size() != 4) {
            if (!animalesSelect.contains(an = animales[generador.nextInt(animales.length - 1)])) {
                animalesSelect.add(an);
            }
        }

        uno.setImageResource(getResources().getIdentifier(animalesSelect.get(0), "drawable", getPackageName()));
        dos.setImageResource(getResources().getIdentifier(animalesSelect.get(1), "drawable", getPackageName()));
        tres.setImageResource(getResources().getIdentifier(animalesSelect.get(2), "drawable", getPackageName()));
        cuatro.setImageResource(getResources().getIdentifier(animalesSelect.get(3), "drawable", getPackageName()));
        sonido(correcto = animalesSelect.get(generador.nextInt(4 - 1))); //aqui ponemos sonido y guardamos cuál es el correecto
    }

    private void sonido(String s) {
        mp = MediaPlayer.create(getApplicationContext(), getResources().getIdentifier(s, "raw", getPackageName()));
        mp.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = new Intent(getApplicationContext(), Juego.class);
        startActivity(intent);
        finish();
    }
}