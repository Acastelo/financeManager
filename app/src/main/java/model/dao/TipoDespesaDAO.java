package model.dao;

import android.content.Context;
import android.database.Cursor;

import model.DBContract;
import model.DatabaseAccess;
import model.bean.TipoDespesa;
import model.bean.TipoReceita;

/**
 * Created by Maikon Igor on 29/09/2016.
 */

public class TipoDespesaDAO extends DatabaseAccess {
    public TipoDespesaDAO(Context context) {
        super(context);
    }

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
}
