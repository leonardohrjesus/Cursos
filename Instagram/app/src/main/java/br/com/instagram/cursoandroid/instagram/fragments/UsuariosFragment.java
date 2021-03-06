package br.com.instagram.cursoandroid.instagram.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import br.com.instagram.cursoandroid.instagram.R;
import br.com.instagram.cursoandroid.instagram.activity.FeedUsuariosActivity;
import br.com.instagram.cursoandroid.instagram.adapter.UsuariosAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsuariosFragment extends Fragment {
    private ListView listView;
    private ArrayAdapter<ParseUser> adapter;
    private ArrayList<ParseUser> usuarios;
    private ParseQuery<ParseUser> query;

    public UsuariosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_usuarios, container, false);

        /*
        *Monta ListView e adpter
        * */

        usuarios = new ArrayList<>();
        listView = (ListView) view.findViewById(R.id.list_usuarios);
        adapter = new UsuariosAdapter(getActivity(),usuarios);
        listView.setAdapter(adapter);

        //Recupera Usuarios
        getUsuarios();
        /*
        * COLOCAR EVENTO CLICK NOS ITENS DA LISTA
        * */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //recupera dados a serem passados
                ParseUser parseUser = usuarios.get(position);

                //envia dados para feed usuario
                Intent intent = new Intent(getActivity(), FeedUsuariosActivity.class);
                intent.putExtra("username",parseUser.getUsername());

                startActivity(intent);


            }
        });


        return view;

    }

    private void getUsuarios() {
        /*
        *Recupera lista de usuarios do parse
        * */
        query = ParseUser.getQuery();
        query.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
        query.orderByAscending("username");

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null){//sucesso
                        if(objects.size() > 0){//verificar se tem usuarios
                            usuarios.clear();
                            for (ParseUser parseUser: objects){
                                usuarios.add(parseUser);
                            }
                            adapter.notifyDataSetChanged();


                        }
                }
                else {//erro
                    e.printStackTrace();
                }
            }
        });
    }

}
