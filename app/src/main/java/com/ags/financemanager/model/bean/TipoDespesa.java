package com.ags.financemanager.model.bean;

/**
 * Created by Maikon Igor on 28/09/2016.
 */

public class TipoDespesa {
    private long id;
    private String descricao;

    public TipoDespesa(long id, String descricao) {
        this(descricao);
        this.id = id;
    }

    public TipoDespesa(String descricao) {
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
