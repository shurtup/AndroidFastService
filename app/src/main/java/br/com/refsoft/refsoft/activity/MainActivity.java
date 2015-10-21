package br.com.refsoft.refsoft.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import br.com.refsoft.refsoft.R;
import br.com.refsoft.refsoft.adapter.NavDrawerMenuAdapter;
import br.com.refsoft.refsoft.adapter.NavDrawerMenuItem;
import br.com.refsoft.refsoft.domain.Reporte;
import br.com.refsoft.refsoft.domain.RepositorioReporte;
import br.com.refsoft.refsoft.domain.Usuario;
import livroandroid.lib.fragment.NavigationDrawerFragment;
import livroandroid.lib.utils.AndroidUtils;

public class MainActivity extends BaseActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks, View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener{

    private NavigationDrawerFragment mNavDrawerFragment;
    private NavDrawerMenuAdapter listAdapter;
    private ListView lista;
    private static final String CATEGORIA = "MainActivity";
    private RepositorioReporte repositorioReporte;
    private Reporte modeloReporte;
    private ImageButton imgSalvar;
    private ImageButton imgEdit;
    private ImageButton imgFolder;
    private ImageButton imgSearch;
    private ImageButton imgDelete;
    private ImageButton imgAdicionar;
    private EditText descricao;
    private EditText status;
    private Spinner tipo;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        descricao = (EditText) findViewById(R.id.descricao);
        tipo = (Spinner) findViewById(R.id.spinner);
        status = (EditText) findViewById(R.id.status);


        imgSalvar = (ImageButton) findViewById(R.id.save);
        imgSalvar.setOnClickListener(this);

        imgFolder = (ImageButton) findViewById(R.id.folder);
        imgFolder.setOnClickListener(this);


        ArrayAdapter adapterSpinner = ArrayAdapter.createFromResource(this, R.array.tipo, android.R.layout.simple_list_item_1);
        tipo.setAdapter(adapterSpinner);


        // Nav Drawer
        mNavDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.nav_drawer_fragment);
        // Configura o drawer layout
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setStatusBarBackground(R.color.primary_dark);
        mNavDrawerFragment.setUp(drawerLayout);
    }

    @Override
    public void onClick(View view) {
        repositorioReporte = new RepositorioReporte(this);

        if(view == imgSalvar){
                if(descricao.getText().length()==0  || status.getText().length() == 0){
                    Toast.makeText(this, "Os campos descrição, tipo, localização e status são obrigatórios.", Toast.LENGTH_SHORT).show();
                }else {
                    long id = repositorioReporte.insertReporte(recuperarDadosCampos(), usuario.getId());
                    limparCampos();
                    Toast.makeText(this, "Reporte cadastrado com sucesso id: " + id, Toast.LENGTH_SHORT).show();
                }
        }
        if(view == imgFolder){
            Intent it = new Intent(this, ListviewCustomizadaFinalActivity.class);
            it.putExtra("usuario", usuario);
            startActivity(it);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String reporteTipo = parent.getItemAtPosition(position).toString();
        repositorioReporte = new RepositorioReporte(this);
        modeloReporte = repositorioReporte.buscaIndividualReporte(reporteTipo);
        setarCampos(modeloReporte);
    }

    private void setarCampos(Reporte modeloReporte) {
        descricao.setText(String.valueOf(modeloReporte.getDescricaoReporte()));
      //  tipo.setSelection(String.valueOf(modeloReporte.getTipoReporte()));
        status.setText(String.valueOf(modeloReporte.getStatusReporte()));

    }

    private void limparCampos() {
        descricao.setText("");
        tipo.setSelection(0);
        status.setText("");
    }

    public void preencherListViewReportes(List<Reporte> listaReportes){
        String[] listNomeReportes = new String[listaReportes.size()];
        for(int i = 0; i < listaReportes.size(); i++){
            listNomeReportes[i] = listaReportes.get(i).getTipoReporte();
        }
        ArrayAdapter<String> adapterListReportes = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, listNomeReportes);
        lista.setAdapter(adapterListReportes);
    }

    private Reporte recuperarDadosCampos(){
        modeloReporte = new Reporte();
        try {
            java.util.Date date = new java.util.Date();
            Date dtAberturaReporte = new java.sql.Date(new java.util.Date().getTime());
            Time hrAberturaReporte = new java.sql.Time(date.getTime());

            modeloReporte.setTipoReporte(tipo.getSelectedItem().toString());
            modeloReporte.setDescricaoReporte(descricao.getText().toString());
            modeloReporte.setStatusReporte(status.getText().toString());
            modeloReporte.setDataAbertura(dtAberturaReporte);
            modeloReporte.setHoraAbertura(hrAberturaReporte);
        }catch (Exception e){
            Log.i(CATEGORIA, e.toString());
        }
        return modeloReporte;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            toast("Ainda vai ser configurado");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public NavigationDrawerFragment.NavDrawerListView getNavDrawerView(NavigationDrawerFragment navDrawerFrag, LayoutInflater layoutInflater, ViewGroup container) {
        View view = layoutInflater.inflate(R.layout.nav_drawer_listview, container, false);

        navDrawerFrag.setHeaderValues(view, R.id.listViewContainer, R.drawable.nav_drawer_header, R.drawable.ic_star_rate_black_18dp, R.string.nav_drawer_username, R.string.nav_drawer_email);

        return new NavigationDrawerFragment.NavDrawerListView(view,R.id.listView);
    }


    public ListAdapter getNavDrawerListAdapter(NavigationDrawerFragment navigationDrawerFragment) {
        List<NavDrawerMenuItem> list = NavDrawerMenuItem.getList();
        // Deixa o primeiro item selecionado
        list.get(0).selected = true;
        this.listAdapter = new NavDrawerMenuAdapter(this, list);
        return listAdapter;
    }


    public void onNavDrawerItemSelected(NavigationDrawerFragment navigationDrawerFragment, int position) {
        List<NavDrawerMenuItem> list = NavDrawerMenuItem.getList();
        NavDrawerMenuItem selectedItem = list.get(position);

        // Seleciona a linha
        this.listAdapter.setSelected(position, true);
        if(position == 1){
            if (AndroidUtils.isAndroid3Honeycomb()) {

            }
        }

        toast("Clicou no item: " + getString(selectedItem.title));
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
