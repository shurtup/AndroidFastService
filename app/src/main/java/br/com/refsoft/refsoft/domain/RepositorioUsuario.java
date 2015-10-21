package br.com.refsoft.refsoft.domain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class RepositorioUsuario  {

    private SQLiteDatabase db;
    private SQliteHelper dbHelper;
    private List<Usuario> listaUsuarios;

    public String[] getColunasTabUsuarios(){
        String[] USUARIOS_COLUNA_TAB_USUARIOS = new String[]{"id_usuario", "nome", "email", "login", "senha"};
        return USUARIOS_COLUNA_TAB_USUARIOS;
    }

    public RepositorioUsuario(Context context){
        try {

            dbHelper = new SQliteHelper(context);
            listaUsuarios = new ArrayList<Usuario>();
            db = dbHelper.getWritableDatabase();
        }catch (Exception e){
            Log.e("Erro: ", e.getMessage());
        }
    }
    public void close(){
        if (db != null){
            if (db.isOpen()){
                db.close();
            }
        }
    }
    public List<Usuario> listUsuario(){
        Cursor cursor = null;
        listaUsuarios.clear();

        try {
            cursor = db.query("usuarios", getColunasTabUsuarios(), null, null, null, null, "id_usuario desc", null);
            if(cursor.getCount() > 0){
                while(cursor.moveToNext()){
                    Usuario usuarioLinha = new Usuario();
                    usuarioLinha.setId(cursor.getInt(cursor.getColumnIndex("id_usuario")));
                    usuarioLinha.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                    usuarioLinha.setLogin(cursor.getString(cursor.getColumnIndex("login")));
                    usuarioLinha.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
                    listaUsuarios.add(usuarioLinha);
                }
            }
        }catch(Exception e){
            Log.e("Erro: ", e.getMessage());
        }
        finally {
            if(cursor != null){
                if(!cursor.isClosed()){
                    cursor.close();
                }
            }
        }
        return listaUsuarios;
    }

    public ContentValues contentValues(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());
        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());
        return values;
    }


    public long insertUsuario(Usuario novoUsuario){
        long id = 0;
        try {
            ContentValues values = contentValues(novoUsuario);
            id = db.insert("usuarios", null, values);

        }catch(Exception e){
            Log.e("Erro: ", e.getMessage());
        }
        return id;
    }
    public boolean excluirUsuario(String ID_USUARIO) {
        boolean resultadoExclusao = false;
        try {
            String where = "id_usuario=?";
            String[] args = new String[]{};
        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        }
        return resultadoExclusao;
    }
}
