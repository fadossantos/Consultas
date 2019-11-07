package br.gov.sp.policiamilitar.seccom.Consultas.repository;

import br.gov.sp.pmesp.wsscpm.PolicialMilitar;
import br.gov.sp.pmesp.wsscpm.ProcuraPMPorCPF;
import br.gov.sp.pmesp.wsscpm.ProcuraPMPorCPFResponse;
import br.gov.sp.policiamilitar.seccom.Consultas.entity.*;
import br.gov.sp.policiamilitar.seccom.Consultas.utils.SecComUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Repository()
public class PolicialMilitarRepository extends WebServiceGatewaySupport {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PolicialMilitarRepository() {
        super();
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("br.gov.sp.pmesp.wsscpm");
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
    }

    public List<Interessado> listar() {
        return jdbcTemplate.query("\n" +
                        "select pm.PMRENUM re\n" +
                        ", pm.PMREDGT digitoRE\n" +
                        ", pm.PMNOM nome\n" +
                        ", pos.POSCOD idPostoGraduacao\n" +
                        ", pos.POSSGL siglaPostoGraduacao\n" +
                        ", pos.POSDES descricaoPostoGraduacao\n" +
                        ", opmatu.OPMN03DES as opmAtual\n" +
                        ", opmatu.OPMCOD as opmAtualCod\n" +
                        ", opmefe.OPMN03DES as opmEfetiva\n" +
                        ", opmefe.OPMCOD as opmEfetivaCod\n" +
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
                        rs.getString("re"),
                        rs.getString("nome").trim(),
                        rs.getString("digitoRE").trim(),
                        rs.getString("cpf"),
                        rs.getString("opmAtual").trim(),
                        rs.getString("opmAtualCod").trim(),
                        rs.getString("opmEfetiva").trim(),
                        rs.getString("opmEfetivaCod").trim(),
                        rs.getString("situacao").trim(),
                        rs.getDate("dataNascimento"),
                        rs.getDate("inatividade"),
                        rs.getDate("inicioExercicio"),
                        rs.getDate("matricula"),
                        new PostoGraduacaoSeccom(rs.getInt("idPostoGraduacao"),
                                rs.getString("siglaPostoGraduacao").trim(),
                                rs.getString("descricaoPostoGraduacao").trim()),
                        null,
                        null
                ));
    }

    public Integer contarSdPMCogitadosPorData(Date dataIngresso) {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) " +
                        "from BDCORP.PMESP.PM pm\n" +
                        "inner join bdcorp.PMESP.pos pos on pm.POSCOD = pos.POSCOD\n" +
                        "inner join BDCORP.PMESP.SLG slg on pm.SLGCOD = slg.SLGCOD\n" +
                        "left join BDCORP.PMESP.OPM opmatu on pm.PMOPMATU = opmatu.OPMCOD\n" +
                        "left join BDCORP.PMESP.OPM opmefe on pm.PMOPMEFE = opmefe.OPMCOD\n" +
                        "left join BDCORP.PMESP.PMDOC pmdoc on pm.PMRENUM = pmdoc.PMRENUM\n" +
                        "where pmdoc.cpf <> -9 \n" +
                        "and slg.slgcod in (1,2) \n" +
                        "and tslcod <> 18 \n" +
                        "and pos.poscod = 16 \n" +
                        "and pm.PMADIDAT <= ?", new Object[]{dataIngresso},
                Integer.class);
    }

    public List<Interessado> listarSdCogitadosPorData(Date dataIngresso) {
        return jdbcTemplate.query("\n" +
                        "select pm.PMRENUM re\n" +
                        ", pm.PMREDGT digitoRE\n" +
                        ", pm.PMNOM nome\n" +
                        ", pos.POSCOD idPostoGraduacao\n" +
                        ", pos.POSSGL siglaPostoGraduacao\n" +
                        ", pos.POSDES descricaoPostoGraduacao\n" +
                        ", opmatu.OPMN03DES as opmAtual\n" +
                        ", opmatu.OPMCOD as opmAtualCod\n" +
                        ", opmefe.OPMN03DES as opmEfetiva\n" +
                        ", opmefe.OPMCOD as opmEfetivaCod\n" +
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
                        "where pmdoc.cpf <> -9 \n" +
                        "and slg.slgcod in (1,2) \n" +
                        "and tslcod <> 18 \n" +
                        "and pos.poscod = 16 \n" +
                        "and pm.PMADIDAT <= ? order by pm.PMADIDAT", new Object[]{dataIngresso},
                (rs, rowNum) -> new Interessado(
                        rs.getString("re"),
                        rs.getString("nome").trim(),
                        rs.getString("digitoRE").trim(),
                        rs.getString("cpf"),
                        rs.getString("opmAtual").trim(),
                        rs.getString("opmAtualCod").trim(),
                        rs.getString("opmEfetiva").trim(),
                        rs.getString("opmEfetivaCod").trim(),
                        rs.getString("situacao").trim(),
                        rs.getDate("dataNascimento"),
                        rs.getDate("inatividade"),
                        rs.getDate("inicioExercicio"),
                        rs.getDate("matricula"),
                        new PostoGraduacaoSeccom(rs.getInt("idPostoGraduacao"),
                                rs.getString("siglaPostoGraduacao").trim(),
                                rs.getString("descricaoPostoGraduacao").trim()),
                        null,
                        null
                ));
    }

    public Interessado buscarPorRe(String id) {
        return jdbcTemplate.queryForObject("\n" +
                        "select top 1 pm.PMRENUM re\n" +
                        ", pm.PMREDGT digitoRE\n" +
                        ", pm.PMNOM nome\n" +
                        ", pos.POSCOD idPostoGraduacao\n" +
                        ", pos.POSSGL siglaPostoGraduacao\n" +
                        ", pos.POSDES descricaoPostoGraduacao\n" +
                        ", opmatu.OPMN03DES as opmAtual\n" +
                        ", opmatu.OPMCOD as opmAtualCod\n" +
                        ", opmefe.OPMN03DES as opmEfetiva\n" +
                        ", opmefe.OPMCOD as opmEfetivaCod\n" +
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
                        rs.getString("re"),
                        rs.getString("nome").trim(),
                        rs.getString("digitoRE").trim(),
                        rs.getString("cpf"),
                        rs.getString("opmAtual").trim(),
                        rs.getString("opmAtualCod").trim(),
                        rs.getString("opmEfetiva").trim(),
                        rs.getString("opmEfetivaCod").trim(),
                        rs.getString("situacao").trim(),
                        rs.getDate("dataNascimento"),
                        rs.getDate("inatividade"),
                        rs.getDate("inicioExercicio"),
                        rs.getDate("matricula"),
                        new PostoGraduacaoSeccom(rs.getInt("idPostoGraduacao"),
                                rs.getString("siglaPostoGraduacao").trim(),
                                rs.getString("descricaoPostoGraduacao").trim()),
                        null,
                        null
                ));
    }

    public Interessado buscarPorCpf(String id) {
        /*return jdbcTemplate.queryForObject("\n" +
                        "select top 1 pm.PMRENUM re\n" +
                        ", pm.PMREDGT digitoRE\n" +
                        ", pm.PMNOM nome\n" +
                        ", pos.POSCOD idPostoGraduacao\n" +
                        ", pos.POSSGL siglaPostoGraduacao\n" +
                        ", pos.POSDES descricaoPostoGraduacao\n" +
                        ", opmatu.OPMN03DES as opmAtual\n" +
                        ", opmatu.OPMCOD as opmAtualCod\n" +
                        ", opmefe.OPMN03DES as opmEfetiva\n" +
                        ", opmefe.OPMCOD as opmEfetivaCod\n" +
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
                        "where pmdoc.cpf <> -9 and pmdoc.cpf = ?", new Object[]{id},
                (rs, rowNum) -> new Interessado(
                        rs.getString("re"),
                        rs.getString("nome").trim(),
                        rs.getString("digitoRE").trim(),
                        rs.getString("cpf"),
                        rs.getString("opmAtual").trim(),
                        rs.getString("opmAtualCod").trim(),
                        rs.getString("opmEfetiva").trim(),
                        rs.getString("opmEfetivaCod").trim(),
                        rs.getString("situacao").trim(),
                        rs.getDate("dataNascimento"),
                        rs.getDate("inatividade"),
                        rs.getDate("inicioExercicio"),
                        rs.getDate("matricula"),
                        new PostoGraduacaoSeccom(rs.getInt("idPostoGraduacao"),
                                rs.getString("siglaPostoGraduacao").trim(),
                                rs.getString("descricaoPostoGraduacao").trim()),
                        null,
                        null
                ));

         */

        ProcuraPMPorCPF request = new ProcuraPMPorCPF();
        request.setPMCPFNum(id);
        ProcuraPMPorCPFResponse response = (ProcuraPMPorCPFResponse) getWebServiceTemplate().marshalSendAndReceive("http://webserviceshom.intranet.policiamilitar.sp.gov.br/WSSCPM/Service.asmx/procuraPMPorCPF",
                request, new SoapActionCallback("http://tempuri.org/"));
        PolicialMilitar pm = response.getProcuraPMPorCPFResult();
        return Interessado.getInteressadoPorPolicial(pm);
    }

    public FotoInteressado obterFotoPorRe(int re) {
        return jdbcTemplate.queryForObject("SELECT TOP 1 RE\n" +
                        "  ,FUCIMGATUFOT\n" +
                        "  FROM [BDCORP].[PMESP].[FUCIMG]\n" +
                        "  where RE = ?", new Object[]{re},
                (rs, rowNum) -> new FotoInteressado(
                        rs.getInt("RE"),
                        Base64.getEncoder().encodeToString(SecComUtils.redimensionaImg(rs.getBytes("FUCIMGATUFOT"), 120, 160))
                ));
    }

    public List<AvaliacaoDesempenho> obterAvaliacoesDesempenhoPorCpf(final String cpf, final Date date) {
        List<AvaliacaoDesempenho> avaliacoesDesempenho = jdbcTemplate.query("SELECT \n" +
                        "ava.[ANOREFAVAL] as 'ano'\n" +
                        ",ava.[NUMREFAVAL] as 'semestre'\n" +
                        ",ava.DSNAOAVAL as 'situacao'\n" +
                        ",con.DESCRICAO as 'conceito'\n" +
                        "FROM [BDCORP].[POLM01].[T0265_AVALDESE1] ava\n" +
                        "left join [BDCORP].[POLM01].[T0267_CONCFIN1] res\n" +
                        "on ava.NUMREAVADO = res.NUMREAVADO \n" +
                        "and ava.ANOREFAVAL = res.ANOREFAVAL \n" +
                        "and ava.NUMREFAVAL = res.NUMREFAVAL\n" +
                        "left join [BDCORP].[POLM01].[SADECON] con\n" +
                        "on res.CONCFINAL = con.CODIGO\n" +
                        "inner join BDCORP.PMESP.PMDOC pmdoc\n" +
                        "on pmdoc.PMRENUM = ava.NUMREAVADO and cpf <> -9\n" +
                        "where pmdoc.CPF = ? \n" +
                        "UNION ALL\n" +
                        "SELECT avaprd.AvaPrdAno as 'ano', \n" +
                        "avaprd.AvaPrdIdfNum as 'semestre', \n" +
                        "avanaomot.AvaNaoMotDes as 'situacao', \n" +
                        "congra.ConGraDes as 'conceito' \n" +
                        "FROM BDCORP.SchSAD.AVAPRD avaprd \n" +
                        "left join BDCORP.SchSAD.AVA ava on ava.AvaPrdAno = avaprd.AvaPrdAno and ava.AvaPrdIdfNum = avaprd.AvaPrdIdfNum \n" +
                        "left join BDCORP.SchSAD.AVACON avacon \n" +
                        "on ava.AvaCpfNum = avacon.AvaCpfNum \n" +
                        "and ava.AvaPrdAno = avacon.AvaPrdAno \n" +
                        "and ava.AvaPrdIdfNum = avacon.AvaPrdIdfNum \n" +
                        "left join BDCORP.SchSAD.CONAPL conapl \n" +
                        "on avacon.ConAplCod = conapl.ConAplCod \n" +
                        "left join BDCORP.SchSAD.CONGRA congra \n" +
                        "on conapl.ConGraCod = congra.ConGraCod \n" +
                        "left join BDCORP.SchSAD.AVASTT avastt \n" +
                        "on ava.AvaSttCod = avastt.AvaSttCod \n" +
                        "left join BDCORP.SchSAD.AVANAOMOT avanaomot \n" +
                        "on ava.AvaNaoMotCod = avanaomot.AvaNaoMotCod \n" +
                        "where ava.AvaCpfNum = ? and avaprd.AvaPrdFimDat < ? \n" +
                        "order by ano desc, semestre desc;", new Object[]{cpf, cpf, date},
                (rs, rowNum) -> new AvaliacaoDesempenho(
                        rs.getString("ano") != null ? rs.getString("ano").trim() : null,
                        rs.getString("semestre") != null ? rs.getString("semestre").trim() : null,
                        rs.getString("situacao") != null ? rs.getString("situacao").trim() : null,
                        rs.getString("conceito") != null ? rs.getString("conceito").trim() : null
                ));
        return avaliacoesDesempenho;
    }

    public List<Taf> obterTAFsPorCpf(final String cpf, final Date dataBase) {

        List<Taf> listaTaf = jdbcTemplate.query("SELECT " +
                        "tafgrp.TafGrpVigIniDatHor as 'dataTaf', " +
                        "TAFFXACLSRES.TafFxaClsResNom as 'resultado' " +
                        "FROM [BDCORP].[SchSITAF].[TAFAPLRSTPONTOT] taf " +
                        "  left join [BDCORP].[SchSITAF].[TAFGRP] tafgrp " +
                        "  on taf.TafGrpNum = tafgrp.TafGrpNum " +
                        "  left join [BDCORP].[SchSITAF].[TAFFXACLSRES] TAFFXACLSRES " +
                        "  on taf.TafFxaClsResCod = TAFFXACLSRES.TafFxaClsResCod " +
                        "  where taf.PMAdoCpfNum = ? and " +
                        "  tafgrp.TafGrpVigIniDatHor < ? " +
                        "  order by tafgrp.TafGrpVigFimDatHor desc", new Object[]{cpf, dataBase},
                (rs, rowNum) -> new Taf(rs.getDate("dataTaf"),
                        rs.getString("resultado") != null ? rs.getString("resultado").trim() : null)
        );
        return listaTaf;
    }

    public String obterEstadoEfetivoPorRe(final String re) {
        List<String> retorno = jdbcTemplate.query("select top 1 pm.PMRENUM re " +
                        ", slg.SLGDES situacao  " +
                        "from BDCORP.PMESP.PM pm\n" +
                        "inner join BDCORP.PMESP.SLG slg on pm.SLGCOD = slg.SLGCOD\n" +
                        "where pm.PMRENUM = ?", new Object[]{re},
                (rs, rowNum) -> {
                    String ret = rs.getString("situacao") != null ? rs.getString("situacao").trim() : null;
                    return ret;
                });
        return retorno.get(0);
    }

    public List<Afastamento> obterLTSPessoaFamiliaPorCPF(final String cpf) {
        List<Afastamento> listaRetorno = jdbcTemplate.query("select \n" +
                        "pm.PMRENUM re \n" +
                        ", convert(varchar, agr.AgrIniDat ,103) inicioAgregacao \n" +
                        ", convert(varchar, agr.AgrFimDat ,103) fimAgregacao \n" +
                        ", agr.AgrDoeNum doeInicio \n" +
                        ", agr.AgrFimDoeNum doeFim \n" +
                        ", tsl.TSLDES afastamento \n" +
                        "from BDCORP.PMESP.PM pm \n" +
                        "inner join BDCORP.PMESP.PMDOC pmdoc on pm.PMRENUM = pmdoc.PMRENUM \n" +
                        "LEFT join BDCORP.PMESP.POS pos on pm.POSCOD = pos.POSCOD \n" +
                        "LEFT join BDCORP.PMESP.SLG slg on pm.SLGCOD = slg.SLGCOD \n" +
                        "LEFT join [BDCORP].[PMESP].[AGR] agr on agr.FucCpfNum = pmdoc.CPF \n" +
                        "LEFT join [BDCORP].[PMESP].[TSL] tsl on agr.TslCod = tsl.TSLCOD and agr.SlgCod = tsl.SLGCOD \n" +
                        "where pmdoc.DOCTIPCOD = 1 \n" +
                        "and (((tsl.SLGCOD = 2) and (tsl.TSLCOD=2)) or ((tsl.SLGCOD = 2) and (tsl.TSLCOD=25))) \n" +
                        "and POSDES <> 'SOLDADO PM TEMPORARIO    ' \n" +
                        "and pmdoc.cpf = ? " +
                        "order by agr.AgrIniDat asc ", new Object[]{cpf},
                (rs, rowNum) -> {
                    Afastamento ret = new Afastamento(
                            rs.getString("re"),
                            rs.getString("inicioAgregacao"),
                            rs.getString("fimAgregacao"),
                            rs.getString("doeInicio"),
                            rs.getString("doeFim"),
                            rs.getString("afastamento")
                    );
                    return ret;
                });
        return listaRetorno;
    }

    public List<Afastamento> obterLSVPorCPF(String cpf) {
        List<Afastamento> listaRetorno = jdbcTemplate.query("select \n" +
                        "pm.PMRENUM re \n" +
                        ", convert(varchar, agr.AgrIniDat ,103) inicioAgregacao \n" +
                        ", convert(varchar, agr.AgrFimDat ,103) fimAgregacao \n" +
                        ", agr.AgrDoeNum doeInicio \n" +
                        ", agr.AgrFimDoeNum doeFim \n" +
                        ", tsl.TSLDES afastamento \n" +
                        "from BDCORP.PMESP.PM pm \n" +
                        "inner join BDCORP.PMESP.PMDOC pmdoc on pm.PMRENUM = pmdoc.PMRENUM \n" +
                        "LEFT join BDCORP.PMESP.POS pos on pm.POSCOD = pos.POSCOD \n" +
                        "LEFT join BDCORP.PMESP.SLG slg on pm.SLGCOD = slg.SLGCOD \n" +
                        "LEFT join [BDCORP].[PMESP].[AGR] agr on agr.FucCpfNum = pmdoc.CPF \n" +
                        "LEFT join [BDCORP].[PMESP].[TSL] tsl on agr.TslCod = tsl.TSLCOD and agr.SlgCod = tsl.SLGCOD \n" +
                        "where pmdoc.DOCTIPCOD = 1 \n" +
                        "and ((tsl.SLGCOD = 2) and (tsl.TSLCOD=3)) \n" +
                        "and POSDES <> 'SOLDADO PM TEMPORARIO    ' \n" +
                        "and pmdoc.cpf = ? " +
                        "order by agr.AgrIniDat asc ", new Object[]{cpf},
                (rs, rowNum) -> {
                    Afastamento ret = new Afastamento(
                            rs.getString("re"),
                            rs.getString("inicioAgregacao"),
                            rs.getString("fimAgregacao"),
                            rs.getString("doeInicio"),
                            rs.getString("doeFim"),
                            rs.getString("afastamento")
                    );
                    return ret;
                });
        return listaRetorno;
    }

    public String obterComportamento(String re){
        RestTemplate restTemplate = new RestTemplate();

      /*  CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(proxyUrl, port),
                new UsernamePasswordCredentials(username, password));

        HttpHost myProxy = new HttpHost(proxyUrl, port);
        HttpClientBuilder clientBuilder = HttpClientBuilder.create();

        clientBuilder.setProxy(myProxy).setDefaultCredentialsProvider(credsProvider).disableCookieManagement();

        HttpClient httpClient = clientBuilder.build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(httpClient);
       */

        final String uri = "https://www.sijd.policiamilitar.sp.gov.br/sijd/pesquisa_cpp_json.asp?re=990777&token=WERKJHFBSKVBXMCKJDHALSKDJFKJAHAFMNDFN";
        ResponseEntity<Punicao[]> response =
                restTemplate.getForEntity(
                        uri,
                        Punicao[].class);
        Punicao[] punicoes = response.getBody();
        System.out.println(punicoes);
        return "sucesso";
    }
}