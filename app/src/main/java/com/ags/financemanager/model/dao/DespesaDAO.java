package com.ags.financemanager.model.dao;

import com.ags.financemanager.model.bean.Despesa;

/**
 * Created by Max on 04/10/2016.
 */
public interface DespesaDAO {

    long inserirDespesa(Despesa despesa);

    Despesa buscarDespesa(long idDespesa);

    boolean excluirDespesa(Despesa despesa);
}
