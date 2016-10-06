package com.ags.financemanager.controller;

import android.content.Context;

import com.ags.financemanager.controller.business.ValidadorData;
import com.ags.financemanager.controller.exception.ControllerException;
import com.ags.financemanager.controller.helper.ExceptionHelper;
import com.ags.financemanager.controller.servicos.DespesaServico;
import com.ags.financemanager.model.bean.Despesa;
import com.ags.financemanager.model.dao.DespesaDAO;
import com.ags.financemanager.model.dao.DespesaDAOImpl;

import java.util.List;

/**
 * Created by Max on 03/10/2016.
 */
public class DespesaControllerImpl extends BaseControllerImpl<Despesa> implements DespesaController {

    private DespesaDAO despesaDAO;
    private ExceptionHelper exceptionHelper;
    private ValidadorData validadorData;
    private Context context;

    public DespesaControllerImpl(DespesaDAO despesaDAO, Context context) {
        this.despesaDAO = despesaDAO;
        this.context = context;
        this.exceptionHelper = new ExceptionHelper();
        this.validadorData = new ValidadorData();
    }

    public DespesaControllerImpl(Context context) {
        this.context = context;
        this.despesaDAO = new DespesaDAOImpl(context);
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

            despesaDAO.excluirDespesa(despesa);

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewExcluirControllerException(despesa, e);
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

    private void validarDAO() {
        if (despesaDAO == null)
            throw new NullPointerException("DespesaDAO está nulo.");
    }

    private void validarDespesaSalvar(Despesa despesa) {

        if (despesa == null) {
            throw new IllegalArgumentException("Despesa não pode ser nulo.");
        } else {

            try {
                despesa.validarUsuario();
            } catch (Exception e) {
                throw e;
            }

        }

    }

    private void validarDespesaExcluir(Despesa despesa) {
        if (despesa == null) {
            throw new IllegalArgumentException("Despesa não pode ser nulo.");
        } else {
            try {
                despesa.validarId();
            } catch (Exception e) {
                throw e;
            }
        }
    }

    @Override
    public void sincronizar() {

        try {

            validarDAO();
            int qtd = despesaDAO.getTodos().size();

            if(qtd == 0) {

                DespesaServico servico = new DespesaServico(this.context);
                List<Despesa> despesaList = servico.listarDespesas();
                salvarTodos(despesaList);

            }

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewSincronizacaoControllerException(e);
        }
    }
}
