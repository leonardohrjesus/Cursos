package listview.android.leonardo.com.br.signos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView lista_signos;
    private  String[] signos = {"Aries", "Touro","Gemeos","Cancer","Leao","Virgem","Libra","Escorpiao"
            ,"Sagitario","Capricornio","Aquario","Peixes"

    };

    private  String[] perfis= { "Arianos loucos ",
                                "Nao existe essa papo de signo",
                                "Nao existe essa papo de signo","...","...","...","...","...","...","...","...","...",


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista_signos = (ListView) findViewById(R.id.listviewid);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                signos
        );

        lista_signos.setAdapter(adapter);

        lista_signos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int codigoposicao = position;
                Toast.makeText(getApplicationContext(),perfis[codigoposicao],Toast.LENGTH_LONG).show();
            }
        });
    }
}
