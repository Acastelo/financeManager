package com.ags.financemanager.controller.servicos;

import android.content.Context;

import com.ags.financemanager.model.bean.Despesa;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Maikon Igor on 06/10/2016.
 */

public class DespesaServico {

    private Context context;

    public DespesaServico(Context context) {
        this.context = context;
    }

    public List<Despesa> listarDespesas(){
        final List<Despesa> despesas = new ArrayList<Despesa>();
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://safemoney-onhandcs.rhcloud.com/safemoney/apirest/despesa/listar";
        final Gson gson = new Gson();
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray array) {
                try{
                    for (int i = 0; i< array.length(); i++){
                        JSONObject item = array.getJSONObject(i);
                        Despesa despesa = gson.fromJson(String.valueOf(item), Despesa.class);
                        despesas.add(despesa);
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

        return despesas;
    }

    public Despesa buscarDespesa(long idDespesa){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://safemoney-onhandcs.rhcloud.com/safemoney/apirest/despesa/buscar/"+idDespesa;
        final Gson gson = new Gson();
        final Despesa[] despesa = new Despesa[1];
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Despesa d = gson.fromJson(String.valueOf(response), Despesa.class);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

        return despesa[0];
    }


}
