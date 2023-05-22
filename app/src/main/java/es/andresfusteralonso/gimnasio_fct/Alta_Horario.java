package es.andresfusteralonso.gimnasio_fct;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import es.andresfusteralonso.gimnasio_fct.db.DbHelper;

public class Alta_Horario extends AppCompatActivity {
    private String opcionSeleccionadaActividades;
    private String opcionSeleccionadaDias;
    private String opcionSeleccionadaMonitores;
    private TextView TextViewSalas;
    private EditText EditTextHoraIncio, EditTextHoraFin;
    private ImageButton buttonGuardar;
    private ImageButton buttonCancelar;
    private DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_horario);

        DB = new DbHelper(this);
        TextViewSalas = findViewById(R.id.Sala_idHO);
        EditTextHoraIncio = findViewById(R.id.edHoraInicioHO);
        EditTextHoraFin = findViewById(R.id.edHoraFinHO);
        buttonGuardar = findViewById(R.id.btnAceptarHO);
        buttonCancelar = findViewById(R.id.btnCancelarHO);
        Spinner spinnerActividades = findViewById(R.id.spinnerActividadHO);

        Intent intent = getIntent();
        String nombreSA = intent.getStringExtra("nombreSA");
        TextViewSalas.setText(nombreSA);

        ArrayList<String> datosSpinnerActividades = new ArrayList<>();
        ArrayList<String> datosSpinnerMonitores = new ArrayList<>();
        ArrayList<String> datosSpinnerDias = new ArrayList<>();
        // Obtén una instancia de SQLiteDatabase
        SQLiteDatabase db = DB.getReadableDatabase();

        Spinner spinnerMonitores = findViewById(R.id.spinnerMonitoresHO);

        // Realiza la consulta a la tabla "clientes" para obtener los nombres
        Cursor cursor2 = db.rawQuery("SELECT nombreMO FROM monitores", null);

        // Recorre el cursor y agrega los nombres al ArrayList
        if (cursor2.moveToFirst()) {
            do {
                int columnIndex = cursor2.getColumnIndex("nombreMO");
                if (columnIndex != -1) {
                    String nombre = cursor2.getString(columnIndex);
                    datosSpinnerMonitores.add(nombre);
                }
            } while (cursor2.moveToNext());
        }
        // Cierra el cursor y la conexión a la base de datos
        cursor2.close();
        db.close();

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, datosSpinnerMonitores);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonitores.setAdapter(adapter2);

        spinnerMonitores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                opcionSeleccionadaMonitores = parent.getItemAtPosition(position).toString();
                ContentValues values = new ContentValues();
                values.put("opcion", opcionSeleccionadaMonitores);

                Toast.makeText(Alta_Horario.this, "Opción seleccionada: " + opcionSeleccionadaMonitores, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Alta_Horario.this, "Selecciona una opción", Toast.LENGTH_SHORT).show();
            }
        });

        SQLiteDatabase db2 = DB.getReadableDatabase();
        // Realiza la consulta a la tabla "actividades" para obtener los nombres
        Cursor cursor = db2.rawQuery("SELECT nombreAC FROM actividades", null);

        // Recorre el cursor y agrega los nombres al ArrayList
        if (cursor.moveToFirst()) {
            do {
                int columnIndex = cursor.getColumnIndex("nombreAC");
                if (columnIndex != -1) {
                    String nombre = cursor.getString(columnIndex);
                    datosSpinnerActividades.add(nombre);
                }
            } while (cursor.moveToNext());
        }
        // Cierra el cursor y la conexión a la base de datos
        cursor.close();
        db2.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, datosSpinnerActividades);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerActividades.setAdapter(adapter);

        spinnerActividades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                opcionSeleccionadaActividades = parent.getItemAtPosition(position).toString();
                ContentValues values = new ContentValues();
                values.put("opcion", opcionSeleccionadaActividades);

                Toast.makeText(Alta_Horario.this, "Opción seleccionada: " + opcionSeleccionadaActividades, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                opcionSeleccionadaActividades = null;
                Toast.makeText(Alta_Horario.this, "Selecciona una opción", Toast.LENGTH_SHORT).show();

            }
        });


        Spinner spinnerDias = findViewById(R.id.spinnerDiaHO);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.diasdelasemana, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDias.setAdapter(adapter3);

        spinnerDias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                opcionSeleccionadaDias = parent.getItemAtPosition(position).toString();
                ContentValues values = new ContentValues();
                values.put("opcion", opcionSeleccionadaDias);

                Toast.makeText(Alta_Horario.this, "Opción seleccionada: " + opcionSeleccionadaDias, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                opcionSeleccionadaDias = null;
                Toast.makeText(Alta_Horario.this, "Selecciona una opción", Toast.LENGTH_SHORT).show();

            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sala = TextViewSalas.getText().toString();
                String actividades = opcionSeleccionadaActividades;
                String dia = opcionSeleccionadaDias;
                String HoraInicio = EditTextHoraIncio.getText().toString();
                String HoraFin = EditTextHoraFin.getText().toString();
                String monitores = opcionSeleccionadaMonitores;
                Boolean insert = DB.addHorario(sala, actividades, dia, HoraInicio, HoraFin, monitores);
                Intent intent = new Intent(getApplicationContext(), InformacionProducto.class);
                intent.putExtra("sala_nombre", sala);
                intent.putExtra("actividad_nombre", actividades);
                intent.putExtra("dia", dia);
                intent.putExtra("h_inicio", HoraInicio);
                intent.putExtra("h_fin", HoraFin);
                intent.putExtra("monitor_nombre", monitores);
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