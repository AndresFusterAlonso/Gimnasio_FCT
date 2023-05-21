package es.andresfusteralonso.gimnasio_fct;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class InformacionMonitor extends Activity {
    private TextView textViewNombre;
    private TextView textViewApellidos;
    private TextView textViewDNI;
    private TextView textViewTelefono;
    private TextView textViewCorreo;
    private TextView textViewSexo;
    private TextView textViewContrato;
    private ImageButton btnAceptar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_monitor); // Asegúrate de que el diseño correcto esté inflado

        // Obtener referencias a los TextViews de la interfaz
        textViewNombre = findViewById(R.id.txtNombreMOIN);
        textViewApellidos = findViewById(R.id.txtApellidosMOIN);
        textViewDNI = findViewById(R.id.txtdniMOIN);
        textViewTelefono = findViewById(R.id.txtTelefonoMOIN);
        textViewCorreo = findViewById(R.id.txtCorreoMOIN);
        textViewSexo = findViewById(R.id.txtSexoMOIN);
        textViewContrato = findViewById(R.id.txtContratoMOIN);

        // Obtener los datos del cliente actualizado de la actividad anterior
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombreMO");
        String apellidos = intent.getStringExtra("apellidosMO");
        String dni = intent.getStringExtra("dniMO");
        String telefono = intent.getStringExtra("telefonoMO");
        String correo = intent.getStringExtra("correoMO");
        String sexo = intent.getStringExtra("sexoMO");
        String contrato = intent.getStringExtra("contrato");

        // Mostrar los datos en los TextViews de la interfaz
        textViewNombre.setText(nombre);
        textViewApellidos.setText(apellidos);
        textViewDNI.setText(dni);
        textViewTelefono.setText(telefono);
        textViewCorreo.setText(correo);
        textViewSexo.setText(sexo);
        textViewContrato.setText(contrato);

        btnAceptar = findViewById(R.id.btnaceptarMOIN);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(InformacionMonitor.this, Monitores.class);
                startActivity(intent1);
            }
        });
    }
}
