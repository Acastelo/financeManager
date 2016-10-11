package com.ags.financemanager.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.ags.financemanager.R;
import com.ags.financemanager.controller.ReceitaController;
import com.ags.financemanager.controller.ReceitaControllerImpl;
import com.ags.financemanager.model.bean.Receita;
import com.ags.financemanager.model.dao.ReceitaDAO;
import com.ags.financemanager.model.dao.ReceitaDAOImpl;

import java.util.List;

public class ListaActivity extends AppCompatActivity {
    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ReceitaDAO rdao = new ReceitaDAOImpl(getApplicationContext());
        ReceitaController rc = new ReceitaControllerImpl(rdao);

        List<Receita> receitas = rdao.buscarReceitas();

        lista = (ListView) findViewById(R.id.lista);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
