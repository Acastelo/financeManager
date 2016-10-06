package com.ags.financemanager.controller.servicos;

import android.content.Context;
import android.widget.Toast;

import com.ags.financemanager.model.bean.Despesa;
import com.ags.financemanager.model.bean.Usuario;
import com.ags.financemanager.model.dao.UsuarioDAOImpl;
import com.google.gson.Gson;
import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


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

        final Usuario usuario = new Usuario("", "", "");

        try {

            JSONObject response = getJSONObjectFromURL("http://safemoney-onhandcs.rhcloud.com/safemoney/apirest/usuario/logar/" + emailUsuario);

            long idUsuario = response.getLong("idUsuario");
            String senha = response.getString("senha");
            String email = response.getString("email");
            String nome = response.getString("nome");

            usuario.setEmail(email);
            usuario.setId(idUsuario);
            usuario.setNome(nome);
            usuario.setSenha(senha);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public List<Usuario> listarUsuarios() {

        final List<Usuario> usuarios = new ArrayList<Usuario>();
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://safemoney-onhandcs.rhcloud.com/safemoney/apirest/usuario/listar";
        final Gson gson = new Gson();
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray array) {
                try{
                    for (int i = 0; i< array.length(); i++){
                        JSONObject item = array.getJSONObject(i);
                        Usuario usuario = gson.fromJson(String.valueOf(item), Usuario.class);
                        usuarios.add(usuario);
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

        return usuarios;
    }
}
