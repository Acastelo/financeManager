package com.ags.financemanager.model.dao;

import java.util.ArrayList;

import com.ags.financemanager.model.bean.ItemDespesa;

/**
 * Created by Max on 04/10/2016.
 */
public interface ItemDespesaDAO {

    long inserirItemDespesa(ItemDespesa item);

    ArrayList<ItemDespesa> buscarItemDespesaPorData(long dataInicial, long dataFinal);

    ItemDespesa buscarItemDespesa(long idItem);
}
