package es.andresfusteralonso.gimnasio_fct;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.andresfusteralonso.gimnasio_fct.db.DbHelper;

public class EditarSala extends AppCompatActivity {
    private SQLiteDatabase database;
    private EditText editTextNombre;
    private EditText editTextdimension;
    private EditText editTextaforo;
    private EditText editTextdescripcion;
    private DbHelper DB;
    private int SalaID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_sala);
        DB = new DbHelper(this);
        editTextNombre = findViewById(R.id.edNombreSA);
        editTextdimension = findViewById(R.id.edDimensionSA);
        editTextaforo = findViewById(R.id.edAforoSA);
        editTextdescripcion = findViewById(R.id.edDescripcionSA);

        // Obtener los datos del cliente del Intent
        Intent intent = getIntent();
        int id = intent.getIntExtra("idSA", 0);
        String nombre = intent.getStringExtra("nombreSA");
        String dimension = intent.getStringExtra("dimension");
        String aforo = intent.getStringExtra("aforo");
        String descripcion = intent.getStringExtra("descripcion");

// Configurar los campos de edición con los datos del monitor
        editTextNombre.setText(nombre);
        editTextdimension.setText(dimension);
        editTextaforo.setText(aforo);
        editTextdescripcion.setText(descripcion);
        SalaID = id;
        // Configurar los demás campos según sea necesario

        ImageButton btnGuardar = findViewById(R.id.btnAceptarSA);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los nuevos valores de los campos de edición
                String nuevoNombre = editTextNombre.getText().toString();
                String nuevoDimension = editTextdimension.getText().toString();
                String nuevoAforo = editTextaforo.getText().toString();
                String nuevoDescripcion = editTextdescripcion.getText().toString();
                // Obtener los demás nuevos valores según sea necesario
                // Validar campos obligatorios
                if (nuevoNombre.isEmpty() || nuevoDimension.isEmpty() || nuevoAforo.isEmpty() || nuevoDescripcion.isEmpty()) {
                    Toast.makeText(EditarSala.this, "Por favor, complete todos los campos obligatorios.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Actualizar los datos del cliente en la base de datos
                DB.actualizarDatosSA(SalaID, nuevoNombre, nuevoDimension, nuevoAforo, nuevoDescripcion);
                // Crear un Intent para abrir la actividad InformacionMonitor
                Intent intent = new Intent(EditarSala.this, InformacionSala.class);
                intent.putExtra("nombreSA", nuevoNombre);
                intent.putExtra("dimension", nuevoDimension);
                intent.putExtra("aforo", nuevoAforo);
                intent.putExtra("descripcion", nuevoDescripcion);
                // Agregar los demás datos según sea necesario

                // Abrir la actividad InformacionCliente
                startActivity(intent);

                // Finalizar la actividad de edición
                finish();
            }
        });

        ImageButton btnEliminar = findViewById(R.id.btnEliminarSAED);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.deleteDataSA(SalaID);
                Intent intent = new Intent(EditarSala.this, Salas.class);
                startActivity(intent);
            }
        });

        ImageButton btnCancelar = findViewById(R.id.btnCancelarSAED);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(EditarSala.this, Salas.class);
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
