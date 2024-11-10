package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;



public class Registro extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailEditText, passwordEditText;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mAuth = FirebaseAuth.getInstance();
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(v -> registerUser());
    }
    private void registerUser() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Registro.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        // Obtener UID del usuario
                        String userId = mAuth.getCurrentUser().getUid();

                        Intent i = new Intent(Registro.this, Login.class);
                        i.putExtra("userId", userId);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(Registro.this, "Registro fallido: " + task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void mtdIrLogin(View v) {
        Intent i = new Intent(Registro.this, Login.class);
        startActivity(i);
    }
}
