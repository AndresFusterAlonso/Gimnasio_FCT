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
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import es.andresfusteralonso.gimnasio_fct.db.DbHelper;

public class Inventario extends Activity {
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
    private Button buttonAddProducto;
    private Button btnAtras;
    private DbHelper DB;
    private ListView listViewProducto;
    private ArrayAdapter<Producto> adapterProductos;
    private ArrayList<Producto> listaProductos;

    private class adapterProductos extends ArrayAdapter<Producto> {

        public adapterProductos(Context context, ArrayList<Producto> productos) {
            super(context, 0, productos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Obtener el objeto Cliente correspondiente
            Producto productos = getItem(position);

            // Inflar la vista personalizada para cada elemento en la lista
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_item_productos, parent, false);
            }

            // Obtener los textviews para mostrar el nombre y el correo electrónico del cliente
            TextView nombreTextView = convertView.findViewById(R.id.item_nombre);
            TextView tipoTextView = convertView.findViewById(R.id.item_tipo);
            TextView salaTextView = convertView.findViewById(R.id.item_sala);
            TextView marcaTextView = convertView.findViewById(R.id.item_marca);
            TextView modeloTextView = convertView.findViewById(R.id.item_modelo);
            TextView precioTextView = convertView.findViewById(R.id.item_precio);



            // Configurar el contenido de los textviews con los valores de nombre y correo electrónico del cliente
            nombreTextView.setText(productos.getNombrePO());
            tipoTextView.setText(productos.getTipoPO());
            marcaTextView.setText(productos.getMarca());
            modeloTextView.setText(productos.getModelo());
            precioTextView.setText(productos.getPrecio());

            salaTextView.setText(productos.getSala());

            Button btnVerInfo = convertView.findViewById(R.id.btnInformacionCL);
            btnVerInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Obtén los datos del elemento seleccionado


                    // Crea un Intent para iniciar la actividad de información detallada
                    Intent intent = new Intent(getApplicationContext(), InformacionProducto.class);

                    // Pasa los datos necesarios a través del Intent
                    intent.putExtra("nombrePO", (nombreTextView.getText()));
                    intent.putExtra("marca", (marcaTextView.getText()));
                    intent.putExtra("precio", (precioTextView.getText()));
                    intent.putExtra("modelo", (modeloTextView.getText()));
                    intent.putExtra("tipoPO", (tipoTextView.getText()));
                    intent.putExtra("sala_id", (salaTextView.getText()));

                    // Inicia la actividad de información detallada
                    startActivity(intent);
                }
            });

            Button btnEditar = convertView.findViewById(R.id.btnEditarCL);
            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Crear un Intent para abrir la actividad de edición
                    Intent intent = new Intent(getContext(), EditarProducto.class);

                    // Pasar los datos del cliente a la actividad de edición
                    intent.putExtra("idPO", productos.getNombrePO());
                    intent.putExtra("nombrePO", productos.getNombrePO());
                    intent.putExtra("tipoPO", productos.getTipoPO());
                    intent.putExtra("sala_id", productos.getSala());
                    intent.putExtra("marca", productos.getMarca());
                    intent.putExtra("modelo", productos.getModelo());
                    intent.putExtra("precio", productos.getPrecio());
                    // Iniciar la actividad de edición
                    getContext().startActivity(intent);
                }
            });

            return convertView;
        }
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventario);

        DB = new DbHelper(this);
        SQLiteDatabase db = DB.getReadableDatabase();
        listViewProducto = findViewById(R.id.listSalas);
        listaProductos = new ArrayList<Producto>();
        adapterProductos = new adapterProductos(this, listaProductos);
        listViewProducto.setAdapter(adapterProductos);
        buttonAddProducto = findViewById(R.id.btnAltaSA);
        btnAtras = findViewById(R.id.btnAtrasSA);


// Realizar una consulta para obtener los datos de los clientes
        String[] projection = {
                "idPO",
                "nombrePO",
                "tipoPO",
                "sala_id",
                "modelo",
                "marca",
                "precio"
        };
        Cursor cursor = db.query(
                "inventario",   // nombre de la tabla
                projection,   // columnas que se desean obtener
                null,         // cláusula WHERE (se seleccionan todos los registros)
                null,         // valores para la cláusula WHERE
                null,         // agrupamiento (no se agrupa)
                null,         // filtros de agrupamiento (no se filtra)
                null          // ordenamiento (no se ordena)
        );
        while (cursor.moveToNext()) {
            Producto producto = new Producto();
            producto.setIdPO(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("idPO"))));
            producto.setNombrePO(cursor.getString(cursor.getColumnIndexOrThrow("nombrePO")));
            producto.setMarca(cursor.getString(cursor.getColumnIndexOrThrow("marca")));
            producto.setModelo(cursor.getString(cursor.getColumnIndexOrThrow("modelo")));
            producto.setPrecio(cursor.getString(cursor.getColumnIndexOrThrow("precio")));
            producto.setTipoPO(cursor.getString(cursor.getColumnIndexOrThrow("tipoPO")));
            producto.setSala(cursor.getString(cursor.getColumnIndexOrThrow("sala_id")));
            listaProductos.add(producto);
        }
        cursor.close();
        db.close();
        adapterProductos.notifyDataSetChanged();

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), MenuPrincipal.class);
                startActivity(intent2);
            }
        });

        buttonAddProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Alta_Producto.class);
                startActivity(intent);
            }
        });
    }
}
