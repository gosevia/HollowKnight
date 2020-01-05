package com.proyecto.hollowknight;

public class Arma{
    private String name;
    private String description;
    private String damage;
    private int imageResourceId;

    public static final Arma[] armas = {
            new Arma("Aguijón antiguo", R.drawable.aguijonantiguo),
            new Arma("Aguijón afilado", R.drawable.aguijonafilado),
            new Arma("Aguijón estilizado", R.drawable.aguijonestilizado),
            new Arma("Aguijón en espiral", R.drawable.aguijonespiral),
            new Arma("Aguijón puro", R.drawable.aguijonpuro)
    };
    private Arma(String name, int imageResourceId){
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
    public String getDamage(){
        return damage;
    }
}
