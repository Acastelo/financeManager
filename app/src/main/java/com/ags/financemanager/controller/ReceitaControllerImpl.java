package com.ags.financemanager.controller;

import android.content.Context;

import com.ags.financemanager.controller.business.ValidadorData;
import com.ags.financemanager.controller.exception.ControllerException;
import com.ags.financemanager.controller.helper.ExceptionHelper;
import com.ags.financemanager.controller.servicos.ReceitaServico;
import com.ags.financemanager.model.bean.Receita;
import com.ags.financemanager.model.dao.ReceitaDAO;
import com.ags.financemanager.model.dao.ReceitaDAOImpl;

import java.util.Date;
import java.util.List;

/**
 * Created by Max on 29/09/2016.
 */
public class ReceitaControllerImpl extends BaseControllerImpl<Receita> implements ReceitaController {

    private ReceitaDAO receitaDAO;
    private ExceptionHelper exceptionHelper;
    private ValidadorData validadorData;
    private Context context;
    private ReceitaServico receitaServico;

    public ReceitaControllerImpl(ReceitaDAO receitaDAO, Context context) {
        this.receitaDAO = receitaDAO;
        this.context = context;
        this.exceptionHelper = new ExceptionHelper();
        this.validadorData = new ValidadorData();
        this.receitaServico = new ReceitaServico(context);
    }

    public ReceitaControllerImpl(Context context) {
        this.context = context;
        this.receitaDAO = new ReceitaDAOImpl(context);
        this.exceptionHelper = new ExceptionHelper();
        this.validadorData = new ValidadorData();
        this.receitaServico = new ReceitaServico(context);
    }

    @Override
    public void salvar(Receita receita) {
        try {

            validarDAO();

            validarReceitaSalvar(receita);
            receitaDAO.inserirReceita(receita);

//            receitaServico.cadastrarReceita(receita);

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
//                receitaServico.cadastrarReceita(receita);
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
//            receitaServico.excluirReceita(receita.getId());

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
//                receitaServico.excluirReceita(receita.getId());
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

    @Override
    public void sincronizar() {
        try {

            validarDAO();
            int qtd = receitaDAO.getTodos().size();

            if (qtd == 0) {

                ReceitaServico servico = new ReceitaServico(this.context);
                List<Receita> receitaList = servico.listarReceitas();
                salvarTodos(receitaList);

            }

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewSincronizacaoControllerException(e);
        }
    }

}

