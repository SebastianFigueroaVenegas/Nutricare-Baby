package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;

public class Mis_recetas extends AppCompatActivity {

    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView mesesTextView;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mis_recetas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userId = getIntent().getStringExtra("userId");
        String recetaId = getIntent().getStringExtra("recetaId");


        if (userId != null && recetaId != null) {
            db.collection("users").document(userId).collection("Recetas").document(recetaId)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            Receta receta = documentSnapshot.toObject(Receta.class);
                            if (receta != null) {
                                TextView tituloTextView = findViewById(R.id.titleTextView);
                                TextView descripcionTextView = findViewById(R.id.descriptionTextView);
                                TextView mesTextView = findViewById(R.id.mesesTextView);

                                tituloTextView.setText(receta.getTitulo());
                                descripcionTextView.setText(receta.getDescripcion());
                                mesTextView.setText("Receta para bebes de " + receta.getMes());
                            }
                        }
                    })
                    .addOnFailureListener(e -> {
                        Log.w("Firebase", "Error al obtener la receta", e);
                    });
        }
    }

    public void mtdirRecetas(View v) {
        Intent i = new Intent(Mis_recetas.this, Recetas_lobby.class);
        startActivity(i);
    }
}

