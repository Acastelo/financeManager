package com.ags.financemanager.controller;

import android.content.Context;

import com.ags.financemanager.controller.exception.ControllerException;
import com.ags.financemanager.controller.helper.ExceptionHelper;
import com.ags.financemanager.controller.servicos.TipoReceitaServico;
import com.ags.financemanager.controller.servicos.UsuarioServico;
import com.ags.financemanager.model.bean.TipoReceita;
import com.ags.financemanager.model.dao.TipoReceitaDAO;

import java.util.List;

/**
 * Created by Max on 04/10/2016.
 */
public class TipoReceitaControllerImpl extends BaseControllerImpl<TipoReceita> implements TipoReceitaController {

    private TipoReceitaDAO tipoReceitaDAO;
    private ExceptionHelper exceptionHelper;
    private Context contexto;

    public TipoReceitaControllerImpl(TipoReceitaDAO tipoReceitaDAO) {
        this.tipoReceitaDAO = tipoReceitaDAO;
        this.exceptionHelper = new ExceptionHelper();
    }
    public TipoReceitaControllerImpl(TipoReceitaDAO tipoReceitaDAO, Context ctx) {
        this.tipoReceitaDAO = tipoReceitaDAO;
        this.exceptionHelper = new ExceptionHelper();
        this.contexto = ctx;
    }

    @Override
    public void salvarTodos(List<TipoReceita> tipos) {

        TipoReceita tipo = null;

        try {

            validarDAO();

            for (TipoReceita t : tipos) {

                tipoReceitaDAO.inserirTipoReceita(t);
            }

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewSalvarControllerException(tipo, e);
        }
    }

    @Override
    public List<TipoReceita> getTodos() {
        try {

            validarDAO();

            List<TipoReceita> tiposReceita = tipoReceitaDAO.getTodos();

            return tiposReceita;

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewConsultarControllerException(e);
        }
    }

    private void validarDAO() {
        if (tipoReceitaDAO == null)
            throw new NullPointerException("TipoReceitaDAO está nulo.");
    }
}
