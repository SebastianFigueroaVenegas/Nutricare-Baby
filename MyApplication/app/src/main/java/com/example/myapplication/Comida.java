package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Comida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_comida);
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


            Intent intent = new Intent(Comida.this, Comida_info.class);
            intent.putExtra("month", month);
            startActivity(intent);
        });
    }
    public void mtdIrLobby(View v) {
        Intent i = new Intent(Comida.this, Lobby.class);
        startActivity(i);
    }
    public void mtdIrComida_info(View v) {
        Intent i = new Intent(Comida.this, Comida_info.class);
        startActivity(i);
    }
}