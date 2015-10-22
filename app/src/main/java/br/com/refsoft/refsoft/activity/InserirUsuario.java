package br.com.refsoft.refsoft.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.refsoft.refsoft.R;
import br.com.refsoft.refsoft.domain.RepositorioUsuario;
import br.com.refsoft.refsoft.domain.Usuario;

public class InserirUsuario extends Activity implements View.OnClickListener {
    private static final String CATEGORIA = "InserirUsuario";
    EditText nome;
    EditText email;
    EditText senha;
    EditText login;
    Button btn1;
    private RepositorioUsuario repositorioUsuario;
    private Usuario modeloUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_usuario);

        nome = (EditText) findViewById(R.id.nome);
        email = (EditText) findViewById((R.id.email));
        senha = (EditText) findViewById(R.id.password);
        login = (EditText) findViewById(R.id.login);
        btn1 = (Button) findViewById(R.id.salvar);
        btn1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btn1) {
            String emails = email.getText().toString();
            String pass = senha.getText().toString();
            if (nome.getText().length() == 0) {
                nome.setError("Nome inválido");
            } else if (login.getText().length() == 0) {
                login.setError("Login inválido");
            } else if (!isValidEmail(emails)) {
                email.setError("Email inválido.");
            } else if (!isValidPassword(pass)) {
                senha.setError("A senha tem que ter mais de 6 characters.");
            } else {
                repositorioUsuario = new RepositorioUsuario(this);
                if (view == btn1) {
                    modeloUsuario = inserirUsuario();
                    long id = repositorioUsuario.insertUsuario(modeloUsuario);
                    modeloUsuario.setId(id);
                    Intent it = new Intent(this, MainActivity.class);
                    it.putExtra("usuario", modeloUsuario);
                    startActivity(it);
                    limpaCampos();
                    Toast.makeText(this, "Usuario cadastrado com sucesso id: " + id, Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    private void limpaCampos() {
        nome.setText("");
        email.setText("");
        login.setText("");
        senha.setText("");
    }

    private Usuario inserirUsuario() {
        modeloUsuario = new Usuario();

        try {
            modeloUsuario.setNome(nome.getText().toString());
            modeloUsuario.setEmail(email.getText().toString());
            modeloUsuario.setLogin(login.getText().toString());
            modeloUsuario.setSenha(senha.getText().toString());
        } catch (Exception e) {
            Log.i(CATEGORIA, e.toString());
        }
        return modeloUsuario;
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }

}
