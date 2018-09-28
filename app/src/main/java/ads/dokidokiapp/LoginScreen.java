package ads.dokidokiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import android.view.View;
import android.os.AsyncTask;
import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LoginScreen extends AppCompatActivity {

    public static final String EXTRA_IDCLIENTE = "ads.dokidokiapp.EXTRA_IDCLIENTE";
    public static final String EXTRA_NOMBRECLIENTE = "ads.dokidokiapp.EXTRA_NOMBRECLIENTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        final EditText idField = findViewById(R.id.clientIdField);
        final EditText passField = findViewById(R.id.clientPassField);
        final Button loginButton = findViewById(R.id.loginButton);

        idField.setTransformationMethod(null);

        loginButton.setOnClickListener(new View.OnClickListener() {
            private String id = idField.getText().toString();
            @Override
            public void onClick(View view) {
                new LoginTask(id, view.getContext()).execute();
            }
        });
    }

    private class LoginTask extends AsyncTask<String, String, String>{

        private String id;
        private Context context;

        private LoginTask(String id, Context context) {
            this.id = id;
            this.context = context;
        }

        @Override
        protected String doInBackground(String... strings) {
            String result = null;
            try {
                // result = RESTService.makeGetRequest("http://127.0.0.1/rest/cliente/id/" + this.id);
                result = RESTService.makeGetRequest("http://www.mocky.io/v2/5b22511f2e00006500e31619");
            } catch (IOException e) {
                Log.d("INFO", e.toString());
            }
            if (result != null)
                Log.d("INFO", result.toString());
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null)
                if (result.length() > 0) {
                    try {
                        JSONObject cliente = new JSONObject(result);
                        Integer idCliente = cliente.getInt("id");
                        String nombreCliente = cliente.getString("nombre");
                        Intent intent = new Intent(this.context, MainScreen.class);
                        intent.putExtra(LoginScreen.EXTRA_IDCLIENTE, idCliente);
                        intent.putExtra(LoginScreen.EXTRA_NOMBRECLIENTE, nombreCliente);
                        context.startActivity(intent);
                    } catch (JSONException e) {
                    }
                } else {
                    EditText idField = findViewById(R.id.clientIdField);
                    EditText passField = findViewById(R.id.clientPassField);
                    idField.setText("");
                    passField.setText("");
                }
        }
    }

}
