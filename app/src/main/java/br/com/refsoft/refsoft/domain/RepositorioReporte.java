package br.com.refsoft.refsoft.domain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import br.com.refsoft.refsoft.R;


public class RepositorioReporte {

    private SQLiteDatabase db;
    private SQliteHelper dbHelper;
    private List<Reporte> listaReporte;
    private int images = R.drawable.ic_icon_72;

    public RepositorioReporte(Context context) {
        try {
            dbHelper = new SQliteHelper(context);
            listaReporte = new ArrayList<>();
            db = dbHelper.getWritableDatabase();
        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        }
    }

    public List<Reporte> listaQuery(long id_usuario) {
        Cursor cursor = db.rawQuery("SELECT * FROM reportes WHERE id_user = " + id_usuario, null);
        try {
            while (cursor.moveToNext()) {
                Reporte reporteLinha = new Reporte();
                reporteLinha.setIdReporte(cursor.getInt(cursor.getColumnIndex("id_reporte")));
                reporteLinha.setTipoReporte(cursor.getString(cursor.getColumnIndex("tipo")));
                reporteLinha.setDescricaoReporte(cursor.getString(cursor.getColumnIndex("descricao")));
                reporteLinha.setStatusReporte(cursor.getString(cursor.getColumnIndex("status")));
                reporteLinha.setDataAbertura(Date.valueOf(cursor.getString(cursor.getColumnIndex("data"))));
                reporteLinha.setHoraAbertura(Time.valueOf(cursor.getString(cursor.getColumnIndex("hora"))));
                reporteLinha.setLatitude(cursor.getString(cursor.getColumnIndex("latitude")));
                reporteLinha.setLongitude(cursor.getString(cursor.getColumnIndex("longitude")));
                reporteLinha.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
                reporteLinha.setBanner(images);
                listaReporte.add(reporteLinha);
            }
        } catch (Exception e) {
            // Log.e("Erro: ", e.getMessage());
            String msg = (e.getMessage() == null) ? "Error" : e.getMessage();
            Log.i("Error", msg);
        } finally {
            if (cursor != null) {
                if (!cursor.isClosed()) {
                    cursor.close();
                }
            }
        }
        return listaReporte;
    }

    public ContentValues contentValues(Reporte reporte, long id_usuario) {
        ContentValues values = new ContentValues();
        values.put("id_user", id_usuario);
        values.put("tipo", reporte.getTipoReporte());
        values.put("descricao", reporte.getDescricaoReporte());
        values.put("status", reporte.getStatusReporte());
        values.put("data", String.valueOf(reporte.getDataAbertura()));
        values.put("hora", String.valueOf(reporte.getHoraAbertura()));
        values.put("latitude", reporte.getLatitude());
        values.put("longitude", reporte.getLongitude());
        values.put("endereco", reporte.getEndereco());
        return values;
    }

    public ContentValues contentValues2(Reporte reporte) {
        ContentValues values = new ContentValues();
        values.put("tipo", reporte.getTipoReporte());
        values.put("descricao", reporte.getDescricaoReporte());
        values.put("status", reporte.getStatusReporte());
        values.put("data", String.valueOf(reporte.getDataAbertura()));
        values.put("hora", String.valueOf(reporte.getHoraAbertura()));
        values.put("latitude", reporte.getLatitude());
        values.put("longitude", reporte.getLongitude());
        values.put("endereco", reporte.getEndereco());
        return values;
    }

    public long insertReporte(Reporte novoReporte, long id_usuario) {
        long id = 0;
        try {
            ContentValues values = contentValues(novoReporte, id_usuario);
            id = db.insert("reportes", null, values);
        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        }
        return id;
    }


    public boolean excluirReporte(String ID) {
        boolean resultadoExclusao = false;
        try {
            String where = "id_reporte = ?";
            String[] args = new String[]{ID};
            int num = db.delete("reportes", where, args);

            if (num == 1) {
                resultadoExclusao = true;
            }
        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        }
        return resultadoExclusao;
    }

    public boolean alterarReporte(Reporte reporte) {
        boolean resultadoAlteracao = false;
        try {
            String where = "id_reporte = ?";
            String[] args = new String[]{String.valueOf(reporte.getIdReporte())};
            int num = db.update("reportes", contentValues2(reporte), where, args);
            if (num == 1) {
                resultadoAlteracao = true;
            }
        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        }
        return resultadoAlteracao;
    }
}
