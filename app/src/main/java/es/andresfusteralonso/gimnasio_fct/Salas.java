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

public class Salas extends Activity {
    private SQLiteDatabase database;
    private ListView listView;
    private EditText editTextNombre;
    private EditText editTextDimension;
    private EditText editTextAforo;
    private EditText editTextDescripcion;
    private ImageButton btnAtras;
    private ImageButton buttonAddSala;
    private Button BotonInformacion;
    private DbHelper DB;
    private ListView listViewSalas;
    private ArrayAdapter<Sala> adapterSalas;
    private ArrayList<Sala> listaSala;

    private class SalaAdapter extends ArrayAdapter<Sala> {

        public SalaAdapter(Context context, ArrayList<Sala> salas) {
            super(context, 0, salas);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Obtener el objeto Cliente correspondiente
            Sala salas = getItem(position);

            // Inflar la vista personalizada para cada elemento en la lista
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_item_salas, parent, false);
            }

            // Obtener los textviews para mostrar el nombre y el correo electrónico del cliente
            TextView nombreTextView = convertView.findViewById(R.id.item_nombre);
            TextView dimensionTextView = convertView.findViewById(R.id.item_dimension);
            TextView aforoTextView = convertView.findViewById(R.id.item_aforo);
            TextView descripcionTextView = convertView.findViewById(R.id.item_descripcion);

            // Configurar el contenido de los textviews con los valores de nombre y correo electrónico del cliente
            nombreTextView.setText(salas.getNombreSA());
            dimensionTextView.setText(salas.getDimension());
            aforoTextView.setText(salas.getAforo());
            descripcionTextView.setText(salas.getDescripcion());


            ImageButton btnVerInfo = convertView.findViewById(R.id.btnInformacionCL);
            btnVerInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Obtén los datos del elemento seleccionado


                    // Crea un Intent para iniciar la actividad de información detallada
                    Intent intent = new Intent(getApplicationContext(), InformacionSala.class);

                    // Pasa los datos necesarios a través del Intent
                    intent.putExtra("nombreSA", (nombreTextView.getText()));
                    intent.putExtra("dimension", (dimensionTextView.getText()));
                    intent.putExtra("aforo", (aforoTextView.getText()));
                    intent.putExtra("descripcion", (descripcionTextView.getText()));

                    // Inicia la actividad de información detallada
                    startActivity(intent);
                }
            });

            ImageButton btnEditar = convertView.findViewById(R.id.btnEditarCL);
            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Crear un Intent para abrir la actividad de edición
                    Intent intent = new Intent(getContext(), EditarSala.class);

                    // Pasar los datos del cliente a la actividad de edición
                    intent.putExtra("idSA", salas.getIdSA());
                    intent.putExtra("nombreSA", salas.getNombreSA());
                    intent.putExtra("descripcion", salas.getDescripcion());
                    intent.putExtra("aforo", salas.getAforo());
                    intent.putExtra("dimension", salas.getDimension());

                    // Iniciar la actividad de edición
                    getContext().startActivity(intent);
                }
            });

            ImageButton btnHorario = convertView.findViewById(R.id.HorarioSala);
            btnHorario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Crear un Intent para abrir la actividad de edición
                    Intent intent = new Intent(getContext(), Alta_Horario.class);
                    intent.putExtra("nombreSA", salas.getNombreSA());
                    // Iniciar la actividad de edición
                    getContext().startActivity(intent);
                }
            });

            return convertView;
        }
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salas);

        DB = new DbHelper(this);

        listViewSalas = findViewById(R.id.listSalas);
        listaSala = new ArrayList<Sala>();
        adapterSalas = new Salas.SalaAdapter(this, listaSala);
        listViewSalas.setAdapter(adapterSalas);
        buttonAddSala = findViewById(R.id.btnAltaSA);
        btnAtras = findViewById(R.id.btnAtrasSA);
        SQLiteDatabase db = DB.getReadableDatabase();

// Realizar una consulta para obtener los datos de los clientes
        String[] projection = {
                "idSA",
                "nombreSA",
                "dimension",
                "aforo",
                "descripcion"
        };
        Cursor cursor = db.query(
                "salas",   // nombre de la tabla
                projection,   // columnas que se desean obtener
                null,         // cláusula WHERE (se seleccionan todos los registros)
                null,         // valores para la cláusula WHERE
                null,         // agrupamiento (no se agrupa)
                null,         // filtros de agrupamiento (no se filtra)
                null          // ordenamiento (no se ordena)
        );
        while (cursor.moveToNext()) {
            Sala salas = new Sala();
            salas.setIdSA(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("idSA"))));
            salas.setNombreSA(cursor.getString(cursor.getColumnIndexOrThrow("nombreSA")));
            salas.setDimension(cursor.getString(cursor.getColumnIndexOrThrow("dimension")));
            salas.setAforo(cursor.getString(cursor.getColumnIndexOrThrow("aforo")));
            salas.setDescripcion(cursor.getString(cursor.getColumnIndexOrThrow("descripcion")));
            listaSala.add(salas);
        }
        cursor.close();
        db.close();
        adapterSalas.notifyDataSetChanged();

        buttonAddSala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Alta_Salas.class);
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

