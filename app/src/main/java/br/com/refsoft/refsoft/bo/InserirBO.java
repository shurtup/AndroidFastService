package br.com.refsoft.refsoft.bo;

import android.content.Context;

import br.com.refsoft.refsoft.domain.RepositorioUsuario;
import br.com.refsoft.refsoft.domain.SQliteHelper;
import br.com.refsoft.refsoft.domain.Usuario;
import br.com.refsoft.refsoft.validacao.ValidacaoInserir;


public class InserirBO {

    private SQliteHelper sqliteHelper;
    private RepositorioUsuario repositorioUsuario;

    public InserirBO(Context context) {

        sqliteHelper = new SQliteHelper(context);
    }

        public ValidacaoInserir inserirUsuario(Usuario usuario){
            ValidacaoInserir retorno = new ValidacaoInserir();
            repositorioUsuario.insertUsuario(usuario);
            retorno.setValido(true);
            retorno.setMensagem("Usuario cadastrado com sucesso!");
                return retorno;
            }
        }


