package com.proyecto.hollowknight;

public class Hechizo{
    private String name;
    private String description;
    private int imageResourceId;

    public static final Hechizo[] hechizos = {
            new Hechizo("Concentración", R.drawable.concentracion),
            new Hechizo("Espíritu vengativo", R.drawable.espirituvengativo),
            new Hechizo("Salto desolador", R.drawable.saltodesolador),
            new Hechizo("Espectros aulladores", R.drawable.espectrosaulladores),
            new Hechizo("Alma sombría", R.drawable.almasombria),
            new Hechizo("Oscuridad descendente", R.drawable.oscuridaddescendente),
            new Hechizo("Chillido del abismo", R.drawable.chillidodelabismo)
    };
    private Hechizo(String name, int imageResourceId){
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