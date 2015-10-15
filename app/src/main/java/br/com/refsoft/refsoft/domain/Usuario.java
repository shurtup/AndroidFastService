package br.com.refsoft.refsoft.domain;

import java.io.Serializable;

/**
 * Created by Gabriel on 22/09/2015.
 */
public class Usuario implements Serializable {

    public long id;
    public String login;
    public String nome;
    public String email;
    public String senha;



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
