package com.ags.financemanager.controller;

import android.content.Context;

import com.ags.financemanager.controller.exception.ControllerException;
import com.ags.financemanager.controller.helper.ExceptionHelper;
import com.ags.financemanager.model.bean.Despesa;
import com.ags.financemanager.model.bean.ItemDespesa;
import com.ags.financemanager.model.dao.ItemDespesaDAO;
import com.ags.financemanager.model.dao.ItemDespesaDAOImpl;

import java.util.List;

/**
 * Created by Max on 06/10/2016.
 */
public class ItemDespesaControllerImpl extends BaseControllerImpl<ItemDespesa> implements ItemDespesaController {

    private ItemDespesaDAO itemDespesaDAO;
    private Context context;
    private ExceptionHelper exceptionHelper;

    public ItemDespesaControllerImpl(Context context) {
        this.context = context;
        this.itemDespesaDAO = new ItemDespesaDAOImpl(context);
        this.exceptionHelper = new ExceptionHelper();
    }

    public ItemDespesaControllerImpl(Context context, ItemDespesaDAO itemDespesaDAO) {
        this.context = context;
        this.itemDespesaDAO = itemDespesaDAO;
        this.exceptionHelper = new ExceptionHelper();
    }

    @Override
    public List<ItemDespesa> getItensDespesaByDespesa(Despesa despesa) {
        try {

            validarDAO();

            if (despesa == null)
                throw new NullPointerException("Despesa não pode ser nulo.");

            List<ItemDespesa> itensDespesa = itemDespesaDAO.buscarItemDespesaPorDespesa(despesa);

            return itensDespesa;

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewConsultarControllerException(e);
        }
    }

    @Override
    public void salvar(ItemDespesa itemDespesa) {

        try {

            validarDAO();
            itemDespesaDAO.inserirItemDespesa(itemDespesa);

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewSalvarControllerException(itemDespesa, e);
        }
    }

    @Override
    public void excluir(ItemDespesa itemDespesa) {
        try {

            validarDAO();
            itemDespesa.validarId();

            itemDespesaDAO.excluirItemDespesa(itemDespesa);

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewExcluirControllerException(itemDespesa, e);
        }
    }

    @Override
    public void sincronizar() {

        try {

            validarDAO();
            int qtd = itemDespesaDAO.getTodos().size();

            if (qtd == 0) {

                ItemDespesaServico servico = new ItemReceitaServico(this.context);
                List<ItemDespesa> itemDespesaList = servico.listarItensDespesa();

                for (ItemDespesa itemDespesa : itemDespesaList) {
                    salvar(itemDespesa);
                }

            }

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewSincronizacaoControllerException(e);
        }

    }

    private void validarDAO() {
        if (itemDespesaDAO == null)
            throw new NullPointerException("ItemDespesaDAO está nulo.");
    }
}
