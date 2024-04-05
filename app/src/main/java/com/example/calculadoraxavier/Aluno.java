package com.example.calculadoraxavier;

public class Aluno {

    private String nome;
    private String nomeDaDisciplina;
    private double notaAtividade1;
    private double notaAtividade2;
    private double notaProva;
    private int faltas;


    public Aluno(String nome, String nomeDaDisciplina, double notaAtividade1, double notaAtividade2, double notaProva, int faltas) {
        this.nome = nome;
        this.nomeDaDisciplina = nomeDaDisciplina;
        this.notaAtividade1 = notaAtividade1;
        this.notaAtividade2 = notaAtividade2;
        this.notaProva = notaProva;
        this.faltas = faltas;
    }


    public double calcularNotaFinal() {

        double pesoAtividades = 0.4;
        double pesoProva = 0.6;

        double notaFinal = (this.notaAtividade1 * pesoAtividades) + (this.notaAtividade2 * pesoAtividades) + (notaProva * pesoProva);

        return notaFinal;
    }

    public boolean verificarAprovado(double notaFinal) {

      if (this.faltas > 4) {
          return false;
      } else if (notaFinal >= 6.0) {
          return true;
      } else {
          return false;
      }
    }
}
