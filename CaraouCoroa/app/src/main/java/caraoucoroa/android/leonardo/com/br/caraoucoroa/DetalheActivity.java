package caraoucoroa.android.leonardo.com.br.caraoucoroa;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DetalheActivity extends AppCompatActivity {

    private ImageView botaovoltar;
    private ImageView botaomoeda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        botaovoltar = (ImageView) findViewById(R.id.botaovoltarid);
        botaomoeda = (ImageView) findViewById(R.id.moedaid);

        Bundle extra = getIntent().getExtras();
        if (extra != null){
            String opcao_Escolhida = extra.getString("opcao");

            if (opcao_Escolhida.equals("cara")){

                botaomoeda.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.moeda_cara));
            }
            else{

                botaomoeda.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.moeda_coroa));
            }

        }

        botaovoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetalheActivity.this,MainActivity.class));
            }
        });
    }
}
