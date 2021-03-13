package com.gurzelai.adivinaelanimal;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Juego extends AppCompatActivity {

    String[] animales = {"burro","cabra", "canario", "gabiota", "lobo", "tigre"};
    Random generador;
    ImageButton uno, dos, tres, cuatro;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        getSupportActionBar().setTitle("Adivina el animal");

        uno = (ImageButton) findViewById(R.id.imagen1);
        dos = (ImageButton) findViewById(R.id.imagen2);
        tres = (ImageButton) findViewById(R.id.imagen3);
        cuatro = (ImageButton) findViewById(R.id.imagen4);
        inicializar();
    }

    @Override
    protected void onDestroy() {
        mp.stop();
        mp.release();
        super.onDestroy();
    }

    private void inicializar() {
        generador = new Random();
        String an;
        List<String> animalesSelect = new ArrayList<>();
        while (animalesSelect.size() != 4) {
            if(!animalesSelect.contains(an = animales[generador.nextInt(animales.length-1)]))
            {
                animalesSelect.add(an);
            }
        }

        uno.setImageResource(getResources().getIdentifier(animalesSelect.get(0), "drawable", getPackageName()));
        dos.setImageResource(getResources().getIdentifier(animalesSelect.get(1), "drawable", getPackageName()));
        tres.setImageResource(getResources().getIdentifier(animalesSelect.get(2) , "drawable", getPackageName()));
        cuatro.setImageResource(getResources().getIdentifier(animalesSelect.get(3), "drawable", getPackageName()));
        sonido(animalesSelect.get(generador.nextInt(4-1)));
    }

    private void sonido(String s) {
        mp = MediaPlayer.create(getApplicationContext(),getResources().getIdentifier(s, "raw", getPackageName()) );
        mp.start();
    }
}