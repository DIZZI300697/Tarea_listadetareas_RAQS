package com.example.listadetareas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {


    private static final String USUARIO_ESPECIFICO = "root";
    private static final String CONTRASENIA_ESPECIFICA = "root";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final EditText editTextUsuario = findViewById(R.id.editTextUsuario);
        final EditText editTextContraseña = findViewById(R.id.editTextContraseña);
        Button buttonIniciarSesion = findViewById(R.id.buttonIniciarSesion);

        buttonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = editTextUsuario.getText().toString();
                String contraseña = editTextContraseña.getText().toString();


                if (usuario.equals(USUARIO_ESPECIFICO) && contraseña.equals(CONTRASENIA_ESPECIFICA)) {
                    Toast.makeText(MainActivity2.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity2.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
