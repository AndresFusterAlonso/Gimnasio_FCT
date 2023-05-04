package es.andresfusteralonso.gimnasio_fct;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.andresfusteralonso.gimnasio_fct.db.DbHelper;


public class MainActivity extends AppCompatActivity {

        EditText username, password, repassword;
        Button signup, signin;
        DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.signup);
        signin = (Button) findViewById(R.id.signin);
        DB = new DbHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(MainActivity.this, "Porfavor rellene todos los campos", Toast.LENGTH_LONG).show();
                else{
                    if (pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(MainActivity.this, "Registro exitoso", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(MainActivity.this, "Registro fallido", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "El usuario ya existe! Porfavor continue", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Las contraseñas no son iguales", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}