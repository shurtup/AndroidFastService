package br.com.refsoft.refsoft.domain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Gabriel on 23/09/2015.
 */
public class SQliteHelper extends SQLiteOpenHelper {
    private static final String TAG = "sql";

    // Nome do banco e versão
    private static final String NOME_BANCO = "fastservice";
    private static final int VERSAO_BANCO = 1;

    public SQliteHelper(Context context) {
        // context, nome do banco, factory, versão
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando a Tabela usuario...");
        db.execSQL("create table if not exists usuarios (id_usuario integer primary key autoincrement,nome text, email text,login text, senha text);");
        Log.d(TAG, "Tabela usuario criada com sucesso.");
        db.execSQL("create table if not exists reportes (id_reporte integer primary key autoincrement,tipo text, descricao text,status text, data text, hora text, longitude text, latitude text, endereco text, id_user integer, foreign key(id_user) references usuarios (id_usuario));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion == 1 && newVersion == 2) {
        }
    }
}
