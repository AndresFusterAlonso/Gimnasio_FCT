package es.andresfusteralonso.gimnasio_fct;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    public class ConnectionClass {
        String classs = "com.mysql.cj.jdbc.Driver";
        //String ip = "localhost";
        String ip = "10.0.2.2";
        String baseDatos = "gimnasio";
        String url = "jdbc:mysql://";
        String un = "root";
        String password = "root";


        @SuppressLint("NewApi")
        public Connection CONN() {
            /*
            Connection conn = null;
            try {
                 //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gimnasio", "root", "root");
                conn = DriverManager.getConnection(url + ip + ":3306/" + baseDatos, "root", "root");
            } catch (Exception e) {
                Log.e("Exception error", e.getMessage());
            }

            return conn;*/

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Connection conn = null;
            String ConnURL = null;
            try {

                Class.forName(classs);

                conn = DriverManager.getConnection(url + ip + ":3306/" + baseDatos, un, password);

            } catch (SQLException se) {
                Log.e("ERRO", se.getMessage());
            } catch (ClassNotFoundException e) {
                Log.e("ERRO", e.getMessage());
            } catch (Exception e) {
                Log.e("ERRO", e.getMessage());
            }
            return conn;



        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText campoContrasena = findViewById(R.id.txtPassword);
        String contrasena = campoContrasena.getText().toString();


        Button botonNavegar = findViewById(R.id.btnCuenta);
        botonNavegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                try {
                    Connection con = new ConnectionClass().CONN();
                    if (con != null) {
                        PreparedStatement statement = con.prepareStatement("select * from clientes");
                        ResultSet result = statement.executeQuery();
                        Log.println(Log.INFO, "INFO", "Correcto");
                    } else {
                        Log.e("ERROR:" ,"La conexión es null");
                    }
                } catch (Exception e) {
                    Log.e("ERROR: ", e.getMessage());
                }*/


                APIJson api = new APIJson();
                try {
                    JSONObject object = api.readJsonFromUrl("http://10.0.2.2:1337/monitores");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }



                /*
                Connection con;
                try {
                    // Conectar a la base de datos
                    con = (Connection) new ConnectionClass().CONN();

                    // Crear la consulta SQL con el valor del campo de texto
                    PreparedStatement consultaClientes = con.prepareStatement("SELECT gimnasio.* FROM clientes;");
                    consultaClientes.setString(1, contrasena);
                    ResultSet resultadoClientes = consultaClientes.executeQuery();

                    // Crear la consulta SQL con el valor del campo de texto
                    PreparedStatement consultaMonitores = con.prepareStatement("SELECT gimnasio.* FROM monitores;");
                    consultaMonitores.setString(1, contrasena);
                    ResultSet resultadoMonitores = consultaClientes.executeQuery();



                    // Procesar los resultados de la consulta
                    if (resultadoClientes.next()) {
                        // El dato pertenece a la tabla clientes
                        Intent intent = new Intent(getApplicationContext(), Horario_Clientes.class);
                        startActivity(intent);
                    } else if (resultadoMonitores.next()) {
                        // El dato pertenece a la tabla monitores
                        Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);
                        startActivity(intent);
                    }

                    // Cerrar la conexión, la declaración y el resultado
                    resultadoClientes.close();
                    consultaClientes.close();
                    resultadoMonitores.close();
                    consultaMonitores.close();
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }
        });
    }


}