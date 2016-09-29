package com.ags.financemanager.controller;

import android.content.Context;

import java.util.List;

import model.bean.Receita;
import model.dao.ReceitaDAO;

/**
 * Created by Max on 29/09/2016.
 */
public class ReceitaControllerImpl extends BaseControllerImpl<Receita> implements ReceitaController {

    private ReceitaDAO receitaDAO;

    public ReceitaControllerImpl(ReceitaDAO receitaDAO) {
        this.receitaDAO = receitaDAO;
    }

    @Override
    public void salvar(Receita receita) {
        // TODO
    }

    @Override
    public void salvarTodos(List<Receita> receitas) {
        //TODO
    }

    @Override
    public void excluir(Receita receita) {
        //TODO
    }

    @Override
    public void excluirTodos(List<Receita> receitas) {
        //TODO
    }

    @Override
    public List<Receita> getTodos() {
        //TODO
        return null;
    }
}
