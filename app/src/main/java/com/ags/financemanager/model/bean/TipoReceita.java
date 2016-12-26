package com.ags.financemanager.model.bean;

import com.orm.SugarRecord;

/**
 * Created by Maikon Igor on 28/09/2016.
 */

public class TipoReceita extends SugarRecord{
    private long id;
    private String descricao;

    public TipoReceita(long id, String descricao) {
        this(descricao);
        this.id = id;
    }

    public TipoReceita(String descricao) {
        this.descricao = descricao;
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
