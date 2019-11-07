package br.gov.sp.policiamilitar.seccom.Consultas.entity;

import java.util.Objects;

public class Punicao {

    private String tipoPunicao;
    private String dataPublicacao;
    private String boletimPublicacao;

    public Punicao() {
    }

    public Punicao(String tipoPunicao, String dataPublicacao, String boletimPublicacao) {
        this.tipoPunicao = tipoPunicao;
        this.dataPublicacao = dataPublicacao;
        this.boletimPublicacao = boletimPublicacao;
    }

    public String getTipoPunicao() {
        return tipoPunicao;
    }

    public void setTipoPunicao(String tipoPunicao) {
        this.tipoPunicao = tipoPunicao;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getBoletimPublicacao() {
        return boletimPublicacao;
    }

    public void setBoletimPublicacao(String boletimPublicacao) {
        this.boletimPublicacao = boletimPublicacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Punicao punicao = (Punicao) o;
        return Objects.equals(getTipoPunicao(), punicao.getTipoPunicao()) &&
                Objects.equals(getDataPublicacao(), punicao.getDataPublicacao()) &&
                Objects.equals(getBoletimPublicacao(), punicao.getBoletimPublicacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTipoPunicao(), getDataPublicacao(), getBoletimPublicacao());
    }

    @Override
    public String toString() {
        return "Punicao{" +
                "tipoPunicao='" + tipoPunicao + '\'' +
                ", dataPublicacao='" + dataPublicacao + '\'' +
                ", boletimPublicacao='" + boletimPublicacao + '\'' +
                '}';
    }
}
