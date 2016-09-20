package com.ags.financemanager.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ags.financemanager.R;

public class LoginActivity extends AppCompatActivity {
    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnEnviar;
    private TextView txtConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        edtEmail     = (EditText) findViewById(R.id.edtLoginEmail);
        edtSenha     = (EditText) findViewById(R.id.edtLoginSenha);
        btnEnviar    = (Button) findViewById(R.id.btnEnviar);
        txtConta     = (TextView) findViewById(R.id.txtConta);

        btnEnviar.setOnClickListener(new BtnEnviarListener());
        txtConta.setOnClickListener(new TxtContaListener());
    }

    private boolean validarCamposLogin(String login, String senha) {
        boolean resultado = true;

        if (login == null || "".equals(login)) {
            edtEmail.setError("Campo Obrigatório");
            resultado = false;
        }

        if (senha == null || "".equals(senha)) {
            edtSenha.setError("Campo Obrigatório");
            resultado = false;
        }
        return resultado;
    }

    private class BtnEnviarListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String email = edtEmail.getText().toString();
            String senha = edtSenha.getText().toString();

            boolean isValido = validarCamposLogin(email, senha);

        }
    }

    private class TxtContaListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent cadastro = new Intent(getApplicationContext(), Cadastro.class);
            startActivity(cadastro);

        }
    }

}
