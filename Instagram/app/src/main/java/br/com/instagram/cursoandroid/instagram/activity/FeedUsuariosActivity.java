package br.com.instagram.cursoandroid.instagram.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import br.com.instagram.cursoandroid.instagram.R;
import br.com.instagram.cursoandroid.instagram.adapter.HomeAdapter;

public class FeedUsuariosActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    private String userName;
    private ArrayAdapter<ParseObject> adapter;
    private ArrayList<ParseObject> postagens;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_usuarios);


        //Recupera dados enviados da intent
        Intent intent = getIntent();
        userName = intent.getStringExtra("username");

         //Configurar toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar__feed_usuario);
        toolbar.setTitle(userName);
        toolbar.setTitleTextColor(R.color.preto);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
        setSupportActionBar(toolbar);


        //configura listview e adapter
        postagens = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list_feed_usuario);
        adapter =  new HomeAdapter(getApplicationContext(),postagens);
        listView.setAdapter(adapter);


        //Recuperar  postagens
        getPostagens();


    }

    private void getPostagens() {

        //Recupera imagens das postagens
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Imagem");
        query.whereEqualTo("username",userName);
        query.orderByAscending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null){//sucesso
                        if(objects.size()>0){
                            postagens.clear();
                            for(ParseObject parseObject :objects){
                                postagens.add(parseObject);
                            }
                            adapter.notifyDataSetChanged();
                        }
            }else {//erro
                    Toast.makeText(getApplicationContext(),"Erro ao Recuperar o feed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
