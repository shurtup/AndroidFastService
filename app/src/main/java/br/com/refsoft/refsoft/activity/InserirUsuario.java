package br.com.refsoft.refsoft.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.refsoft.refsoft.R;
import br.com.refsoft.refsoft.domain.RepositorioUsuario;
import br.com.refsoft.refsoft.domain.Usuario;

public class InserirUsuario extends Activity  implements View.OnClickListener {
    private static final String CATEGORIA = "InserirUsuario";
    EditText nome;
    EditText email;
    EditText senha;
    EditText login;
    Button btn1;
    private RepositorioUsuario repositorioUsuario;
    private Usuario modeloUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    { super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_usuario);

        nome  = (EditText) findViewById(R.id.nome);
        email = (EditText) findViewById((R.id.email));
        senha = (EditText) findViewById(R.id.password);
        login = (EditText) findViewById(R.id.login);
        btn1  = (Button) findViewById(R.id.salvar);
        btn1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
            repositorioUsuario = new RepositorioUsuario(this);
        if(view == btn1){
            long id = repositorioUsuario.insertUsuario(inserirUsuario());
            limpaCampos();
            Toast.makeText(this, "Usuario cadastrado com sucesso id: " + id, Toast.LENGTH_SHORT).show();
        }
    }

    private void limpaCampos() {
        nome.setText("");
        email.setText("");
        login.setText("");
        senha.setText("");
    }

    private Usuario inserirUsuario(){
        modeloUsuario = new Usuario();

        try {
            modeloUsuario.setNome(nome.getText().toString());
            modeloUsuario.setEmail(email.getText().toString());
            modeloUsuario.setLogin(login.getText().toString());
            modeloUsuario.setSenha(senha.getText().toString());
        }catch(Exception e){
            Log.i(CATEGORIA, e.toString());
        }
        return modeloUsuario;
    }
}
