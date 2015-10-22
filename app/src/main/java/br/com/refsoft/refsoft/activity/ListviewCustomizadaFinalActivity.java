package br.com.refsoft.refsoft.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.refsoft.refsoft.R;
import br.com.refsoft.refsoft.adapter.ListaCustomizadaAdapter;
import br.com.refsoft.refsoft.domain.Reporte;
import br.com.refsoft.refsoft.domain.RepositorioReporte;
import br.com.refsoft.refsoft.domain.Usuario;

public class ListviewCustomizadaFinalActivity extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {

    Usuario usuario;
    private List<Reporte> reporte;
    RepositorioReporte repositorioReporte;
    Button btnAdicionar;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_customizada_final);

        //Recupera sessao do usuario
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
        btnAdicionar.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.lista);
        repositorioReporte = new RepositorioReporte(this);
        reporte = repositorioReporte.listaQuery(usuario.getId());

        ListaCustomizadaAdapter adapterList = new ListaCustomizadaAdapter(this, R.layout.listacustomizada_reportes, reporte);

        listView.setAdapter(adapterList);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Reporte reportePosition = reporte.get(position);
        Intent intent = new Intent(this, ReporteDetalhes.class);
        intent.putExtra("reporteTipo", reportePosition);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        if (v == btnAdicionar) {
            Intent it = new Intent(this, MainActivity.class);
            it.putExtra("usuario", usuario);
            startActivity(it);
        }
    }
}
