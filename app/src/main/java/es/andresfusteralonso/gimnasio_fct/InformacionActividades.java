package es.andresfusteralonso.gimnasio_fct;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InformacionActividades extends AppCompatActivity {
    private TextView textViewNombre;
    private TextView textViewTipo;
    private TextView TextViewDescripcion;
    private Button btnAceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_actividades);
        // Obtener referencias a los TextViews de la interfaz
        textViewNombre = findViewById(R.id.txtNombreACIN);
        textViewTipo = findViewById(R.id.txtTipoACIN);
        TextViewDescripcion = findViewById(R.id.txtDescrcionACIN);

        // Obtener los datos del cliente actualizado de la actividad anterior
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombreAC");
        String tipoAC = intent.getStringExtra("tipoAC");
        String descripcionAC = intent.getStringExtra("descripcionAC");

        // Mostrar los datos en los TextViews de la interfaz
        textViewNombre.setText(nombre);
        textViewTipo.setText(tipoAC);
        TextViewDescripcion.setText(descripcionAC);

        btnAceptar = findViewById(R.id.btnaceptarACIN);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(InformacionActividades.this, actividades.class);
                startActivity(intent1);
            }
        });
    }
}
