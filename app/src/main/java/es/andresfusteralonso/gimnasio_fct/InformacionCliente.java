package es.andresfusteralonso.gimnasio_fct;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class InformacionCliente extends Activity {
    private TextView textViewNombre;
    private TextView textViewApellidos;
    private TextView textViewDNI;
    private TextView textViewTelefono;
    private TextView textViewCorreo;
    private TextView textViewSexo;
    private TextView textViewTarifa;
    private ImageButton btnAceptar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_cliente); // Asegúrate de que el diseño correcto esté inflado

        // Obtener referencias a los TextViews de la interfaz
        textViewNombre = findViewById(R.id.txtNombreCLIN);
        textViewApellidos = findViewById(R.id.txtApellidosCLIN);
        textViewDNI = findViewById(R.id.txtdniCLIN);
        textViewTelefono = findViewById(R.id.txtTelefonoCLIN);
        textViewCorreo = findViewById(R.id.txtCorreoCLIN);
        textViewSexo = findViewById(R.id.txtSexoCLIN);
        textViewTarifa = findViewById(R.id.txtTarifaCLIN);

        // Obtener los datos del cliente actualizado de la actividad anterior
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombreCL");
        String apellidos = intent.getStringExtra("apellidosCL");
        String dni = intent.getStringExtra("dniCL");
        String telefono = intent.getStringExtra("telefonoCL");
        String correo = intent.getStringExtra("correoCL");
        String sexo = intent.getStringExtra("sexoCL");
        String tarifa = intent.getStringExtra("tarifaCL");

        // Mostrar los datos en los TextViews de la interfaz
        textViewNombre.setText(nombre);
        textViewApellidos.setText(apellidos);
        textViewDNI.setText(dni);
        textViewTelefono.setText(telefono);
        textViewCorreo.setText(correo);
        textViewSexo.setText(sexo);
        textViewTarifa.setText(tarifa);

        btnAceptar = findViewById(R.id.btnaceptarCLIN);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(InformacionCliente.this, Clientes.class);
                startActivity(intent1);
            }
        });
    }
}
