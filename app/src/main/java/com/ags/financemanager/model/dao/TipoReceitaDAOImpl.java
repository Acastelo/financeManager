package com.ags.financemanager.model.dao;

import android.content.Context;
import android.database.Cursor;

import com.ags.financemanager.model.DBContract;
import com.ags.financemanager.model.DatabaseAccess;
import com.ags.financemanager.model.bean.TipoReceita;

/**
 * Created by Maikon Igor on 29/09/2016.
 */

public class TipoReceitaDAOImpl extends DatabaseAccess implements TipoReceitaDAO {

    public TipoReceitaDAOImpl(Context context) {
        super(context);
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
}
