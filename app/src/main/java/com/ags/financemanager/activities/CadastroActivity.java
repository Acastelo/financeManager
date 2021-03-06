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
import com.ags.financemanager.controller.UsuarioControllerImpl;
import com.ags.financemanager.controller.exception.ControllerException;
import com.ags.financemanager.model.bean.Usuario;

public class CadastroActivity extends AppCompatActivity {
    private TextView txtLogin;
    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnCriaConta;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtNome = (EditText)findViewById(R.id.cad_nome);
        edtEmail = (EditText)findViewById(R.id.cad_email);
        edtSenha = (EditText)findViewById(R.id.cad_senha);

        String nome = edtNome.getText().toString();
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();

        usuario = new Usuario(nome, email, senha);

        btnCriaConta = (Button)findViewById(R.id.btn_cria_conta);
        btnCriaConta.setOnClickListener(new BtnCriaContaListener());

        txtLogin = (TextView)findViewById(R.id.link_login);
        txtLogin.setOnClickListener(new TxtLoginListener());

    }

    private class TxtLoginListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent login = new Intent(getApplicationContext(), ListaActivity.class);
            startActivity(login);
        }
    }

    private class BtnCriaContaListener implements View.OnClickListener{
        private UsuarioControllerImpl controller;

        @Override
        public void onClick(View v) {
            try {
                controller.salvar(usuario);
                Toast.makeText(CadastroActivity.this, "Usuário Cadastrado com sucesso.", Toast.LENGTH_LONG);
            }catch (ControllerException e){
                e.printStackTrace();
                Toast.makeText(CadastroActivity.this, "Usuário não cadastrado.", Toast.LENGTH_LONG);
            }


        }
    }
}
