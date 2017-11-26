package prefenrenciacorusuario.android.leonardo.com.br.prefenrenciacorusuario;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButtonSelecionado;
    private Button botoasalvar;
    private static  final String ARQUIVO_PREFERENCIA = "ArqPreferencia";
    private RelativeLayout layout;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.radiogroupid);
        botoasalvar= (Button) findViewById(R.id.botaosalvarid);
        layout = (RelativeLayout) findViewById(R.id.layoutid);

        botoasalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idradioButtonEscolhido = radioGroup.getCheckedRadioButtonId();
                if (idradioButtonEscolhido  > 0)
                radioButtonSelecionado = (RadioButton) findViewById(idradioButtonEscolhido );
                String corEscolhida = radioButtonSelecionado.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("corEscolhida", corEscolhida);
                editor.commit();

                setBackground(corEscolhida);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);
        if(sharedPreferences.contains("corEscolhida")){
            String corRecuperada = sharedPreferences.getString("corEscolhida","Laranja");
            setBackground(corRecuperada);
        }

    }

    private void setBackground(String cor){
        if (cor.equals("Azul")){
            layout.setBackgroundColor(Color.parseColor("#495b7c"));

        }else if (cor.equals("Laranja")){
            layout.setBackgroundColor(Color.parseColor("#bf4609"));

        }else if (cor.equals("Verde")){
            layout.setBackgroundColor(Color.parseColor("#1cdd16"));

        }

    }
}
