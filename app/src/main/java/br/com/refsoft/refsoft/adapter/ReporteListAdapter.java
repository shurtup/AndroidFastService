package br.com.refsoft.refsoft.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.refsoft.refsoft.R;
import br.com.refsoft.refsoft.domain.Reporte;

/**
 * Created by Gabriel on 03/10/2015.
 */
public class ReporteListAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private List<Reporte> lista;

    public ReporteListAdapter(Context context, List<Reporte> lista) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Reporte reportes = lista.get(position);
        View view = inflater.inflate(R.layout.reporte_linha_tabela, null);
        TextView tipo = (TextView) view.findViewById(R.id.txTipo);
        tipo.setText(reportes.getTipoReporte());
        TextView descricao = (TextView) view.findViewById(R.id.txDescricao);
        descricao.setText(reportes.getDescricaoReporte());
        return view;
    }
}
