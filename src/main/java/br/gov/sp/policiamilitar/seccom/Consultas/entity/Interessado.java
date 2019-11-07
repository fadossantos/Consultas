package br.gov.sp.policiamilitar.seccom.Consultas.entity;

import br.gov.sp.pmesp.wsscpm.FuncionarioDocumento;
import br.gov.sp.pmesp.wsscpm.PolicialMilitar;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Interessado implements Serializable{

    private String re;
    private String nome;
    private String digitoRE;
    private String cpf;
    private String opmAtual;
    private String opmAtualCod;
    private String opmEfetiva;
    private String opmEfetivaCod;
    private String situacao;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dataNascimento;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dataInatividade;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dataInicioExercicio;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dataMatricula;

    private PostoGraduacaoSeccom postoGraduacaoSeccom;

    private List<Promocao> promocoes;

    private List<Curso> cursos;


    public Interessado(String re, String nome, String digitoRE, String cpf, String opmAtual, String opmAtualCod, String opmEfetiva,  String opmEfetivaCod, String situacao, Date dataNascimento, Date dataInatividade, Date dataInicioExercicio, Date dataMatricula, PostoGraduacaoSeccom postoGraduacaoSeccom, List<Promocao> promocoes, List<Curso> cursos) {
        this.re = re;
        this.nome = nome;
        this.digitoRE = digitoRE;
        this.cpf = cpf;
        this.opmAtual = opmAtual;
        this.opmAtualCod = opmAtualCod;
        this.opmEfetiva = opmEfetiva;
        this.opmEfetivaCod = opmEfetivaCod;
        this.situacao = situacao;
        this.dataNascimento = dataNascimento;
        this.dataInatividade = dataInatividade;
        this.dataInicioExercicio = dataInicioExercicio;
        this.dataMatricula = dataMatricula;
        this.postoGraduacaoSeccom = postoGraduacaoSeccom;
        this.promocoes = promocoes;
        this.cursos = cursos;
    }

    public Interessado() {
    }

    public String getOpmAtualCod() {
        return opmAtualCod;
    }

    public void setOpmAtualCod(String opmAtualCod) {
        this.opmAtualCod = opmAtualCod;
    }

    public String getOpmEfetivaCod() {
        return opmEfetivaCod;
    }

    public void setOpmEfetivaCod(String opmEfetivaCod) {
        this.opmEfetivaCod = opmEfetivaCod;
    }

    public String getRe() {
        return re;
    }

    public void setRe(String re) {
        this.re = re;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDigitoRE() {
        return digitoRE;
    }

    public void setDigitoRE(String digitoRE) {
        this.digitoRE = digitoRE;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public PostoGraduacaoSeccom getPostoGraduacaoSeccom() {
        return postoGraduacaoSeccom;
    }

    public void setPostoGraduacaoSeccom(PostoGraduacaoSeccom postoGraduacaoSeccom) {
        this.postoGraduacaoSeccom = postoGraduacaoSeccom;
    }

    public List<Promocao> getPromocoes() {
        return promocoes;
    }

    public void setPromocoes(List<Promocao> promocoes) {
        this.promocoes = promocoes;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    @Override
    public String toString() {
        return "Interessado{" +
                "re='" + re + '\'' +
                ", nome='" + nome + '\'' +
                ", digitoRE='" + digitoRE + '\'' +
                ", cpf='" + cpf + '\'' +
                ", opmAtual='" + opmAtual + '\'' +
                ", opmAtualCod='" + opmAtualCod + '\'' +
                ", opmEfetiva='" + opmEfetiva + '\'' +
                ", opmEfetivaCod='" + opmEfetivaCod + '\'' +
                ", situacao='" + situacao + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", dataInatividade=" + dataInatividade +
                ", dataInicioExercicio=" + dataInicioExercicio +
                ", dataMatricula=" + dataMatricula +
                ", postoGraduacaoSeccom=" + postoGraduacaoSeccom +
                ", promocoes=" + promocoes +
                ", cursos=" + cursos +
                '}';
    }

    public static Interessado getInteressadoPorPolicial(PolicialMilitar policialMilitar){
        Interessado interessado = new Interessado();
        for(FuncionarioDocumento doc : policialMilitar.getDocumentos().getFuncionarioDocumento()){
            if(doc.getCodigoTipoDocumento() == 1){
                interessado.setCpf(doc.getNumero().trim()+doc.getDigitoDocumento().trim());
            }
        }
        interessado.setDataInatividade(policialMilitar.getDataInatividadePM().toGregorianCalendar().getTime());
        interessado.setDataMatricula(policialMilitar.getDataMatriculaPM().toGregorianCalendar().getTime());
        interessado.setDataInicioExercicio(policialMilitar.getDataAdmissaoPM().toGregorianCalendar().getTime());
        interessado.setDataNascimento(policialMilitar.getDataNascimentoPM().toGregorianCalendar().getTime());
        interessado.setDigitoRE(policialMilitar.getDigitoREPM());
        interessado.setNome(policialMilitar.getNomePM());
        interessado.setOpmAtual(policialMilitar.getCodigoOPMAtualPM().getDescricaoNivel03OPMBatalhao());
        interessado.setOpmAtualCod(String.valueOf(policialMilitar.getCodigoOPMAtualPM().getCodigoOPM()));
        interessado.setOpmEfetivaCod(String.valueOf(policialMilitar.getCodigoOPMEfetivaPM()));
        interessado.setOpmEfetivaCod(String.valueOf(policialMilitar.getCodigoOPMEfetivaPM()));
        interessado.setOpmEfetiva(String.valueOf(policialMilitar.getCodigoOPMEfetivaPM()));
        return interessado;
    }
}
