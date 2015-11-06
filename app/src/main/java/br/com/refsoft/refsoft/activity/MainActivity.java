package br.com.refsoft.refsoft.activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
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
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import br.com.refsoft.refsoft.R;
import br.com.refsoft.refsoft.adapter.NavDrawerMenuAdapter;
import br.com.refsoft.refsoft.adapter.NavDrawerMenuItem;
import br.com.refsoft.refsoft.domain.Reporte;
import br.com.refsoft.refsoft.domain.RepositorioReporte;
import br.com.refsoft.refsoft.domain.Usuario;
import livroandroid.lib.fragment.NavigationDrawerFragment;

public class MainActivity extends BaseActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks, View.OnClickListener, AdapterView.OnItemSelectedListener {

    private NavigationDrawerFragment mNavDrawerFragment;
    private NavDrawerMenuAdapter listAdapter;
    private static final String CATEGORIA = "MainActivity";
    private RepositorioReporte repositorioReporte;
    private ImageButton imgSalvar;
    private ImageButton imgFolder;
    private ImageButton find;
    private EditText descricao;
    private EditText status;
    private EditText localizacao;
    private Spinner tipo;
    private Usuario usuario;
    private Reporte modeloReporte;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();

        //Recupera sessao do usuario
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        descricao = (EditText) findViewById(R.id.descricao);
        localizacao = (EditText) findViewById(R.id.localizacao);
        status = (EditText) findViewById(R.id.status);
        tipo = (Spinner) findViewById(R.id.spinner);
        find = (ImageButton) findViewById(R.id.searchMap);
        find.setOnClickListener(this);
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

        //Coloca o mapa na tela.
        implementarGoogleMaps();
    }

    @Override
    public void onClick(View view) {

        repositorioReporte = new RepositorioReporte(this);
        String stringLocal = localizacao.getText().toString();

        //Convertendo endereço para coordenada
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocationName(stringLocal, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Address address = addresses.get(0);
        double longitude = address.getLongitude();
        double latitude = address.getLatitude();

        if (view == imgSalvar) {
            if (descricao.getText().length() == 0 || status.getText().length() == 0) {
                Toast.makeText(this, "Os campos descrição, tipo, localização e status são obrigatórios.", Toast.LENGTH_SHORT).show();
            } else {
                modeloReporte = recuperarDadosCampos();
                modeloReporte.setLongitude(longitude);
                modeloReporte.setLatitude(latitude);
                long id = repositorioReporte.insertReporte(modeloReporte, usuario.getId());
                modeloReporte.setIdReporte(id);
                limparCampos();
                Toast.makeText(this, "Reporte cadastrado com sucesso! ", Toast.LENGTH_SHORT).show();
            }
        }
        if (view == find) {
            LatLng latLng = new LatLng(latitude, longitude);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
            map.animateCamera(CameraUpdateFactory.zoomTo(17), 2000, null);
        }
        if (view == imgFolder) {
            Intent it = new Intent(this, ListviewCustomizadaFinalActivity.class);
            it.putExtra("usuario", usuario);
            startActivity(it);
        }

    }

    private void limparCampos() {
        descricao.setText("");
        tipo.setSelection(0);
        status.setText("");
        localizacao.setText("");
    }

    private Reporte recuperarDadosCampos() {
        modeloReporte = new Reporte();
        try {
            //Formatando hora e data
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
            String dateTime = df.format(Calendar.getInstance().getTime());

            modeloReporte.setTipoReporte(tipo.getSelectedItem().toString());
            modeloReporte.setDescricaoReporte(descricao.getText().toString());
            modeloReporte.setStatusReporte(status.getText().toString());
            modeloReporte.setDataAbertura(dateTime);
        } catch (Exception e) {
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

        return new NavigationDrawerFragment.NavDrawerListView(view, R.id.listView);
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
        if (position == 3) {
            Intent it = new Intent(this, ListviewCustomizadaFinalActivity.class);
            it.putExtra("usuario", usuario);
            startActivity(it);
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void implementarGoogleMaps() {
        try {
            if (map == null) {
                map = ((MapFragment) getFragmentManager().
                        findFragmentById(R.id.mapFragLayout)).getMap();
            }
            map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
