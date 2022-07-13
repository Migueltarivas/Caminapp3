package com.example.caminapp3.ui.insertarComentario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.caminapp3.R;
import com.example.caminapp3.ui.BD.Comentario;
import com.example.caminapp3.ui.BD.FuncionesDB;

public class InsertarComentarioActivity extends AppCompatActivity {

    private EditText etTitulo;
    private EditText etTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_comentario);

        etTitulo = (EditText) findViewById(R.id.et_Titulo);
        etTxt = (EditText) findViewById(R.id.et_Txt);
    }

    public void insertarComent(View view){
        Comentario comenta = new Comentario();

        String titulo = etTitulo.getText().toString();
        String txt = etTxt.getText().toString();

        comenta.setTitulo(titulo);
        comenta.setTexto(txt);
        comenta.setUsuario("Uknown");//Esto hay que cambiarlo cuando se puedan loguear los usuarios (CAMBIAR)

        FuncionesDB.insertarComentario(comenta);

        Log.d("Pruebas", "Se ha insertado un usuario con Titulo: " + titulo + " Txt : " + txt );
    }

}