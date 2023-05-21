package es.andresfusteralonso.gimnasio_fct;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import es.andresfusteralonso.gimnasio_fct.db.DbHelper;

public class Alta_Monitor extends Activity {
    private EditText editTextName, editTextApellidos, editTextdni, editTextPhone, editTextcorreo, editTextContrato;
    private RadioGroup radioGroupSexo;
    private ImageButton buttonSave;
    private ImageButton buttonCancelar;
    private DbHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.altamonitores);

        DB = new DbHelper(this);
        editTextName = findViewById(R.id.edNombreMO);
        editTextApellidos = findViewById(R.id.edApellidosMO);
        editTextdni = findViewById(R.id.edDniMO);
        editTextPhone = findViewById(R.id.edTelefonoMO);
        editTextcorreo = findViewById(R.id.edCorreoMO);
        radioGroupSexo = findViewById(R.id.rgCLAL);
        editTextContrato = findViewById(R.id.edContrato);
        buttonSave = findViewById(R.id.btnAceptarMO);
        buttonCancelar = findViewById(R.id.btnCancelarMO);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editTextName.getText().toString();
                String apellidos = editTextApellidos.getText().toString();
                String dni = editTextdni.getText().toString();
                String telefono = editTextPhone.getText().toString();
                String correo = editTextcorreo.getText().toString();
                int selectedId = radioGroupSexo.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedId);
                String sexo = radioButton.getText().toString();
                String contrato = editTextContrato.getText().toString();
                Boolean insert = DB.addMonitor( nombre,  apellidos,  dni,  telefono,  correo,  sexo,  contrato);
                Intent intent = new Intent(getApplicationContext(), InformacionMonitor.class);
                intent.putExtra("nombreMO", nombre);
                intent.putExtra("apellidosMO", apellidos);
                intent.putExtra("dniMO", dni);
                intent.putExtra("telefonoMO", telefono);
                intent.putExtra("correoMO", correo);
                intent.putExtra("sexoMO", sexo);
                intent.putExtra("contrato", contrato);
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
                Intent intent1 = new Intent(getApplicationContext(), Monitores.class);
                startActivity(intent1);
            }
        });
    }
}

