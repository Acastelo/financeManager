package com.ags.financemanager.model.dao;

import com.ags.financemanager.model.bean.Despesa;

import java.util.List;

/**
 * Created by Max on 04/10/2016.
 */
public interface DespesaDAO {

    long inserirDespesa(Despesa despesa);

    Despesa buscarDespesa(long idDespesa);

    boolean excluirDespesa(Despesa despesa);

    List<Despesa> getTodos();
}
