package br.gov.sp.policiamilitar.seccom.Consultas.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Curso {

    private Long idCurso;
    private String descricaoCurso;

    public Curso() {
    }

    public Curso(Long idCurso, String descricaoCurso) {
        this.idCurso = idCurso;
        this.descricaoCurso = descricaoCurso;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public String getDescricaoCurso() {
        return descricaoCurso;
    }

    public void setDescricaoCurso(String descricaoCurso) {
        this.descricaoCurso = descricaoCurso;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "idCurso=" + idCurso +
                ", descricaoCurso='" + descricaoCurso + '\'' +
                '}';
    }
}
