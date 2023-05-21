package es.andresfusteralonso.gimnasio_fct;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InformacionProducto extends AppCompatActivity {
    private TextView textViewNombre;
    private TextView textViewTipo;
    private TextView textViewMarca;
    private TextView textViewModelo;
    private TextView textViewPrecio;
    private TextView textViewSala;
    private ImageButton btnAceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_producto);
        // Obtener referencias a los TextViews de la interfaz
        textViewNombre = findViewById(R.id.txtNombrePOIN);
        textViewTipo = findViewById(R.id.txtTipoPOIN);
        textViewModelo = findViewById(R.id.txtModeloPOIN);
        textViewPrecio = findViewById(R.id.txtPrecioPOIN);
        textViewSala = findViewById(R.id.txtSalaPOIN);
        textViewMarca = findViewById(R.id.txtMarcaPOIN);

        // Obtener los datos del cliente actualizado de la actividad anterior
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombrePO");
        String tipoPO = intent.getStringExtra("tipoPO");
        String marca = intent.getStringExtra("marca");
        String modelo = intent.getStringExtra("modelo");
        String sala = intent.getStringExtra("sala_id");
        String precio = intent.getStringExtra("precio");

        // Mostrar los datos en los TextViews de la interfaz
        textViewNombre.setText(nombre);
        textViewTipo.setText(tipoPO);
        textViewMarca.setText(marca);
        textViewModelo.setText(modelo);
        textViewSala.setText(sala);
        textViewPrecio.setText(precio);

        btnAceptar = findViewById(R.id.btnaceptarPOIN);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(InformacionProducto.this, Inventario.class);
                startActivity(intent1);
            }
        });
    }
}
