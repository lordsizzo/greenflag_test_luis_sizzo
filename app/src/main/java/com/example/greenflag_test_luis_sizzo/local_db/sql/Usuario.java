package com.example.greenflag_test_luis_sizzo.local_db.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lou Sizzo on 18/04/2017.
 */
public class Usuario {
    public  static final String EMAIL = "nombre_usuario";
    public  static final String PASSWORD = "password";

    private static final String N_BD = "Usuario";
    private static final String N_TABLA = "Tabla_Usuario";
    private static final int VERSION_BD = 1;

    private BDHelper nHelper;
    private final Context nContexto;
    private SQLiteDatabase nBD;

    private static class BDHelper extends SQLiteOpenHelper {

        public BDHelper(Context context) {
            super(context, N_BD, null, VERSION_BD);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + N_TABLA +
                    "(" +
                    EMAIL + " TEXT, " +
                    PASSWORD + " TEXT);");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + N_TABLA);
            onCreate(db);

        }
    }

    public Usuario(Context c)
    {
        nContexto = c;
    }
    public Usuario abrir()
    {
        nHelper = new BDHelper(nContexto);
        nBD = nHelper.getWritableDatabase();
        return this;
    }
    public void cerrar()
    {
        nHelper.close();
    }
    public long crearEntrada(String user, String password) {
        ContentValues cv = new ContentValues();
        cv.put(EMAIL, user);
        cv.put(PASSWORD, password);

        return nBD.insert(N_TABLA, null, cv);
    }

    public Boolean checkUsuario(String email)
    {
        String[] columnas = new String[]{EMAIL, PASSWORD};
        Cursor c = nBD.query(N_TABLA, columnas, EMAIL+" = '"+email+"'", null, null, null, null);
        String resultado = "";
        int iSesion = c.getColumnIndex(PASSWORD);
        int iUsuario = c.getColumnIndex(EMAIL);
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
        {
            resultado = c.getString(iUsuario);
        }
        if(resultado.isEmpty())
        {
            return true;
        }else {
            return false;
        }
    }
    public String recibirUsuario()
    {
        String[] columnas = new String[]{EMAIL, PASSWORD};
        Cursor c = nBD.query(N_TABLA, columnas, null, null, null, null, null);
        String resultado = "";
        int iSesion = c.getColumnIndex(PASSWORD);
        int iUsuario = c.getColumnIndex(EMAIL);
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
        {
            resultado = c.getString(iUsuario);
        }
        return resultado;
    }
    public void UpdateUser (String email, String idPassword) throws SQLException
    {
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(EMAIL, email);
        cvUpdate.put(PASSWORD, idPassword);
        nBD.update(N_TABLA, cvUpdate, EMAIL + "=" + email,null);
    }
    public void Eliminar(String idUsuario) throws SQLException {
        nBD.delete(N_TABLA, EMAIL +"="+idUsuario, null);
    }
    public void borrar() {
        nBD.execSQL("DROP TABLE IF EXISTS " + N_TABLA);
        nHelper.onCreate(nBD);
    }

}
