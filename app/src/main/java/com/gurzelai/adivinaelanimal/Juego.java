package com.gurzelai.adivinaelanimal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Juego extends AppCompatActivity {

    String[] animales = {"burro", "caballo", "cabra", "canario", "cerdo", "cordero", "cuervo", "delfin", "elefante", "foca", "gabiota", "gallina", "gallo", "gato", "leon", "lobo", "mono", "mosca", "murcielago", "oso", "paloma", "pato", "perro", "tigre", "rana", "toro", "vaca"};
    Random generador;
    ImageButton uno, dos, tres, cuatro;
    ImageView corazon1, corazon2, corazon3;
    MediaPlayer mp;
    String correcto;
    List<String> animalesSelect;
    FloatingActionButton boton;
    TextView tvpuntos;
    int puntos;
    int vidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        getSupportActionBar().setTitle("Adivina el animal");
        if (getIntent().getExtras() != null) {
            puntos = getIntent().getExtras().getInt("puntos", 0);
            vidas = getIntent().getExtras().getInt("vidas", 3);
            if (puntos % 5 == 0 && vidas < 3) {
                vidas++;
            }
        } else {
            puntos = 0;
            vidas = 3;
        }
        actualizarVidas();
        tvpuntos = findViewById(R.id.tvpuntos);
        tvpuntos.setText(String.valueOf(puntos));
        uno = findViewById(R.id.imagen1);
        dos = findViewById(R.id.imagen2);
        tres = findViewById(R.id.imagen3);
        cuatro = findViewById(R.id.imagen4);
        boton = findViewById(R.id.boton);
        boton.setOnClickListener(view -> volverAReproducir());
        inicializar();
        onlisten();
    }

    private void sumarVida() {
        LinearLayout layoutVidas = findViewById(R.id.layoutVidas);
        ImageView iv;
        layoutVidas.addView(iv = new ImageView(getApplicationContext()));
        iv.setImageResource(R.drawable.corazon);
        iv.setMaxHeight(100);
        iv.setMaxWidth(100);
        iv.setAdjustViewBounds(true);
    }

    private void actualizarVidas() {
        if (vidas < 3) {
            corazon3 = findViewById(R.id.corazon3);
            corazon3.setVisibility(View.INVISIBLE);
        }
        if (vidas < 2) {
            corazon2 = findViewById(R.id.corazon2);
            corazon2.setVisibility(View.INVISIBLE);
        }
        if (vidas == 0) {
            corazon1 = findViewById(R.id.corazon1);
            corazon1.setVisibility(View.INVISIBLE);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("GAME OVER");
            builder.setMessage("Has perdido con " + puntos + " puntos");
            builder.setPositiveButton("Volver a jugar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getApplicationContext(), Juego.class);
                    startActivity(intent);
                    finish();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            if (mp.isPlaying()) mp.stop();
            dialog.setCancelable(false);
        }
    }

    private void volverAReproducir() {
        mp.start();

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
            Intent intent = new Intent(getApplicationContext(), Acierto.class);
            intent.putExtra("nombre del animal", s);
            intent.putExtra("puntos", ++puntos);
            startActivityForResult(intent, 0);
        } else {
            vidas--;
            actualizarVidas();
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
            if (!animalesSelect.contains(an = animales[generador.nextInt(animales.length)])) {
                animalesSelect.add(an);
            }
        }

        try {
            uno.setImageResource(getResources().getIdentifier(animalesSelect.get(0), "drawable", getPackageName()));
            dos.setImageResource(getResources().getIdentifier(animalesSelect.get(1), "drawable", getPackageName()));
            tres.setImageResource(getResources().getIdentifier(animalesSelect.get(2), "drawable", getPackageName()));
            cuatro.setImageResource(getResources().getIdentifier(animalesSelect.get(3), "drawable", getPackageName()));
            sonido(correcto = animalesSelect.get(generador.nextInt(4 - 1)));
        } catch (Exception e) {
            e.printStackTrace();
            //Este try catch lo usaremos para ver si algun audio o imagen falla
            Toast.makeText(getApplicationContext(), correcto, Toast.LENGTH_LONG).show();
        }
    }

    private void sonido(String s) {
        mp = MediaPlayer.create(getApplicationContext(), getResources().getIdentifier(s, "raw", getPackageName()));
        mp.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = new Intent(getApplicationContext(), Juego.class);
        intent.putExtra("puntos", puntos);
        intent.putExtra("vidas", vidas);
        startActivity(intent);
        finish();
    }
}