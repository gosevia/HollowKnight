package com.proyecto.hollowknight;

public class Enemigo{
    private String name;
    private String location;
    private String hp;
    private String description;
    private int imageResourceId;

    public static final Enemigo[] enemigos = {
            new Enemigo("Receptáculo Rojo", R.drawable.receptaculorojo),
            new Enemigo("Mawlek Incubador", R.drawable.mawlekincubador),
            new Enemigo("El Coleccionista", R.drawable.elcoleccionista),
            new Enemigo("Guardián de Cristal", R.drawable.guardiandecristal),
            new Enemigo("Falso Caballero", R.drawable.falsocaballero),
            new Enemigo("Tremarmita", R.drawable.tremarmita),
            new Enemigo("Grimm", R.drawable.grimm),
            new Enemigo("Madre Gruz", R.drawable.madregruz),
            new Enemigo("Hollow Knight", R.drawable.hollowknight),
            new Enemigo("Señores Mantis", R.drawable.senoresmantis),
            new Enemigo("Musgoagresor Gigante", R.drawable.musgoagresorgigante),
            new Enemigo("Nosk", R.drawable.nosk),
            new Enemigo("El Destello", R.drawable.eldestello),
            new Enemigo("Señor Desleal", R.drawable.senordesleal),
            new Enemigo("Uumuu", R.drawable.uumuu),
            new Enemigo("Rey Vengamosca", R.drawable.reyvengamosca),
            new Enemigo("Caballero Vigía", R.drawable.caballerovigia),
            new Enemigo("Zote el Todopoderoso", R.drawable.zote)
    };
    private Enemigo(String name, int imageResourceId){
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
    public String getHp(){
        return hp;
    }
    public String getLocation(){
        return location;
    }
}
