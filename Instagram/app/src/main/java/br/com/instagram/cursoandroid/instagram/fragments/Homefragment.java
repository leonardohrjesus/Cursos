package br.com.instagram.cursoandroid.instagram.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import br.com.instagram.cursoandroid.instagram.R;
import br.com.instagram.cursoandroid.instagram.adapter.HomeAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Homefragment extends Fragment {

    private ListView listView;
    private ArrayAdapter<ParseObject> adapter;
    private ArrayList<ParseObject> postagens;
    private ParseQuery<ParseObject> query;

    public Homefragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_homefragment,container,false);

        //Montar listView e adapter
        postagens = new ArrayList<>();
        listView = (ListView) view.findViewById(R.id.list_postagens);
        adapter = new HomeAdapter(getActivity(),postagens);
        listView.setAdapter(adapter);

        //recupera postagens
        getPostagens();
        
        return view;

    }

    private void getPostagens() {

        /**
         * recupera imagens das postagens
         */

        query = ParseQuery.getQuery("Imagem");
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.orderByDescending("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){//sucesso

                    if(objects.size()>0){
                        postagens.clear();
                        for (ParseObject parseObject:objects){
                            postagens.add(parseObject);
                        }
                        adapter.notifyDataSetChanged();

                    }

                }//erro
                else{
                       e.printStackTrace();
                }
            }
        });

    }

    public void atualizaPostagens(){
        getPostagens();
    }

}
