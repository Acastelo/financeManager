package com.ags.financemanager.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
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
import com.ags.financemanager.controller.Callback;
import com.ags.financemanager.controller.UsuarioController;
import com.ags.financemanager.controller.UsuarioControllerImpl;
import com.ags.financemanager.controller.servicos.UsuarioServico;
import com.ags.financemanager.model.bean.Usuario;

public class CadastroActivity extends AppCompatActivity {
    private TextView txtLogin;
    private EditText etnome;
    private EditText etemail;
    private EditText etsenha;
    private AppCompatButton btCriar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etnome = (EditText)findViewById(R.id.input_nome);
        etemail = (EditText)findViewById(R.id.input_email);
        etsenha = (EditText)findViewById(R.id.input_password);
        btCriar = (AppCompatButton)findViewById(R.id.btn_signup);
        btCriar.setOnClickListener(new BtnCriarListener());
        txtLogin = (TextView)findViewById(R.id.link_login);
        txtLogin.setOnClickListener(new TxtLoginListener());
    }

    private class BtnCriarListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            String nome = etnome.getText().toString();
            String email = etemail.getText().toString();
            String senha = etsenha.getText().toString();

            Usuario usuario = new Usuario(nome,email,senha);

            UsuarioServico us = new UsuarioServico(getApplicationContext());
            us.cadastrarUsuario(usuario, new CadastroCallback(usuario));
        }
    }

    private class TxtLoginListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(login);
        }
    }

    private class CadastroCallback implements Callback {
        private Usuario usuario;

        public CadastroCallback(Usuario usuario) {
            this.usuario = usuario;
        }

        @Override
        public Object sucesso() {
            UsuarioController uController = new UsuarioControllerImpl(getApplicationContext());
            String senhaMD5 = uController.md5(usuario.getSenha());
            usuario.setSenha(senhaMD5);
            uController.salvar(usuario);

            Intent lista = new Intent(getApplicationContext(), ListaActivity.class);
            startActivity(lista);
            return null;
        }

        @Override
        public Object erro() {
           Toast.makeText(getApplicationContext(),"Erro ao cadastrar novo usuário",Toast.LENGTH_LONG).show();
            return null;
        }
    }
}
