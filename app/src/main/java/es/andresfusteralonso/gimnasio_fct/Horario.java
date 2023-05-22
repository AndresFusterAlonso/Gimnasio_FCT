package es.andresfusteralonso.gimnasio_fct;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
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
                String dia = cursor.getString(cursor.getColumnIndexOrThrow("dia"));
                String horaInicio = cursor.getString(cursor.getColumnIndexOrThrow("h_inicio"));
                String horaFin = cursor.getString(cursor.getColumnIndexOrThrow("h_fin"));
                String Actividad = cursor.getString(cursor.getColumnIndexOrThrow("actividad_nombre"));

                // Crear un nuevo TableRow
                TableRow row = new TableRow(this);

                // Crear TextViews para los datos del horario
                TextView tvDia = new TextView(this);
                tvDia.setText(dia);
                tvDia.setTextColor(Color.WHITE);
                TableRow.LayoutParams paramsDia = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
                paramsDia.weight = 1;
                paramsDia.setMargins(80, 8, 8, 8); // Ajusta los márgenes aquí
                tvDia.setLayoutParams(paramsDia);

                TextView tvHoraInicio = new TextView(this);
                tvHoraInicio.setText(horaInicio);
                tvHoraInicio.setTextColor(Color.WHITE);
                TableRow.LayoutParams paramsHoraInicio = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
                paramsHoraInicio.weight = 1;
                paramsHoraInicio.setMargins(8, 8, 8, 8); // Ajusta los márgenes aquí
                tvHoraInicio.setLayoutParams(paramsHoraInicio);

                TextView tvHoraFin = new TextView(this);
                tvHoraFin.setText(horaFin);
                tvHoraFin.setTextColor(Color.WHITE);
                TableRow.LayoutParams paramsHoraFin = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
                paramsHoraFin.weight = 1;
                paramsHoraFin.setMargins(58, 8, 8, 8); // Ajusta los márgenes aquí
                tvHoraFin.setLayoutParams(paramsHoraFin);

                TextView tvActividad = new TextView(this);
                tvActividad.setText(Actividad);
                tvActividad.setTextColor(Color.WHITE);
                TableRow.LayoutParams paramsActividad = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
                paramsActividad.weight = 1;
                paramsActividad.setMargins(0, 8, 0, 8); // Ajusta los márgenes aquí
                tvActividad.setLayoutParams(paramsActividad);

                // Añadir los TextViews al TableRow
                row.addView(tvDia);
                row.addView(tvHoraInicio);
                row.addView(tvHoraFin);
                row.addView(tvActividad);
                // Añadir el TableRow al TableLayout
                TablaHorario.addView(row);

            } while (cursor.moveToNext());
            cursor.close();
        }

    }
}