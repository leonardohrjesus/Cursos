package frasesdodia.android.leonardo.com.br.frasedodia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView txtnovafrase;
    private Button btnnovafrase;
    private String[] frases = {"se vc pensa que pode ou se vc  pensa que nao pode de qualquer modo vc esta certo ","sucesso so vem pra quem esta ocupado demais para procurar por ele"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtnovafrase = (TextView) findViewById(R.id.txt_nova_frase);
        btnnovafrase = (Button) findViewById(R.id.btn_nova_frase);




        btnnovafrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random random = new Random();
                int numeroAleatorio = random.nextInt(frases.length);
                txtnovafrase.setText(frases[numeroAleatorio]);
            }
        });
    }
}
