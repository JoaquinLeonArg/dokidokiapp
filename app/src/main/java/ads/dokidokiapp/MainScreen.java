package ads.dokidokiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.Toast;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        Intent intent = getIntent();
        final Integer idCliente = intent.getIntExtra(LoginScreen.EXTRA_IDCLIENTE, 0);
        final String nombreCliente = intent.getStringExtra(LoginScreen.EXTRA_NOMBRECLIENTE);

        final Button saldoButton = findViewById(R.id.saldoButton);
        final Button transferenciaButton = findViewById(R.id.transferenciaButton);
        final LinearLayout saldoLayout = findViewById(R.id.saldoLayout);
        final LinearLayout transferenciaLayout = findViewById(R.id.transferenciaLayout);

        saldoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saldoLayout.setVisibility(View.VISIBLE);
                transferenciaLayout.setVisibility(View.GONE);
            }
        });
        transferenciaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saldoLayout.setVisibility(View.GONE);
                transferenciaLayout.setVisibility(View.VISIBLE);
            }
        });

        if (nombreCliente != null)
            Toast.makeText(getApplicationContext(), "Bienvenido, " + nombreCliente + "!", Toast.LENGTH_LONG).show();


    }
}
