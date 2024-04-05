package com.example.calculadoraxavier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUsuario;
    private CheckBox checkBoxConectado;
    private Button buttonEntrar;
    private SharedPreferences sp;

    public EditText getEditTextUsuario() {
        return editTextUsuario;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("arquivo_conf", Context.MODE_PRIVATE);

        editTextUsuario = findViewById(R.id.editTextUsuario);
        checkBoxConectado = findViewById(R.id.checkBoxConectado);
        buttonEntrar = findViewById(R.id.buttonEntrar);
        buttonEntrar.setOnClickListener(this);

        continuarConectado();
    }

    @Override
    public  void onClick(View v) {
        if (v.getId() == R.id.buttonEntrar) {
            if (editTextUsuario.getText().toString().isEmpty()) {
            } else {
                if (this.checkBoxConectado.isChecked()) {
                    salvarUsuarioConectado();
                }
                avancarTela();
            }
        }
    }

    private void salvarUsuarioConectado() {
        String nomeUsuario = editTextUsuario.getText().toString();

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("usuario", nomeUsuario);
        editor.putBoolean("Logado", true);
        editor.apply();
    }

    private void continuarConectado() {
        boolean conectado = sp.getBoolean("Logado", false);

        if (conectado) {
            avancarTela();
        }
    }

    private void avancarTela() {
        Intent telaHome = new Intent(this, HomeActivity.class);
        startActivity(telaHome);

        finish();
    }
}