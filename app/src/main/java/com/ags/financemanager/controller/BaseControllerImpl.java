package com.ags.financemanager.controller;

import android.content.Context;

/**
 * Created by Max on 29/09/2016.
 */
public abstract class BaseControllerImpl<T> implements BaseController<T> {

    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
