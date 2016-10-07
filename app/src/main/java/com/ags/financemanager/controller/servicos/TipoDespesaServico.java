package com.ags.financemanager.controller.servicos;

import android.content.Context;

import com.ags.financemanager.model.bean.TipoDespesa;
import com.ags.financemanager.model.dao.TipoDespesaDAO;
import com.ags.financemanager.model.dao.TipoDespesaDAOImpl;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Maikon Igor on 06/10/2016.
 */

public class TipoDespesaServico {

    private Context context;

    public TipoDespesaServico(Context context) {
        this.context = context;
    }

    public List<TipoDespesa> listarTipoDespesa(){
        final List<TipoDespesa> itens = new ArrayList<>();

        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://safemoney-onhandcs.rhcloud.com/safemoney/apirest/tipodespesa/listar";
        final Gson gson = new Gson();
        final TipoDespesaDAO tDao = new TipoDespesaDAOImpl(context);
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray array) {
                try{
                    for (int i = 0; i< array.length(); i++){
                        JSONObject item = array.getJSONObject(i);
                        TipoDespesa tipo = gson.fromJson(String.valueOf(item), TipoDespesa.class);
                        tDao.inserirTipoDespesa(tipo);
                        itens.add(tipo);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

        });

        return itens;
    }
}
