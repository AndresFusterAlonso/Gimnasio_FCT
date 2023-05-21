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

public class Alta_Cliente extends Activity {
    private EditText editTextName, editTextApellidos, editTextdni, editTextPhone, editTextcorreo, editTextTarifa;
    private RadioGroup radioGroupSexo;
    private ImageButton buttonSave;
    private ImageButton buttonCancelar;
    private DbHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.altaclientes);

        DB = new DbHelper(this);
        editTextName = findViewById(R.id.edNombreCL);
        editTextApellidos = findViewById(R.id.edApellidosCL);
        editTextdni = findViewById(R.id.edDniCL);
        editTextPhone = findViewById(R.id.edTelefonoCL);
        editTextcorreo = findViewById(R.id.edCorreoCl);
        radioGroupSexo = findViewById(R.id.rgCLAL);
        editTextTarifa = findViewById(R.id.edTarifaCL);
        buttonSave = findViewById(R.id.btnAceptarCL);
        buttonCancelar = findViewById(R.id.btnAtrasClAL);

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
                String tarifa = editTextTarifa.getText().toString();
                Boolean insert = DB.addClient( nombre,  apellidos,  dni,  telefono,  correo,  sexo,  tarifa);
                Intent intent = new Intent(getApplicationContext(), InformacionCliente.class);
                intent.putExtra("nombreCL", nombre);
                intent.putExtra("apellidosCL", apellidos);
                intent.putExtra("dniCL", dni);
                intent.putExtra("telefonoCL", telefono);
                intent.putExtra("correoCL", correo);
                intent.putExtra("sexoCL", sexo);
                intent.putExtra("tarifaCL", tarifa);
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
                Intent intent1 = new Intent(getApplicationContext(), Clientes.class);
                startActivity(intent1);
            }
        });
    }
}
