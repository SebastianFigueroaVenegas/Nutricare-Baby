package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class Lobby extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lobby);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        }
    public void mtdIrLeche(View v) {
        Intent i = new Intent(Lobby.this, Leche.class);
        startActivity(i);
    }

    public void mtdIrComida(View v) {
        Intent i = new Intent(Lobby.this, Comida.class);
        startActivity(i);
    }

    public void mtdIrRecetas(View v) {
        Intent i = new Intent(Lobby.this, Recetas_lobby.class);
        startActivity(i);
    }

    public void mtdLogout(View view) {
        FirebaseAuth.getInstance().signOut();

        Intent intent = new Intent(Lobby.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);  // Limpia el historial de actividades
        startActivity(intent);
        finish();
    }
}