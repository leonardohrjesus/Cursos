package android.leonardo.com.br.adivinha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button botaojogar;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        botaojogar = (Button) findViewById(R.id.btnresultadoid);
        txtResultado = (TextView) findViewById(R.id.Resultadoid);



        botaojogar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Random randomico = new Random();

            int numeroaleatorio = randomico.nextInt(24);
            txtResultado.setText("Resultado..: "+ numeroaleatorio);

        }
        }


        );

    }


}
