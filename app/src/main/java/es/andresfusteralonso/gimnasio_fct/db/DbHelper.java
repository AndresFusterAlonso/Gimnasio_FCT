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
    public static final String TABLE_HORARIOS = "horarios";


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

    //Inventario Info
    public static final String COLUMN_IDPO = "idPO";
    public static final String COLUMN_NOMBREPO = "nombrePO";
    public static final String COLUMN_MODELOPO = "modelo";
    public static final String COLUMN_TIPOPO = "tipoPO";
    public static final String COLUMN_SALA = "sala_id";
    public static final String COLUMN_MARCA = "marca";
    public static final String COLUMN_PRECIO = "precio";

    //Salas Info
    public static final String COLUMN_IDSA = "idSA";
    public static final String COLUMN_NOMBRESA = "nombreSA";
    public static final String COLUMN_DIMENSION = "dimension";
    public static final String COLUMN_AFORO = "aforo";
    public static final String COLUMN_DESCRIPCION = "descripcion";

    //Actividad Info
    public static final String COLUMN_IDAC = "idAC";
    public static final String COLUMN_NOMBREAC = "nombreAC";
    public static final String COLUMN_TIPOAC = "tipoAC";
    public static final String COLUMN_DESCRIPCIONAC = "descripcionAC";

    //Horario Info
    public static final String COLUMN_IDHO = "idAC";
    public static final String COLUMN_SALANOMBREHO = "sala_nombre";
    public static final String COLUMN_ACTIVIDADNOMBREHO = "actividad_nombre";
    public static final String COLUMN_DIAHO = "dia";
    public static final String COLUMN_HORAINCIO = "h_incio";
    public static final String COLUMN_HORAFIN = "h_fin";
    public static final String COLUMN_MONITORNOMBREHO = "monitor_nombre";

    private Context mContext;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE users (username TEXT PRIMARY KEY, password TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE clientes (idCL INTEGER PRIMARY KEY AUTOINCREMENT, nombreCL TEXT NOT NULL, apellidosCL TEXT, dniCL TEXT UNIQUE, telefonoCL INTEGER, correoCL TEXT, sexoCL TEXT, tarifa TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE monitores (idMO INTEGER PRIMARY KEY AUTOINCREMENT, nombreMO TEXT NOT NULL, apellidosMO TEXT, dniMO TEXT UNIQUE, telefonoMO INTEGER, correoMO TEXT, sexoMO TEXT, contrato TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE salas (idSA INTEGER PRIMARY KEY AUTOINCREMENT, nombreSA TEXT NOT NULL, dimension REAL, aforo INTEGER, descripcion TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE actividades (idAC INTEGER PRIMARY KEY AUTOINCREMENT, nombreAC TEXT NOT NULL, tipoAC TEXT, descripcionAC TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE inventario (idPO INTEGER PRIMARY KEY AUTOINCREMENT, nombrePO TEXT NOT NULL, tipoPO TEXT, sala_id INTEGER, marca TEXT, modelo TEXT, precio REAL, FOREIGN KEY(sala_id) REFERENCES salas(idSA))");
        sqLiteDatabase.execSQL("CREATE TABLE horarios (idHO INTEGER PRIMARY KEY AUTOINCREMENT, sala_nombre TEXT, actividad_nombre TEXT, dia TEXT, h_inicio TEXT, h_fin TEXT, monitor_nombre TEXT, FOREIGN KEY(sala_nombre) REFERENCES salas(nombreSA), FOREIGN KEY(actividad_nombre) REFERENCES actividades(nombreAC), FOREIGN KEY(monitor_nombre) REFERENCES monitores(nombreMO))");


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
    public int actualizarDatosPO(int ProductoID, String nombrePO, String tipoPO, String sala, String marca, String modelo, String precio) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DbHelper.COLUMN_NOMBREPO, nombrePO);
        cvActualizar.put(DbHelper.COLUMN_TIPOPO, tipoPO);
        cvActualizar.put(DbHelper.COLUMN_SALA, sala);
        cvActualizar.put(DbHelper.COLUMN_MARCA, marca);
        cvActualizar.put(DbHelper.COLUMN_MODELOPO, modelo);
        cvActualizar.put(DbHelper.COLUMN_PRECIO, precio);

        int i = db.update(DbHelper.TABLE_INVENTARIO, cvActualizar,
                DbHelper.COLUMN_IDPO + " = " + ProductoID, null);
        return i;
    }
    public int actualizarDatosSA(int SalaID, String nombrePO, String dimension, String aforo, String descripcion) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DbHelper.COLUMN_NOMBRESA, nombrePO);
        cvActualizar.put(DbHelper.COLUMN_DIMENSION, dimension);
        cvActualizar.put(DbHelper.COLUMN_AFORO, aforo);
        cvActualizar.put(DbHelper.COLUMN_DESCRIPCION, descripcion);

        int i = db.update(DbHelper.TABLE_SALAS, cvActualizar,
                DbHelper.COLUMN_IDSA + " = " + SalaID, null);
        return i;
    }
    public int actualizarDatosAC(int ActividadID, String nombreAC, String tipoAC, String descripcionAC) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DbHelper.COLUMN_NOMBREAC, nombreAC);
        cvActualizar.put(DbHelper.COLUMN_TIPOAC, tipoAC);
        cvActualizar.put(DbHelper.COLUMN_DESCRIPCIONAC, descripcionAC);

        int i = db.update(DbHelper.TABLE_ACTIVIDADES, cvActualizar,
                DbHelper.COLUMN_IDAC + " = " + ActividadID, null);
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

    public Boolean addProducto(String nombre, String tipo, String sala, String marca, String modelo, String precio) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombrePO", nombre);
        contentValues.put("tipoPO", tipo);
        contentValues.put("sala_id", sala);
        contentValues.put("marca", marca);
        contentValues.put("modelo", modelo);
        contentValues.put("precio", precio);
        long result = db.insert(TABLE_INVENTARIO, null, contentValues);
        if (result ==-1) return false;
        else
            return true;
    }
    public Boolean addSala(String nombre, String dimension, String aforo, String descripcion) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombreSA", nombre);
        contentValues.put("dimension", dimension);
        contentValues.put("aforo", aforo);
        contentValues.put("descripcion", descripcion);
        long result = db.insert(TABLE_SALAS, null, contentValues);
        if (result ==-1) return false;
        else
            return true;
    }
    public Boolean addActividad(String nombreAC, String tipoAC, String descripcionAC) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombreAC", nombreAC);
        contentValues.put("tipoAC", tipoAC);
        contentValues.put("descripcionAC", descripcionAC);
        long result = db.insert(TABLE_ACTIVIDADES, null, contentValues);
        if (result ==-1) return false;
        else
            return true;
    }

    public Boolean addHorario(String sala_nombre, String actividad_nombre, String dia, String h_incio, String h_fin, String monitor_nombre) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sala_nombre", sala_nombre);
        contentValues.put("actividad_nombre", actividad_nombre);
        contentValues.put("dia", dia);
        contentValues.put("h_inicio", h_incio);
        contentValues.put("h_fin", h_fin);
        contentValues.put("monitor_nombre", monitor_nombre);
        long result = db.insert(TABLE_HORARIOS, null, contentValues);
        if (result ==-1) return false;
        else
            return true;
    }
    public void deleteDataCL(int clienteID) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DbHelper.TABLE_CLIENTES, DbHelper.COLUMN_IDCL + "="
                + clienteID, null);
    }
    public void deleteDataMO(int monitorID) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DbHelper.TABLE_MONITORES, DbHelper.COLUMN_IDMO + "="
                + monitorID, null);
    }
    public void deleteDataPO(int productoID) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DbHelper.TABLE_INVENTARIO, DbHelper.COLUMN_IDPO + "="
                + productoID, null);
    }
    public void deleteDataAC(int ActividadID) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DbHelper.TABLE_ACTIVIDADES, DbHelper.COLUMN_IDAC + "="
                + ActividadID, null);
    }
    public void deleteDataSA(int salaID) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DbHelper.TABLE_SALAS, DbHelper.COLUMN_IDSA + "="
                + salaID, null);
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