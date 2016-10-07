package com.ags.financemanager.controller.servicos;

import android.content.Context;
import android.widget.Toast;

import com.ags.financemanager.controller.Callback;
import com.ags.financemanager.model.bean.Despesa;
import com.ags.financemanager.model.dao.DespesaDAO;
import com.ags.financemanager.model.dao.DespesaDAOImpl;
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

public class DespesaServico {

    private Context context;

    public DespesaServico(Context context) {
        this.context = context;
    }

    public List<Despesa> listarDespesas(){
        final List<Despesa> despesas = new ArrayList<Despesa>();
        final DespesaDAO dDao = new DespesaDAOImpl(context);
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
                        dDao.inserirDespesa(despesa);
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
        final List<Despesa> despesa = new ArrayList<>();
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Despesa d = gson.fromJson(String.valueOf(response), Despesa.class);
                despesa.add(d);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

        return despesa.get(0);
    }

    public void cadastrarDespesa(Despesa despesa, final Callback callback){
        AsyncHttpClient client = new AsyncHttpClient();
        Gson gson = new Gson();
        String jsonBody = gson.toJson(despesa);
        String url = "http://safemoney-onhandcs.rhcloud.com/safemoney/apirest/despesa/cadastrar";
        try {
            StringEntity entity = new StringEntity(jsonBody);
            client.post(context, url, entity, "application/json",
                    new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                           callback.sucesso();
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            callback.erro();
                        }
                    });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void excluirDespesa(long idDespesa, final Callback callback){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://safemoney-onhandcs.rhcloud.com/safemoney/apirest/despesa/excluir/"+idDespesa;
        client.get(url, new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
               callback.sucesso();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
               callback.erro();
            }
        });
    }


}
