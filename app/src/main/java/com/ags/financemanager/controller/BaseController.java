package com.ags.financemanager.controller;

import java.util.Date;
import java.util.List;

import model.bean.Receita;

/**
 * Created by Max on 29/09/2016.
 */
public interface BaseController<T> {

    void salvar(T objeto);

    void salvarTodos(List<T> objetos);

    void excluir(T objeto);

    void excluirTodos(List<T> objetos);

    List<T> getPorData(Date dataInicial, Date dataFinal);
}
