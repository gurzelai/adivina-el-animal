package com.gurzelai.adivinaelanimal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class Configuracion extends AppCompatActivity {

    SwitchMaterial switchMaterial;
    Button atras, btnBorrarDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        switchMaterial = findViewById(R.id.switchmaterial);
        switchMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchMaterial.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Se han activado las notificaciones", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Se han desactivado las notificaciones", Toast.LENGTH_SHORT).show();
                }
            }
        });

        atras = findViewById(R.id.volver);
        atras.setOnClickListener(view -> finish());
        btnBorrarDatos = findViewById(R.id.btnBorrarDatos);
        btnBorrarDatos.setOnClickListener(view -> aviso());

    }

    private void aviso() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Se borraran todos tus records");
        builder.setMessage("¿Quieres borrar los datos?");

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                borrarDatos();
            }
        });
        builder.setNegativeButton("Cancelar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void borrarDatos() {
        Toast.makeText(getApplicationContext(), "Se han borrado los datos", Toast.LENGTH_SHORT).show();
    }
}