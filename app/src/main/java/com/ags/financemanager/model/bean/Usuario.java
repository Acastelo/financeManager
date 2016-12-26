package com.ags.financemanager.model.bean;

import com.orm.SugarRecord;

/**
 * Created by Maikon Igor on 28/09/2016.
 */
public class Usuario extends SugarRecord{
    private long id;
    private String nome;
    private String email;
    private String senha;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(long id, String nome, String email, String senha) {
        this(nome, email, senha);
        this.id = id;
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
