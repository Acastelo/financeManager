package com.ags.financemanager.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ags.financemanager.model.DBContract;
import com.ags.financemanager.model.DatabaseAccess;
import com.ags.financemanager.model.bean.TipoDespesa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maikon Igor on 29/09/2016.
 */

public class TipoDespesaDAOImpl extends DatabaseAccess implements TipoDespesaDAO {
    public TipoDespesaDAOImpl(Context context) {
        super(context);
    }

    @Override
    public void inserirTipoDespesa(TipoDespesa tipoDespesa) {
        ContentValues values = new ContentValues();
        values.put(DBContract.TipoDespesaTable.COL_ID, tipoDespesa.getId());
        values.put(DBContract.TipoDespesaTable.COL_DESCRICAO, tipoDespesa.getDescricao());
        long insertedId = getDb().insertWithOnConflict(DBContract.TipoDespesaTable.TABLE_NAME, null, values,
                SQLiteDatabase.CONFLICT_REPLACE);
    }

    @Override
    public TipoDespesa buscarTipoDespesa(long idTipoDespesa){
        TipoDespesa tipo = null;
        String colunas[] = {
                DBContract.TipoDespesaTable.COL_ID,
                DBContract.TipoDespesaTable.COL_DESCRICAO
        };

        Cursor cursor;
        String clausula = DBContract.TipoDespesaTable.COL_ID + " = " + idTipoDespesa;
        cursor = getDb().query(DBContract.TipoDespesaTable.TABLE_NAME, colunas, clausula,null,null,null,null);

        while (cursor.moveToNext()){
            long idTipo = cursor.getLong(cursor
                    .getColumnIndex(DBContract.TipoDespesaTable.COL_ID));
            String desc = cursor.getString(cursor
                    .getColumnIndex(DBContract.TipoDespesaTable.COL_DESCRICAO));

            tipo = new TipoDespesa(idTipo,desc);
        }
        return tipo;
    }

    @Override
    public List<TipoDespesa> getTodos() {

        List<TipoDespesa> tipos = new ArrayList<TipoDespesa>();
        String colunas[] = {
                DBContract.TipoDespesaTable.COL_ID,
                DBContract.TipoDespesaTable.COL_DESCRICAO
        };
        Cursor cursor;

        cursor = getDb().query(DBContract.TipoDespesaTable.TABLE_NAME, colunas, null,null,null,null,null);
        while (cursor.moveToNext()){
            long idTipo = cursor.getLong(cursor
                    .getColumnIndex(DBContract.TipoDespesaTable.COL_ID));
            String desc = cursor.getString(cursor
                    .getColumnIndex(DBContract.TipoDespesaTable.COL_DESCRICAO));

           TipoDespesa tipo = new TipoDespesa(idTipo,desc);
            tipos.add(tipo);
        }
        return tipos;
    }
}
