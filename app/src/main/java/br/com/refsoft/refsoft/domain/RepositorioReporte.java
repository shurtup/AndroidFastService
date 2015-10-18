package br.com.refsoft.refsoft.domain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.refsoft.refsoft.R;


public class RepositorioReporte {

    private SQLiteDatabase db;
    private SQliteHelper dbHelper;
    private List<Reporte> listaReporte;
    public static final String CATEGORIA = "FastService";

    String[] campos = new String[]{"id_reporte", "id_user", "tipo", "descricao", "status", "data", "hora", "latitude", "longitude", "endereco"};

    private int images = R.drawable.ic_icon_72;

    public String[] getColunasTabReporte() {
        String[] USUARIOS_COLUNA_TAB_USUARIOS = new String[]{"id_reporte", "id_user", "tipo", "descricao", "status", "data", "hora", "latitude", "longitude", "endereco"};
        return USUARIOS_COLUNA_TAB_USUARIOS;
    }

    public RepositorioReporte(Context context) {
        try {
            dbHelper = new SQliteHelper(context);
            listaReporte = new ArrayList<Reporte>();
            db = dbHelper.getWritableDatabase();
        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        }
    }

    public void close() {
        if (db != null) {
            if (db.isOpen()) {
                db.close();
            }
        }
    }
    public Cursor getCursor(){
        try {
            return db.query("reportes", campos, null, null, null, null, null, null);
        }catch (android.database.SQLException e){
            Log.e(CATEGORIA, "Erro ao buscar os reportes: " + e.toString());
            return null;
        }
    }


    public List <Reporte> listaQuery(long id_usuario){
            Cursor cursor = db.rawQuery("SELECT * FROM reportes WHERE id_user = " + id_usuario, null);

        try {
                while (cursor.moveToNext()) {
                    Reporte reporteLinha = new Reporte();
                    reporteLinha.setIdReporte(cursor.getInt(cursor.getColumnIndex("id_reporte")));
                    reporteLinha.setTipoReporte(cursor.getString(cursor.getColumnIndex("tipo")));
                    reporteLinha.setDescricaoReporte(cursor.getString(cursor.getColumnIndex("descricao")));
                    reporteLinha.setStatusReporte(cursor.getString(cursor.getColumnIndex("status")));
                    reporteLinha.setDataAbertura(cursor.getString(cursor.getColumnIndex("data")));
                    reporteLinha.setHoraAbertura(cursor.getString(cursor.getColumnIndex("hora")));
                    reporteLinha.setLatitude(cursor.getString(cursor.getColumnIndex("latitude")));
                    reporteLinha.setLongitude(cursor.getString(cursor.getColumnIndex("longitude")));
                    reporteLinha.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
                    reporteLinha.setBanner(images);
                    listaReporte.add(reporteLinha);
            }
        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        } finally {
            if (cursor != null) {
                if (!cursor.isClosed()) {
                    cursor.close();
                }
            }
        }
        return listaReporte;
    }



    public List<Reporte> listReporte() {
        Cursor cursor = getCursor();
        listaReporte.clear();

        try {
                while (cursor.moveToNext()) {
                    Reporte reporteLinha = new Reporte();
                    reporteLinha.setIdReporte(cursor.getInt(cursor.getColumnIndex("id_reporte")));
                    reporteLinha.setTipoReporte(cursor.getString(cursor.getColumnIndex("tipo")));
                    reporteLinha.setDescricaoReporte(cursor.getString(cursor.getColumnIndex("descricao")));
                    reporteLinha.setStatusReporte(cursor.getString(cursor.getColumnIndex("status")));
                    reporteLinha.setDataAbertura(cursor.getString(cursor.getColumnIndex("data")));
                    reporteLinha.setHoraAbertura(cursor.getString(cursor.getColumnIndex("hora")));
                    reporteLinha.setLatitude(cursor.getString(cursor.getColumnIndex("latitude")));
                    reporteLinha.setLongitude(cursor.getString(cursor.getColumnIndex("longitude")));
                    reporteLinha.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
                    listaReporte.add(reporteLinha);
            }
        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
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
        values.put("data", reporte.getDataAbertura());
        values.put("hora", reporte.getHoraAbertura());
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



    public boolean excluirReporte(String ID_REPORTE) {
        boolean resultadoExclusao = false;
        try {
            String where = "id_reporte=?";
            String[] args = new String[]{ID_REPORTE};
            int num = db.delete("reportes", where, args);

            if (num == 1) {
                resultadoExclusao = true;
            }
        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        }
        return resultadoExclusao;
    }

    public boolean alterarReporte(Reporte reporte, long id_usuario) {
        boolean resultadoAlteracao = false;
        try {
            String where = "id_reporte=?";
            String[] args = new String[]{String.valueOf(reporte.getIdReporte())};
            int num = db.update("reportes", contentValues(reporte, id_usuario), where, args);
            if (num == 1) {
                resultadoAlteracao = true;
            }
        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        }
        return resultadoAlteracao;
    }

    public Reporte buscaIndividualReporte(String reporteTipo){
        Cursor cursor = getCursor();
        Reporte reporteLinha = new Reporte();
        String where = "tipo=?";
        String[] args = new String[]{reporteTipo};
        try {
            cursor = db.query( "reportes", getColunasTabReporte(), where, args, null, null, null);
            if(cursor.getCount() > 0){
                while (cursor.moveToNext()){
                    reporteLinha.setIdReporte(cursor.getInt(cursor.getColumnIndex("id_reporte")));
                    reporteLinha.setTipoReporte(cursor.getString(cursor.getColumnIndex("tipo")));
                    reporteLinha.setDescricaoReporte(cursor.getString(cursor.getColumnIndex("descricao")));
                    reporteLinha.setStatusReporte(cursor.getString(cursor.getColumnIndex("status")));
                    reporteLinha.setDataAbertura(cursor.getString(cursor.getColumnIndex("data")));
                    reporteLinha.setHoraAbertura(cursor.getString(cursor.getColumnIndex("hora")));
                    reporteLinha.setLatitude(cursor.getString(cursor.getColumnIndex("latitude")));
                    reporteLinha.setLongitude(cursor.getString(cursor.getColumnIndex("longitude")));
                    reporteLinha.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
                }
            }
        }catch (Exception e){
            Log.e("Erro: ", e.toString());
        }
        finally {
            if(cursor != null){
                if(!cursor.isClosed()){
                    cursor.close();
                }
            }
        }
        return reporteLinha;
    }
}
