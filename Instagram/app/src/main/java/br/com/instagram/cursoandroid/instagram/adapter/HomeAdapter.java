package br.com.instagram.cursoandroid.instagram.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.parse.ParseObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.instagram.cursoandroid.instagram.R;

/**
 * Created by Amministratore on 24/11/2017.
 */

public class HomeAdapter extends ArrayAdapter<ParseObject> {

    private Context context;
    private ArrayList<ParseObject> postagens;

    public HomeAdapter(Context c, ArrayList<ParseObject> objects) {
        super(c,0,objects);
        this.context = c;
        this.postagens = objects;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        /****************************************************************
         * verifica  se nao existe o objeto view  criado.
         * pois a view utilizada e armazenado no cache do android e fica na variavel
         * convertview
         * **************************************************************/
        if(view == null){
            //inicializa o objeto para montagem do layout
            LayoutInflater inflater =  (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            //monta a view a partir do xml
            view = inflater.inflate(R.layout.lista_postagem,parent,false);

        }
        //verifica se existe postagens
        if(postagens.size()>0){
            //Recupera componentes da tela
            ImageView imagempostagem = (ImageView) view.findViewById(R.id.image_lista_postagem);
            ParseObject parseObject = postagens.get(position);
            //parseObject.getParseFile("imagem");
            Picasso.with(context).
                    load(parseObject.getParseFile("imagem").getUrl())
                    .fit()
                    .into(imagempostagem);

        }


        return view;
    }
}
