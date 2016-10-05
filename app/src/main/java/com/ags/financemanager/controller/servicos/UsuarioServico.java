package com.ags.financemanager.controller.servicos;

import android.content.Context;
import android.widget.Toast;

import com.ags.financemanager.model.bean.Usuario;
import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


/**
 * Created by Maikon Igor on 05/10/2016.
 */

public class UsuarioServico {

    private Context context;

    public UsuarioServico(Context context) {
        this.context = context;
    }

    public boolean login(Usuario usuario){
        final boolean[] sucesso = new boolean[1];
        String email  = usuario.getEmail();

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        String url = "http://safemoney-onhandcs.rhcloud.com/safemoney/apirest/usuario/logar/"+email;

        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray usuario = null;
                sucesso[0] = true;
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                sucesso[0] = false;
            }
        });
        return sucesso[0];
    }
}
