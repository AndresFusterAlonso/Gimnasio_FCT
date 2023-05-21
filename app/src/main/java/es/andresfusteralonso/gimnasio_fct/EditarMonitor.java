package es.andresfusteralonso.gimnasio_fct;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import es.andresfusteralonso.gimnasio_fct.db.DbHelper;

public class EditarMonitor extends Activity {

    private SQLiteDatabase database;
    private EditText editTextNombre;
    private EditText editTextApellidos;
    private EditText editTextDNI;
    private EditText editTextTelefono;
    private EditText editTextCorreo;
    private RadioGroup radioGroupSexo;
    private EditText editTextContrato;
    private DbHelper DB;
    private int MonitorID;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_monitor);
        DB = new DbHelper(this);
        editTextNombre = findViewById(R.id.edNombreMO);
        editTextApellidos = findViewById(R.id.edApellidosMO);
        editTextDNI = findViewById(R.id.edDniMO);
        editTextTelefono = findViewById(R.id.edTelefonoMO);
        editTextCorreo = findViewById(R.id.edCorreoMO);
        radioGroupSexo = findViewById(R.id.rgCLAL);
        editTextContrato = findViewById(R.id.edContrato);

        // Obtener los datos del cliente del Intent
        Intent intent = getIntent();
        int id = intent.getIntExtra("idMO", 0);
        String nombre = intent.getStringExtra("nombreMO");
        String apellidos = intent.getStringExtra("apellidosMO");
        String dni = intent.getStringExtra("dniMO");
        String telefono = intent.getStringExtra("telefonoMO");
        String correo = intent.getStringExtra("correoMO");
        String sexo = intent.getStringExtra("sexoMO");
        String contrato = intent.getStringExtra("contrato");

// Configurar los campos de edición con los datos del monitor
        editTextNombre.setText(nombre);
        editTextApellidos.setText(apellidos);
        editTextDNI.setText(dni);
        editTextTelefono.setText(telefono);
        editTextCorreo.setText(correo);
        editTextContrato.setText(contrato);
        MonitorID = id;
        // Configurar los demás campos según sea necesario

        ImageButton btnGuardar = findViewById(R.id.btnAceptarMO);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los nuevos valores de los campos de edición
                String nuevoNombre = editTextNombre.getText().toString();
                String nuevoApellidos = editTextApellidos.getText().toString();
                String nuevoDni = editTextDNI.getText().toString();
                String nuevoTelefono = editTextTelefono.getText().toString();
                String nuevoCorreo = editTextCorreo.getText().toString();
                String nuevoContrato = editTextContrato.getText().toString();
                int selectedId = radioGroupSexo.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedId);
                String nuevoSexo = radioButton.getText().toString();
                // Obtener los demás nuevos valores según sea necesario
                // Validar campos obligatorios
                if (nuevoNombre.isEmpty() || nuevoApellidos.isEmpty() || nuevoDni.isEmpty() || nuevoTelefono.isEmpty() || nuevoCorreo.isEmpty() || nuevoContrato.isEmpty()) {
                    Toast.makeText(EditarMonitor.this, "Por favor, complete todos los campos obligatorios.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Actualizar los datos del cliente en la base de datos
                DB.actualizarDatosMO(MonitorID, nuevoNombre, nuevoApellidos, nuevoDni, nuevoTelefono, nuevoCorreo, nuevoSexo, nuevoContrato);
                // Crear un Intent para abrir la actividad InformacionMonitor
                Intent intent = new Intent(EditarMonitor.this, InformacionMonitor.class);
                intent.putExtra("nombreMO", nuevoNombre);
                intent.putExtra("apellidosMO", nuevoApellidos);
                intent.putExtra("dniMO", nuevoDni);
                intent.putExtra("telefonoMO", nuevoTelefono);
                intent.putExtra("correoMO", nuevoCorreo);
                intent.putExtra("sexoMO", nuevoSexo);
                intent.putExtra("contrato", nuevoContrato);
                // Agregar los demás datos según sea necesario

                // Abrir la actividad InformacionCliente
                startActivity(intent);

                // Finalizar la actividad de edición
                finish();
            }
        });

        ImageButton btnEliminar = findViewById(R.id.btnEliminarMOED);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.deleteDataMO(MonitorID);
                Intent intent = new Intent(EditarMonitor.this, Monitores.class);
                startActivity(intent);
            }
        });

        ImageButton btnCancelar = findViewById(R.id.btnCancelarMO);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(EditarMonitor.this, Monitores.class);
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