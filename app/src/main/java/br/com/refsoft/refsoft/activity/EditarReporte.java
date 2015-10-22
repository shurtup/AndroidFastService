package br.com.refsoft.refsoft.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.refsoft.refsoft.R;
import br.com.refsoft.refsoft.domain.Reporte;
import br.com.refsoft.refsoft.domain.RepositorioReporte;
import br.com.refsoft.refsoft.domain.Usuario;

public class EditarReporte extends Activity implements View.OnClickListener {
    private static final String CATEGORIA = "EditarReportes";
    private Spinner tipo;
    EditText descricao;
    EditText status;
    Button btnSalvar;
    private RepositorioReporte repositorioReporte;
    private Usuario usuario;
    private Reporte modeloReporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_reporte);

        //Recupera sessao do reporte
        modeloReporte = (Reporte) getIntent().getSerializableExtra("reporte");
        //Recupera sessao do usuario
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        descricao = (EditText) findViewById(R.id.textDescricao);
        status = (EditText) findViewById(R.id.textStatus);
        btnSalvar = (Button) findViewById(R.id.salvar);
        tipo = (Spinner) findViewById(R.id.spinner);
        setarCampos(modeloReporte);

        btnSalvar.setOnClickListener(this);
        ArrayAdapter adapterSpinner = ArrayAdapter.createFromResource(this, R.array.tipo, android.R.layout.simple_list_item_1);
        tipo.setAdapter(adapterSpinner);
    }

    private void setarCampos(Reporte modeloReporte) {
        descricao.setText(modeloReporte.getDescricaoReporte());
        //  tipo.setSelection(String.valueOf(modeloReporte.getTipoReporte()));
        status.setText(modeloReporte.getStatusReporte());

    }

    private void limparCampos() {
        descricao.setText("");
        tipo.setSelection(0);
        status.setText("");
    }

    @Override
    public void onClick(View view) {
        repositorioReporte = new RepositorioReporte(this);

        if (view == btnSalvar) {
            boolean update = repositorioReporte.alterarReporte(recuperarDadosCampos());
            limparCampos();
            if (update) {
                Toast.makeText(this, "Reporte alterado.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Reporte alterado...", Toast.LENGTH_SHORT).show();
            }
            Intent it = new Intent(this, ListviewCustomizadaFinalActivity.class);
            it.putExtra("usuario", usuario);
            startActivity(it);
        }
    }

    private Reporte recuperarDadosCampos() {
        try {
            modeloReporte.setTipoReporte(tipo.getSelectedItem().toString());
            modeloReporte.setDescricaoReporte(descricao.getText().toString());
            modeloReporte.setStatusReporte(status.getText().toString());
        } catch (Exception e) {
            Log.i(CATEGORIA, e.toString());
        }
        return modeloReporte;
    }

}
