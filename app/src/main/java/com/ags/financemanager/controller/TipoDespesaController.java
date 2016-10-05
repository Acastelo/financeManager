package com.ags.financemanager.controller;

import java.util.List;

import com.ags.financemanager.model.bean.TipoDespesa;

/**
 * Created by Max on 29/09/2016.
 */
public interface TipoDespesaController extends BaseController<TipoDespesa> {

    List<TipoDespesa> getTodos();
}
