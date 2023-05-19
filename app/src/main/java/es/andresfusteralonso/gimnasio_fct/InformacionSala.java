package es.andresfusteralonso.gimnasio_fct;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InformacionSala extends AppCompatActivity {
    private TextView textViewNombre;
    private TextView textViewDimension;
    private TextView textViewAforo;
    private TextView textViewDescripcion;
    private Button btnAceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_sala);
        // Obtener referencias a los TextViews de la interfaz
        textViewNombre = findViewById(R.id.txtNombreSAIN);
        textViewDimension = findViewById(R.id.txtDimensionSAIN);
        textViewAforo = findViewById(R.id.txtAforoSAIN);
        textViewDescripcion = findViewById(R.id.txtDescripcionSAIN);

        // Obtener los datos del cliente actualizado de la actividad anterior
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombreSA");
        String dimension = intent.getStringExtra("dimension");
        String aforo = intent.getStringExtra("aforo");
        String descripcion = intent.getStringExtra("descripcion");

        // Mostrar los datos en los TextViews de la interfaz
        textViewNombre.setText(nombre);
        textViewDimension.setText(dimension);
        textViewAforo.setText(aforo);
        textViewDescripcion.setText(descripcion);

        btnAceptar = findViewById(R.id.btnaceptarSAIN);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(InformacionSala.this, Salas.class);
                startActivity(intent1);
            }
        });
    }
}
