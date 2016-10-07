package com.ags.financemanager.controller;

import android.content.Context;

import com.ags.financemanager.controller.exception.ControllerException;
import com.ags.financemanager.controller.helper.ExceptionHelper;
import com.ags.financemanager.controller.servicos.TipoReceitaServico;
import com.ags.financemanager.controller.servicos.UsuarioServico;
import com.ags.financemanager.controller.servicos.TipoReceitaServico;
import com.ags.financemanager.model.bean.TipoReceita;
import com.ags.financemanager.model.dao.TipoReceitaDAO;
import com.ags.financemanager.model.dao.TipoReceitaDAOImpl;

import java.util.List;

/**
 * Created by Max on 04/10/2016.
 */
public class TipoReceitaControllerImpl extends BaseControllerImpl<TipoReceita> implements TipoReceitaController {

    private TipoReceitaDAO tipoReceitaDAO;
    private ExceptionHelper exceptionHelper;
    private Context context;
    private TipoReceitaServico tipoReceitaServico;

    public TipoReceitaControllerImpl(TipoReceitaDAO tipoReceitaDAO, Context context) {
        this.tipoReceitaDAO = tipoReceitaDAO;
        this.context = context;
        this.exceptionHelper = new ExceptionHelper();
        this.tipoReceitaServico = new TipoReceitaServico(this.context);
    }

    public TipoReceitaControllerImpl(Context context) {
        this.context = context;
        this.tipoReceitaDAO = new TipoReceitaDAOImpl(context);
        this.exceptionHelper = new ExceptionHelper();
        this.tipoReceitaServico = new TipoReceitaServico(this.context);
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

    @Override
    public void sincronizar() {

        try {

            validarDAO();
            int qtd = tipoReceitaDAO.getTodos().size();

            if (qtd == 0) {

                List<TipoReceita> tiposReceitaList = tipoReceitaServico.listarTipoReceita();

                for (TipoReceita tipoReceita : tiposReceitaList) {
                    salvar(tipoReceita);
                }

            }

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewSincronizacaoControllerException(e);
        }

    }

    private void salvar(TipoReceita tipoReceita) {

        try {

            validarDAO();
            tipoReceitaDAO.inserirTipoReceita(tipoReceita);
//            tipoReceitaServico.cadastrarTipoReceita(tipoReceita);

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewSalvarControllerException(tipoReceita, e);
        }

    }
}
