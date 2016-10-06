package com.ags.financemanager.controller.servicos;

import android.content.Context;
import android.widget.Toast;

import com.ags.financemanager.model.bean.ItemDespesa;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by Maikon Igor on 06/10/2016.
 */

public class ItemDespesaServico {

    private Context context;

    public ItemDespesaServico(Context context) {
        this.context = context;
    }

    public List<ItemDespesa> listarItemDespesas(){
        final List<ItemDespesa> itens = new ArrayList<>();

        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://safemoney-onhandcs.rhcloud.com/safemoney/apirest/itemdespesa/listar";
        final Gson gson = new Gson();

        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray array) {
                try{
                    for (int i = 0; i< array.length(); i++){
                        JSONObject item = array.getJSONObject(i);
                        ItemDespesa id = gson.fromJson(String.valueOf(item), ItemDespesa.class);
                        itens.add(id);
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

    public ItemDespesa buscarItemDespesa(long idItemDespesa){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://safemoney-onhandcs.rhcloud.com/safemoney/apirest/itemdespesa/buscar/"+idItemDespesa;
        final Gson gson = new Gson();
        final List<ItemDespesa> itens = new ArrayList<>();
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                ItemDespesa item = gson.fromJson(String.valueOf(response), ItemDespesa.class);
                itens.add(item);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

        return itens.get(0);
    }

    public void cadastrarItemDespesa(ItemDespesa itemDespesa){
        AsyncHttpClient client = new AsyncHttpClient();
        Gson gson = new Gson();
        String jsonBody = gson.toJson(itemDespesa);
        String url = "http://safemoney-onhandcs.rhcloud.com/safemoney/apirest/itemdespesa/cadastrar";
        try {
            StringEntity entity = new StringEntity(jsonBody);
            client.post(context, url, entity, "application/json",
                    new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                            Toast.makeText(context,"Sucess",Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Toast.makeText(context,"Failure",Toast.LENGTH_LONG).show();
                        }
                    });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void excluirItemDespesa(long idItemDespesa){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://safemoney-onhandcs.rhcloud.com/safemoney/apirest/itemdespesa/excluir/"+idItemDespesa;
        client.get(url, new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(context,"Sucess",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(context,"Sucess",Toast.LENGTH_LONG).show();
            }
        });

    }
}
