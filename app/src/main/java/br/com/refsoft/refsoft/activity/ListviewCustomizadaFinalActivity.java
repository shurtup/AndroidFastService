package br.com.refsoft.refsoft.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import br.com.refsoft.refsoft.R;
import br.com.refsoft.refsoft.adapter.ListaCustomizadaAdapter;
import br.com.refsoft.refsoft.domain.Reporte;
import br.com.refsoft.refsoft.domain.RepositorioReporte;
import br.com.refsoft.refsoft.domain.Usuario;

public class ListviewCustomizadaFinalActivity extends Activity {

    Usuario usuario;
    private List<Reporte> reporte;
    RepositorioReporte repositorioReporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_customizada_final);

        ListView listView = (ListView) findViewById(R.id.lista);
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        repositorioReporte = new RepositorioReporte(this);
        reporte = repositorioReporte.listaQuery(6);
        ListaCustomizadaAdapter adapterList = new ListaCustomizadaAdapter(this,R.layout.listacustomizada_reportes, reporte);

        listView.setAdapter(adapterList);


    }

}
