package com.ags.financemanager.controller;

import com.ags.financemanager.controller.business.ValidadorData;
import com.ags.financemanager.controller.exception.ControllerException;
import com.ags.financemanager.controller.helper.ExceptionHelper;
import com.ags.financemanager.model.bean.Receita;
import com.ags.financemanager.model.dao.ReceitaDAO;

import java.util.Date;
import java.util.List;

/**
 * Created by Max on 29/09/2016.
 */
public class ReceitaControllerImpl extends BaseControllerImpl<Receita> implements ReceitaController {

    private ReceitaDAO receitaDAO;
    private ExceptionHelper exceptionHelper;
    private ValidadorData validadorData;

    public ReceitaControllerImpl(ReceitaDAO receitaDAO) {
        this.receitaDAO = receitaDAO;
        this.exceptionHelper = new ExceptionHelper();
        this.validadorData = new ValidadorData();
    }

    @Override
    public void salvar(Receita receita) {
        try {

            validarDAO();

            validarReceitaSalvar(receita);
            receitaDAO.inserirReceita(receita);

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewSalvarControllerException(receita, e);
        }
    }


    @Override
    public void salvarTodos(List<Receita> receitas) {

        Receita recitaTemp = null;

        try {

            validarDAO();

            for (Receita receita : receitas) {
                recitaTemp = receita;
                validarReceitaSalvar(receita);
                receitaDAO.inserirReceita(receita);
            }

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewSalvarControllerException(recitaTemp, e);
        }
    }

    @Override
    public void excluir(Receita receita) {
        try {

            validarDAO();
            validarReceitaExcluir(receita);

            receitaDAO.excluirReceita(receita);

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewExcluirControllerException(receita, e);
        }
    }

    @Override
    public void excluirTodos(List<Receita> receitas) {

        Receita recitaTemp = null;

        try {

            validarDAO();

            for (Receita receita : receitas) {
                recitaTemp = receita;
                validarReceitaExcluir(receita);
                receitaDAO.excluirReceita(receita);
            }

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewSalvarControllerException(recitaTemp, e);
        }
    }

    @Override
    public List<Receita> getPorData(Date dataInicial, Date dataFinal) {

        try {

            validarDAO();

            this.validadorData.validarDatas(dataInicial, dataFinal);

            List<Receita> receitas = receitaDAO.buscarReceitaPorData(dataInicial.getTime(), dataFinal.getTime());

            return receitas;

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewConsultarControllerException(e);
        }
    }

    private void validarDAO() {
        if (receitaDAO == null)
            throw new NullPointerException("ReceitaDAO está nulo.");
    }

    private void validarReceitaSalvar(Receita receita) {

        if (receita == null) {
            throw new IllegalArgumentException("Receita não pode ser nulo.");
        } else {

            try {
                receita.validarUsuario();
                receita.validarData();
            } catch (Exception e) {
                throw e;
            }

        }

    }

    private void validarReceitaExcluir(Receita receita) {
        if (receita == null) {
            throw new IllegalArgumentException("Receita não pode ser nulo.");
        } else {
            try {
                receita.validarId();
            } catch (Exception e) {
                throw e;
            }
        }
    }

}

