package com.ags.financemanager.model.bean;

import com.orm.SugarRecord;

/**
 * Created by Maikon Igor on 28/09/2016.
 */

public class ItemDespesa extends SugarRecord{
    private long id;
    private Despesa despesa;
    private int data;
    private float valor;

    public ItemDespesa(Despesa despesa, int data, float valor) {
        this.despesa = despesa;
        this.data = data;
        this.valor = valor;
    }

    public ItemDespesa(long id, Despesa despesa, int data, float valor) {
        this(despesa, data, valor);
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Despesa getDespesa() {
        return despesa;
    }

    public void setDespesa(Despesa despesa) {
        this.despesa = despesa;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
