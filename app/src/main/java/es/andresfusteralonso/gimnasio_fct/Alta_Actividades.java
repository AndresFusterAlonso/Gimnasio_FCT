package es.andresfusteralonso.gimnasio_fct;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import es.andresfusteralonso.gimnasio_fct.db.DbHelper;

public class Alta_Actividades extends Activity {
    private EditText editTextName, editTextTipo, editTextDescripcion;
    private ImageButton buttonSaveAC;
    private ImageButton buttonCancelar;
    private DbHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividadesalta);

        DB = new DbHelper(this);
        editTextName = findViewById(R.id.edtNombreACAL);
        editTextTipo = findViewById(R.id.edTipoACAL);
        editTextDescripcion = findViewById(R.id.edDescripcionACAL);
        buttonSaveAC = findViewById(R.id.btnAltaACAL);
        buttonCancelar = findViewById(R.id.btnCancelarACAL);
        buttonSaveAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editTextName.getText().toString();
                String Tipo = editTextTipo.getText().toString();
                String Descripcion = editTextDescripcion.getText().toString();
                Boolean insert = DB.addActividad( nombre,  Tipo,  Descripcion);
                Intent intent = new Intent(getApplicationContext(), InformacionActividades.class);
                intent.putExtra("nombreAC", nombre);
                intent.putExtra("tipoAC", Tipo);
                intent.putExtra("descripcionAC", Descripcion);
                // Agregar los demás datos según sea necesario

                // Abrir la actividad InformacionActividad
                startActivity(intent);

                // Finalizar la actividad de edición
                finish();
                startActivity(intent);
            }
        });

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), actividades.class);
                startActivity(intent1);
            }
        });
    }
}
