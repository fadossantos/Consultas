package br.gov.sp.policiamilitar.seccom.Consultas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

public class Promocao {

    private Long idPromocao;
    private PostoGraduacaoSeccom postoGraduacaoSeccom;
    private Interessado interessado;
    private Date dataPromocao;

    public Promocao(Long idPromocao, PostoGraduacaoSeccom postoGraduacaoSeccom, Interessado interessado, Date dataPromocao) {
        this.idPromocao = idPromocao;
        this.postoGraduacaoSeccom = postoGraduacaoSeccom;
        this.interessado = interessado;
        this.dataPromocao = dataPromocao;
    }

    public Promocao() {
    }

    public Long getIdPromocao() {
        return idPromocao;
    }

    public void setIdPromocao(Long idPromocao) {
        this.idPromocao = idPromocao;
    }

    public PostoGraduacaoSeccom getPostoGraduacaoSeccom() {
        return postoGraduacaoSeccom;
    }

    public void setPostoGraduacaoSeccom(PostoGraduacaoSeccom postoGraduacaoSeccom) {
        this.postoGraduacaoSeccom = postoGraduacaoSeccom;
    }

    public Interessado getInteressado() {
        return interessado;
    }

    public void setInteressado(Interessado interessado) {
        this.interessado = interessado;
    }

    public Date getDataPromocao() {
        return dataPromocao;
    }

    public void setDataPromocao(Date dataPromocao) {
        this.dataPromocao = dataPromocao;
    }

    @Override
    public String toString() {
        return "Promocao{" +
                "idPromocao=" + idPromocao +
                ", postoGraduacaoSeccom=" + postoGraduacaoSeccom +
                ", interessado=" + interessado +
                ", dataPromocao=" + dataPromocao +
                '}';
    }
}
