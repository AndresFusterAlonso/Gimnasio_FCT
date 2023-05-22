package es.andresfusteralonso.gimnasio_fct;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipal extends AppCompatActivity {
    Button btnMenu, btnInventario, btnClientes, btnMonitores, btnsalas, btnHorario, btnActividades;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuprincipal);

        btnInventario = (Button) findViewById(R.id.btnInventario);
        btnClientes = (Button) findViewById(R.id.btnClientesMENU);
        btnMonitores = (Button) findViewById(R.id.btnMonitoresMENU);
        btnsalas = (Button) findViewById(R.id.btnsalasMENU);
        btnHorario = (Button) findViewById(R.id.btnHorario);
        btnActividades = (Button) findViewById(R.id.btnActividades);

        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Clientes.class);
                startActivity(intent);
            }
        });
        btnMonitores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Monitores.class);
                startActivity(intent);
            }
        });
        btnInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Inventario.class);
                startActivity(intent);
            }
        });
        btnsalas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Salas.class);
                startActivity(intent);
            }
        });
        btnHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Horario.class);
                startActivity(intent);
            }
        });
        btnActividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), actividades.class);
                startActivity(intent);
            }
        });
    }

}