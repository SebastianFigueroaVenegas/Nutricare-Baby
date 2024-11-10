package com.example.myapplication;

public class Receta {
    private String id;
    private String titulo;
    private String descripcion;
    private String mes;


    public Receta() {}

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getMes() {
        return mes;
    }


    public void setId(String id) {
        this.id = id;
    }

}
