package model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import model.DBContract;
import model.DatabaseAccess;
import model.bean.Receita;
import model.bean.TipoReceita;
import model.bean.Usuario;

/**
 * Created by Maikon Igor on 28/09/2016.
 */

public class ReceitaDAO extends DatabaseAccess {
    public ReceitaDAO(Context context) {
        super(context);
    }

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

    public ArrayList<Receita> buscarReceitaPorData(int dataInicial, int dataFinal){
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

            UsuarioDAO udao = new UsuarioDAO(getContext());
            TipoReceitaDAO trdao = new TipoReceitaDAO(getContext());

            Usuario usuario = udao.buscarUsuario(idUsuario);
            TipoReceita tipoReceita = trdao.buscarTipoReceita(categoriaReceita);

            Receita receita = new Receita(descricao,data,valor,tipoReceita,usuario);
            receitas.add(receita);
        }
        return receitas;
    }

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

            UsuarioDAO udao = new UsuarioDAO(getContext());
            TipoReceitaDAO trdao = new TipoReceitaDAO(getContext());

            Usuario usuario = udao.buscarUsuario(idUsuario);
            TipoReceita tipoReceita = trdao.buscarTipoReceita(categoriaReceita);

            receita = new Receita(descricao,data,valor,tipoReceita,usuario);
        }
        return receita;
    }
}
