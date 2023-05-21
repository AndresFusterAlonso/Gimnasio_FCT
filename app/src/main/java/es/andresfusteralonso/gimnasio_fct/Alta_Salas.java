package es.andresfusteralonso.gimnasio_fct;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import es.andresfusteralonso.gimnasio_fct.db.DbHelper;

public class Alta_Salas extends Activity {
    private EditText editTextNombre, EditTextdimension, EditTextaforo, EditTextdescripcion;
    private ImageButton buttonGuardar;
    private ImageButton buttonCancelar;
    private DbHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.altasalas);
        DB = new DbHelper(this);
        editTextNombre = findViewById(R.id.edNombreSA);
        EditTextdimension = findViewById(R.id.edTipoSA);
        EditTextaforo = findViewById(R.id.edMarcaSA);
        EditTextdescripcion = findViewById(R.id.edModeloSA);

        buttonGuardar = findViewById(R.id.btnAceptarSA);
        buttonCancelar = findViewById(R.id.btnCancelarSAED);

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editTextNombre.getText().toString();
                String dimension = EditTextdimension.getText().toString();
                String aforo = EditTextaforo.getText().toString();
                String descripcion = EditTextdescripcion.getText().toString();
                Boolean insert = DB.addSala( nombre,  dimension, aforo,  descripcion);
                Intent intent = new Intent(getApplicationContext(), InformacionSala.class);
                intent.putExtra("nombreSA", nombre);
                intent.putExtra("dimension", dimension);
                intent.putExtra("aforo", aforo);
                intent.putExtra("descripcion", descripcion);
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
                Intent intent1 = new Intent(getApplicationContext(), Salas.class);
                startActivity(intent1);
            }
        });
    }
}
