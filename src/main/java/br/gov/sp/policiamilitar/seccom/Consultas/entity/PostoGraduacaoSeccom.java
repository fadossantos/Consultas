package br.gov.sp.policiamilitar.seccom.Consultas.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class PostoGraduacaoSeccom implements Serializable {

    private static final long serialVersionUID = 4318287372523402587L;
    private int idPostoGraduacao;

    private String siglaPostoGraduacao;

    private String descricaoPostoGraduacao;

    public PostoGraduacaoSeccom(int idPostoGraduacao, String siglaPostoGraduacao, String descricaoPostoGraduacao) {
        this.idPostoGraduacao = idPostoGraduacao;
        this.siglaPostoGraduacao = siglaPostoGraduacao;
        this.descricaoPostoGraduacao = descricaoPostoGraduacao;
    }

    public PostoGraduacaoSeccom() {
    }

    public int getIdPostoGraduacao() {
        return idPostoGraduacao;
    }

    public void setIdPostoGraduacao(int idPostoGraduacao) {
        this.idPostoGraduacao = idPostoGraduacao;
    }

    public String getSiglaPostoGraduacao() {
        return siglaPostoGraduacao;
    }

    public void setSiglaPostoGraduacao(String siglaPostoGraduacao) {
        this.siglaPostoGraduacao = siglaPostoGraduacao;
    }

    public String getDescricaoPostoGraduacao() {
        return descricaoPostoGraduacao;
    }

    public void setDescricaoPostoGraduacao(String descricaoPostoGraduacao) {
        this.descricaoPostoGraduacao = descricaoPostoGraduacao;
    }

    @Override
    public String toString() {
        return "PostoGraduacaoSeccom{" +
                "idPostoGraduacao=" + idPostoGraduacao +
                ", siglaPostoGraduacao='" + siglaPostoGraduacao + '\'' +
                ", descricaoPostoGraduacao='" + descricaoPostoGraduacao + '\'' +
                '}';
    }
}
