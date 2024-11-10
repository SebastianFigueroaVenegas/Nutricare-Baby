package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder> {
    private List<Receta> recetaList;
    private Context context;
    private String userId;  // Agregar UID del usuario

    public RecetaAdapter(List<Receta> recetaList, Context context, String userId) {
        this.recetaList = recetaList;
        this.context = context;
        this.userId = userId;
    }

    @NonNull
    @Override
    public RecetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_receta, parent, false);
        return new RecetaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecetaViewHolder holder, int position) {
        Receta receta = recetaList.get(position);
        holder.titleTextView.setText(receta.getTitulo());

        holder.titleTextView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Mis_recetas.class);
            intent.putExtra("recetaId", receta.getId());
            intent.putExtra("userId", userId);  // Pasar UID
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recetaList.size();
    }

    public static class RecetaViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;

        public RecetaViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textViewTitulo);
        }
    }
}

