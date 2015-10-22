package br.com.refsoft.refsoft.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.refsoft.refsoft.R;
import br.com.refsoft.refsoft.domain.Reporte;

public class ListaCustomizadaAdapter extends ArrayAdapter<Reporte> {

    private Context context;
    private List<Reporte> reporteList;
    private int layoutResourceId;

    public ListaCustomizadaAdapter(Context context, int layoutResourceId, List<Reporte> reportelist) {
        super(context, layoutResourceId, reportelist);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.reporteList = reportelist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Reporte reporte = reporteList.get(position);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(layoutResourceId, parent, false);

        TextView textTipo = (TextView) view.findViewById(R.id.textTipo);
        textTipo.setText(reporte.getTipoReporte());

        TextView textDescricao = (TextView) view.findViewById(R.id.textDescricao);
        textDescricao.setText(reporte.getDescricaoReporte());

        TextView textStatus = (TextView) view.findViewById(R.id.textStatus);
        textStatus.setText(String.valueOf(reporte.getStatusReporte()));

        ImageView img = (ImageView) view.findViewById(R.id.imageFastService);
        img.setImageResource(reporte.getBanner());

        return view;
    }
}
