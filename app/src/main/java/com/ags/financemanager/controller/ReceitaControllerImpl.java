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

    public ReceitaControllerImpl(Context context, ReceitaDAO receitaDAO) {
        super.setContext(context);
        this.receitaDAO = receitaDAO;
    }

    @Override
    public void salvar(Receita objeto) {
        //TODO
    }

    @Override
    public void salvarTodos(List<Receita> objetos) {
        //TODO
    }

    @Override
    public void excluir(Receita objeto) {
        //TODO
    }

    @Override
    public void excluirTodos(List<Receita> objetos) {
        //TODO
    }

    @Override
    public List<Receita> getTodos() {
        //TODO
        return null;
    }
}
