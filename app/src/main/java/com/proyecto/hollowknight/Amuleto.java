package com.proyecto.hollowknight;

public class Amuleto{
    private String name;
    private String description;
    private int imageResourceId;

    public static final Amuleto[] amuletos = {
            new Amuleto("Brújula caprichosa", R.drawable.brujulacaprichosa),
            new Amuleto("Maestro de las embestidas", R.drawable.maestrodelasembestidas),
            new Amuleto("Canción de larvas", R.drawable.canciondelarvas),
            new Amuleto("Corte rápido", R.drawable.corterapido),
            new Amuleto("Marca de orgullo", R.drawable.marcadeorgullo),
            new Amuleto("Espinas de agonía", R.drawable.espinasdeagonia),
            new Amuleto("Concentración rápida", R.drawable.concentracionrapida),
            new Amuleto("Sangrecolmena", R.drawable.sangrecolmena),
            new Amuleto("Sombra afilada", R.drawable.sombraafilada),
            new Amuleto("Gloria del Maestro de aguijones", R.drawable.gloriadelmaestrodeaguijones),
            new Amuleto("Canción de Tejedora", R.drawable.canciondetejedora)
    };
    private Amuleto(String name, int imageResourceId){
        this.name = name;
        this.imageResourceId = imageResourceId;
    }
    public String getName(){
        return name;
    }
    public int getImageResourceId(){
        return imageResourceId;
    }
    public String getDescription(){
        return description;
    }
}
