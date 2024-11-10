package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Leche extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_leche);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spinnerMes = findViewById(R.id.spinnerComunas);
        TextView btnBusqueda = findViewById(R.id.btnBusqueda);

        btnBusqueda.setOnClickListener(v -> {
            String selectedMonthString = spinnerMes.getSelectedItem().toString();

            int month = Integer.parseInt(selectedMonthString.split(" ")[0]);


            Intent intent = new Intent(Leche.this, Leche_info.class);
            intent.putExtra("month", month);
            startActivity(intent);
        });
    }
    public void mtdIrLobby(View v) {
        Intent i = new Intent(Leche.this, Lobby.class);
        startActivity(i);
    }

    public void mtdIrLeche_info(View v) {
        Intent i = new Intent(Leche.this, Leche_info.class);
        startActivity(i);
    }
}