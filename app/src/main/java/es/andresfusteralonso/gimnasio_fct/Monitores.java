package es.andresfusteralonso.gimnasio_fct;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import es.andresfusteralonso.gimnasio_fct.db.DbHelper;

public class Monitores extends Activity {
    private SQLiteDatabase database;
    private ListView listView;
    private EditText editTextNombre;
    private EditText editTextApellidos;
    private EditText editTextDNI;
    private EditText editTextTelefono;
    private EditText editTextCorreo;
    private RadioGroup radioGroupSexo;
    private EditText editTextContrato;
    private Button btnAtras;
    private Button buttonAddMonitor;
    private DbHelper DB;
    private ListView listViewMonitores;
    private ArrayAdapter<Monitor> adapterMonitores;
    private ArrayList<Monitor> listaMonitor;

    private class MonitorAdapter extends ArrayAdapter<Monitor> {

        public MonitorAdapter(Context context, ArrayList<Monitor> monitors) {
            super(context, 0, monitors);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Obtener el objeto Cliente correspondiente
            Monitor monitor = getItem(position);

            // Inflar la vista personalizada para cada elemento en la lista
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_item_monitores, parent, false);
            }

            // Obtener los textviews para mostrar el nombre y el correo electrónico del cliente
            TextView nombreTextView = convertView.findViewById(R.id.item_nombre);
            TextView correoTextView = convertView.findViewById(R.id.item_correo);
            TextView apellidosTextView = convertView.findViewById(R.id.item_apellidos);
            TextView dniTextView = convertView.findViewById(R.id.item_dni);
            TextView telefonoTextView = convertView.findViewById(R.id.item_telefono);
            TextView contratoTextView = convertView.findViewById(R.id.item_contrato);
            TextView sexoTextView = convertView.findViewById(R.id.item_sexo);

            // Configurar el contenido de los textviews con los valores de nombre y correo electrónico del cliente
            nombreTextView.setText(monitor.getNombreMO());
            correoTextView.setText(monitor.getCorreoMO());
            apellidosTextView.setText(monitor.getApellidosMO());
            dniTextView.setText(monitor.getDniMO());
            telefonoTextView.setText(monitor.getTelefonoMO());
            contratoTextView.setText(monitor.getContrato());
            sexoTextView.setText(monitor.getSexoMO());

            Button btnVerInfo = convertView.findViewById(R.id.btnInformacionCL);
            btnVerInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Crea un Intent para iniciar la actividad de información detallada
                    Intent intent = new Intent(getContext(), InformacionMonitor.class);

                    // Pasa los datos necesarios a través del Intent
                    intent.putExtra("nombreMO", monitor.getNombreMO());
                    intent.putExtra("apellidosMO", monitor.getApellidosMO());
                    intent.putExtra("dniMO", monitor.getDniMO());
                    intent.putExtra("telefonoMO", monitor.getTelefonoMO());
                    intent.putExtra("correoMO", monitor.getCorreoMO());
                    intent.putExtra("sexoMO", monitor.getSexoMO());
                    intent.putExtra("contrato", monitor.getContrato());

                    // Inicia la actividad de información detallada
                    getContext().startActivity(intent);
                }
            });

            Button btnEditar = convertView.findViewById(R.id.btnEditarCL);
            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Crear un Intent para abrir la actividad de edición
                    Intent intent = new Intent(getContext(), EditarMonitor.class);

                    // Pasar los datos del cliente a la actividad de edición
                    intent.putExtra("idMO", monitor.getIdMO());
                    intent.putExtra("nombreMO", monitor.getNombreMO());
                    intent.putExtra("apellidosMO", monitor.getApellidosMO());
                    intent.putExtra("dniMO", monitor.getDniMO());
                    intent.putExtra("telefonoMO", monitor.getTelefonoMO());
                    intent.putExtra("correoMO", monitor.getCorreoMO());
                    intent.putExtra("sexoMO", monitor.getSexoMO());
                    intent.putExtra("contrato", monitor.getContrato());

                    // Iniciar la actividad de edición
                    getContext().startActivity(intent);
                }
            });

            return convertView;
        }
        }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monitores);

        DB = new DbHelper(this);

        listViewMonitores = findViewById(R.id.listviewMO);
        listaMonitor = new ArrayList<Monitor>();
        adapterMonitores = new MonitorAdapter(this, listaMonitor);
        listViewMonitores.setAdapter(adapterMonitores);
        buttonAddMonitor = findViewById(R.id.btnAltaMO);
        btnAtras = findViewById(R.id.btnAtrasMO);
        SQLiteDatabase db = DB.getReadableDatabase();

// Realizar una consulta para obtener los datos de los clientes
        String[] projection = {
                "idMO",
                "nombreMO",
                "apellidosMO",
                "dniMO",
                "telefonoMO",
                "correoMO",
                "sexoMO",
                "contrato"
        };
        Cursor cursor = db.query(
                "monitores",   // nombre de la tabla
                projection,   // columnas que se desean obtener
                null,         // cláusula WHERE (se seleccionan todos los registros)
                null,         // valores para la cláusula WHERE
                null,         // agrupamiento (no se agrupa)
                null,         // filtros de agrupamiento (no se filtra)
                null          // ordenamiento (no se ordena)
        );
        while (cursor.moveToNext()) {
            Monitor monitor = new Monitor();
            monitor.setIdMO(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("idMO"))));
            monitor.setNombreMO(cursor.getString(cursor.getColumnIndexOrThrow("nombreMO")));
            monitor.setCorreoMO(cursor.getString(cursor.getColumnIndexOrThrow("correoMO")));
            monitor.setApellidosMO(cursor.getString(cursor.getColumnIndexOrThrow("apellidosMO")));
            monitor.setDniMO(cursor.getString(cursor.getColumnIndexOrThrow("dniMO")));
            monitor.setTelefonoMO(cursor.getString(cursor.getColumnIndexOrThrow("telefonoMO")));
            monitor.setSexoMO(cursor.getString(cursor.getColumnIndexOrThrow("sexoMO")));
            monitor.setContrato(cursor.getString(cursor.getColumnIndexOrThrow("contrato")));
            listaMonitor.add(monitor);
        }
        cursor.close();
        db.close();
        adapterMonitores.notifyDataSetChanged();

        buttonAddMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Alta_Monitor.class);
                startActivity(intent);
            }
        });

        listViewMonitores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el cliente seleccionado
                Monitor monitor = (Monitor) parent.getItemAtPosition(position);

                // Crear un Intent para abrir la actividad de edición
                Intent intent = new Intent(Monitores.this, EditarMonitor.class);

                // Pasar los datos del cliente a la actividad de edición
                intent.putExtra("idMO", monitor.getIdMO());
                intent.putExtra("nombreMO", monitor.getNombreMO());
                intent.putExtra("apellidosMO", monitor.getApellidosMO());
                intent.putExtra("dniMO", monitor.getDniMO());
                intent.putExtra("telefonoMO", monitor.getTelefonoMO());
                intent.putExtra("correoMO", monitor.getCorreoMO());
                intent.putExtra("sexoMO", monitor.getSexoMO());
                intent.putExtra("contrato", monitor.getContrato());

                // Iniciar la actividad de edición
                startActivity(intent);
            }
        });
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), MenuPrincipal.class);
                startActivity(intent2);
            }
        });
    }
}
