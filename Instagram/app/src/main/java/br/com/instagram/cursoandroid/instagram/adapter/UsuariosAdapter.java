package br.com.instagram.cursoandroid.instagram.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import br.com.instagram.cursoandroid.instagram.R;

/**
 * Created by Amministratore on 26/11/2017.
 */

public class UsuariosAdapter extends ArrayAdapter<ParseUser>{
    private Context context;
    private ArrayList<ParseUser> usuarios;

    public UsuariosAdapter(Context c,  ArrayList<ParseUser> objects) {
        super(c, 0, objects);
        this.context = c;
        this.usuarios = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
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
            view = inflater.inflate(R.layout.lista_usuarios,parent,false);

        }

        //recuperar elementos para exibicao
        TextView username = (TextView) view.findViewById(R.id.text_username);

        //Configurar o textview para exibir os usuarios
        ParseUser parseUser = usuarios.get(position);
        username.setText(parseUser.getUsername());

        return view;
    }
}
