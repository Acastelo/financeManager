package com.ags.financemanager.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ags.financemanager.model.DBContract;
import com.ags.financemanager.model.DatabaseAccess;
import com.ags.financemanager.model.bean.Usuario;

/**
 * Created by Maikon Igor on 29/09/2016.
 */

public class UsuarioDAOImpl extends DatabaseAccess implements UsuarioDAO {
    public UsuarioDAOImpl(Context context) {
        super(context);
    }

    @Override
    public long inserirUsuario(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put(DBContract.UsuarioTable.COL_NOME, usuario.getNome());
        values.put(DBContract.UsuarioTable.COL_EMAIL, usuario.getEmail());
        values.put(DBContract.UsuarioTable.COL_SENHA, usuario.getSenha());

        long insertedId = getDb().insertWithOnConflict(DBContract.UsuarioTable.TABLE_NAME, null, values,
                SQLiteDatabase.CONFLICT_REPLACE);

        return insertedId;
    }

    @Override
    public Usuario buscarUsuario(long idusuario){
        String colunas[]={
                DBContract.UsuarioTable.COL_ID,
                DBContract.UsuarioTable.COL_NOME,
                DBContract.UsuarioTable.COL_EMAIL,
                DBContract.UsuarioTable.COL_SENHA
        };

        Cursor cursor;
        String clausula = DBContract.UsuarioTable.COL_ID + " = "
                + idusuario;
        cursor = getDb().query(DBContract.UsuarioTable.TABLE_NAME, colunas, clausula,null,null,null,null);
        Usuario usuario = null;
        while (cursor.moveToNext()){
            long id = cursor.getLong(cursor
                    .getColumnIndex(DBContract.UsuarioTable.COL_ID));
            String nome = cursor.getString(cursor
                    .getColumnIndex(DBContract.UsuarioTable.COL_NOME));
            String email = cursor.getString(cursor
                    .getColumnIndex(DBContract.UsuarioTable.COL_EMAIL));
            String senha = cursor.getString(cursor
                    .getColumnIndex(DBContract.UsuarioTable.COL_SENHA));

            usuario = new Usuario(id, nome,email,senha);
        }
        return usuario;
    }

    @Override
    public boolean updateUsuario(Usuario usuario){
        String colunas[]={
                DBContract.UsuarioTable.COL_ID,
                DBContract.UsuarioTable.COL_NOME,
                DBContract.UsuarioTable.COL_EMAIL,
                DBContract.UsuarioTable.COL_SENHA
        };

        ContentValues values = new ContentValues();
        values.put(DBContract.UsuarioTable.COL_ID, usuario.getId());
        values.put(DBContract.UsuarioTable.COL_NOME, usuario.getNome());
        values.put(DBContract.UsuarioTable.COL_EMAIL, usuario.getEmail());
        values.put(DBContract.UsuarioTable.COL_SENHA, usuario.getSenha());

        String clausula = DBContract.UsuarioTable.COL_ID + " = " + usuario.getId();
        long affectedRows = getDb().update(DBContract.UsuarioTable.TABLE_NAME, values, clausula, null);

        return affectedRows != 0;
    }
}