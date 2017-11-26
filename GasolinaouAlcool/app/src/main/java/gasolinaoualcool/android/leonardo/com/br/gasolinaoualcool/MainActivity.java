package gasolinaoualcool.android.leonardo.com.br.gasolinaoualcool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText precoalcool;
    private EditText precogasolina;
    private Button Botaoverificar;
    private TextView textoResultado;

    private String texto_gasolina;


    private String texto_alcool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        precoalcool = (EditText) findViewById(R.id.edittext_alcool_id);
        precogasolina = (EditText) findViewById(R.id.edittext_gasolina_id_);
        Botaoverificar = (Button) findViewById(R.id.btn_verificar_id);
        textoResultado = (TextView) findViewById(R.id.textView_resultado_id);


        Botaoverificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 texto_alcool = precoalcool.getText().toString();
                texto_gasolina = precogasolina.getText().toString();


                if (texto_alcool.isEmpty() || texto_gasolina.isEmpty()) {
                    textoResultado.setError("Por Favor digitar os valores ");

                }else
                  {

                      Calcular_combustivel();
                }
            }
        });


    }

    private void Calcular_combustivel(){
    Double valor_alcool  = Double.parseDouble(texto_alcool);
    Double valor_gasolina = Double.parseDouble(texto_gasolina);

    Double Resultado_final = valor_alcool/valor_gasolina;

                    if (Resultado_final >= 0.7) {
        textoResultado.setText("è melhor usar a Gasolina");
    } else {
        textoResultado.setText("è melhor usar a Alcool");
    }

    }
}
