package br.gov.sp.policiamilitar.seccom.Consultas.entity;

import java.util.Date;

public class Interessado {

    private int re;
    private String digitoRE;
    private String nome;
    private PostoGraduacao postoGraduacaoSeccom;
    private String opmAtual;
    private String opmEfetiva;
    private String situacao;
    private Date dataNascimento;
    private Date dataInatividade;
    private Date dataInicioExercicio;
    private Date dataMatricula;
    private String cpf;

    public Interessado(int re, String digitoRE, String nome, PostoGraduacao postoGraduacaoSeccom, String opmAtual, String opmEfetiva, String situacao, Date dataNascimento, Date dataInatividade, Date dataInicioExercicio, Date dataMatricula, String cpf) {
        this.re = re;
        this.digitoRE = digitoRE;
        this.nome = nome;
        this.postoGraduacaoSeccom = postoGraduacaoSeccom;
        this.opmAtual = opmAtual;
        this.opmEfetiva = opmEfetiva;
        this.situacao = situacao;
        this.dataNascimento = dataNascimento;
        this.dataInatividade = dataInatividade;
        this.dataInicioExercicio = dataInicioExercicio;
        this.dataMatricula = dataMatricula;
        this.cpf = cpf;
    }

    public Interessado() {
    }

    public int getRe() {
        return re;
    }

    public void setRe(int re) {
        this.re = re;
    }

    public String getDigitoRE() {
        return digitoRE;
    }

    public void setDigitoRE(String digitoRE) {
        this.digitoRE = digitoRE;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PostoGraduacao getPostoGraduacaoSeccom() {
        return postoGraduacaoSeccom;
    }

    public void setPostoGraduacaoSeccom(PostoGraduacao postoGraduacaoSeccom) {
        this.postoGraduacaoSeccom = postoGraduacaoSeccom;
    }

    public String getOpmAtual() {
        return opmAtual;
    }

    public void setOpmAtual(String opmAtual) {
        this.opmAtual = opmAtual;
    }

    public String getOpmEfetiva() {
        return opmEfetiva;
    }

    public void setOpmEfetiva(String opmEfetiva) {
        this.opmEfetiva = opmEfetiva;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataInatividade() {
        return dataInatividade;
    }

    public void setDataInatividade(Date dataInatividade) {
        this.dataInatividade = dataInatividade;
    }

    public Date getDataInicioExercicio() {
        return dataInicioExercicio;
    }

    public void setDataInicioExercicio(Date dataInicioExercicio) {
        this.dataInicioExercicio = dataInicioExercicio;
    }

    public Date getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(Date dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Interessado{" +
                "re=" + re +
                ", digitoRE='" + digitoRE + '\'' +
                ", nome='" + nome + '\'' +
                ", postoGraduacaoSeccom=" + postoGraduacaoSeccom +
                ", opmAtual='" + opmAtual + '\'' +
                ", opmEfetiva='" + opmEfetiva + '\'' +
                ", situacao='" + situacao + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", dataInatividade=" + dataInatividade +
                ", dataInicioExercicio=" + dataInicioExercicio +
                ", dataMatricula=" + dataMatricula +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
