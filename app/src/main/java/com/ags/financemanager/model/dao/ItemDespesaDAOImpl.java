package com.ags.financemanager.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.ags.financemanager.model.DBContract;
import com.ags.financemanager.model.DatabaseAccess;
import com.ags.financemanager.model.bean.Despesa;
import com.ags.financemanager.model.bean.ItemDespesa;

/**
 * Created by Maikon Igor on 29/09/2016.
 */

public class ItemDespesaDAOImpl extends DatabaseAccess implements ItemDespesaDAO {

    public ItemDespesaDAOImpl(Context context) {
        super(context);
    }

    @Override
    public long inserirItemDespesa(ItemDespesa item){
        ContentValues values = new ContentValues();
        values.put(DBContract.ItemDespesaTable.COL_ID_DESPESA, item.getDespesa().getId());
        values.put(DBContract.ItemDespesaTable.COL_DATA, item.getData());
        values.put(DBContract.ItemDespesaTable.COL_VALOR, item.getValor());

        long insertedId = getDb().insertWithOnConflict(DBContract.ItemDespesaTable.TABLE_NAME, null, values,
                SQLiteDatabase.CONFLICT_REPLACE);
        return insertedId;
    }

    @Override
    public ArrayList<ItemDespesa> buscarItemDespesaPorData(long dataInicial, long dataFinal){
        ArrayList<ItemDespesa> itemDespesas = new ArrayList<ItemDespesa>();

        String colunas[]={
            DBContract.ItemDespesaTable.COL_ID,
            DBContract.ItemDespesaTable.COL_ID_DESPESA,
            DBContract.ItemDespesaTable.COL_DATA,
            DBContract.ItemDespesaTable.COL_VALOR
        };

        Cursor cursor;
        String clausula = DBContract.ItemDespesaTable.COL_DATA+ " BETWEEN " + dataInicial + " AND "+ dataFinal;
        cursor = getDb().query(DBContract.ItemDespesaTable.TABLE_NAME, colunas, clausula,null,null,null,null);

        while(cursor.moveToNext()) {
            long id = cursor.getLong(cursor
                    .getColumnIndex(DBContract.ItemDespesaTable.COL_ID));
            long idDespesa = cursor.getLong(cursor
                    .getColumnIndex(DBContract.ItemDespesaTable.COL_ID_DESPESA));

            int data = cursor.getInt(cursor
                    .getColumnIndex(DBContract.ItemDespesaTable.COL_DATA));

            int valor = cursor.getInt(cursor
                    .getColumnIndex(DBContract.ItemDespesaTable.COL_VALOR));

            DespesaDAOImpl ddao = new DespesaDAOImpl(getContext());
            Despesa despesa = ddao.buscarDespesa(idDespesa);

            ItemDespesa item = new ItemDespesa(id, despesa,data,valor);
            itemDespesas.add(item);
        }
        return itemDespesas;
    }

    @Override
    public ItemDespesa buscarItemDespesa(long idItem){
        ItemDespesa itemDespesa = null;

        String colunas[]={
                DBContract.ItemDespesaTable.COL_ID,
                DBContract.ItemDespesaTable.COL_ID_DESPESA,
                DBContract.ItemDespesaTable.COL_DATA,
                DBContract.ItemDespesaTable.COL_VALOR
        };

        Cursor cursor;
        String clausula = DBContract.ItemDespesaTable.COL_ID+ " = " + idItem;
        cursor = getDb().query(DBContract.ItemDespesaTable.TABLE_NAME, colunas, clausula,null,null,null,null);

        while(cursor.moveToNext()) {
            long id = cursor.getLong(cursor
                    .getColumnIndex(DBContract.ItemDespesaTable.COL_ID));
            long idDespesa = cursor.getLong(cursor
                    .getColumnIndex(DBContract.ItemDespesaTable.COL_ID_DESPESA));

            int data = cursor.getInt(cursor
                    .getColumnIndex(DBContract.ItemDespesaTable.COL_DATA));

            int valor = cursor.getInt(cursor
                    .getColumnIndex(DBContract.ItemDespesaTable.COL_VALOR));

            DespesaDAOImpl ddao = new DespesaDAOImpl(getContext());
            Despesa despesa = ddao.buscarDespesa(idDespesa);

            itemDespesa = new ItemDespesa(id, despesa,data,valor);
        }
        return itemDespesa;
    }
}
