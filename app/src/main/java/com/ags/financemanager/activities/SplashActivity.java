package com.ags.financemanager.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ags.financemanager.R;
import com.ags.financemanager.controller.Callback;
import com.ags.financemanager.controller.TipoDespesaController;
import com.ags.financemanager.controller.TipoDespesaControllerImpl;
import com.ags.financemanager.controller.TipoReceitaController;
import com.ags.financemanager.controller.TipoReceitaControllerImpl;
import com.ags.financemanager.controller.servicos.DespesaServico;
import com.ags.financemanager.controller.servicos.ItemDespesaServico;
import com.ags.financemanager.controller.servicos.ReceitaServico;
import com.ags.financemanager.controller.servicos.TipoDespesaServico;
import com.ags.financemanager.controller.servicos.TipoReceitaServico;
import com.ags.financemanager.model.bean.TipoDespesa;
import com.ags.financemanager.model.bean.TipoReceita;
import com.ags.financemanager.model.dao.TipoDespesaDAO;
import com.ags.financemanager.model.dao.TipoDespesaDAOImpl;
import com.ags.financemanager.model.dao.TipoReceitaDAOImpl;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TipoReceitaServico serv = new TipoReceitaServico(getApplicationContext());
        serv.listarTipoReceita();

        TipoDespesaServico servTipoDespesa = new TipoDespesaServico(getApplicationContext());
        servTipoDespesa.listarTipoDespesa();

        ReceitaServico servReceita = new ReceitaServico(getApplicationContext());
        servReceita.listarReceitas();

        ItemDespesaServico servItem = new ItemDespesaServico(getApplicationContext());
        servItem.listarItemDespesas();

        DespesaServico servDespesa = new DespesaServico(getApplicationContext());
        servDespesa.listarDespesas();


        startLogin();

    }



    public void startLogin(){
        Intent telaLogin = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(telaLogin);
    }
}
