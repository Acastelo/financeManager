package com.ags.financemanager.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ags.financemanager.R;
import com.ags.financemanager.controller.TipoReceitaController;
import com.ags.financemanager.controller.TipoReceitaControllerImpl;
import com.ags.financemanager.controller.servicos.DespesaServico;
import com.ags.financemanager.controller.servicos.ReceitaServico;
import com.ags.financemanager.model.bean.TipoReceita;
import com.ags.financemanager.model.dao.TipoReceitaDAOImpl;

import java.util.List;

public class SplashActivity extends AppCompatActivity {
    List<TipoReceita> lista;

    private class HttpRequestTask extends AsyncTask<Void, Void, List<TipoReceita>> {
        @Override
        protected List<TipoReceita> doInBackground(Void... params) {
            try {
                /*final String url = "http://rest-service.guides.spring.io/greeting";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Greeting greeting = restTemplate.getForObject(url, Greeting.class);
                return greeting;*/
                TipoReceitaControllerImpl tipoReceitaController =
                        new TipoReceitaControllerImpl(
                                new TipoReceitaDAOImpl(getApplicationContext()),getApplicationContext());

                lista = tipoReceitaController.getTodosOnServer();


            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return lista;
        }

        @Override
        protected void onPostExecute(List<TipoReceita> tipos) {

            TipoReceitaControllerImpl tipoReceitaController =
                    new TipoReceitaControllerImpl(
                            new TipoReceitaDAOImpl(getApplicationContext()),getApplicationContext());

                tipoReceitaController.salvarTodos(tipos);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

            new HttpRequestTask().execute();
    }

    public void startLogin(){
        Intent telaLogin = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(telaLogin);
    }

    /*@Override
    public void run() {
        ReceitaServico s = new ReceitaServico(getApplicationContext());
        s.listarReceitas();
        startLogin();
        finish();
    }*/
}
