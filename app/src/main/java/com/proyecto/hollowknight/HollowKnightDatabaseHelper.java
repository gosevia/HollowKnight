package com.proyecto.hollowknight;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class HollowKnightDatabaseHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "hollowknight";
    private static final int DB_VERSION = 1;

    HollowKnightDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        updateMyDatabase(db, 0, DB_VERSION);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        updateMyDatabase(db, oldVersion, newVersion);
    }
    private static void insertArma(SQLiteDatabase db, String name, String description, String damage, int resourceId, boolean obtenido){
        ContentValues armaValues = new ContentValues();
        armaValues.put("NAME", name);
        armaValues.put("DESCRIPTION", description);
        armaValues.put("DAMAGE", damage);
        armaValues.put("IMAGE_RESOURCE_ID", resourceId);
        armaValues.put("OBTENIDO", obtenido);
        db.insert("ARMA", null, armaValues);
    }
    private static void insertHechizo(SQLiteDatabase db, String name, String description, int resourceId, boolean obtenido){
        ContentValues hechizoValues = new ContentValues();
        hechizoValues.put("NAME", name);
        hechizoValues.put("DESCRIPTION", description);
        hechizoValues.put("IMAGE_RESOURCE_ID", resourceId);
        hechizoValues.put("OBTENIDO", obtenido);
        db.insert("HECHIZO", null, hechizoValues);
    }
    private static void insertAmuleto(SQLiteDatabase db, String name, String description, int resourceId, boolean obtenido){
        ContentValues amuletoValues = new ContentValues();
        amuletoValues.put("NAME", name);
        amuletoValues.put("DESCRIPTION", description);
        amuletoValues.put("IMAGE_RESOURCE_ID", resourceId);
        amuletoValues.put("OBTENIDO", obtenido);
        db.insert("AMULETO", null, amuletoValues);
    }
    private static void insertEnemigo(SQLiteDatabase db, String name, String location, String hp, String description, int resourceId, boolean defeated, float difficulty){
        ContentValues enemigoValues = new ContentValues();
        enemigoValues.put("NAME", name);
        enemigoValues.put("LOCATION", location);
        enemigoValues.put("HP", hp);
        enemigoValues.put("DESCRIPTION", description);
        enemigoValues.put("IMAGE_RESOURCE_ID", resourceId);
        enemigoValues.put("DEFEATED", defeated);
        enemigoValues.put("DIFFICULTY", difficulty);
        db.insert("ENEMIGO", null, enemigoValues);
    }
    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
        if(oldVersion<1){
            db.execSQL("CREATE TABLE ARMA (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "DAMAGE TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER, "
                    + "OBTENIDO BOOL);");
            db.execSQL("CREATE TABLE HECHIZO (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER, "
                    + "OBTENIDO BOOL);");
            db.execSQL("CREATE TABLE AMULETO (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER, "
                    + "OBTENIDO BOOL);");
            db.execSQL("CREATE TABLE ENEMIGO (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "LOCATION TEXT, "
                    + "HP TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER, "
                    + "DEFEATED BOOL, "
                    + "DIFFICULTY FLOAT);");
            insertArma(db, "Aguijón antiguo", "Un arma tradicional de Hallownest. Su hoja roma es muestra de su antigüedad y desgaste.",
                    "DAÑO: 5", R.drawable.aguijonantiguo, false);
            insertArma(db, "Aguijón afilado", "Un arma tradicional de Hallownest, restaurada a su estado letal.",
                    "DAÑO: 9", R.drawable.aguijonafilado, false);
            insertArma(db, "Aguijón estilizado", "Un arma con molduras de Hallownest. La hoja tiene un equilibrio exquisito.",
                    "DAÑO: 13", R.drawable.aguijonestilizado, false);
            insertArma(db, "Aguijón en espiral", "Una potente arma de Hallownest, más perfeccionada que las demás.",
                    "DAÑO: 17", R.drawable.aguijonespiral, false);
            insertArma(db, "Aguijón puro", "El arma definitiva de Hallownest. Este antiguo aguijón, que se ha forjado hasta alcanzar la perfección, revela su verdadera forma.",
                    "DAÑO: 21", R.drawable.aguijonpuro, false);
            insertHechizo(db, "Concentración", "Concentra el ALMA reunida para reparar tu coraza y curarte el daño. Golpea a los enemigos para reunir ALMA.",
                    R.drawable.concentracion, false);
            insertHechizo(db, "Espíritu vengativo", "Conjura un espíritu que volará hacia el frente y quemará a los enemigos que se crucen en su camino. Para conjurar este espíritu, se requiere ALMA. Golpea a los enemigos para reunir ALMA.",
                    R.drawable.espirituvengativo, false);
            insertHechizo(db, "Salto desolador", "Golpea el suelo con la intensa fuerza del ALMA. Esta fuerza puede destruir a los enemigos o derribar estructuras frágiles. Para conjurar esta fuerza, se requiere ALMA. Golpea a los enemigos para reunir ALMA.",
                    R.drawable.saltodesolador , false);
            insertHechizo(db, "Espectros aulladores", "Sacude a los enemigos con un ALMA estridente. Para conjurar los espectros, se requiere ALMA. Golpea a los enemigos para reunir ALMA.",
                    R.drawable.espectrosaulladores , false);
            insertHechizo(db, "Alma sombría", "Conjura una sombra que volará hacia el frente y quemará a los enemigos que se crucen en su camino. Para conjurar esta sombra, se requiere ALMA. Golpea a los enemigos para reunir ALMA.",
                    R.drawable.almasombria , false);
            insertHechizo(db, "Oscuridad descendente", "Golpea el suelo con una fuerza combinada de ALMA y sombra. Esta fuerza puede destruir a los enemigos o derribar estructuras frágiles. Para conjurar esta fuerza, se requiere ALMA. Golpea a los enemigos para reunir ALMA.",
                    R.drawable.oscuridaddescendente , false);
            insertHechizo(db, "Chillido del abismo", "Sacude a los enemigos con la fuerza de un ALMA estridente y la sombra. Para conjurar los espectros, se requiere ALMA. Golpea a los enemigos para reunir ALMA.",
                    R.drawable.chillidodelabismo , false);
            insertAmuleto(db, "Brújula caprichosa", "Susurra su ubicación al portador siempre que se abre el mapa.",
                    R.drawable.brujulacaprichosa, false);
            insertAmuleto(db, "Maestro de las embestidas", "El portador podrás realizar el Avance Rápido con más frecuencia, así como realizar el Avance Rápido hacia abajo.",
                    R.drawable.maestrodelasembestidas, false);
            insertAmuleto(db, "Canción de larvas", "Consigue ALMA al recibir daño.",
                    R.drawable.canciondelarvas , false);
            insertAmuleto(db, "Corte rápido", "Permite al portador lanzar cortes mucho más rápido con su aguijón.",
                    R.drawable.corterapido , false);
            insertAmuleto(db, "Marca de orgullo", "Aumenta mucho el alcance del aguijón del portador.",
                    R.drawable.marcadeorgullo , false);
            insertAmuleto(db, "Espinas de agonía", "Al recibir daño, hace brotar vides espinosos que dañan a los enemigos cercanos.",
                    R.drawable.espinasdeagonia , false);
            insertAmuleto(db, "Concentración rápida", "Aumenta la velocidad de la concentración de ALMA, con lo que el portador puede curarse de los daños antes.",
                    R.drawable.concentracionrapida , false);
            insertAmuleto(db, "Sangrecolmena", "Cura las heridas del portador con el paso del tiempo, lo que permite recuperar salud sin concentrar ALMA.",
                    R.drawable.sangrecolmena , false);
            insertAmuleto(db, "Sombra afilada", "Al usar el avance rápido sombrío, el cuerpo del portador se volverá afilado y dañará a los enemigos.",
                    R.drawable.sombraafilada , false);
            insertAmuleto(db, "Gloria del Maestro de aguijones", "Aumenta el dominio de las Artes del aguijón del portador, lo que le permite concentrar su poder más rápido y desatar las artes antes.",
                    R.drawable.gloriadelmaestrodeaguijones , false);
            insertAmuleto(db, "Canción de Tejedora", "Invoca pequeñas tejedoras para acompañar y proteger al solitario portador.",
                    R.drawable.canciondetejedora , false);
            insertEnemigo(db, "Receptáculo Rojo", "UBICACIÓN: Cuenca Antigua", "HP: 525", "La forma de esta criatura... He visto algo similar antes. Más de una vez, incluso. Se parece un poco a los insectos de Hallownest, pero no es exactamente igual. ¿De dónde han venido estos pequeños errantes vacíos?",
                    R.drawable.receptaculorojo, false, 0);
            insertEnemigo(db, "Mawlek Incubador", "UBICACIÓN: Cruces Olvidados", "HP: 300", "Escucho a esta bestia gritar a veces mientras merodeo las cavernas, aunque nunca he posado mis ojos en ella. ¿A quién o qué está llamando? Por lo que yo sé, sus bramidos nunca obtienen respuesta.",
                    R.drawable.mawlekincubador, false, 0);
            insertEnemigo(db, "El Coleccionista", "UBICACIÓN: Torre del Amor", "HP: 750", "Una sombra que se desliza por las cavernas realizando extraños sonidos. Nunca la he visto con claridad, así que no sé qué tipo de criatura es.",
                    R.drawable.elcoleccionista , false, 0);
            insertEnemigo(db, "Guardián de Cristal", "UBICACIÓN: Cumbre de Cristal", "HP: 450", "¿Cómo verá el mundo esta criatura que lo mira a través de su prisión de cristal? ¿Solo ve luz? ¿Es eso lo que lo enloquece de esa forma?",
                    R.drawable.guardiandecristal , false, 0);
            insertEnemigo(db, "Falso Caballero", "UBICACIÓN: Cruces Olvidados", "HP: 385", "A las criaturas débiles les encanta robar la fuerza de los demás. Sus vidas son efímeras y están llenas de miedo, pero anhelan tener el poder para dominar a quienes los han dominado.",
                    R.drawable.falsocaballero , false, 0);
            insertEnemigo(db, "Tremarmita", "UBICACIÓN: Canales Reales", "HP: 350", "El deseo de reproducirse, de dejar atrás un recuerdo de nosotros en forma de un descendiente... parece estar bien impregnado en el corazón de toda criatura viva. Yo también he sentido el reclamo de ese instinto básico.",
                    R.drawable.tremarmita , false, 0);
            insertEnemigo(db, "Grimm", "UBICACIÓN: Bocasucia", "HP: 1000", "Por la llamada de la linterna, a través de sueños yo viajo a consumir las llamas de un reino hecho pedazos. - Grimm",
                    R.drawable.grimm , false, 0);
            insertEnemigo(db, "Madre Gruz", "UBICACIÓN: Cruces Olvidados", "HP: 90", "Sorprendentemente, este monstruo no pone huevos, sino que lleva a sus crías dentro de su gordo estómago. Esta extraña práctica parece agotar a la criatura, que queda adormilada y vulnerable. ¡Aprovecha ese momento!",
                    R.drawable.madregruz , false, 0);
            insertEnemigo(db, "Hollow Knight", "UBICACIÓN: Templo del Huevo Negro", "HP: 1300", "El antiguo Rey de Hallownest... llegó a tomar medidas desesperadas para salvar su pequeño mundo. Obligó a muchos a realizar tremendos sacrificios... y todo para nada.",
                    R.drawable.hollowknight , false, 0);
            insertEnemigo(db, "Señores Mantis", "UBICACIÓN: Aldea Mantis", "HP: 530", "La tribu mantis y los insectos de Hallownest no se llevaban demasiado bien. Sin embargo, las mantis consiguieron sobrevivir a sus rivales, y su civilización aún perdura.",
                    R.drawable.senoresmantis , false, 0);
            insertEnemigo(db, "Musgoagresor Gigante", "UBICACIÓN: Sendero Verde", "HP: 100", "Es cierto, la unión hace la fuerza, pero estas criaturas nunca fueron demasiado fuertes de por sí. ¡Haz pedazos su artimaña!",
                    R.drawable.musgoagresorgigante , false, 0);
            insertEnemigo(db, "Nosk", "UBICACIÓN: Nido Profundo", "HP: 600", "En la oscuridad más profunda hay bestias con rostros robados de tu memoria que tratan de controlarte manipulando tus sentimientos. Conócete a ti mismo y no te dejes engañar.",
                    R.drawable.nosk , false, 0);
            insertEnemigo(db, "El Destello", "UBICACIÓN: Sueños", "HP: 1700", "La plaga, la infección, la locura que reanima los cadáveres de Hallownest... el brillo que surge de los ojos de un reino muerto. ¿De dónde procede? Supongo que es algo que está más allá del entendimiento de mortales como yo.",
                    R.drawable.eldestello , false, 0);
            insertEnemigo(db, "Señor Desleal", "UBICACIÓN: Jardines de la Reina", "HP: 800", "Yo también he sentido esa tentación. La tentación de dejar que la infección entre en mí. Sería más fuerte, más poderoso... esas ideas siguen emponzoñando mis sueños durante los momentos difíciles. Es una mentira, pero brilla tanto que puede confundir tu mente.",
                    R.drawable.senordesleal , false, 0);
            insertEnemigo(db, "Uumuu", "UBICACIÓN: Archivos de la Maestra", "HP: 300", "El desfiladero de abajo, el que está repleto de neblina y chisporrotea con una energía extraña... un cazador puede perder todos sus sentidos allí abajo. Ten cuidado... allí acechan cosas extrañas y antinaturales.",
                    R.drawable.uumuu , false, 0);
            insertEnemigo(db, "Rey Vengamosca", "UBICACIÓN: Sendero Verde", "HP: 50", "Una asquerosa criatura a la que le gusta masticar cualquier porquería que encuentre tirada en el suelo de la caverna. Cuando se percata de una amenaza, emitirá molestos gritos y chillidos, así que mátalo rápido.",
                    R.drawable.reyvengamosca , false, 0);
            insertEnemigo(db, "Caballero Vigía", "UBICACIÓN: Torre del Vigía", "HP: 1320", "Cuando estos silenciosos guerreros caen en batalla, sus cuerpos se abren y de ellos salen volando extraños insectos. Me pregunto qué saldrá volando de mí cuando muera. ¿Mis esperanzas y mis miedos saldrán disparados hasta perderse en la oscuridad?",
                    R.drawable.caballerovigia , false, 0);
            insertEnemigo(db, "Zote el Todopoderoso", "UBICACIÓN: Coliseo de los Insensatos", "HP: 200", "Existen ciertas criaturas que son tan débiles, inútiles, ineptas e irritantes que cazarlas pierde todo el interés.",
                    R.drawable.zote , false, 0);
        }
    }
}
