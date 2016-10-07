package com.ags.financemanager.controller;

import com.ags.financemanager.model.bean.TipoReceita;

import java.util.List;

/**
 * Created by Max on 29/09/2016.
 */
public interface TipoReceitaController extends BaseController<TipoReceita> {


    List<TipoReceita> getTodos();
    void salvarTodos(List<TipoReceita> tipos);
}
