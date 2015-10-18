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

    public ListaCustomizadaAdapter(Context context, int layoutResourceId, List<Reporte> reportelist){
        super(context, layoutResourceId, reportelist);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.reporteList = reportelist;
    }

 /*   @Override
    public int getCount() {
        return reporteList.size();
    }

    @Override
    public Object getItem(int position) {
        return reporteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Recupera o estado da posição atual
        Reporte reporte = reporteList.get(position);

        // Cria uma instância do layout XML para os objetos correspondentes
        // na View
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();

       /* LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       */
        View view = inflater.inflate(layoutResourceId, parent, false);

        // Estado - Abreviação
        TextView textTipo = (TextView)view.findViewById(R.id.textTipo);
        textTipo.setText(reporte.getTipoReporte());

        // Capital
        TextView textDescricao = (TextView)view.findViewById(R.id.textDescricao);
        textDescricao.setText(reporte.getDescricaoReporte());

        // Status
        TextView textStatus = (TextView)view.findViewById(R.id.textStatus);
        textStatus.setText(String.valueOf(reporte.getStatusReporte()));

        // Imagem
        ImageView img = (ImageView)view.findViewById(R.id.imageFastService);
        img.setImageResource(reporte.getBanner());

        return view;
    }
}
