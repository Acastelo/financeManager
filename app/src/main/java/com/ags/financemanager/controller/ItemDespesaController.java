package com.ags.financemanager.controller;

import com.ags.financemanager.model.bean.Despesa;
import com.ags.financemanager.model.bean.ItemDespesa;

import java.util.List;

/**
 * Created by Max on 29/09/2016.
 */
public interface ItemDespesaController extends BaseController<ItemDespesa> {

    List<ItemDespesa> getItensDespesaByDespesa(Despesa despesa);

    void salvar(ItemDespesa itemDespesa);

    void excluir(ItemDespesa itemDespesa);
}
