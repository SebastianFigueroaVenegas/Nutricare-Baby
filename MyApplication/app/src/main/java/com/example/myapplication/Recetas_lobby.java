package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class Recetas_lobby extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecetaAdapter recetaAdapter;
    private List<Receta> recetaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recetas_lobby);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recetaAdapter = new RecetaAdapter(recetaList, this,userId);

        recyclerView.setAdapter(recetaAdapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(userId).collection("Recetas")  // Acceder a la subcolección del usuario
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Receta> recetaList = new ArrayList<>();
                    for (DocumentSnapshot document : queryDocumentSnapshots) {
                        Receta receta = document.toObject(Receta.class);
                        receta.setId(document.getId());  // Asignar el ID del documento
                        recetaList.add(receta);
                    }
                    recetaAdapter = new RecetaAdapter(recetaList, this, userId);
                    recyclerView.setAdapter(recetaAdapter);
                })
                .addOnFailureListener(e -> {
                    Log.w("Firebase", "Error obteniendo recetas del usuario.", e);
                });
    }


    public void mtdIrAgregarReceta(View v) {
        Intent i = new Intent(Recetas_lobby.this, Recetas.class);
        startActivity(i);
    }

    public void mtdIrLobby(View v) {
        Intent i = new Intent(Recetas_lobby.this, Lobby.class);
        startActivity(i);
    }
}