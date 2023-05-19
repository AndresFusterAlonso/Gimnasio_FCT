package es.andresfusteralonso.gimnasio_fct;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.andresfusteralonso.gimnasio_fct.db.DbHelper;

public class EditarProducto extends AppCompatActivity {
    private SQLiteDatabase database;
    private EditText editTextNombre;
    private EditText editTextTipo;
    private Spinner spinnerSalas;
    private EditText editTextMarca;
    private EditText editTextModelo;
    private EditText editTextPrecio;
    private DbHelper DB;
    private int ProductoID;
    private String opcionSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_producto);
        DB = new DbHelper(this);
        editTextNombre = findViewById(R.id.edNombreSA);
        editTextTipo = findViewById(R.id.edTipoSA);
        spinnerSalas = findViewById(R.id.spinnerSalasPOED);
        editTextMarca = findViewById(R.id.edMarcaSA);
        editTextModelo = findViewById(R.id.edModeloSA);
        editTextPrecio = findViewById(R.id.edPrecioSA);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.salasDropdown, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSalas.setAdapter(adapter);

        spinnerSalas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                opcionSeleccionada = parent.getItemAtPosition(position).toString();
                ContentValues values = new ContentValues();
                values.put("opcion", opcionSeleccionada);
                Toast.makeText(EditarProducto.this, "Opción seleccionada: " + opcionSeleccionada, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                opcionSeleccionada = null;
                Toast.makeText(EditarProducto.this, "Selecciona una opción", Toast.LENGTH_SHORT).show();

            }
        });
        // Obtener los datos del cliente del Intent
        Intent intent = getIntent();
        int id = intent.getIntExtra("idPO", 0);
        String nombre = intent.getStringExtra("nombrePO");
        String tipo = intent.getStringExtra("tipoPO");
        String marca = intent.getStringExtra("marca");
        String salas = intent.getStringExtra("sala_id");
        String modelo = intent.getStringExtra("modelo");
        String precio = intent.getStringExtra("precio");

// Configurar los campos de edición con los datos del monitor
        editTextNombre.setText(nombre);
        editTextTipo.setText(tipo);
        editTextMarca.setText(marca);
        editTextModelo.setText(modelo);
        editTextPrecio.setText(precio);
        ProductoID = id;
        // Configurar los demás campos según sea necesario

        Button btnGuardar = findViewById(R.id.btnAceptarSA);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los nuevos valores de los campos de edición
                String nuevoNombre = editTextNombre.getText().toString();
                String nuevoTipo = editTextTipo.getText().toString();
                String nuevoSpinner = opcionSeleccionada;
                String nuevoMarca = editTextMarca.getText().toString();
                String nuevoModelo = editTextModelo.getText().toString();
                String nuevoPrecio = editTextPrecio.getText().toString();


                // Obtener los demás nuevos valores según sea necesario
                // Validar campos obligatorios
                if (nuevoNombre.isEmpty() || nuevoTipo.isEmpty() || nuevoMarca.isEmpty() || nuevoModelo.isEmpty() || nuevoPrecio.isEmpty() || nuevoSpinner.isEmpty()) {
                    Toast.makeText(EditarProducto.this, "Por favor, complete todos los campos obligatorios.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Actualizar los datos del cliente en la base de datos
                DB.actualizarDatosPO(ProductoID, nuevoNombre,nuevoTipo, nuevoSpinner, nuevoMarca, nuevoModelo, nuevoPrecio);
                // Crear un Intent para abrir la actividad InformacionMonitor
                Intent intent = new Intent(EditarProducto.this, InformacionProducto.class);
                intent.putExtra("nombrePO", nuevoNombre);
                intent.putExtra("tipoPO", nuevoTipo);
                intent.putExtra("sala_id", nuevoSpinner);
                intent.putExtra("marca", nuevoMarca);
                intent.putExtra("modelo", nuevoModelo);
                intent.putExtra("precio", nuevoPrecio);
                // Agregar los demás datos según sea necesario

                // Abrir la actividad InformacionCliente
                startActivity(intent);

                // Finalizar la actividad de edición
                finish();
            }
        });

        Button btnEliminar = findViewById(R.id.btnEliminarPOED);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.deleteDataPO(ProductoID);
                Intent intent = new Intent(EditarProducto.this, Inventario.class);
                startActivity(intent);
            }
        });

        Button btnCancelar = findViewById(R.id.btnCancelarSAED);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(EditarProducto.this, Inventario.class);
                startActivity(intent1);
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        DB.close();
    }
}