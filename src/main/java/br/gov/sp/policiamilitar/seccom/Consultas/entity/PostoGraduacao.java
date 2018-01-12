package br.gov.sp.policiamilitar.seccom.Consultas.entity;

public class PostoGraduacao {

    private int idPostoGraduacao;
    private String siglaPostoGraduacao;
    private String descricaoPostoGraduacao;

    public PostoGraduacao(int idPostoGraduacao, String siglaPostoGraduacao, String descricaoPostoGraduacao) {
        this.idPostoGraduacao = idPostoGraduacao;
        this.siglaPostoGraduacao = siglaPostoGraduacao;
        this.descricaoPostoGraduacao = descricaoPostoGraduacao;
    }

    public PostoGraduacao() {
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
        return "postoGraduacao{" +
                "idPostoGraduacao=" + idPostoGraduacao +
                ", siglaPostoGraduacao='" + siglaPostoGraduacao + '\'' +
                ", descricaoPostoGraduacao='" + descricaoPostoGraduacao + '\'' +
                '}';
    }
}
