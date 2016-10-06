package com.ags.financemanager.controller.servicos;

import android.content.Context;

import com.ags.financemanager.model.bean.TipoReceita;
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

public class TipoReceitaServico {
    private Context context;

    public TipoReceitaServico(Context context) {
        this.context = context;
    }

    public List<TipoReceita> listarTipoReceita(){
        final List<TipoReceita> itens = new ArrayList<>();

        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://safemoney-onhandcs.rhcloud.com/safemoney/apirest/tipodespesa/listar";
        final Gson gson = new Gson();

        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray array) {
                try{
                    for (int i = 0; i< array.length(); i++){
                        JSONObject item = array.getJSONObject(i);
                        TipoReceita tipo = gson.fromJson(String.valueOf(item), TipoReceita.class);
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
