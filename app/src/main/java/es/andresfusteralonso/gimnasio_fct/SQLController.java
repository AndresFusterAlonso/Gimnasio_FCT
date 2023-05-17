package es.andresfusteralonso.gimnasio_fct;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import es.andresfusteralonso.gimnasio_fct.db.DbHelper;

public class SQLController {
    private DbHelper dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLController(Context c) {
        ourcontext = c;
    }

    public SQLController abrirBaseDeDatos() throws SQLException {
        dbhelper = new DbHelper(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void cerrar() {
        dbhelper.close();
    }

    public Cursor leerDatosCL() {
        String[] todasLasColumnas = new String[] {
                DbHelper.COLUMN_IDCL,
                DbHelper.COLUMN_NOMBRECL,
                DbHelper.COLUMN_APELLIDOSCL,
                DbHelper.COLUMN_DNICL,
                DbHelper.COLUMN_TELEFONOCL,
                DbHelper.COLUMN_CORREOCL,
                DbHelper.COLUMN_SEXOCL,
                DbHelper.COLUMN_TARIFACL
        };
        Cursor c = database.query(DbHelper.TABLE_CLIENTES, todasLasColumnas, null,
                null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public int actualizarDatosCL(long ClienteID, String nombre, String apellidos, String dni, String telefono, String correo, String sexo, String tarifa) {
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DbHelper.COLUMN_NOMBRECL, nombre);
        cvActualizar.put(DbHelper.COLUMN_APELLIDOSCL, apellidos);
        cvActualizar.put(DbHelper.COLUMN_DNICL, dni);
        cvActualizar.put(DbHelper.COLUMN_TELEFONOCL, telefono);
        cvActualizar.put(DbHelper.COLUMN_CORREOCL, correo);
        cvActualizar.put(DbHelper.COLUMN_SEXOCL, sexo);
        cvActualizar.put(DbHelper.COLUMN_TARIFACL, tarifa);
        int i = database.update(DbHelper.TABLE_CLIENTES, cvActualizar,
                DbHelper.COLUMN_IDCL + " = " + ClienteID, null);
        return i;
    }

    public void deleteDataCL(int clienteID) {
        database.delete(DbHelper.TABLE_CLIENTES, DbHelper.COLUMN_IDCL + "="
                + clienteID, null);
    }
}