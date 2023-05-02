package es.andresfusteralonso.gimnasio_fct;

import android.util.Log;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

public class Controller {
    private String password;
    private String email;
    public void onSuccess(JSONArray result) {
        Log.i("INFO: ", result.toString());
        String email_db = null, password_db = null;
        try {
            JSONObject obj = result.getJSONObject(0);
            email_db = obj.getString("Correo");
            password_db = obj.getString("contrasena");
        } catch (Exception e) {
            Log.e("ERROR: ", e.getMessage());
        }

        if(password_db == password && email == email_db && email_db != null && password_db != null) {
            // TODO Pasamos al siguiente view
        } else {
            // TODO Pasamos al view de error
        }
    }

    public void onError(VolleyError error) {
        Log.e("ERROR: ", error.toString());

        // TODO Pantalla de error o lo que proceda
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
