package caraoucoroa.android.leonardo.com.br.caraoucoroa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageButton botao;
    private String[] opcao = {"cara","coroa"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        botao = (ImageButton) findViewById(R.id.botaojogarid);


        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();

                int numeroaleatorio = random.nextInt(2);

                Intent intent = new Intent(MainActivity.this,DetalheActivity.class);
                intent.putExtra("opcao",opcao[numeroaleatorio]);
                startActivity(intent);

            }
        });
    }
}
