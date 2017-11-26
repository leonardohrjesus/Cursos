package br.com.instagram.cursoandroid.instagram.util;

import java.util.HashMap;

/**
 * Created by Amministratore on 20/11/2017.
 */

public class ParseErros {
    private HashMap<Integer,String> erros;

    public ParseErros() {
        this.erros.put(201,"A senha não foi preenchida!");
        this.erros.put(202,"Usuário já existe, escolha um outro nome de usuário");
    }

    public String getErro(int codErro){
        return this.erros.get(codErro);
    }
}
