package com.example.examenfinal.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDDHelper extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DE_DATOS = "inscripciones",
            NOMBRE_TABLA_PRODUCTOS_1 = "alumno",
            NOMBRE_TABLA_PRODUCTOS_2 = "inscripcion";
    private static final int VERSION_BASE_DE_DATOS = 1;
    // cambiar version de bdd si se agregan tablas

    public BDDHelper(@Nullable Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(idAlumno integer primary key autoincrement, flagEstado integer, nombreCompleto text, carrera text)", NOMBRE_TABLA_PRODUCTOS_1));
        sqLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(idInscripcion integer primary key autoincrement, idAlumno, nombreMateria text, paralelo text integer, FOREIGN KEY (idAlumno) REFERENCES alumno(idAlumno) ON UPDATE RESTRICT ON DELETE RESTRICT)", NOMBRE_TABLA_PRODUCTOS_2));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NOMBRE_TABLA_PRODUCTOS_1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NOMBRE_TABLA_PRODUCTOS_2);
        onCreate(sqLiteDatabase);
    }

}
