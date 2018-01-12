package br.gov.sp.policiamilitar.seccom.Consultas.repository;


import java.util.Base64;
import java.util.List;

import br.gov.sp.policiamilitar.seccom.Consultas.entity.FotoInteressado;
import br.gov.sp.policiamilitar.seccom.Consultas.entity.Interessado;
import br.gov.sp.policiamilitar.seccom.Consultas.entity.PostoGraduacao;
import br.gov.sp.policiamilitar.seccom.Consultas.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InteressadoRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Interessado> listar() {
        return jdbcTemplate.query("\n" +
                        "select pm.PMRENUM re\n" +
                        ", pm.PMREDGT digitoRE\n" +
                        ", pm.PMNOM nome\n" +
                        ", pos.POSCOD idPostoGraduacao\n" +
                        ", pos.POSSGL siglaPostoGraduacao\n" +
                        ", pos.POSDES descricaoPostoGraduacao\n" +
                        ", opmatu.OPMN03DES as opmAtual\n" +
                        ", opmefe.OPMN03DES as opmEfetiva\n" +
                        ", slg.SLGDES situacao  \n" +
                        ", pm.PMNASDAT dataNascimento\n" +
                        ", pm.PMINVDAT inatividade\n" +
                        ", pm.PMADIDAT inicioExercicio\n" +
                        ", pm.PmPseDat matricula\n" +
                        ", pmdoc.cpf\n" +
                        "from BDCORP.PMESP.PM pm\n" +
                        "inner join bdcorp.PMESP.pos pos on pm.POSCOD = pos.POSCOD\n" +
                        "inner join BDCORP.PMESP.SLG slg on pm.SLGCOD = slg.SLGCOD\n" +
                        "left join BDCORP.PMESP.OPM opmatu on pm.PMOPMATU = opmatu.OPMCOD\n" +
                        "left join BDCORP.PMESP.OPM opmefe on pm.PMOPMEFE = opmefe.OPMCOD\n" +
                        "left join BDCORP.PMESP.PMDOC pmdoc on pm.PMRENUM = pmdoc.PMRENUM\n" +
                        "where pmdoc.cpf <> -9",
                (rs, rowNum) -> new Interessado(
                        rs.getInt("re"),
                        rs.getString("digitoRE").trim(),
                        rs.getString("nome").trim(),
                        new PostoGraduacao(rs.getInt("idPostoGraduacao"),
                                rs.getString("siglaPostoGraduacao").trim(),
                                rs.getString("descricaoPostoGraduacao").trim()),
                        rs.getString("opmAtual").trim(),
                        rs.getString("opmEfetiva").trim(),
                        rs.getString("situacao").trim(),
                        rs.getDate("dataNascimento"),
                        rs.getDate("inatividade"),
                        rs.getDate("inicioExercicio"),
                        rs.getDate("matricula"),
                        rs.getString("cpf")
                ));
    }

    public Interessado buscar(Integer id) {
        return jdbcTemplate.queryForObject("\n" +
                        "select top 1 pm.PMRENUM re\n" +
                        ", pm.PMREDGT digitoRE\n" +
                        ", pm.PMNOM nome\n" +
                        ", pos.POSCOD idPostoGraduacao\n" +
                        ", pos.POSSGL siglaPostoGraduacao\n" +
                        ", pos.POSDES descricaoPostoGraduacao\n" +
                        ", opmatu.OPMN03DES as opmAtual\n" +
                        ", opmefe.OPMN03DES as opmEfetiva\n" +
                        ", slg.SLGDES situacao  \n" +
                        ", pm.PMNASDAT dataNascimento\n" +
                        ", pm.PMINVDAT inatividade\n" +
                        ", pm.PMADIDAT inicioExercicio\n" +
                        ", pm.PmPseDat matricula\n" +
                        ", pmdoc.cpf cpf\n" +
                        "from BDCORP.PMESP.PM pm\n" +
                        "inner join bdcorp.PMESP.pos pos on pm.POSCOD = pos.POSCOD\n" +
                        "inner join BDCORP.PMESP.SLG slg on pm.SLGCOD = slg.SLGCOD\n" +
                        "left join BDCORP.PMESP.OPM opmatu on pm.PMOPMATU = opmatu.OPMCOD\n" +
                        "left join BDCORP.PMESP.OPM opmefe on pm.PMOPMEFE = opmefe.OPMCOD\n" +
                        "left join BDCORP.PMESP.PMDOC pmdoc on pm.PMRENUM = pmdoc.PMRENUM\n" +
                        "where pmdoc.cpf <> -9 and pm.pmrenum = ?", new Object[]{id},
                (rs, rowNum) -> new Interessado(
                        rs.getInt("re"),
                        rs.getString("digitoRE").trim(),
                        rs.getString("nome").trim(),
                        new PostoGraduacao(rs.getInt("idPostoGraduacao"),
                                rs.getString("siglaPostoGraduacao").trim(),
                                rs.getString("descricaoPostoGraduacao").trim()),
                        rs.getString("opmAtual").trim(),
                        rs.getString("opmEfetiva").trim(),
                        rs.getString("situacao").trim(),
                        rs.getDate("dataNascimento"),
                        rs.getDate("inatividade"),
                        rs.getDate("inicioExercicio"),
                        rs.getDate("matricula"),
                        rs.getString("cpf")
                ));
    }

    public FotoInteressado obterFotoPorRe(int re){
        return jdbcTemplate.queryForObject("SELECT TOP 1 RE\n" +
                        "  ,FUCIMGATUFOT\n" +
                        "  FROM [BDCORP].[PMESP].[FUCIMG]\n" +
                        "  where RE = ?", new Object[]{re},
                (rs, rowNum) -> new FotoInteressado(
                        rs.getInt("RE"),
                        Base64.getEncoder().encodeToString(Utils.redimensionaImg(rs.getBytes("FUCIMGATUFOT"), 120, 160))
                ));
    }
}
