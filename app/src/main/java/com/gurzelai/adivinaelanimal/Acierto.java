package com.gurzelai.adivinaelanimal;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class Acierto extends AppCompatActivity {

    GifImageView gifImageView;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acierto);

        gifImageView = findViewById(R.id.gif);
        Random r = new Random();

        gifImageView.setImageResource(getResources().getIdentifier("aplausos"+(r.nextInt(8)), "drawable", getPackageName()));
        mp = MediaPlayer.create(getApplicationContext(), getResources().getIdentifier("correcto", "raw", getPackageName()));
        mp.start();
    }
}