package com.example.caminapp3.ui.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class BaseDeDatos extends SQLiteOpenHelper {

    public BaseDeDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Pruebas","Se está accediendo al OnCreate de la BD");
        String sql = String.format("CREATE TABLE %s (%s varchar(20) not null, " +
                        "%s varchar(100) primary key not null, " +
                        "%s varchar(25) not null)",
                Tablas_BD.USSERS_BD, ColumnasUsuarios.NOMBRE,ColumnasUsuarios.EMAIL,
                ColumnasUsuarios.PASSWORD);
        db.execSQL(sql);

        sql = String.format("CREATE TABLE %s (%s varchar(200) not null, " +
                        "%s varchar(20) not null, " +
                        "%s varchar(5000) not null," +
                        "primary key(%s,%s))",
                Tablas_BD.FORO_COMENTARIOS_BD, ColumnasComentarios.TITULO,ColumnasComentarios.USUARIO,
                ColumnasComentarios.TEXTO,ColumnasComentarios.TITULO,ColumnasComentarios.USUARIO);
        db.execSQL(sql);
        Log.d("Pruebas","Se ha ejecutado correctamente la creación de las 2 BD");

        CreateUsuarioYPostPruebas(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void CreateUsuarioYPostPruebas(SQLiteDatabase dbprueba){
        String sql = String.format("INSERT INTO %s VALUES ('Admin','emailejemplo@ejemplo.com','Admin')",
                Tablas_BD.USSERS_BD);
        dbprueba.execSQL(sql);

        sql = String.format("INSERT INTO %s VALUES ('Este es un comentario de prueba'" +
                        ",'Admin','Este comentario es el primer comentario que hago como prueba para ver si" +
                        " todo funciona correctamente')",
                Tablas_BD.FORO_COMENTARIOS_BD);
        dbprueba.execSQL(sql);
        sql = String.format("INSERT INTO %s VALUES ('Segundo comentario'" +
                        ",'Primer usuario','Este comentario es el segundo')",
                Tablas_BD.FORO_COMENTARIOS_BD);
        dbprueba.execSQL(sql);
        Log.d("Pruebas","Se ha ejecutado la función CreateUsuarioYPostPruebas");
    }
}
