package es.andresfusteralonso.gimnasio_fct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

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

                try {
                    // Conectar a la base de datos
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/gimnasio", "root", "root");

                // Crear la consulta SQL con el valor del campo de texto
                    String consulta = "SELECT * FROM clientes WHERE contrasena = ?";
                    PreparedStatement sentencia = conexion.prepareStatement(consulta);
                    sentencia.setString(1, contrasena);

                // Ejecutar la consulta
                    ResultSet resultado = sentencia.executeQuery();

                    // Crear la consulta SQL con el valor del campo de texto
                    String consulta1 = "SELECT * FROM monitores WHERE contrasena = ?";
                    PreparedStatement sentencia1 = conexion.prepareStatement(consulta);
                    sentencia.setString(1, contrasena);

                    // Ejecutar la consulta
                    ResultSet resultado1 = sentencia.executeQuery();

                    // Procesar los resultados de la consulta
                    if (resultado.next()) {
                        // El dato pertenece a la tabla clientes
                        Intent intent = new Intent(getApplicationContext(), Horario_Clientes.class);
                        startActivity(intent);
                    } else if (resultado1.next()) {
                        // El dato pertenece a la monitores
                        Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);
                        startActivity(intent);
                    } else {
                        // El dato no pertenece a ninguna de las tablas
                        System.out.println("El dato no pertenece a ninguna tabla");
                    }

                    // Cerrar la conexión, la declaración y el resultado
                    resultado.close();
                    resultado1.close();
                    sentencia.close();
                    sentencia1.close();
                    conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }




            }
        });
    }
}