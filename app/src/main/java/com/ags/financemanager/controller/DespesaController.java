package com.ags.financemanager.controller;

import com.ags.financemanager.model.bean.Despesa;

import java.util.List;

/**
 * Created by Max on 29/09/2016.
 */
public interface DespesaController extends BaseController<Despesa> {

    void salvar(Despesa despesa);

    void salvarTodos(List<Despesa> despesas);

    void excluir(Despesa despesa);

    void excluirTodos(List<Despesa> despesas);

}
