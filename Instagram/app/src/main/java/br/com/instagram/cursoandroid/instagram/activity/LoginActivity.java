package br.com.instagram.cursoandroid.instagram.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import br.com.instagram.cursoandroid.instagram.R;

public class LoginActivity extends AppCompatActivity {

    private EditText loginUsuario;
    private EditText loginSenha;
    private Button botaoLogar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsuario = (EditText) findViewById(R.id.Edit_login_usuario);
        loginSenha =  (EditText) findViewById(R.id.edit_login_senha);
        botaoLogar = (Button) findViewById(R.id.botao_login_logar);

        /*****************************************************************************************
        * Verificar se o usuario esta logado
        *****************************************************************************************/

       // Bundle extra = getIntent().getExtras();
       // if (extra == null){
           verificarUsuarioLogado();
          //  Log.i("Extra", String.valueOf(extra));
        //}


        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = loginUsuario.getText().toString();
                String senha = loginSenha.getText().toString();
                
                verificarLogin(usuario ,senha);
            }
        });


    }

    private void verificarLogin(String usuario,String senha) {
        ParseUser.logInInBackground(usuario, senha, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null){
                    Toast.makeText(LoginActivity.this,"Login Realizado com Sucesso!!",Toast.LENGTH_LONG).show();
                    abrirtelaPrincipal();
                }else {

                    Toast.makeText(LoginActivity.this, e.getMessage(),Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    public void abrirCadastroUsuario(View view){
        Intent intent = new Intent(LoginActivity.this,CadastroActivity.class);
        startActivity(intent);
        finish();
    }


    private void verificarUsuarioLogado(){
        ParseUser user =ParseUser.getCurrentUser();

        if( user!= null ){
            //Enviar usuário para tela principal do App
            abrirtelaPrincipal();
        }

    }

    private void abrirtelaPrincipal() {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }


}
