package es.andresfusteralonso.gimnasio_fct;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.andresfusteralonso.gimnasio_fct.db.DbHelper;

public class EditarActividad extends AppCompatActivity {
    private SQLiteDatabase database;
    private EditText editTextNombre;
    private EditText editTextTipo;
    private EditText editTextDescripcion;
    private Button btnInsertar;
    private Button buttonAddClient;
    private Button buttonCancelar;
    private DbHelper DB;
    private int ActividadID;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_actividad);
        DB = new DbHelper(this);
        editTextNombre = findViewById(R.id.edNombreACED);
        editTextTipo = findViewById(R.id.edTIPOACED);
        editTextDescripcion = findViewById(R.id.edDescripcionACED);

        // Obtener los datos del cliente del Intent
        Intent intent = getIntent();
        int id = intent.getIntExtra("idAC", 0);
        String nombre = intent.getStringExtra("nombreAC");
        String Tipo = intent.getStringExtra("tipoAC");
        String descripcion = intent.getStringExtra("descripcionAC");


// Configurar los campos de edición con los datos del cliente
        editTextNombre.setText(nombre);
        editTextTipo.setText(Tipo);
        editTextDescripcion.setText(descripcion);
        ActividadID = id;
        // Configurar los demás campos según sea necesario

        ImageButton btnGuardar = findViewById(R.id.btnAceptarACED);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los nuevos valores de los campos de edición
                String nuevoNombre = editTextNombre.getText().toString();
                String nuevoTipo = editTextTipo.getText().toString();
                String nuevoDescripcion = editTextDescripcion.getText().toString();
                // Obtener los demás nuevos valores según sea necesario
                // Validar campos obligatorios
                if (nuevoNombre.isEmpty() || nuevoTipo.isEmpty() || nuevoDescripcion.isEmpty()) {
                    Toast.makeText(EditarActividad.this, "Por favor, complete todos los campos obligatorios.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Actualizar los datos del cliente en la base de datos
                DB.actualizarDatosAC(ActividadID, nuevoNombre, nuevoTipo, nuevoDescripcion);
                // Crear un Intent para abrir la actividad InformacionCliente
                Intent intent = new Intent(EditarActividad.this, InformacionActividades.class);
                intent.putExtra("nombreAC", nuevoNombre);
                intent.putExtra("tipoAC", nuevoTipo);
                intent.putExtra("descripcionAC", nuevoDescripcion);
                // Agregar los demás datos según sea necesario

                // Abrir la actividad InformacionCliente
                startActivity(intent);

                // Finalizar la actividad de edición
                finish();
            }
        });

        ImageButton btnEliminar = findViewById(R.id.btnEliminarACED);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.deleteDataAC(ActividadID);
                Intent intent = new Intent(EditarActividad.this, actividades.class);
                startActivity(intent);
            }
        });

        ImageButton btnCancelar = findViewById(R.id.btnCancelarACED);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(EditarActividad.this, actividades.class);
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