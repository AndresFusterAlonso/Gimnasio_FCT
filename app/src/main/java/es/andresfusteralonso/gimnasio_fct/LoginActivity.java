package es.andresfusteralonso.gimnasio_fct;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.andresfusteralonso.gimnasio_fct.db.DbHelper;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnlogin;
    DbHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.signin1);
        DB = new DbHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Porfavor rellene todos los campos", Toast.LENGTH_LONG).show();
                else{
                    Boolean checkuserpass = DB.chekuserpassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(LoginActivity.this, "Log exitoso", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Credenciales invalidos", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}