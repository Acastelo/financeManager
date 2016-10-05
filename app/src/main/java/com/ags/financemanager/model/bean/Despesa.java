package com.ags.financemanager.model.bean;

/**
 * Created by Maikon Igor on 28/09/2016.
 */

public class Despesa {

    private long id;
    private String descricao;
    private int qtdParcelas;
    private Usuario usuario;
    private TipoDespesa tipoDespesa;

    public Despesa(String descricao, int qtdParcelas, Usuario usuario, TipoDespesa tipoDespesa) {
        this.descricao = descricao;
        this.qtdParcelas = qtdParcelas;
        this.usuario = usuario;
        this.tipoDespesa = tipoDespesa;
    }

    public Despesa(long id, String descricao, int qtdParcelas, Usuario usuario, TipoDespesa tipoDespesa) {
        this(descricao, qtdParcelas, usuario, tipoDespesa);
        this.id = id;
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

    public int getQtdParcelas() {
        return qtdParcelas;
    }

    public void setQtdParcelas(int qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoDespesa getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    public void validarUsuario() {
        if (getUsuario() == null)
            throw new IllegalArgumentException("Usuário da despesa não pode ser nulo.");
    }

    public void validarId() {
        if (getId() <= 0)
            throw new IllegalArgumentException("ID inválido.");

    }

}

