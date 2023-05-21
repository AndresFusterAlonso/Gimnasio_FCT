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
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import es.andresfusteralonso.gimnasio_fct.db.DbHelper;

public class Clientes extends Activity {
    private SQLiteDatabase database;
    private ListView listView;
    private EditText editTextNombre;
    private EditText editTextApellidos;
    private EditText editTextDNI;
    private EditText editTextTelefono;
    private EditText editTextCorreo;
    private RadioGroup radioGroupSexo;
    private EditText editTextTarifa;
    private Button btnInsertar;
    private ImageButton buttonAddClient;
    private ImageButton btnAtras;
    private DbHelper DB;
    private ListView listViewClientes;
    private ArrayAdapter<Cliente> adapterClientes;
    private ArrayList<Cliente> listaClientes;

    private class ClienteAdapter extends ArrayAdapter<Cliente> {

        public ClienteAdapter(Context context, ArrayList<Cliente> clientes) {
            super(context, 0, clientes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Obtener el objeto Cliente correspondiente
            Cliente cliente = getItem(position);

            // Inflar la vista personalizada para cada elemento en la lista
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_item_clientes, parent, false);
            }

            // Obtener los textviews para mostrar el nombre y el correo electrónico del cliente
            TextView nombreTextView = convertView.findViewById(R.id.item_nombre);
            TextView correoTextView = convertView.findViewById(R.id.item_correo);
            TextView apellidosTextView = convertView.findViewById(R.id.item_apellidos);
            TextView dniTextView = convertView.findViewById(R.id.item_dni);
            TextView telefonoTextView = convertView.findViewById(R.id.item_telefono);
            TextView tarifaTextView = convertView.findViewById(R.id.item_tarifa);
            TextView sexoTextView = convertView.findViewById(R.id.item_sexo);

            // Configurar el contenido de los textviews con los valores de nombre y correo electrónico del cliente
            nombreTextView.setText(cliente.getNombre());
            correoTextView.setText(cliente.getCorreo());
            apellidosTextView.setText(cliente.getApellidos());
            dniTextView.setText(cliente.getDni());
            telefonoTextView.setText(cliente.getTelefono());
            tarifaTextView.setText(cliente.getTarifa());
            sexoTextView.setText(cliente.getSexo());

            ImageButton btnVerInfo = convertView.findViewById(R.id.btnInformacionCL);
            btnVerInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Crea un Intent para iniciar la actividad de información detallada
                    Intent intent = new Intent(getContext(), InformacionCliente.class);

                    // Pasa los datos necesarios a través del Intent
                    intent.putExtra("nombreCL", cliente.getNombre());
                    intent.putExtra("apellidosCL", cliente.getApellidos());
                    intent.putExtra("dniCL", cliente.getDni());
                    intent.putExtra("telefonoCL", cliente.getTelefono());
                    intent.putExtra("correoCL", cliente.getCorreo());
                    intent.putExtra("sexoCL", cliente.getSexo());
                    intent.putExtra("tarifaCL", cliente.getTarifa());

                    // Inicia la actividad de información detallada
                    getContext().startActivity(intent);
                }
            });

            ImageButton btnEditar = convertView.findViewById(R.id.btnEditarCL);
            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Crear un Intent para abrir la actividad de edición
                    Intent intent = new Intent(getContext(), EditarCliente.class);

                    // Pasar los datos del cliente a la actividad de edición
                    intent.putExtra("idCL", cliente.getId());
                    intent.putExtra("nombreCL", cliente.getNombre());
                    intent.putExtra("apellidosCL", cliente.getApellidos());
                    intent.putExtra("dniCL", cliente.getDni());
                    intent.putExtra("telefonoCL", cliente.getTelefono());
                    intent.putExtra("correoCL", cliente.getCorreo());
                    intent.putExtra("sexoCL", cliente.getSexo());
                    intent.putExtra("tarifaCL", cliente.getTarifa());

                    // Iniciar la actividad de edición
                    getContext().startActivity(intent);
                }
            });

            return convertView;
        }
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientes);

        DB = new DbHelper(this);
        SQLiteDatabase db = DB.getReadableDatabase();
        listViewClientes = findViewById(R.id.listClientes);
        listaClientes = new ArrayList<Cliente>();
        adapterClientes = new ClienteAdapter(this, listaClientes);
        listViewClientes.setAdapter(adapterClientes);
        buttonAddClient = findViewById(R.id.btnAltaCL);
        btnAtras = findViewById(R.id.btnAtrasCL);


// Realizar una consulta para obtener los datos de los clientes
        String[] projection = {
                "idCL",
                "nombreCL",
                "apellidosCL",
                "dniCL",
                "telefonoCL",
                "sexoCL",
                "correoCL",
                "tarifa"
        };
        Cursor cursor = db.query(
                "clientes",   // nombre de la tabla
                projection,   // columnas que se desean obtener
                null,         // cláusula WHERE (se seleccionan todos los registros)
                null,         // valores para la cláusula WHERE
                null,         // agrupamiento (no se agrupa)
                null,         // filtros de agrupamiento (no se filtra)
                null          // ordenamiento (no se ordena)
        );
        while (cursor.moveToNext()) {
            Cliente cliente = new Cliente();
            cliente.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("idCL"))));
            cliente.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombreCL")));
            cliente.setCorreo(cursor.getString(cursor.getColumnIndexOrThrow("correoCL")));
            cliente.setApellidos(cursor.getString(cursor.getColumnIndexOrThrow("apellidosCL")));
            cliente.setDni(cursor.getString(cursor.getColumnIndexOrThrow("dniCL")));
            cliente.setTelefono(cursor.getString(cursor.getColumnIndexOrThrow("telefonoCL")));
            cliente.setSexo(cursor.getString(cursor.getColumnIndexOrThrow("sexoCL")));
            cliente.setTarifa(cursor.getString(cursor.getColumnIndexOrThrow("tarifa")));
            listaClientes.add(cliente);
        }
        cursor.close();
        db.close();
        adapterClientes.notifyDataSetChanged();

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), MenuPrincipal.class);
                startActivity(intent2);
            }
        });

        buttonAddClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Alta_Cliente.class);
                startActivity(intent);
            }
        });

    }
}



      /*listViewClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el cliente seleccionado
                Cliente cliente = (Cliente) parent.getItemAtPosition(position);

                // Crear un Intent para abrir la actividad de edición
                Intent intent = new Intent(Clientes.this, EditarCliente.class);

                // Pasar los datos del cliente a la actividad de edición
                intent.putExtra("idCL", cliente.getId());
                intent.putExtra("nombreCL", cliente.getNombre());
                intent.putExtra("apellidosCL", cliente.getApellidos());
                intent.putExtra("dniCL", cliente.getDni());
                intent.putExtra("telefonoCL", cliente.getTelefono());
                intent.putExtra("correoCL", cliente.getCorreo());
                intent.putExtra("sexoCL", cliente.getSexo());
                intent.putExtra("tarifaCL", cliente.getTarifa());

                // Iniciar la actividad de edición
                startActivity(intent);
            }
        });*/