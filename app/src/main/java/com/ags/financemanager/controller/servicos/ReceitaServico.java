package com.ags.financemanager.controller.servicos;

import android.content.Context;
import android.widget.Toast;

import com.ags.financemanager.model.bean.Receita;
import com.ags.financemanager.model.dao.ReceitaDAO;
import com.ags.financemanager.model.dao.ReceitaDAOImpl;
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

public class ReceitaServico {

    private Context context;

    public ReceitaServico(Context context) {
        this.context = context;
    }

    public List<Receita> listarReceitas(){
        final List<Receita> receitas = new ArrayList<Receita>();
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://safemoney-onhandcs.rhcloud.com/safemoney/apirest/receita/listar";
        final Gson gson = new Gson();
        final ReceitaDAO rDao = new ReceitaDAOImpl(context);
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray array) {
                try{
                    for (int i = 0; i< array.length(); i++){
                        JSONObject item = array.getJSONObject(i);
                        Receita receita = gson.fromJson(String.valueOf(item), Receita.class);
                        rDao.inserirReceita(receita);
                        receitas.add(receita);
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

        return receitas;
    }

    public Receita buscarReceita(long idReceita){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://safemoney-onhandcs.rhcloud.com/safemoney/apirest/receita/buscar/"+idReceita;
        final Gson gson = new Gson();
        final List<Receita> receita = new ArrayList<>();
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Receita r = gson.fromJson(String.valueOf(response), Receita.class);
                receita.add(r);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

        return receita.get(0);
    }

    public void cadastrarReceita(Receita receita){
        AsyncHttpClient client = new AsyncHttpClient();
        Gson gson = new Gson();
        String jsonBody = gson.toJson(receita);
        String url = "http://safemoney-onhandcs.rhcloud.com/safemoney/apirest/receita/cadastrar";
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

    public void excluirReceita(long idReceita){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://safemoney-onhandcs.rhcloud.com/safemoney/apirest/receita/excluir/"+idReceita;
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
