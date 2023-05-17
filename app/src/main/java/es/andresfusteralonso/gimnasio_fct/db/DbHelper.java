package es.andresfusteralonso.gimnasio_fct.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    //DataBase Info
    private static final int PERMISSION_WRITE_EXTERNAL_STORAGE = 1;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "gimnasio.db";

    //Tablas Info
    private static final String TABLE_USERS = "users";
    public static final String TABLE_CLIENTES = "clientes";
    public static final String TABLE_MONITORES = "monitores";
    public static final String TABLE_SALAS = "salas";
    public static final String TABLE_INVENTARIO = "inventario";
    public static final String TABLE_ACTIVIDADES = "actividades";

    //Users Info
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    //Clientes Info
    public static final String COLUMN_IDCL = "idCL";
    public static final String COLUMN_NOMBRECL = "nombreCL";
    public static final String COLUMN_APELLIDOSCL = "apellidosCL";
    public static final String COLUMN_DNICL = "dniCL";
    public static final String COLUMN_TELEFONOCL = "telefonoCL";
    public static final String COLUMN_CORREOCL = "correoCL";
    public static final String COLUMN_SEXOCL = "sexoCL";
    public static final String COLUMN_TARIFACL = "tarifa";
    public static final String COLUMN_FECHA_ALTACL = "fechaAltaCL";
    public static final String COLUMN_FECHA_BAJACL = "fechaBajaCL";

    //Monitores Info
    public static final String COLUMN_IDMO = "idMO";
    public static final String COLUMN_NOMBREMO = "nombreMO";
    public static final String COLUMN_APELLIDOSMO = "apellidosMO";
    public static final String COLUMN_DNIMO = "dniMO";
    public static final String COLUMN_TELEFONOMO = "telefonoMO";
    public static final String COLUMN_CORREOMO = "correoMO";
    public static final String COLUMN_SEXOMO = "sexoMO";
    public static final String COLUMN_CONTRATO = "contrato";
    public static final String COLUMN_FECHA_ALTAMO = "fechaAltaMO";
    public static final String COLUMN_FECHA_BAJAMO = "fechaBajaMO";

    private Context mContext;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

            sqLiteDatabase.execSQL("CREATE TABLE users(username TEXT PRIMARY KEY, password TEXT)");
            sqLiteDatabase.execSQL("CREATE TABLE clientes (idCL INTEGER PRIMARY KEY AUTOINCREMENT, nombreCL TEXT NOT NULL, apellidosCL TEXT, dniCL TEXT, telefonoCL INTEGER, correoCL TEXT, sexoCL TEXT, tarifa TEXT )");
            sqLiteDatabase.execSQL("CREATE TABLE monitores (idMO INTEGER PRIMARY KEY AUTOINCREMENT, nombreMO TEXT NOT NULL, apellidosMO TEXT, dniMO TEXT, telefonoMO INTEGER, correoMO TEXT, sexoMO TEXT, contrato TEXT )");
            sqLiteDatabase.execSQL("CREATE TABLE inventario (idPO INTEGER PRIMARY KEY AUTOINCREMENT, nombrePO TEXT NOT NULL, tipoPO TEXT, sala TEXT, marca INTEGER, modelo TEXT, precio REAL )");
            sqLiteDatabase.execSQL("CREATE TABLE salas (idSA INTEGER PRIMARY KEY AUTOINCREMENT, nombreSA TEXT NOT NULL, dimension REAL, aforo INTEGER, descripcion TEXT )");
            sqLiteDatabase.execSQL("CREATE TABLE actividades (id INTEGER PRIMARY KEY AUTOINCREMENT, nombreAC TEXT NOT NULL, tipoAC TEXT, descripcion TEXT)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTARIO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVIDADES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SALAS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MONITORES);

    }

    public int actualizarDatosCL(int ClienteID, String nombre, String apellidos, String dni, String telefono, String correo, String sexo, String tarifa) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DbHelper.COLUMN_NOMBRECL, nombre);
        cvActualizar.put(DbHelper.COLUMN_APELLIDOSCL, apellidos);
        cvActualizar.put(DbHelper.COLUMN_DNICL, dni);
        cvActualizar.put(DbHelper.COLUMN_TELEFONOCL, telefono);
        cvActualizar.put(DbHelper.COLUMN_CORREOCL, correo);
        cvActualizar.put(DbHelper.COLUMN_SEXOCL, sexo);
        cvActualizar.put(DbHelper.COLUMN_TARIFACL, tarifa);

        int i = db.update(DbHelper.TABLE_CLIENTES, cvActualizar,
                DbHelper.COLUMN_IDCL + " = " + ClienteID, null);
        return i;
    }
    public int actualizarDatosMO(int MonitorID, String nombre, String apellidos, String dni, String telefono, String correo, String sexo, String contrato) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DbHelper.COLUMN_NOMBREMO, nombre);
        cvActualizar.put(DbHelper.COLUMN_APELLIDOSMO, apellidos);
        cvActualizar.put(DbHelper.COLUMN_DNIMO, dni);
        cvActualizar.put(DbHelper.COLUMN_TELEFONOMO, telefono);
        cvActualizar.put(DbHelper.COLUMN_CORREOMO, correo);
        cvActualizar.put(DbHelper.COLUMN_SEXOMO, sexo);
        cvActualizar.put(DbHelper.COLUMN_CONTRATO, contrato);

        int i = db.update(DbHelper.TABLE_MONITORES, cvActualizar,
                DbHelper.COLUMN_IDMO + " = " + MonitorID, null);
        return i;
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = db.insert("users", null, contentValues);
        if (result ==-1) return false;
        else
            return true;
    }

    public Boolean addClient(String nombre, String apellidos, String dni, String telefono, String correo, String sexo, String tarifa) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombreCL", nombre);
        contentValues.put("apellidosCL", apellidos);
        contentValues.put("dniCL", dni);
        contentValues.put("telefonoCL", telefono);
        contentValues.put("correoCL", correo);
        contentValues.put("sexoCL", sexo);
        contentValues.put("tarifa", tarifa);
        long result = db.insert(TABLE_CLIENTES, null, contentValues);
        if (result ==-1) return false;
        else
            return true;
    }
    public Boolean addMonitor(String nombre, String apellidos, String dni, String telefono, String correo, String sexo, String contrato) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombreMO", nombre);
        contentValues.put("apellidosMO", apellidos);
        contentValues.put("dniMO", dni);
        contentValues.put("telefonoMO", telefono);
        contentValues.put("correoMO", correo);
        contentValues.put("sexoMO", sexo);
        contentValues.put("contrato", contrato);
        long result = db.insert(TABLE_MONITORES, null, contentValues);
        if (result ==-1) return false;
        else
            return true;
    }
    public void deleteDataCL(int clienteID) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DbHelper.TABLE_CLIENTES, DbHelper.COLUMN_IDCL + "="
                + clienteID, null);
    }
    public void deleteDataMO(int clienteID) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DbHelper.TABLE_MONITORES, DbHelper.COLUMN_IDMO + "="
                + clienteID, null);
    }
    public Cursor leerDatosCL() {
        SQLiteDatabase db = getWritableDatabase();
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

        Cursor c = db.query(DbHelper.TABLE_CLIENTES, todasLasColumnas, null,
                null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
    public boolean checkusername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ?", new String[]{username});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public boolean chekuserpassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

}

    /*public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
            Client client = new Client(name, phone);
            clients.add(client);
        }
        cursor.close();
        db.close();
        return clients;
    }*/

 /*public void actualizarCliente(int id, String nombre, String apellidos, String dni, String telefono, String correo, String sexo, String tarifa) {

            // Los permisos ya se han concedido, continuar con la actualización del cliente
            SQLiteDatabase db = getWritableDatabase();

            // Crear un objeto ContentValues con los nuevos valores
            ContentValues valores = new ContentValues();
            valores.put("nombreCL", nombre);
            valores.put("apellidosCL", apellidos);
            valores.put("dniCL", dni);
            valores.put("telefonoCL", telefono);
            valores.put("correoCL", correo);
            valores.put("sexoCL", sexo);
            valores.put("tarifa", tarifa);

            int i = db.update(DbHelper.TABLE_CLIENTES, valores, DbHelper.COLUMN_IDCL + " = " + id, null);
            return i;
            // Crear la cláusula WHERE para identificar el cliente que se va a actualizar
            /*String whereClause = "idCL = ?";
            String[] whereArgs = {String.valueOf(id)};*/

// Actualizar el registro del cliente en la base de datos
            /*int filasActualizadas = db.update("clientes", valores, whereClause, whereArgs);

            // Verificar si la actualización fue exitosa
            if (filasActualizadas > 0) {
                Log.d("DbHelper", "Cliente actualizado correctamente");
            } else {
                Log.e("DbHelper", "Error al actualizar el cliente");
            }

            // Cerrar la conexión con la base de datos
            db.close();
    }*/