package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText etApMail;
    private EditText etApContraseña;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance();
        etApMail = findViewById(R.id.etMail);
        etApContraseña = findViewById(R.id.etContraseña);
    }


    public void mtdLobby(View v) {
        String mail = etApMail.getText().toString().trim();
        String contraseña = etApContraseña.getText().toString().trim();

        if (mail.isEmpty() || contraseña.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(mail, contraseña)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {

                        String userId = mAuth.getCurrentUser().getUid();


                        Intent i = new Intent(Login.this, Lobby.class);
                        i.putExtra("userId", userId);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(Login.this, "Error al iniciar sesión: " + task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void mtdRegistro(View v) {
        Intent i = new Intent(Login.this, Registro.class);
        startActivity(i);
    }
}