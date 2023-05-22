package es.andresfusteralonso.gimnasio_fct;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InformacionHorario extends AppCompatActivity {
    private TextView getTextViewSala;
    private TextView textViewActividad;
    private TextView textViewDia;
    private TextView textViewHoraInicio;
    private TextView textViewHoraFin;
    private TextView textViewMonitor;
    private ImageButton btnAceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_horario);
        // Obtener referencias a los TextViews de la interfaz
        getTextViewSala = findViewById(R.id.txtSalaHOIN);
        textViewActividad = findViewById(R.id.txtActividadHOIN);
        textViewDia = findViewById(R.id.txtDiaHOIN);
        textViewHoraInicio = findViewById(R.id.txtHoraInicioHOIN);
        textViewHoraFin = findViewById(R.id.txtHorafinHOIN);
        textViewMonitor = findViewById(R.id.txtmonitorHOIN);

        // Obtener los datos del cliente actualizado de la actividad anterior
        Intent intent = getIntent();
        String sala = intent.getStringExtra("sala_nombre");
        String actividad = intent.getStringExtra("actividad_nombre");
        String dia = intent.getStringExtra("dia");
        String horaInicio = intent.getStringExtra("h_inicio");
        String horaFin = intent.getStringExtra("h_fin");
        String monitor = intent.getStringExtra("monitor_nombre");
        // Mostrar los datos en los TextViews de la interfaz
        getTextViewSala.setText(sala);
        textViewActividad.setText(actividad);
        textViewDia.setText(dia);
        textViewHoraInicio.setText(horaInicio);
        textViewHoraFin.setText(horaFin);
        textViewMonitor.setText(monitor);

        btnAceptar = findViewById(R.id.btnaceptarHOIN);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(InformacionHorario.this, Salas.class);
                startActivity(intent1);
            }
        });
    }
}
