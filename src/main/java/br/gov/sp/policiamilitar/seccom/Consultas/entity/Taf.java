package br.gov.sp.policiamilitar.seccom.Consultas.entity;

import java.util.Date;
import java.util.Objects;

public class Taf {
    private Date dataTaf;
    private String resultado;

    public Taf() {
    }

    public Taf(Date dataTaf, String resultado) {
        this.dataTaf = dataTaf;
        this.resultado = resultado;
    }

    public Date getDataTaf() {
        return dataTaf;
    }

    public void setDataTaf(Date dataTaf) {
        this.dataTaf = dataTaf;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taf taf = (Taf) o;
        return Objects.equals(getDataTaf(), taf.getDataTaf()) &&
                Objects.equals(getResultado(), taf.getResultado());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDataTaf(), getResultado());
    }

    @Override
    public String toString() {
        return "Taf{" +
                "dataTaf=" + dataTaf +
                ", resultado='" + resultado + '\'' +
                '}';
    }
}
