package com.ags.financemanager.activities;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ags.financemanager.R;

public class AddReceitasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addReceitas);

        getSupportActionBar().setTitle("teste");

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Receitas"));
        tabs.addTab(tabs.newTab().setText("Despesas"));
    }
}
