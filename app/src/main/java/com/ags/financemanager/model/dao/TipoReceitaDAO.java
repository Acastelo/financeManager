package com.ags.financemanager.model.dao;

import java.util.List;

import com.ags.financemanager.model.bean.TipoReceita;

/**
 * Created by Max on 04/10/2016.
 */
public interface TipoReceitaDAO {

    TipoReceita buscarTipoReceita(long id);

    List<TipoReceita> getTodos();
    
    long inserirTipoReceita(TipoReceita tipo);

}
