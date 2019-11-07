package br.gov.sp.policiamilitar.seccom.Consultas.entity;

import java.util.Objects;

public class AvaliacaoDesempenho {

    private String ano;
    private String semestre;
    private String situacao;
    private String conceito;

    public AvaliacaoDesempenho() {
    }

    public AvaliacaoDesempenho(String ano, String semestre, String situacao, String conceito) {
        this.ano = ano;
        this.semestre = semestre;
        this.situacao = situacao;
        this.conceito = conceito;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getConceito() {
        return conceito;
    }

    public void setConceito(String conceito) {
        this.conceito = conceito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvaliacaoDesempenho that = (AvaliacaoDesempenho) o;
        return Objects.equals(getAno(), that.getAno()) &&
                Objects.equals(getSemestre(), that.getSemestre()) &&
                Objects.equals(getSituacao(), that.getSituacao()) &&
                Objects.equals(getConceito(), that.getConceito());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAno(), getSemestre(), getSituacao(), getConceito());
    }

    @Override
    public String toString() {
        return "AvaliacaoDesempenho{" +
                "ano='" + ano + '\'' +
                ", semestre='" + semestre + '\'' +
                ", situacao='" + situacao + '\'' +
                ", conceito='" + conceito + '\'' +
                '}';
    }
}
