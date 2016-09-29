package model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import model.DBContract;
import model.DatabaseAccess;
import model.bean.Despesa;
import model.bean.ItemDespesa;

/**
 * Created by Maikon Igor on 29/09/2016.
 */

public class ItemDespesaDAO extends DatabaseAccess {

    public ItemDespesaDAO(Context context) {
        super(context);
    }

    public long inserirItemDespesa(ItemDespesa item){
        ContentValues values = new ContentValues();
        values.put(DBContract.ItemDespesaTable.COL_ID_DESPESA, item.getDespesa().getId());
        values.put(DBContract.ItemDespesaTable.COL_DATA, item.getData());
        values.put(DBContract.ItemDespesaTable.COL_VALOR, item.getValor());

        long insertedId = getDb().insertWithOnConflict(DBContract.ItemDespesaTable.TABLE_NAME, null, values,
                SQLiteDatabase.CONFLICT_REPLACE);
        return insertedId;
    }

    public ArrayList<ItemDespesa> buscarItemDespesaPorData(int dataInicial, int dataFinal){
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

            DespesaDAO ddao = new DespesaDAO(getContext());
            Despesa despesa = ddao.buscarDespesa(idDespesa);

            ItemDespesa item = new ItemDespesa(id, despesa,data,valor);
            itemDespesas.add(item);
        }
        return itemDespesas;
    }

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

            DespesaDAO ddao = new DespesaDAO(getContext());
            Despesa despesa = ddao.buscarDespesa(idDespesa);

            itemDespesa = new ItemDespesa(id, despesa,data,valor);
        }
        return itemDespesa;
    }
}
