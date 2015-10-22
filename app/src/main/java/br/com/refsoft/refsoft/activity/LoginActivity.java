package br.com.refsoft.refsoft.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.refsoft.refsoft.R;
import br.com.refsoft.refsoft.domain.RepositorioUsuario;
import br.com.refsoft.refsoft.domain.Usuario;

public class LoginActivity extends Activity implements View.OnClickListener {

    EditText login;
    EditText senha;
    Button btnEntrar;
    Button btnCadastrar;
    RepositorioUsuario repositorioUsuario;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        login = (EditText) findViewById(R.id.login);
        senha = (EditText) findViewById(R.id.senha);
        btnCadastrar.setOnClickListener(this);
        btnEntrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        repositorioUsuario = new RepositorioUsuario(this);
        if (view == btnEntrar) {
            String loginTexto = login.getText().toString();
            String senhaTexto = senha.getText().toString();
            try {
                usuario = repositorioUsuario.validarLogin(loginTexto, senhaTexto);
                if (usuario != null) {
                    Intent it = new Intent(this, ListviewCustomizadaFinalActivity.class);
                    it.putExtra("usuario", usuario);
                    startActivity(it);
                } else {
                    Toast.makeText(LoginActivity.this, "Login Errado!", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(LoginActivity.this, "Erro ao validar Login", Toast.LENGTH_SHORT).show();
                Log.e("Erro: ", e.getMessage());
            }

        }
        if (view == btnCadastrar) {
            Intent it = new Intent(LoginActivity.this, InserirUsuario.class);
            startActivity(it);
        }
    }
}
