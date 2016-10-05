package com.ags.financemanager.model.dao;

import java.util.ArrayList;

import com.ags.financemanager.model.bean.Receita;

/**
 * Created by Max on 04/10/2016.
 */
public interface ReceitaDAO {

    long inserirReceita(Receita receita);

    ArrayList<Receita> buscarReceitaPorData(long dataInicial, long dataFinal);

    Receita buscarReceita(long idReceita);

    boolean excluirReceita(Receita receita);
}
