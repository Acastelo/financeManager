package com.ags.financemanager.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ags.financemanager.model.DBContract;
import com.ags.financemanager.model.DatabaseAccess;
import com.ags.financemanager.model.bean.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maikon Igor on 29/09/2016.
 */

public class UsuarioDAO extends DatabaseAccess implements GenericDAO {

    public UsuarioDAO(Context context) {
        super(context);
    }

    @Override
    public long inserir(Usuario usuario){
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
    public Usuario buscarUsuarioByEmail(String mail) {
        String colunas[]={
                DBContract.UsuarioTable.COL_ID,
                DBContract.UsuarioTable.COL_NOME,
                DBContract.UsuarioTable.COL_EMAIL,
                DBContract.UsuarioTable.COL_SENHA
        };

        Cursor cursor;
        String clausula = DBContract.UsuarioTable.COL_EMAIL + " = '"
                + mail + "'";
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

    @Override
    public boolean excluirUsuario(Usuario usuario) {
        String where = DBContract.UsuarioTable.COL_ID +" = "+ usuario.getId();
        return getDb().delete(DBContract.UsuarioTable.TABLE_NAME,where,null) > 0;
    }

    @Override
    public List<Usuario> getTodos() {
        List<Usuario> tipos = new ArrayList<Usuario>();
        String colunas[]={
                DBContract.UsuarioTable.COL_ID,
                DBContract.UsuarioTable.COL_NOME,
                DBContract.UsuarioTable.COL_EMAIL,
                DBContract.UsuarioTable.COL_SENHA
        };

        Cursor cursor;
        cursor = getDb().query(DBContract.UsuarioTable.TABLE_NAME, colunas, null,null,null,null,null);
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
            tipos.add(usuario);
        }
        return tipos;
    }
}
