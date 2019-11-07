package br.gov.sp.policiamilitar.seccom.Consultas.entity;

public class FotoInteressado {

    private int re;
    private String fotoBase64;

    public FotoInteressado(int re, String fotoBase64) {
        this.re = re;
        this.fotoBase64 = fotoBase64;
    }

    public FotoInteressado() {
    }

    public int getRe() {
        return re;
    }

    public void setRe(int re) {
        this.re = re;
    }

    public String getFotoBase64() {
        return fotoBase64;
    }

    public void setFotoBase64(String fotoBase64) {
        this.fotoBase64 = fotoBase64;
    }

    @Override
    public String toString() {
        return "FotoPolicialMilitar{" +
                "re=" + re +
                ", fotoBase64='" + fotoBase64 + '\'' +
                '}';
    }
}
