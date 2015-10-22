package br.com.refsoft.refsoft.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.com.refsoft.refsoft.R;
import br.com.refsoft.refsoft.domain.Reporte;
import br.com.refsoft.refsoft.domain.RepositorioReporte;
import br.com.refsoft.refsoft.domain.Usuario;

/**
 * Created by Gabriel on 18/10/2015.
 */
public class ReporteDetalhes extends Activity implements View.OnClickListener {
    private static final String CATEGORIA = "ReporteDetalhes";
    RepositorioReporte repositorioReporte;
    Usuario usuario;
    Button btnEditar;
    Button btnVoltar;
    Button btnDeletar;
    Reporte modeloReporte;


    private String tipoReporte;
    private AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reporte_detalhes);

        //Recupera sessao do usuario
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        TextView textTipo = (TextView) findViewById(R.id.textTipo);
        TextView textDescricao = (TextView) findViewById(R.id.textDescricao);
        TextView textStatus = (TextView) findViewById(R.id.textStatus);
        btnDeletar = (Button) findViewById(R.id.btnDeletar);
        btnEditar = (Button) findViewById(R.id.btnEditar);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnDeletar.setOnClickListener(this);
        btnEditar.setOnClickListener(this);
        btnVoltar.setOnClickListener(this);

        modeloReporte = (Reporte) getIntent().getSerializableExtra("reporteTipo");
        if (modeloReporte != null) {
            textTipo.setText(modeloReporte.getTipoReporte());
            textDescricao.setText(modeloReporte.getDescricaoReporte());
            textStatus.setText(modeloReporte.getStatusReporte());
            //    edtData.setText((CharSequence) modeloReporte.getDataAbertura());
            tipoReporte = textTipo.toString();
        }

    }

    @Override
    public void onClick(View view) {

        repositorioReporte = new RepositorioReporte(this);

        if (view == btnEditar) {

            Intent it = new Intent(this, EditarReporte.class);
            it.putExtra("reporte", modeloReporte);
            it.putExtra("usuario", usuario);
            startActivity(it);
        }

        if (view == btnDeletar) {
            try {
                alertMessage();

            } catch (Exception e) {
                Log.i(CATEGORIA, e.toString());
            }
        }
        if (view == btnVoltar) {
            finish();
        }
    }

    public void alertMessage() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:

                        boolean delete = repositorioReporte.excluirReporte(String.valueOf(modeloReporte.getIdReporte()));
                        Intent it = new Intent(ReporteDetalhes.this, ListviewCustomizadaFinalActivity.class);
                        it.putExtra("usuario", usuario);
                        startActivity(it);
                        if (delete) {
                            Toast.makeText(ReporteDetalhes.this, "Reporte deletado.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ReporteDetalhes.this, "Selecionar um cadastro.", Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case DialogInterface.BUTTON_NEGATIVE:

                        break;
                }
            }


        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja realmente deletar O reporte" + usuario.getId() + "?")
                .setPositiveButton("Sim", dialogClickListener)
                .setNegativeButton("Não", dialogClickListener).show();


    }
}
