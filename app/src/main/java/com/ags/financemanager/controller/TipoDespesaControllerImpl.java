package com.ags.financemanager.controller;

import android.content.Context;

import com.ags.financemanager.controller.exception.ControllerException;
import com.ags.financemanager.controller.helper.ExceptionHelper;
import com.ags.financemanager.model.bean.TipoDespesa;
import com.ags.financemanager.model.bean.TipoReceita;
import com.ags.financemanager.model.dao.TipoDespesaDAO;
import com.ags.financemanager.model.dao.TipoDespesaDAOImpl;

import java.util.List;

/**
 * Created by Max on 04/10/2016.
 */
public class TipoDespesaControllerImpl extends BaseControllerImpl<TipoDespesa> implements TipoDespesaController {


    private TipoDespesaDAO tipoDespesaDAO;
    private ExceptionHelper exceptionHelper;
    private Context context;

    public TipoDespesaControllerImpl(TipoDespesaDAO tipoDespesaDAO, Context context) {
        this.tipoDespesaDAO = tipoDespesaDAO;
        this.context = context;
        this.exceptionHelper = new ExceptionHelper();
    }

    public TipoDespesaControllerImpl(Context context) {
        this.context = context;
        this.tipoDespesaDAO = new TipoDespesaDAOImpl(context);
        this.exceptionHelper = new ExceptionHelper();
    }

    @Override
    public List<TipoDespesa> getTodos() {

        try {
            validarDAO();

            List<TipoDespesa> tiposDespesa = tipoDespesaDAO.getTodos();

            return tiposDespesa;

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewConsultarControllerException(e);
        }

    }

    private void validarDAO() {
        if (tipoDespesaDAO == null)
            throw new NullPointerException("TipoDespesaDAO está nulo.");
    }

    @Override
    public void sincronizar() {
        try {

            validarDAO();
            int qtd = tipoDespesaDAO.getTodos().size();

            if (qtd == 0) {

                TipoDespesaServico servico = new TipoReceitaServico(this.context);
                List<TipoDespesa> tiposDespesaList = servico.listarTiposDespesa();

                for (TipoDespesa tipoDespesa : tiposDespesaList) {
                    salvar(tipoDespesa);
                }

            }

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewSincronizacaoControllerException(e);
        }
    }

    private void salvar(TipoDespesa tipoDespesa) {
        try {

            validarDAO();
            tipoDespesaDAO.inserirTipoDespesa(tipoDespesa);

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewSalvarControllerException(tipoDespesa, e);
        }
    }
}
