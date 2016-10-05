package com.ags.financemanager.model.dao;

import java.util.List;

import com.ags.financemanager.model.bean.TipoDespesa;

/**
 * Created by Max on 04/10/2016.
 */
public interface TipoDespesaDAO {

    TipoDespesa buscarTipoDespesa(long idTipoDespesa);

    List<TipoDespesa> getTodos();
}
