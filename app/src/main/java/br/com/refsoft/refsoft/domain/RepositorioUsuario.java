package br.com.refsoft.refsoft.domain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class RepositorioUsuario {

    private SQLiteDatabase db;
    private SQliteHelper dbHelper;

    public RepositorioUsuario(Context context) {
        try {
            dbHelper = new SQliteHelper(context);
            db = dbHelper.getWritableDatabase();
        } catch (Exception e) {
            Log.e("Erro: ", e.getMessage());
        }
    }

    public ContentValues contentValues(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());
        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());
        return values;
    }


    public long insertUsuario(Usuario novoUsuario) {
        long id = 0;
        try {
            ContentValues values = contentValues(novoUsuario);
            id = db.insert("usuarios", null, values);

        } catch (Exception e) {
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

    public Usuario validarLogin(String login, String senha) {
        String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";
        String[] selectionArgs = new String[]{login, senha};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        Usuario usuarioLinha = new Usuario();
        if (cursor.moveToFirst()) {
            usuarioLinha.setId(cursor.getLong(cursor.getColumnIndex("id_usuario")));
            usuarioLinha.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            usuarioLinha.setLogin(cursor.getString(cursor.getColumnIndex("login")));
            usuarioLinha.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
        }
        return usuarioLinha;
    }
}
