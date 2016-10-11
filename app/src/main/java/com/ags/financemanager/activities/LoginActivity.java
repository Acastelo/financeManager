package com.ags.financemanager.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ags.financemanager.R;

import com.ags.financemanager.controller.UsuarioControllerImpl;
import com.ags.financemanager.model.dao.UsuarioDAOImpl;


public class LoginActivity extends AppCompatActivity implements Runnable{
    private Handler handler = new Handler();
    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnEnviar;
    private TextView txtConta;
    UsuarioControllerImpl sinc;
    UsuarioDAOImpl dao;
    String _email;
    String _senha;
    Boolean logar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        edtEmail     = (EditText) findViewById(R.id.edtLoginEmail);
        edtSenha     = (EditText) findViewById(R.id.edtLoginSenha);
        btnEnviar    = (Button) findViewById(R.id.btnLogin);
        txtConta     = (TextView) findViewById(R.id.txtConta);
        dao = new UsuarioDAOImpl(getApplicationContext());
        sinc = new UsuarioControllerImpl(dao,getApplicationContext());
        btnEnviar.setOnClickListener(new BtnEnviarListener());
        txtConta.setOnClickListener(new TxtContaListener());

        handler=new Handler()
        {
            public void handleMessage(Message msg)
            {
                if(msg.what==0)
                {
                    Toast.makeText(LoginActivity.this,"Usuário ou senha inválidos",Toast.LENGTH_LONG).show();
                }

            }
        };
    }

    public void importarUsuario(){
        new Thread(this).start();
    }

    @Override
    public void run() {

        try {

            logar = sinc.logar(_email,_senha);

            handler.post(new Runnable() {
                public void run() {
                    if (logar){
                        Intent lista = new Intent(getApplicationContext(), AddReceitasActivity.class);
                        startActivity(lista);
                        finish();
                    }else
                    {
                        handler.sendEmptyMessage(0);
                    }
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            _email = email;
            _senha = senha;

            boolean isValido = validarCamposLogin(email, senha);

            importarUsuario();

            /*Usuario u = sinc.getUsuarioByEmail(_email);

            if (u.getSenha().equals(_senha)){
                Intent lista = new Intent(getApplicationContext(), AddReceitasActivity.class);
                startActivity(lista);
            }*/


        }
    }

    private class TxtContaListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent cadastro = new Intent(getApplicationContext(), CadastroActivity.class);
            startActivity(cadastro);

        }
    }

}
