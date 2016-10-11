package com.ags.financemanager.activities;

import android.app.Fragment;
import android.support.design.widget.TabLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import com.ags.financemanager.R;
import com.ags.financemanager.activities.fragments.DespesasFragment;
import com.ags.financemanager.activities.fragments.ReceitasFragment;


public class AddReceitasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receitas);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Receitas"));
        tabs.addTab(tabs.newTab().setText("Despesas"));
        tabs.setOnTabSelectedListener(new TabListener());

        tabs.performClick();
    }

    private class TabListener implements TabLayout.OnTabSelectedListener{

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            Fragment fragment = null;
            switch (tab.getPosition()){
                case 0:
                    fragment = new ReceitasFragment();
                    getSupportActionBar().setTitle("Receitas");

                    break;
                case 1:
                    fragment = new DespesasFragment();
                    getSupportActionBar().setTitle("Despesas");
                    break;
            }
            getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, fragment).commit();

        }


        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }
}
