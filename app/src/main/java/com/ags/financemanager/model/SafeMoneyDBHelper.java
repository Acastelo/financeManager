package com.ags.financemanager.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Maikon Igor on 28/09/2016.
 */

public class SafeMoneyDBHelper extends SQLiteOpenHelper {

    private static SafeMoneyDBHelper db;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "safemoney.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_RECEITA =
            "CREATE TABLE "+ DBContract.ReceitaTable.TABLE_NAME + " (" +
                    DBContract.ReceitaTable.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    DBContract.ReceitaTable.COL_DESCRICAO + TEXT_TYPE + COMMA_SEP +
                    DBContract.ReceitaTable.COL_DATA +  INT_TYPE + COMMA_SEP +
                    DBContract.ReceitaTable.COL_VALOR + REAL_TYPE + COMMA_SEP +
                    DBContract.ReceitaTable.COL_ID_USUARIO + INT_TYPE + COMMA_SEP+
                    DBContract.ReceitaTable.COL_ID_CATEGORIA_RECEITA + INT_TYPE + COMMA_SEP+
                    " FOREIGN KEY ("+ DBContract.ReceitaTable.COL_ID_USUARIO +") REFERENCES "+ DBContract.UsuarioTable.TABLE_NAME +"("+DBContract.UsuarioTable.COL_ID+")"+ COMMA_SEP +
                    " FOREIGN KEY ("+ DBContract.ReceitaTable.COL_ID_CATEGORIA_RECEITA +") REFERENCES "+ DBContract.TipoReceitaaTable.TABLE_NAME +"("+DBContract.TipoReceitaaTable.COL_ID+")"+
            " )";

    private static final String SQL_CREATE_DESPESA =
            "CREATE TABLE " + DBContract.DespesaTable.TABLE_NAME + " (" +
                    DBContract.DespesaTable.COL_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    DBContract.DespesaTable.COL_DESCRICAO + TEXT_TYPE + COMMA_SEP +
                    DBContract.DespesaTable.COL_QTD_PARCELAS + INT_TYPE + COMMA_SEP +
                    DBContract.DespesaTable.COL_ID_USUARIO + INT_TYPE + COMMA_SEP+
                    DBContract.DespesaTable.COL_ID_CATEGORIA_DESPESA + INT_TYPE + COMMA_SEP+
                    " FOREIGN KEY ("+ DBContract.DespesaTable.COL_ID_USUARIO +") REFERENCES "+ DBContract.UsuarioTable.TABLE_NAME +"("+DBContract.UsuarioTable.COL_ID+")" +COMMA_SEP +
                    " FOREIGN KEY ("+DBContract.DespesaTable.COL_ID_CATEGORIA_DESPESA +") REFERENCES "+ DBContract.TipoDespesaTable.TABLE_NAME +"("+DBContract.TipoDespesaTable.COL_ID+")"+
            ")";

    private static final String SQL_CREATE_USUARIO =
            "CREATE TABLE " + DBContract.UsuarioTable.TABLE_NAME + " (" +
                    DBContract.UsuarioTable.COL_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    DBContract.UsuarioTable.COL_NOME + TEXT_TYPE + COMMA_SEP +
                    DBContract.UsuarioTable.COL_EMAIL + TEXT_TYPE + COMMA_SEP +
                    DBContract.UsuarioTable.COL_SENHA + TEXT_TYPE +
            " )";

    private static final String SQL_CREATE_ITEM_DESPESA =
            "CREATE TABLE " + DBContract.ItemDespesaTable.TABLE_NAME + " (" +
                    DBContract.ItemDespesaTable.COL_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    DBContract.ItemDespesaTable.COL_DATA +  INT_TYPE + COMMA_SEP +
                    DBContract.ItemDespesaTable.COL_VALOR + REAL_TYPE + COMMA_SEP +
                    DBContract.ItemDespesaTable.COL_ID_DESPESA + INT_TYPE + COMMA_SEP +
                    " FOREIGN KEY ("+ DBContract.ItemDespesaTable.COL_ID_DESPESA +") REFERENCES "+ DBContract.DespesaTable.TABLE_NAME +"("+DBContract.DespesaTable.COL_ID+"))";

    private static final String SQL_CREATE_TIPO_DESPESA =
            "CREATE TABLE " + DBContract.TipoDespesaTable.TABLE_NAME + " (" +
                    DBContract.TipoDespesaTable.COL_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    DBContract.TipoDespesaTable.COL_DESCRICAO + TEXT_TYPE +
             " )";

    private static final String SQL_CREATE_TIPO_RECEITA =
            "CREATE TABLE " + DBContract.TipoReceitaaTable.TABLE_NAME + " (" +
                    DBContract.TipoReceitaaTable.COL_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    DBContract.TipoReceitaaTable.COL_DESCRICAO + TEXT_TYPE +
            " )";

    private static final String SQL_INSERT_TIPO_DESPESA =
            "INSERT INTO "+ DBContract.TipoDespesaTable.TABLE_NAME + " ("+DBContract.TipoDespesaTable.COL_DESCRICAO +") "+
                    "VALUES('LAZER')";

    private static final String SQL_INSERT_TIPO_RECEITA =
            "INSERT INTO "+ DBContract.TipoReceitaaTable.TABLE_NAME + " ("+DBContract.TipoReceitaaTable.COL_DESCRICAO +") "+
                    "VALUES('SALARIO')";

    private SafeMoneyDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public synchronized static SafeMoneyDBHelper getInstance(Context context){
        if(db == null)
            db = new SafeMoneyDBHelper(context);
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_RECEITA);
        db.execSQL(SQL_CREATE_DESPESA);
        db.execSQL(SQL_CREATE_USUARIO);
        db.execSQL(SQL_CREATE_ITEM_DESPESA);
        db.execSQL(SQL_CREATE_TIPO_DESPESA);
        db.execSQL(SQL_CREATE_TIPO_RECEITA);
        //db.execSQL(SQL_INSERT_TIPO_DESPESA);
        //db.execSQL(SQL_INSERT_TIPO_RECEITA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
