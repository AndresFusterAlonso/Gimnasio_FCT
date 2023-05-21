package es.andresfusteralonso.gimnasio_fct;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import es.andresfusteralonso.gimnasio_fct.db.DbHelper;

public class Alta_Producto extends Activity {
    private String opcionSeleccionada;
    private EditText editTextNombre, EditTextTipo, EditTextMarca, EditTextModelo, EditTextPrecio;
    private ImageButton buttonGuardar;
    private ImageButton buttonCancelar;
    private DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.altaproducto);

        DB = new DbHelper(this);
        editTextNombre = findViewById(R.id.edNombreSA);
        EditTextTipo = findViewById(R.id.edTipoSA);
        EditTextMarca = findViewById(R.id.edModeloSA);
        EditTextModelo = findViewById(R.id.edMarcaSA);
        EditTextPrecio = findViewById(R.id.edPrecioSA);
        buttonGuardar = findViewById(R.id.btnAceptarSA);
        buttonCancelar = findViewById(R.id.btnCancelarSAED);

        Spinner spinnerSalas = findViewById(R.id.spinnerSalasPOED);
        ArrayList<String> datosSpinner = new ArrayList<>();
        // Obtén una instancia de SQLiteDatabase
        SQLiteDatabase db = DB.getReadableDatabase();

// Realiza la consulta a la tabla "clientes" para obtener los nombres
        Cursor cursor = db.rawQuery("SELECT nombreSA FROM salas", null);

// Recorre el cursor y agrega los nombres al ArrayList
        if (cursor.moveToFirst()) {
            do {
                int columnIndex = cursor.getColumnIndex("nombreSA");
                if (columnIndex != -1) {
                    String nombre = cursor.getString(columnIndex);
                    datosSpinner.add(nombre);
                }
            } while (cursor.moveToNext());
        }

// Cierra el cursor y la conexión a la base de datos
            cursor.close();
            db.close();

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, datosSpinner);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSalas.setAdapter(adapter);

            spinnerSalas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    opcionSeleccionada = parent.getItemAtPosition(position).toString();
                    ContentValues values = new ContentValues();
                    values.put("opcion", opcionSeleccionada);

                    Toast.makeText(Alta_Producto.this, "Opción seleccionada: " + opcionSeleccionada, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    opcionSeleccionada = null;
                    Toast.makeText(Alta_Producto.this, "Selecciona una opción", Toast.LENGTH_SHORT).show();

                }
            });

            buttonGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nombre = editTextNombre.getText().toString();
                    String tipo = EditTextTipo.getText().toString();
                    String Marca = EditTextMarca.getText().toString();
                    String Modelo = EditTextModelo.getText().toString();
                    String precio = EditTextPrecio.getText().toString();
                    String Salas = opcionSeleccionada;
                    Boolean insert = DB.addProducto(nombre, tipo, Salas, Marca, Modelo, precio);
                    Intent intent = new Intent(getApplicationContext(), InformacionProducto.class);
                    intent.putExtra("nombrePO", nombre);
                    intent.putExtra("tipoPO", tipo);
                    intent.putExtra("sala_id", Salas);
                    intent.putExtra("marca", Marca);
                    intent.putExtra("modelo", Modelo);
                    intent.putExtra("precio", precio);
                    // Agregar los demás datos según sea necesario

                    // Abrir la actividad InformacionCliente
                    startActivity(intent);

                    // Finalizar la actividad de edición
                    finish();
                    startActivity(intent);
                }
            });

            buttonCancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(getApplicationContext(), Inventario.class);
                    startActivity(intent1);
                }
            });
        }
    }