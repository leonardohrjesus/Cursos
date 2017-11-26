package atmempresaconsultoria.android.leonardo.com.br.atmempresaconsultoria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    private ImageView botaoEmpresa;
    private ImageView botaoServicos;
    private ImageView botaoCliente;
    private ImageView botaoContato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoEmpresa= (ImageView) findViewById(R.id.empresaid);
        botaoServicos= (ImageView) findViewById(R.id.servicosid);
        botaoCliente = (ImageView) findViewById(R.id.clientesid);
        botaoContato= (ImageView) findViewById(R.id.contatoid);


        botaoEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,EmpresaActivity.class));
            }
        });

        botaoServicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ServicoActivity.class));
            }
        });

        botaoCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ClienteActivity.class));
            }
        });

        botaoContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ContatoActivity.class));
            }
        });
    }
}
