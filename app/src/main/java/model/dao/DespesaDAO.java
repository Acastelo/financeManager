package model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import model.DBContract;
import model.DatabaseAccess;
import model.bean.Despesa;
import model.bean.TipoDespesa;
import model.bean.Usuario;

/**
 * Created by Maikon Igor on 29/09/2016.
 */

public class DespesaDAO extends DatabaseAccess {
    public DespesaDAO(Context context) {
        super(context);
    }

    public long inserirDespesa(Despesa despesa){
        ContentValues values = new ContentValues();
        values.put(DBContract.DespesaTable.COL_DESCRICAO, despesa.getDescricao());
        values.put(DBContract.DespesaTable.COL_QTD_PARCELAS, despesa.getQtdParcelas());
        values.put(DBContract.DespesaTable.COL_ID_USUARIO, despesa.getUsuario().getId());
        values.put(DBContract.DespesaTable.COL_ID_CATEGORIA_DESPESA, despesa.getTipoDespesa().getId());

        long insertedId = getDb().insertWithOnConflict(DBContract.DespesaTable.TABLE_NAME, null, values,
                SQLiteDatabase.CONFLICT_REPLACE);
        return insertedId;
    }

    public Despesa buscarDespesa(long idDespesa){
        Despesa despesas = null;

        String colunas[]= {
            DBContract.DespesaTable.COL_ID,
            DBContract.DespesaTable.COL_DESCRICAO,
            DBContract.DespesaTable.COL_QTD_PARCELAS,
            DBContract.DespesaTable.COL_ID_USUARIO,
            DBContract.DespesaTable.COL_ID_CATEGORIA_DESPESA
        };

        Cursor cursor;
        String clausula = DBContract.DespesaTable.COL_ID+ " = " + idDespesa;
        cursor = getDb().query(DBContract.ReceitaTable.TABLE_NAME, colunas, clausula,null,null,null,null);
        while (cursor.moveToNext()){
            long id = cursor.getLong(cursor
                    .getColumnIndex(DBContract.DespesaTable.COL_ID));
            String desc = cursor.getString(cursor
                    .getColumnIndex(DBContract.DespesaTable.COL_DESCRICAO));
            int qtdParcelas = cursor.getInt(cursor
                    .getColumnIndex(DBContract.DespesaTable.COL_QTD_PARCELAS));
            long idUsuario = cursor.getLong(cursor
                    .getColumnIndex(DBContract.DespesaTable.COL_ID_USUARIO));
            long idTipoDespesa = cursor.getLong(cursor
                    .getColumnIndex(DBContract.DespesaTable.COL_ID_CATEGORIA_DESPESA));

            UsuarioDAO udao = new UsuarioDAO(getContext());
            TipoDespesaDAO tddao = new TipoDespesaDAO(getContext());

            Usuario usuario = udao.buscarUsuario(idUsuario);
            TipoDespesa tipoDespesa = tddao.buscarTipoDespesa(idTipoDespesa);

            despesas = new Despesa(id,desc,qtdParcelas,usuario,tipoDespesa);
        }
        return despesas;
    }
}
