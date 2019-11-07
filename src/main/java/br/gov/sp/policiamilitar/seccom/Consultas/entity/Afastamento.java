package br.gov.sp.policiamilitar.seccom.Consultas.entity;

import java.util.Objects;

public class Afastamento {

    private String re;
    private String inicioAgregacao;
    private String fimAgregacao;
    private String doeInicio;
    private String doeFim;
    private String afastamento;

    public Afastamento() {
    }

    public Afastamento(String re, String inicioAgregacao, String fimAgregacao, String doeInicio, String doeFim, String afastamento) {
        this.re = re;
        this.inicioAgregacao = inicioAgregacao;
        this.fimAgregacao = fimAgregacao;
        this.doeInicio = doeInicio;
        this.doeFim = doeFim;
        this.afastamento = afastamento;
    }

    public String getRe() {
        return re;
    }

    public void setRe(String re) {
        this.re = re;
    }

    public String getInicioAgregacao() {
        return inicioAgregacao;
    }

    public void setInicioAgregacao(String inicioAgregacao) {
        this.inicioAgregacao = inicioAgregacao;
    }

    public String getFimAgregacao() {
        return fimAgregacao;
    }

    public void setFimAgregacao(String fimAgregacao) {
        this.fimAgregacao = fimAgregacao;
    }

    public String getDoeInicio() {
        return doeInicio;
    }

    public void setDoeInicio(String doeInicio) {
        this.doeInicio = doeInicio;
    }

    public String getDoeFim() {
        return doeFim;
    }

    public void setDoeFim(String doeFim) {
        this.doeFim = doeFim;
    }

    public String getAfastamento() {
        return afastamento;
    }

    public void setAfastamento(String afastamento) {
        this.afastamento = afastamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Afastamento that = (Afastamento) o;
        return Objects.equals(getRe(), that.getRe()) &&
                Objects.equals(getInicioAgregacao(), that.getInicioAgregacao()) &&
                Objects.equals(getFimAgregacao(), that.getFimAgregacao()) &&
                Objects.equals(getDoeInicio(), that.getDoeInicio()) &&
                Objects.equals(getDoeFim(), that.getDoeFim()) &&
                Objects.equals(getAfastamento(), that.getAfastamento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRe(), getInicioAgregacao(), getFimAgregacao(), getDoeInicio(), getDoeFim(), getAfastamento());
    }

    @Override
    public String toString() {
        return "Afastamento{" +
                "re='" + re + '\'' +
                ", inicioAgregacao='" + inicioAgregacao + '\'' +
                ", fimAgregacao='" + fimAgregacao + '\'' +
                ", doeInicio='" + doeInicio + '\'' +
                ", doeFim='" + doeFim + '\'' +
                ", afastamento='" + afastamento + '\'' +
                '}';
    }
}
