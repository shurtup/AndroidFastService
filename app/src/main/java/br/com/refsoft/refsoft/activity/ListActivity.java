package br.com.refsoft.refsoft.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.refsoft.refsoft.R;
import br.com.refsoft.refsoft.adapter.ReporteListAdapter;
import br.com.refsoft.refsoft.domain.Reporte;
import br.com.refsoft.refsoft.domain.RepositorioReporte;
import br.com.refsoft.refsoft.domain.SQliteHelper;
import br.com.refsoft.refsoft.domain.Usuario;

public class ListActivity extends android.app.ListActivity {
    private SQLiteDatabase db;
    private SQliteHelper dbHelper;
    ListView listView;
    private ArrayList<Reporte> partes = new ArrayList<Reporte>();
    private CursorAdapter dataSource;
    private List<Reporte> reportes;
    String[] campos = new String[]{"id_reporte", "tipo", "descricao", "status", "data", "hora", "latitude", "longitude", "endereco"};
    Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        listView = (ListView) findViewById(R.id.list);
        RepositorioReporte repositorioReporte = new RepositorioReporte(this);
        reportes = repositorioReporte.listaQuery(usuario.getId());
        setListAdapter(new ReporteListAdapter(this, reportes));
    }

    public void preencherListViewReportes(List<Reporte> listaReportes){
        String[] listNomeReportes = new String[listaReportes.size()];
        for(int i = 0; i < listaReportes.size(); i++){
            listNomeReportes[i] = listaReportes.get(i).getTipoReporte();
        }
        ArrayAdapter<String> adapterListReportes = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, listNomeReportes);
        listView.setAdapter(adapterListReportes);
    }

}
