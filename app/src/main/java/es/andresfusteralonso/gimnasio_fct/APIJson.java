package es.andresfusteralonso.gimnasio_fct;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class APIJson {
    public JSONObject readJsonFromUrl(String url_string) throws IOException, JSONException {
        URL url = new URL(url_string);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int response_code = conn.getResponseCode();

        if(response_code != 200) {
            throw new RuntimeException("HttpResponseCode: " + response_code);
        } else {
            String inline = "";
            Scanner scanner = new Scanner(url.openStream());

            // Escribe toda la información a un string
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }

            scanner.close();

            JSONObject jsonObject = new JSONObject(inline);

            return jsonObject;
        }
    }
}

