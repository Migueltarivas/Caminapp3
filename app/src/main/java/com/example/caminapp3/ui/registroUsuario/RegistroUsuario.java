package com.example.caminapp3.ui.registroUsuario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.caminapp3.R;
import com.example.caminapp3.ui.BD.FuncionesDB;
import com.example.caminapp3.ui.BD.Usuario;

public class RegistroUsuario extends AppCompatActivity {
    private EditText etUsr;
    private EditText etEmail;
    private EditText etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        etUsr = (EditText) findViewById(R.id.et_regisUsuario);
        etEmail = (EditText) findViewById(R.id.et_regisEmail);
        etPass = (EditText) findViewById(R.id.et_regisContraseña);
    }

    public void Registrarse(View view){
        Usuario insertar = new Usuario();

        String usr = etUsr.getText().toString();
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();

        insertar.setNombre(usr);
        insertar.setEmail(email);
        insertar.setPassword(pass);

        FuncionesDB.insertarUsuario(insertar);

        Log.d("Pruebas", "Se ha insertado un usuario con nombre: " + usr + " Email : " + email + " y Contraseña: " + pass);
    }
}