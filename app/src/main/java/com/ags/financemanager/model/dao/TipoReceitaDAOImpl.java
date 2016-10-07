package com.ags.financemanager.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ags.financemanager.model.DBContract;
import com.ags.financemanager.model.DatabaseAccess;
import com.ags.financemanager.model.bean.TipoReceita;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maikon Igor on 29/09/2016.
 */

public class TipoReceitaDAOImpl extends DatabaseAccess implements TipoReceitaDAO {

    public TipoReceitaDAOImpl(Context context) {
        super(context);
    }

    @Override
    public long inserirTipoReceita(TipoReceita tipo) {
        ContentValues values = new ContentValues();
        values.put(DBContract.TipoReceitaaTable.COL_ID, tipo.getId());
        values.put(DBContract.TipoReceitaaTable.COL_DESCRICAO, tipo.getDescricao());
        long insertedId = getDb().insertWithOnConflict(DBContract.TipoReceitaaTable.TABLE_NAME, null, values,
                SQLiteDatabase.CONFLICT_REPLACE);
        return insertedId;
    }

    public TipoReceita buscarTipoReceita(long id){
        TipoReceita tipo = null;
        String colunas[] = {
            DBContract.TipoReceitaaTable.COL_ID,
            DBContract.TipoReceitaaTable.COL_DESCRICAO
        };

        Cursor cursor;
        String clausula = DBContract.TipoReceitaaTable.COL_ID + " = " + id;
        cursor = getDb().query(DBContract.TipoReceitaaTable.TABLE_NAME, colunas, clausula,null,null,null,null);

        while (cursor.moveToNext()){
            long idTipo = cursor.getLong(cursor
                    .getColumnIndex(DBContract.TipoReceitaaTable.COL_ID));
            String desc = cursor.getString(cursor
                    .getColumnIndex(DBContract.TipoReceitaaTable.COL_DESCRICAO));

            tipo = new TipoReceita(idTipo,desc);
        }
        return tipo;
    }

    @Override
    public List<TipoReceita> getTodos() {
        List<TipoReceita> tipos = new ArrayList<TipoReceita>();
        String colunas[] = {
                DBContract.TipoReceitaaTable.COL_ID,
                DBContract.TipoReceitaaTable.COL_DESCRICAO
        };

        Cursor cursor;
        cursor = getDb().query(DBContract.TipoReceitaaTable.TABLE_NAME, colunas, null,null,null,null,null);

        while (cursor.moveToNext()){
            long idTipo = cursor.getLong(cursor
                    .getColumnIndex(DBContract.TipoReceitaaTable.COL_ID));
            String desc = cursor.getString(cursor
                    .getColumnIndex(DBContract.TipoReceitaaTable.COL_DESCRICAO));

            TipoReceita tipo = new TipoReceita(idTipo,desc);
            tipos.add(tipo);
        }
        return tipos;
    }


}
