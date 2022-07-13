package com.example.caminapp3.ui.BD;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.caminapp3.R;

import java.util.ArrayList;
import java.util.List;

public class FuncionesDB {

    private static String nombreDB = "Caminapp";
    private static SQLiteDatabase db;

    public static void CrearDB(Context contexto){
        Log.d("Pruebas","Se llega a la sección de código personalizado");
        //contexto.deleteDatabase("Caminapp");//BORRAR TRAS LAS PRUEBAS, ESTO ELIMINA LA DB
        BaseDeDatos admin = new BaseDeDatos(contexto,nombreDB,null,1);

        db = admin.getWritableDatabase();
    }

    /*----------------------------------------------------------------------------------------------
    --------------------------------------TABLA DE USUARIOS-----------------------------------------
    ----------------------------------------------------------------------------------------------*/

    public static void insertarUsuario(Usuario us){

        db.insert(Tablas_BD.USSERS_BD,null,valoresUsuario(us));
    }

    private static ContentValues valoresUsuario(Usuario us){

        ContentValues valores = new ContentValues();
        valores.put(ColumnasUsuarios.NOMBRE,us.getNombre());
        valores.put(ColumnasUsuarios.EMAIL,us.getEmail());
        valores.put(ColumnasUsuarios.PASSWORD,us.getPassword());

        return valores;
    }

    public static Boolean comprobarLogin(String email, String contraseña){

        String sql = String.format("Select * FROM %s WHERE email = " + email + " and contraseña = " + contraseña,
                ColumnasUsuarios.EMAIL,ColumnasUsuarios.PASSWORD,ColumnasUsuarios.NOMBRE, Tablas_BD.USSERS_BD);

        Cursor cur = db.rawQuery(sql,null);
        if (cur.getCount()>0)
            return true;
        else
                return false;
    }

    public static ArrayList<Usuario> SeleccionarUsu(){
        //Creación del ArrayList de Usuarios
        ArrayList<Usuario> usuarios = new ArrayList<>();
        //Defino una consulta dinámica en sql
        String sql = String.format("Select %s,%s,%s FROM %s",
                ColumnasUsuarios.EMAIL,ColumnasUsuarios.PASSWORD,ColumnasUsuarios.NOMBRE, Tablas_BD.USSERS_BD);
        //Creo un cursor que realiza la consulta en la BBDD
        Cursor c = db.rawQuery(sql,null);
        //Compruebo que hay algún resultado
        if(c.moveToFirst()){
            //Hago iteraciones hasta que no haya más elementos
            do{
                //Creo un usuario al que añado los datos recuperados (Email columna 0, Password columna 1)
                Usuario p = new Usuario();
                p.setEmail(c.getString(0));
                p.setPassword(c.getString(1));
                p.setNombre(c.getString(2));
                //Añado el usuario al arraylist
                usuarios.add(p);

            }while(c.moveToNext());
        }
        //Devuelvo el arraylist con los usuarios ya en el
        return usuarios;
    }

    /*----------------------------------------------------------------------------------------------
    --------------------------------------TABLA DE COMENTARIOS--------------------------------------
    ----------------------------------------------------------------------------------------------*/

    public static void insertarComentario(Comentario co){

        db.insert(Tablas_BD.FORO_COMENTARIOS_BD,null,valoresComentario(co));
    }

    private static ContentValues valoresComentario(Comentario com){

        ContentValues valoresCom = new ContentValues();
        valoresCom.put(ColumnasComentarios.TITULO,com.getTitulo());
        valoresCom.put(ColumnasComentarios.TEXTO,com.getTexto());
        valoresCom.put(ColumnasComentarios.USUARIO,com.getUsuario());

        return valoresCom;
    }

    public static ArrayList<Comentario> SeleccionarCom(){
        //Creación del ArrayList de Comentarios
        ArrayList<Comentario> comentarios = new ArrayList<>();
        //Defino una consulta dinámica en sql
        String sql = String.format("Select * FROM %s",
                Tablas_BD.FORO_COMENTARIOS_BD);
        //Creo un cursor que realiza la consulta en la BBDD
        Cursor c = db.rawQuery(sql,null);
        //Compruebo que hay algún resultado

        if(c==null){
            System.out.println("PROBLEMA DE INSERCIÓN, VACÍO");
        }

        if(c.moveToFirst()){
            //Hago iteraciones hasta que no haya más elementos
            do{
                //Creo un comentario al que añado los datos recuperados (Título columna 0, Usuario columna 1, Texto columna 2)
                Comentario co = new Comentario();
                co.setTitulo(c.getString(0));
                co.setUsuario(c.getString(1));
                co.setTexto(c.getString(2));
                //Añado el comentario al arraylist
                comentarios.add(co);

            }while(c.moveToNext());
        }
        //Devuelvo el arraylist con los comentarios ya en el
        return comentarios;
    }

    public static ArrayList<String> leer(Context context,ListView li){
        final ArrayList<Comentario> comentarios = SeleccionarCom();

        final ArrayList<String> datosMostrar = new ArrayList<>();
        Log.d("Pruebas","Antes del For");
        for(int i = 0; i< comentarios.size();i++){
            Log.d("Pruebas","Elemento " + i);
            Log.d("Pruebas",comentarios.get(i).getUsuario().toString());
            //datosMostrar.add(comentarios.get(i).getUsuario().toString());
            Log.d("Pruebas",comentarios.get(i).getTitulo().toString());
            datosMostrar.add(comentarios.get(i).getTitulo().toString());
            Log.d("Pruebas",comentarios.get(i).getTexto().toString());
            //datosMostrar.add(comentarios.get(i).getTexto().toString());
        }
        Log.d("Pruebas","Sale del for");
        return datosMostrar;

        //Log.d("Pruebas","supera el SetAdapter");
    }
    /*public static Comentario getComentarioNum(int num){

    }*/
}
