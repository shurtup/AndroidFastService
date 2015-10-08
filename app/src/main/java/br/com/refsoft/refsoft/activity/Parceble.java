package br.com.refsoft.refsoft.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import br.com.refsoft.refsoft.R;
import br.com.refsoft.refsoft.domain.Usuario;

public class Parceble extends AppCompatActivity {
    public static final int DIFFICULTY_EASY = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parceble);

        Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");

                Toast.makeText(Parceble.this, usuario.getNome().toString() + " id: " + usuario.getId(), Toast.LENGTH_SHORT).show();

    }

}
