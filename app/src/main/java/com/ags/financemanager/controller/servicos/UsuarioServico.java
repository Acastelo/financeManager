package com.ags.financemanager.controller.servicos;

import android.content.Context;

import com.ags.financemanager.controller.Callback;
import com.ags.financemanager.model.bean.Usuario;
<<<<<<< HEAD
import com.google.gson.Gson;
import com.loopj.android.http.*;
=======
>>>>>>> 40639a90c569e166b3e91aec0c8e6aac513747f1

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

<<<<<<< HEAD
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

=======
>>>>>>> 40639a90c569e166b3e91aec0c8e6aac513747f1

/**
 * Created by Maikon Igor on 05/10/2016.
 */

public class UsuarioServico {

    private Context context;

    public UsuarioServico(Context context) {
        this.context = context;
    }

    public JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {

        HttpURLConnection urlConnection = null;

        URL url = new URL(urlString);

        urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */);
        urlConnection.setConnectTimeout(15000 /* milliseconds */);

        urlConnection.setDoOutput(true);

        urlConnection.connect();

        BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));

        char[] buffer = new char[1024];

        String jsonString = new String();

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }
        br.close();

        jsonString = sb.toString();

        System.out.println("JSON: " + jsonString);

        return new JSONObject(jsonString);
    }

    public Usuario login(String emailUsuario) {

        Usuario usuario = new Usuario("", "", "");

        try {

            JSONObject response = getJSONObjectFromURL("http://safemoney-onhandcs.rhcloud.com/safemoney/apirest/usuario/logar/" + emailUsuario);
            Gson gson = new Gson();
            usuario = gson.fromJson(String.valueOf(response), Usuario.class);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public void cadastrarUsuario(Usuario usuario, final Callback callback){
        AsyncHttpClient client = new AsyncHttpClient();
        Gson gson = new Gson();
        String jsonBody = gson.toJson(usuario);
        String url = "http://safemoney-onhandcs.rhcloud.com/safemoney/apirest/usuario/cadastrar";
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
}
