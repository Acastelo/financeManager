package com.ags.financemanager.controller;

import java.util.Date;
import java.util.List;

import com.ags.financemanager.model.bean.Receita;

/**
 * Created by Max on 29/09/2016.
 */
public interface ReceitaController extends BaseController<Receita> {

    void salvar(Receita receita);

    void salvarTodos(List<Receita> receitas);

    void excluir(Receita receita);

    void excluirTodos(List<Receita> receitas);

    List<Receita> getPorData(Date dataInicial, Date dataFinal);
}
