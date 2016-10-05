package com.ags.financemanager.controller;

import com.ags.financemanager.controller.exception.ControllerException;
import com.ags.financemanager.controller.helper.ExceptionHelper;
import com.ags.financemanager.model.bean.TipoDespesa;
import com.ags.financemanager.model.dao.TipoDespesaDAO;

import java.util.List;

/**
 * Created by Max on 04/10/2016.
 */
public class TipoDespesaControllerImpl extends BaseControllerImpl<TipoDespesa> implements TipoDespesaController {


    private TipoDespesaDAO tipoDespesaDAO;
    private ExceptionHelper exceptionHelper;


    public TipoDespesaControllerImpl(TipoDespesaDAO tipoDespesaDAO) {
        this.tipoDespesaDAO = tipoDespesaDAO;
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
}
