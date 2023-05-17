package es.andresfusteralonso.gimnasio_fct;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import es.andresfusteralonso.gimnasio_fct.db.DbHelper;

public class EditarCliente extends Activity{

     private SQLiteDatabase database;
    private EditText editTextNombre;
    private EditText editTextApellidos;
    private EditText editTextDNI;
    private EditText editTextTelefono;
    private EditText editTextCorreo;
    private RadioGroup radioGroupSexo;
    private EditText editTextTarifa;
    private Button btnInsertar;
    private Button buttonAddClient;
    private Button buttonCancelar;
    private DbHelper DB;
    private int ClienteID;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_cliente);
        DB = new DbHelper(this);
        editTextNombre = findViewById(R.id.ednombreCLED);
        editTextApellidos = findViewById(R.id.edapellidosCLED);
        editTextDNI = findViewById(R.id.eddniCLED);
        editTextTelefono = findViewById(R.id.edtelefonoCLED);
        editTextCorreo = findViewById(R.id.edcorreoCLED);
        radioGroupSexo = findViewById(R.id.rgCLAL);
        editTextTarifa = findViewById(R.id.edTarifaCLED);

    // Obtener los datos del cliente del Intent
    Intent intent = getIntent();
    int id = intent.getIntExtra("idCL", 0);
    String nombre = intent.getStringExtra("nombreCL");
    String apellidos = intent.getStringExtra("apellidosCL");
    String dni = intent.getStringExtra("dniCL");
    String telefono = intent.getStringExtra("telefonoCL");
    String correo = intent.getStringExtra("correoCL");
    String sexo = intent.getStringExtra("sexoCL");
    String tarifa = intent.getStringExtra("tarifaCL");

// Configurar los campos de edición con los datos del cliente
        editTextNombre.setText(nombre);
        editTextApellidos.setText(apellidos);
        editTextDNI.setText(dni);
        editTextTelefono.setText(telefono);
        editTextCorreo.setText(correo);
        editTextTarifa.setText(tarifa);
        ClienteID = id;
    // Configurar los demás campos según sea necesario

    Button btnGuardar = findViewById(R.id.btnGuardarCLED);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los nuevos valores de los campos de edición
                String nuevoNombre = editTextNombre.getText().toString();
                String nuevoApellidos = editTextApellidos.getText().toString();
                String nuevoDni = editTextDNI.getText().toString();
                String nuevoTelefono = editTextTelefono.getText().toString();
                String nuevoCorreo = editTextCorreo.getText().toString();
                String nuevoTarifa = editTextTarifa.getText().toString();
                int selectedId = radioGroupSexo.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedId);
                String nuevoSexo = radioButton.getText().toString();
                // Obtener los demás nuevos valores según sea necesario
                // Validar campos obligatorios
                if (nuevoNombre.isEmpty() || nuevoApellidos.isEmpty() || nuevoDni.isEmpty() || nuevoTelefono.isEmpty() || nuevoCorreo.isEmpty() || nuevoTarifa.isEmpty()) {
                    Toast.makeText(EditarCliente.this, "Por favor, complete todos los campos obligatorios.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Actualizar los datos del cliente en la base de datos
                DB.actualizarDatosCL(ClienteID, nuevoNombre, nuevoApellidos, nuevoDni, nuevoTelefono, nuevoCorreo, nuevoSexo, nuevoTarifa);
                // Crear un Intent para abrir la actividad InformacionCliente
                Intent intent = new Intent(EditarCliente.this, InformacionCliente.class);
                intent.putExtra("nombreCL", nuevoNombre);
                intent.putExtra("apellidosCL", nuevoApellidos);
                intent.putExtra("dniCL", nuevoDni);
                intent.putExtra("telefonoCL", nuevoTelefono);
                intent.putExtra("correoCL", nuevoCorreo);
                intent.putExtra("sexoCL", nuevoSexo);
                intent.putExtra("tarifaCL", nuevoTarifa);
                // Agregar los demás datos según sea necesario

                // Abrir la actividad InformacionCliente
                startActivity(intent);

                // Finalizar la actividad de edición
                finish();
            }
        });

        Button btnEliminar = findViewById(R.id.btnEliminarCLED);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.deleteDataCL(ClienteID);
                Intent intent = new Intent(EditarCliente.this, Clientes.class);
                startActivity(intent);
            }
        });

        Button btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(EditarCliente.this, Clientes.class);
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
