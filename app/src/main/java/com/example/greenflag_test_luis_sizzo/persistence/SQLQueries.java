package com.example.greenflag_test_luis_sizzo.persistence;

import android.content.Context;

import com.example.greenflag_test_luis_sizzo.persistence.sql.Usuario;

public class SQLQueries{

    public static void InsertUsuario(Context context, String email, String password) {
        Usuario usuarioInsert = new Usuario(context);
        usuarioInsert.abrir();
        usuarioInsert.crearEntrada(email, password);
        usuarioInsert.cerrar();
    }

    public static Boolean CheckUsuario(Context context, String email) {
        Usuario usuario = new Usuario(context);
        usuario.abrir();
        boolean check = usuario.checkUsuario(email);
        usuario.cerrar();

        return check;
    }

    public static String checkAllUsuario(Context context) {
        Usuario usuario = new Usuario(context);
        usuario.abrir();
        String email = usuario.recibirUsuario();
        usuario.cerrar();

        return email;
    }
}
