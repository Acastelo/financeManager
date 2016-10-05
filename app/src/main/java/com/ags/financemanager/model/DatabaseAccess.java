package com.ags.financemanager.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Maikon Igor on 28/09/2016.
 */

public class DatabaseAccess {

    private SafeMoneyDBHelper helper;
    private SQLiteDatabase db;
    private Context context;

    /*
     * Constante usada para o teste de sucesso de UPDATE
     */
    protected final int SUCESSO = 1;

    public DatabaseAccess(Context context ){
        this.helper = SafeMoneyDBHelper.getInstance(context);
        this.db = helper.getWritableDatabase();

        this.context = context;
    }

    public SafeMoneyDBHelper getHelper() {
        return helper;
    }

    public void setHelper(SafeMoneyDBHelper helper) {
        this.helper = helper;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }



}
