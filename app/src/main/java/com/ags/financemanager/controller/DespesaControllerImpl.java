package com.ags.financemanager.controller;

import android.content.Context;

import com.ags.financemanager.controller.business.ValidadorData;
import com.ags.financemanager.controller.exception.ControllerException;
import com.ags.financemanager.controller.helper.ExceptionHelper;

import java.util.Date;
import java.util.List;

import model.bean.Despesa;
import model.dao.DespesaDAO;

/**
 * Created by Max on 03/10/2016.
 */
public class DespesaControllerImpl extends BaseControllerImpl<Despesa> implements DespesaController {

    private DespesaDAO despesaDAO;
    private ExceptionHelper exceptionHelper;
    private ValidadorData validadorData;

    public DespesaControllerImpl(Context context) {
        this.despesaDAO = new DespesaDAO(context);
        this.exceptionHelper = new ExceptionHelper();
        this.validadorData = new ValidadorData();
    }

    @Override
    public void salvar(Despesa despesa) {
        try {

            validarDAO();

            validarDespesaSalvar(despesa);
            despesaDAO.inserirDespesa(despesa);

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewSalvarControllerException(despesa, e);
        }
    }

    private void validarDAO() {
        if (despesaDAO == null)
            throw new NullPointerException("ReceitaDAO está nulo.");
    }

    private void validarDespesaSalvar(Despesa despesa) {

        if (despesa == null) {
            throw new IllegalArgumentException("Receita não pode ser nulo.");
        } else {

            try {
                despesa.validarUsuario();
                despesa.validarData();
            } catch (Exception e) {
                throw e;
            }

        }

    }

    @Override
    public void salvarTodos(List<Despesa> despesas) {

        Despesa despesaTemp = null;

        try {

            validarDAO();

            for (Despesa despesa : despesas) {
                despesaTemp = despesa;
                validarDespesaSalvar(despesa);
                despesaDAO.inserirDespesa(despesa);
            }

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewSalvarControllerException(despesaTemp, e);
        }
    }

    @Override
    public void excluir(Despesa despesa) {
        try {

            validarDAO();

            validarDespesaExcluir(despesa);

            despesaDAO.excluirDespesa(receita);

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewExcluirControllerException(despesa, e);
        }
    }

    private void validarDespesaExcluir(Despesa despesa) {
        if (despesa == null) {
            throw new IllegalArgumentException("Receita não pode ser nulo.");
        } else {
            try {
                despesa.validarId();
            } catch (Exception e) {
                throw e;
            }
        }
    }

    @Override
    public void excluirTodos(List<Despesa> despesas) {

        Despesa despesaTemp = null;

        try {

            validarDAO();

            for (Despesa despesa : despesas) {
                despesaTemp = despesa;
                validarDespesaExcluir(despesa);
                despesaDAO.excluirDespesa(despesa);
            }

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewSalvarControllerException(despesaTemp, e);
        }
    }

    @Override
    public List<Despesa> getPorData(Date dataInicial, Date dataFinal) {

        try {

            validarDAO();

            this.validadorData.validarDatas(dataInicial, dataFinal);

            int intDataInicial = (int) dataInicial.getTime();
            int intDataFinal = (int) dataFinal.getTime();

            List<Despesa> despesas = despesaDAO.buscarDespesaPorData(intDataInicial, intDataInicial);

            return despesas;

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewConsultarControllerException(e);
        }
    }

}
