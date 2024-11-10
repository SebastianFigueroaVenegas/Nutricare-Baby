package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Leche_info extends AppCompatActivity {

    private EditText inputOnza;
    private EditText inputGramo;
    private boolean isUpdating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_leche_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        inputOnza = findViewById(R.id.inputOnza);
        inputGramo = findViewById(R.id.inputGramo);


        inputOnza.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (!isUpdating) {
                    isUpdating = true;
                    try {
                        double onza = s.toString().isEmpty() ? 0 : Double.parseDouble(s.toString());
                        double gramo = onza * 28.3495; // Conversión de onza a gramo
                        inputGramo.setText(String.format("%.2f", gramo));
                    } catch (NumberFormatException e) {
                        inputGramo.setText("");
                    }
                    isUpdating = false;
                }
            }
        });


        inputGramo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (!isUpdating) {
                    isUpdating = true;
                    try {
                        double gramo = s.toString().isEmpty() ? 0 : Double.parseDouble(s.toString());
                        double onza = gramo / 28.3495; // Conversión de gramo a onza
                        inputOnza.setText(String.format("%.2f", onza));
                    } catch (NumberFormatException e) {
                        inputOnza.setText("");
                    }
                    isUpdating = false;
                }
            }
        });


        int month = getIntent().getIntExtra("month", 0); // El segundo parámetro es el valor predeterminado si no se recibe el dato

        if (month > 0) {
            String feedingInfo = getFeedingInfo(month, "leche");
            TextView infoTextView = findViewById(R.id.infoTextView); // Cambia el ID si es necesario
            infoTextView.setText(feedingInfo);
        } else {
            Toast.makeText(this, "Mes no válido", Toast.LENGTH_SHORT).show();
        }
    }

    private String getFeedingInfo(int month, String type) {
        int resId = getResources().getIdentifier(type + "_mes_" + month, "string", getPackageName());
        return getString(resId);
    }

    public void mtdIrLeche(View v) {
        Intent i = new Intent(Leche_info.this, Leche.class);
        startActivity(i);
    }
}