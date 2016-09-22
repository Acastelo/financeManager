package com.ags.financemanager.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ags.financemanager.R;

public class CadastroActivity extends AppCompatActivity {
    private TextView txtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txtLogin = (TextView)findViewById(R.id.link_login);
        txtLogin.setOnClickListener(new TxtLoginListener());

    }

    private class TxtLoginListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(login);
        }
    }
}
