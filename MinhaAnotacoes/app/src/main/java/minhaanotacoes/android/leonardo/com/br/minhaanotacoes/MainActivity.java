package minhaanotacoes.android.leonardo.com.br.minhaanotacoes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private ImageView botaosalvar;
    private  static final String NOME_ARQUIVO = "arquivo_anotacao.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.textoid);
        botaosalvar = (ImageView) findViewById(R.id.botoasalvarid);

        botaosalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TextoDigitado = editText.getText().toString();
                Gravarnoarquivo(TextoDigitado);
                Toast.makeText(MainActivity.this, "Salvo com Sucesso!   ", Toast.LENGTH_SHORT).show();
            }
        });

        if (lerarquivo()!= null){
            editText.setText(lerarquivo());
        }
    }

    private void Gravarnoarquivo(String texto){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter( openFileOutput(NOME_ARQUIVO, Context.MODE_PRIVATE));
            outputStreamWriter.write(texto);
            outputStreamWriter.close();

        }catch (IOException e){
            Log.v("MainActivity",e.toString() );

        }

    }

    private String lerarquivo(){

        String resultado = "";
        try {
            InputStream arquivo = openFileInput(NOME_ARQUIVO);
            if(arquivo != null){

                //ler arquivo
                InputStreamReader inputstreamreader = new InputStreamReader(arquivo);

                //gerar Buffer do arquivo lido
                BufferedReader  bufferereader = new BufferedReader(inputstreamreader);

                String linhaarquivo = "";
                while ((linhaarquivo = bufferereader.readLine()) != null){
                    resultado +=linhaarquivo;

                }
                arquivo.close();

            }


        }catch (IOException e){
            Log.v("MainActivity",e.toString());

        }
        return   resultado;
    }

}
