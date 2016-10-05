package com.ags.financemanager.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.ags.financemanager.model.DBContract;
import com.ags.financemanager.model.DatabaseAccess;
import com.ags.financemanager.model.bean.Receita;
import com.ags.financemanager.model.bean.TipoReceita;
import com.ags.financemanager.model.bean.Usuario;

/**
 * Created by Maikon Igor on 28/09/2016.
 */

public class ReceitaDAOImpl extends DatabaseAccess implements ReceitaDAO {
    public ReceitaDAOImpl(Context context) {
        super(context);
    }

    @Override
    public long inserirReceita(Receita receita){
        ContentValues values = new ContentValues();
        values.put(DBContract.ReceitaTable.COL_DESCRICAO, receita.getDescricao());
        values.put(DBContract.ReceitaTable.COL_DATA, receita.getData());
        values.put(DBContract.ReceitaTable.COL_ID_CATEGORIA_RECEITA, receita.getTipoReceita().getId());
        values.put(DBContract.ReceitaTable.COL_ID_USUARIO,receita.getUsuario().getId());

        long insertedId = getDb().insertWithOnConflict(DBContract.UsuarioTable.TABLE_NAME, null, values,
                SQLiteDatabase.CONFLICT_REPLACE);

        return insertedId;
    }

    @Override
    public ArrayList<Receita> buscarReceitaPorData(long dataInicial, long dataFinal){
        ArrayList<Receita> receitas = new ArrayList<Receita>();

        String colunas[] = {
                DBContract.ReceitaTable.COL_ID,
                DBContract.ReceitaTable.COL_DESCRICAO,
                DBContract.ReceitaTable.COL_DATA,
                DBContract.ReceitaTable.COL_VALOR,
                DBContract.ReceitaTable.COL_ID_CATEGORIA_RECEITA,
                DBContract.ReceitaTable.COL_ID_USUARIO
        };

        Cursor cursor;
        String clausula = DBContract.ReceitaTable.COL_DATA+ " BETWEEN " + dataInicial + " AND "+ dataFinal;
        cursor = getDb().query(DBContract.ReceitaTable.TABLE_NAME, colunas, clausula,null,null,null,null);


        while(cursor.moveToNext()){
            long id = cursor.getLong(cursor
                    .getColumnIndex(DBContract.ReceitaTable.COL_ID));
            String descricao = cursor.getString(cursor
                    .getColumnIndex(DBContract.ReceitaTable.COL_DESCRICAO));
            int data = cursor.getInt(cursor
                    .getColumnIndex(DBContract.ReceitaTable.COL_DATA));
            float valor = cursor.getFloat(cursor
                    .getColumnIndex(DBContract.ReceitaTable.COL_VALOR));
            int categoriaReceita = cursor.getInt(cursor
                    .getColumnIndex(DBContract.ReceitaTable.COL_ID_CATEGORIA_RECEITA));
            int idUsuario = cursor.getInt(cursor
                    .getColumnIndex(DBContract.ReceitaTable.COL_DATA));

            UsuarioDAOImpl udao = new UsuarioDAOImpl(getContext());
            TipoReceitaDAOImpl trdao = new TipoReceitaDAOImpl(getContext());

            Usuario usuario = udao.buscarUsuario(idUsuario);
            TipoReceita tipoReceita = trdao.buscarTipoReceita(categoriaReceita);

            Receita receita = new Receita(descricao,data,valor,tipoReceita,usuario);
            receitas.add(receita);
        }
        return receitas;
    }

    @Override
    public Receita buscarReceita(long idReceita){
        Receita receita = null;

        String colunas[] = {
                DBContract.ReceitaTable.COL_ID,
                DBContract.ReceitaTable.COL_DESCRICAO,
                DBContract.ReceitaTable.COL_DATA,
                DBContract.ReceitaTable.COL_VALOR,
                DBContract.ReceitaTable.COL_ID_CATEGORIA_RECEITA,
                DBContract.ReceitaTable.COL_ID_USUARIO
        };

        Cursor cursor;
        String clausula = DBContract.ReceitaTable.COL_ID + " = " + idReceita;
        cursor = getDb().query(DBContract.ReceitaTable.TABLE_NAME, colunas, clausula,null,null,null,null);


        while(cursor.moveToNext()){
            long id = cursor.getLong(cursor
                    .getColumnIndex(DBContract.ReceitaTable.COL_ID));
            String descricao = cursor.getString(cursor
                    .getColumnIndex(DBContract.ReceitaTable.COL_DESCRICAO));
            int data = cursor.getInt(cursor
                    .getColumnIndex(DBContract.ReceitaTable.COL_DATA));
            float valor = cursor.getFloat(cursor
                    .getColumnIndex(DBContract.ReceitaTable.COL_VALOR));
            int categoriaReceita = cursor.getInt(cursor
                    .getColumnIndex(DBContract.ReceitaTable.COL_ID_CATEGORIA_RECEITA));
            int idUsuario = cursor.getInt(cursor
                    .getColumnIndex(DBContract.ReceitaTable.COL_DATA));

            UsuarioDAOImpl udao = new UsuarioDAOImpl(getContext());
            TipoReceitaDAOImpl trdao = new TipoReceitaDAOImpl(getContext());

            Usuario usuario = udao.buscarUsuario(idUsuario);
            TipoReceita tipoReceita = trdao.buscarTipoReceita(categoriaReceita);

            receita = new Receita(descricao,data,valor,tipoReceita,usuario);
        }
        return receita;
    }
}
