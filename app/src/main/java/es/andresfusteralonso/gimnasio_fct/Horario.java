package es.andresfusteralonso.gimnasio_fct;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.andresfusteralonso.gimnasio_fct.db.DbHelper;

public class Horario extends AppCompatActivity {
    private Context context;
    private TableLayout TablaHorario;
    private ImageButton BtnAtrasHO;
    private DbHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario);
        DB = new DbHelper(this);
        SQLiteDatabase db = DB.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM horarios", null);
        TablaHorario = findViewById(R.id.tablaHorario);


        if (cursor.moveToFirst()) {
            do {
                String dia = cursor.getString(cursor.getColumnIndex("dia"));
                String horaInicio = cursor.getString(cursor.getColumnIndex("h_inicio"));
                String horaFin = cursor.getString(cursor.getColumnIndex("h_fin"));

                // Crear un nuevo TextView para cada horario
                TextView tvHorario = new TextView(this);
                tvHorario.setText("Día: " + dia + ", Hora inicio: " + horaInicio + ", Hora fin: " + horaFin);

                // Agregar el nuevo TextView a la tabla
                TableRow row = new TableRow(this);
                row.addView(tvHorario);
                TablaHorario.addView(row);
            } while (cursor.moveToNext());
            cursor.close();
        }
            cursor.close();

        }

    }