package br.com.refsoft.refsoft.activity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.refsoft.refsoft.R;
import br.com.refsoft.refsoft.domain.RepositorioUsuario;
import br.com.refsoft.refsoft.domain.SQliteHelper;
import br.com.refsoft.refsoft.domain.Usuario;

public class ListarUsuarios extends Activity {

    private SQLiteDatabase db;
    private SQliteHelper dbHelper;
    private RepositorioUsuario repositorioUsuario;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);
        listView = (ListView) findViewById(R.id.list);

        repositorioUsuario = new RepositorioUsuario(this);

        preencherListViewUsuarios(repositorioUsuario.listUsuario());
    }

    private void preencherListViewUsuarios(List<Usuario> listaUsuarios){
         db = dbHelper.getWritableDatabase();
        String[] listNomeUsuarios = new String[listaUsuarios.size()];
        for (int i = 0; i < listaUsuarios.size(); i++){
            listNomeUsuarios[i] = listaUsuarios.get(i).getNome();
        }
        ArrayAdapter<String> adapterListaUsuarios = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, listNomeUsuarios);
        listView.setAdapter(adapterListaUsuarios);
    }
}
