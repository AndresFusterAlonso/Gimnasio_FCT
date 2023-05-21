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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import es.andresfusteralonso.gimnasio_fct.db.DbHelper;

public class actividades extends Activity {
    private SQLiteDatabase database;
    private ListView listView;
    private EditText editTextNombre;
    private EditText editTextApellidos;
    private EditText editTextDNI;
    private Button btnInsertar;
    private ImageButton buttonAddActividad;
    private ImageButton btnAtras;
    private DbHelper DB;
    private ListView listViewActividades;
    private ArrayAdapter<Actividad> adapterActividades;
    private ArrayList<Actividad> listaActividades;

    private class ActividadesAdapter extends ArrayAdapter<Actividad> {

        public ActividadesAdapter(Context context, ArrayList<Actividad> actividads) {
            super(context, 0, actividads);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Obtener el objeto Cliente correspondiente
            Actividad actividad = getItem(position);

            // Inflar la vista personalizada para cada elemento en la lista
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_item_actividad, parent, false);
            }

            // Obtener los textviews para mostrar el nombre y el correo electrónico del cliente
            TextView nombreTextView = convertView.findViewById(R.id.item_nombre);
            TextView TipoTextView = convertView.findViewById(R.id.item_tipo);
            TextView DescripcionTextView = convertView.findViewById(R.id.item_descripcion);
            TextView sexoTextView = convertView.findViewById(R.id.item_sexo);

            // Configurar el contenido de los textviews con los valores de nombre y correo electrónico del cliente
            nombreTextView.setText(actividad.getNombreAC());
            TipoTextView.setText(actividad.getTipoAC());
            DescripcionTextView.setText(actividad.getDescripcionAC());
            ImageButton btnVerInfo = convertView.findViewById(R.id.btnInformacionAC);
            btnVerInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Crea un Intent para iniciar la actividad de información detallada
                    Intent intent = new Intent(getContext(), InformacionActividades.class);

                    // Pasa los datos necesarios a través del Intent
                    intent.putExtra("nombreAC", actividad.getNombreAC());
                    intent.putExtra("tipoAC", actividad.getTipoAC());
                    intent.putExtra("descripcionAC", actividad.getDescripcionAC());

                    // Inicia la actividad de información detallada
                    getContext().startActivity(intent);
                }
            });

            ImageButton btnEditar = convertView.findViewById(R.id.btnEditarAC);
            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Crear un Intent para abrir la actividad de edición
                    Intent intent = new Intent(getContext(), EditarActividad.class);

                    // Pasar los datos del cliente a la actividad de edición
                    intent.putExtra("idAC", actividad.getIdAC());
                    intent.putExtra("nombreAC", actividad.getNombreAC());
                    intent.putExtra("tipoAC", actividad.getTipoAC());
                    intent.putExtra("descripcionAC", actividad.getDescripcionAC());

                    // Iniciar la actividad de edición
                    getContext().startActivity(intent);
                }
            });

            return convertView;
        }
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividades);

        DB = new DbHelper(this);
        SQLiteDatabase db = DB.getReadableDatabase();
        listViewActividades = findViewById(R.id.listActividades);
        listaActividades = new ArrayList<Actividad>();
        adapterActividades = new actividades.ActividadesAdapter(this, listaActividades);
        listViewActividades.setAdapter(adapterActividades);
        buttonAddActividad = findViewById(R.id.btnAltaAC);
        btnAtras = findViewById(R.id.btnAtrasAC);


// Realizar una consulta para obtener los datos de los clientes
        String[] projection = {
                "idAC",
                "nombreAC",
                "tipoAC",
                "descripcionAC"
        };
        Cursor cursor = db.query(
                "actividades",   // nombre de la tabla
                projection,   // columnas que se desean obtener
                null,         // cláusula WHERE (se seleccionan todos los registros)
                null,         // valores para la cláusula WHERE
                null,         // agrupamiento (no se agrupa)
                null,         // filtros de agrupamiento (no se filtra)
                null          // ordenamiento (no se ordena)
        );
        while (cursor.moveToNext()) {
            Actividad actividad = new Actividad();
            actividad.setIdAC(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("idAC"))));
            actividad.setNombreAC(cursor.getString(cursor.getColumnIndexOrThrow("nombreAC")));
            actividad.setTipoAC(cursor.getString(cursor.getColumnIndexOrThrow("tipoAC")));
            actividad.setDescripcionAC(cursor.getString(cursor.getColumnIndexOrThrow("descripcionAC")));
            listaActividades.add(actividad);
        }
        cursor.close();
        db.close();
        adapterActividades.notifyDataSetChanged();

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), MenuPrincipal.class);
                startActivity(intent2);
            }
        });

        buttonAddActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Alta_Actividades.class);
                startActivity(intent);
            }
        });

    }
}
