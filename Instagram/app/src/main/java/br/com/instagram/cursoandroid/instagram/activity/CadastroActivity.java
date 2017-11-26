package br.com.instagram.cursoandroid.instagram.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import br.com.instagram.cursoandroid.instagram.R;
import br.com.instagram.cursoandroid.instagram.util.ParseErros;

public class CadastroActivity extends AppCompatActivity {

    private EditText textoUsuario;
    private EditText textoEmail;
    private EditText textoSenha;
    private TextView textoFacaLogin;
    private Button botaoCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        textoUsuario = (EditText) findViewById(R.id.textousuario);
        textoFacaLogin = (TextView) findViewById(R.id.textofalogin);
        textoEmail = (EditText) findViewById(R.id.textoemail);
        textoSenha = (EditText) findViewById(R.id.textosenha);
        botaoCadastrar = (Button) findViewById(R.id.bt_cadastrar);

        textoFacaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirLoginUsuario();
            }
        });


        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastarUsuario();
            }
        });




    }

    private void cadastarUsuario() {
        //Criar objeto usuario
        ParseUser usuario = new ParseUser();
        usuario.setUsername(textoUsuario.getText().toString());
        usuario.setEmail(textoEmail.getText().toString());
        usuario.setPassword(textoSenha.getText().toString());

        usuario.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Toast.makeText(CadastroActivity.this,"Cadastro Feito com Sucesso!!!",Toast.LENGTH_LONG).show();
                    abrirLoginUsuario();
                }else {
                    ParseErros parseErros = new ParseErros();
                    String erro = parseErros.getErro(e.getCode());

                    Toast.makeText(CadastroActivity.this,erro ,Toast.LENGTH_LONG).show();

                }
            }
        });
    }



    private void abrirLoginUsuario(){
        Intent intent = new Intent(CadastroActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
