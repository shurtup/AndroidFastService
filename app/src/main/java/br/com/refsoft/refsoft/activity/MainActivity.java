package br.com.refsoft.refsoft.activity;

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

import java.util.ArrayList;
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
    private EditText localizacao;
    private EditText status;
    private EditText id;
    private Spinner tipo;
    private ArrayList<String> options;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        options = new ArrayList<>();
        descricao = (EditText) findViewById(R.id.descricao);
        tipo = (Spinner) findViewById(R.id.spinner);
        localizacao = (EditText) findViewById(R.id.localizacao);
        status = (EditText) findViewById(R.id.status);
        id = (EditText) findViewById(R.id.id);

        lista = (ListView) findViewById(R.id.list);
        lista.setOnItemClickListener(this);

        imgAdicionar = (ImageButton) findViewById(R.id.adicionar);
        imgAdicionar.setOnClickListener(this);

        imgSalvar = (ImageButton) findViewById(R.id.save);
        imgSalvar.setOnClickListener(this);

        imgSearch = (ImageButton) findViewById(R.id.search);
        imgSearch.setOnClickListener(this);

        imgEdit = (ImageButton) findViewById(R.id.edit);
        imgEdit.setOnClickListener(this);

        imgFolder = (ImageButton) findViewById(R.id.folder);
        imgFolder.setOnClickListener(this);

        imgDelete = (ImageButton) findViewById(R.id.delete);
        imgDelete.setOnClickListener(this);

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
        int id_user = usuario.getId();
        if(view == imgAdicionar){
                if(descricao.getText().length()==0  || localizacao.getText().length() == 0 || status.getText().length() == 0){
                    Toast.makeText(this, "Os campos descrição, tipo, localização e status são obrigatórios.", Toast.LENGTH_SHORT).show();
                }else {
                    long id = repositorioReporte.insertReporte(recuperarDadosCampos(),id_user );
                    limparCampos();
                    Toast.makeText(this, "Reporte cadastrado com sucesso id: " + id, Toast.LENGTH_SHORT).show();
                }
        }
        if(view == imgFolder){
            preencherListViewReportes(repositorioReporte.listReporte());
            limparCampos();
        }
        if(view == imgEdit){
            if(id.length() > 0){
                boolean update = repositorioReporte.alterarReporte(recuperarDadosCampos(), id_user);
                limparCampos();
                preencherListViewReportes(repositorioReporte.listReporte());
                if (update == true) {
                    Toast.makeText(this, "Reporte alterado.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Selecionar um cadastro.", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if(view == imgDelete){
            if(id.length() > 0){
                boolean delete = repositorioReporte.excluirReporte(id.getText().toString());
                limparCampos();
                preencherListViewReportes(repositorioReporte.listReporte());

                if (delete == true) {
                    Toast.makeText(this, "Reporte deletado.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Selecionar um cadastro.", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if (view == imgSearch){
            String reporteId = id.getText().toString();
            if(reporteId.length() > 0 ){
                repositorioReporte = new RepositorioReporte(this);
                modeloReporte = repositorioReporte.buscaIndividualReporte(reporteId);
                setarCampos(modeloReporte);
            }else{
                Toast.makeText(this, "Informar id para pesquisa.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String reporteTipo = parent.getItemAtPosition(position).toString();
     //   Toast.makeText(this, "Você clicou em: " + reporteId, Toast.LENGTH_SHORT).show();
     //   Toast.makeText(this, "Você clicou em: " + descricao.toString(), Toast.LENGTH_SHORT).show();
        repositorioReporte = new RepositorioReporte(this);
        modeloReporte = repositorioReporte.buscaIndividualReporte(reporteTipo);
        setarCampos(modeloReporte);
    }

    private void setarCampos(Reporte modeloReporte) {
        id.setText(Integer.toString(modeloReporte.getIdReporte()));
        descricao.setText(String.valueOf(modeloReporte.getDescricaoReporte()));
      //  tipo.setSelection(String.valueOf(modeloReporte.getTipoReporte()));
        status.setText(String.valueOf(modeloReporte.getStatusReporte()));

    }

    private void limparCampos() {
        id.setText("");
        descricao.setText("");
        tipo.setSelection(0);
        localizacao.setText("");
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
            modeloReporte.setIdReporte(Integer.parseInt(id.getText().toString()));
            modeloReporte.setTipoReporte(tipo.getSelectedItem().toString());
            modeloReporte.setDescricaoReporte(descricao.getText().toString());
            modeloReporte.setStatusReporte(status.getText().toString());
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
