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
import android.widget.Toast;

import com.ags.financemanager.R;

public class Cadastro extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtEmail;
    private Spinner spnFuncao;
    private RadioButton rdoMasculino;
    private RadioButton rdoFeminino;
    private CheckBox chkDancar;
    private CheckBox chkLer;
    private CheckBox chkViajar;
    private Button btnFoto;
    private Button btnSalvar;
    private ImageView imgFoto;

    private int CAMERA_REQUEST = 1;

    private String funcoes[] ={"Desenvolvedor", "Analista", "Gerente", "Presidente"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtNome = (EditText)findViewById(R.id.edtNome);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        spnFuncao = (Spinner) findViewById(R.id.spnFuncao);
        rdoMasculino = (RadioButton) findViewById(R.id.rdoMasc);
        rdoFeminino = (RadioButton) findViewById(R.id.rdoFem);
        chkDancar = (CheckBox) findViewById(R.id.chkDanca);
        chkLer = (CheckBox)findViewById(R.id.chkLer);
        chkViajar = (CheckBox)findViewById(R.id.chkViajar);
        btnFoto = (Button) findViewById(R.id.btnFoto);
        imgFoto = (ImageView) findViewById(R.id.imagen);
        btnSalvar = (Button)findViewById(R.id.btnFoto);

        btnFoto.setOnClickListener(new BtnFotoListener());
        btnSalvar.setOnClickListener(new BtnSalvarListener());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, funcoes);
        spnFuncao.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK){
            Bitmap btmFoto = (Bitmap) data.getExtras().get("data");
            imgFoto.setImageBitmap(btmFoto);
        }
    }

    private class BtnFotoListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent cameraTela = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraTela, CAMERA_REQUEST);
        }
    }

    private class BtnSalvarListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String nomeCadastrado = edtNome.getText().toString();
            String emailCadastrado = edtEmail.getText().toString();
            String funcaoCadastrada = spnFuncao.getSelectedItem().toString();
            String sexoCadastrado = null;

            if(rdoMasculino.isEnabled()){
                sexoCadastrado = "Masculino";
            } else {
                sexoCadastrado = "Feminino";
            }

            boolean hobbies[] = {chkDancar.isChecked(), chkLer.isChecked(), chkViajar.isChecked()};

            Toast.makeText(getApplicationContext(), "Nome: " + nomeCadastrado, Toast.LENGTH_LONG).show();

            AlertDialog.Builder dialogo = new AlertDialog.Builder(Cadastro.this);
            dialogo.setTitle("Cadastro");
            dialogo.setMessage("Contato Salvo");
            dialogo.setNeutralButton("Cancelar", null);
            dialogo.setPositiveButton("Novo", new DialogInterface.OnClickListener(){
               @Override
               public void onClick(DialogInterface dialog, int which){
                   edtNome.setText("");
                   edtEmail.setText("");
                   rdoMasculino.setChecked(false);
                   rdoMasculino.setChecked(false);
                   chkDancar.setChecked(false);
                   chkLer.setChecked(false);
                   chkViajar.setChecked(false);
               }
            });
        }
    }
}
