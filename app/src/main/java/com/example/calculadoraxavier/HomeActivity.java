package com.example.calculadoraxavier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textViewTitulo;
    private EditText editTextNome;
    private EditText editTextDisciplina;
    private EditText editTextAtividadeUm;
    private EditText editTextAtividadeDois;
    private EditText editTextProva;
    private EditText editTextFaltas;
    private Button buttonAvaliar;
    private Button buttonLimpar;
    private Button buttonDeslogar;
    private SharedPreferences sp;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonAvaliar) {
            this.avaliarAluno();
        } else if (view.getId() == R.id.buttonLimpar) {
            this.limparTexto();
        } else if (view.getId() == R.id.buttonDeslogar) {
            deleteSharedPreferences("arquivo_conf");
            this.fecharTela();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textViewTitulo = findViewById(R.id.textViewTItulo);

        editTextNome = findViewById(R.id.editTextNome);
        editTextDisciplina = findViewById(R.id.editTextDisciplina);
        editTextAtividadeUm = findViewById(R.id.editTextAtividadeUm);
        editTextAtividadeDois = findViewById(R.id.editTextAtividadeDois);
        editTextProva = findViewById(R.id.editTextProva);
        editTextFaltas = findViewById(R.id.editTextFaltas);

        buttonAvaliar = findViewById(R.id.buttonAvaliar);
        buttonLimpar = findViewById(R.id.buttonLimpar);
        buttonDeslogar = findViewById(R.id.buttonDeslogar);

        buttonAvaliar.setOnClickListener(this);
        buttonLimpar.setOnClickListener(this);
        buttonDeslogar.setOnClickListener(this);

        sp = getSharedPreferences("arquivo_conf", Context.MODE_PRIVATE);
        alterarTitulo();
    }

    private void alterarTitulo() {
        String nomeUsuario = sp.getString("usuario", "");
        String titulo = "Calculo de Desempenho - " + nomeUsuario;
        textViewTitulo.setText(titulo);
    }

    private void avaliarAluno() {


        String nome = editTextNome.getText().toString();
        String nomeDisciplina = editTextDisciplina.getText().toString();
        double nota1 = Double.parseDouble(editTextAtividadeUm.getText().toString());
        double nota2 = Double.parseDouble(editTextAtividadeDois.getText().toString());
        double notaP = Double.parseDouble(editTextProva.getText().toString());
        int qtdeFaltas = Integer.parseInt(editTextFaltas.getText().toString());

        Aluno aluno = new Aluno(nome, nomeDisciplina, nota1, nota2, notaP, qtdeFaltas);
        double finalNota = aluno.calcularNotaFinal();
        boolean aprovado = aluno.verificarAprovado(finalNota);

        String mensagem = "";
        if (aprovado) {
            mensagem = "O aluno está aprovado! :)";
        } else {
            mensagem = "O aluno está reprovdo! :(";
        }
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }

    private void limparTexto() {
        editTextNome.setText("");
        editTextDisciplina.setText("");
        editTextAtividadeUm.setText("");
        editTextAtividadeDois.setText("");
        editTextProva.setText("");
        editTextFaltas.setText("");
    }

    private void fecharTela() {
        Intent telaMain = new Intent(this, MainActivity.class);
        startActivity(telaMain);

        finish();
    }
}