package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Recetas extends AppCompatActivity {

    TextView btn_agregar;
    EditText etDescripcion;
    EditText etTitulo;
    Spinner spinnerMeses;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recetas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        etDescripcion=(EditText) findViewById(R.id.etDescripcion);
        btn_agregar=(TextView) findViewById(R.id.btn_agregar);
        spinnerMeses=(Spinner) findViewById(R.id.spinnerMeses);
        etTitulo=(EditText) findViewById(R.id.etTitulo);

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String descripcion = etDescripcion.getText().toString();
                String titulo = etTitulo.getText().toString();
                String spinner = spinnerMeses.getSelectedItem().toString();

                if (descripcion.isEmpty() || spinner.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
                }else{
                    ingresar(titulo, descripcion, spinner);
                }
            }
        });

    }



    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    private void ingresar(String titulo, String descripcion, String spinner) {
        Map<String, Object> receta = new HashMap<>();
        receta.put("titulo", titulo);
        receta.put("descripcion", descripcion);
        receta.put("mes", spinner);


        db.collection("users").document(userId).collection("Recetas").add(receta)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Ingreso Correcto", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Fallo Ingreso", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public boolean onSupportNavigateUp(){
        onBackPressed();
        return false;
    }



    public void mtdIrLobby(View v) {
        Intent i = new Intent(Recetas.this, Recetas_lobby.class);
        startActivity(i);
    }

}