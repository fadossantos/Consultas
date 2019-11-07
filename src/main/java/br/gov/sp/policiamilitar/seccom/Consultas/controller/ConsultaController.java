package br.gov.sp.policiamilitar.seccom.Consultas.controller;


import br.gov.sp.policiamilitar.seccom.Consultas.entity.*;
import br.gov.sp.policiamilitar.seccom.Consultas.repository.PolicialMilitarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class ConsultaController {

    @Autowired
    PolicialMilitarRepository policialMilitarRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/consulta/interessado")
    public Interessado getInteressado(@RequestParam(value = "re", required = true) String re) {
        return this.policialMilitarRepository.buscarPorRe(re);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/consulta/interessadoPorCpf")
    public Interessado getInteressadoPorCpf(@RequestParam(value = "cpf", required = true) String cpf) {
        return this.policialMilitarRepository.buscarPorCpf(cpf);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/consulta/fotoInteressado")
    public FotoInteressado getFotoInteressado(@RequestParam(value = "re", required = true) int re) {
        return this.policialMilitarRepository.obterFotoPorRe(re);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/consulta/cogitadosPorData")
    public List<Interessado> getCogitadosPorData(@RequestParam(value = "dataCorte", required = true) String dataCorte) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return this.policialMilitarRepository.listarSdCogitadosPorData(sdf.parse(dataCorte));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/consulta/totalCogitadosPorData")
    public Integer getTotalCogitadosPorData(@RequestParam(value = "dataCorte", required = true) String dataCorte) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return this.policialMilitarRepository.contarSdPMCogitadosPorData(sdf.parse(dataCorte));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/consulta/avaliacaoDesempenhoPorCpf")
    public List<AvaliacaoDesempenho> getAvaliacaoDesempenhoPorCpf(@RequestParam(value = "cpf", required = true) String cpf,
                                                                  @RequestParam(value = "dataBase", required = true) String dataBase) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return this.policialMilitarRepository.obterAvaliacoesDesempenhoPorCpf(cpf, sdf.parse(dataBase));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/consulta/tafPorCpf")
    public List<Taf> getTafPorCpf(@RequestParam(value = "cpf", required = true) String cpf,
                                  @RequestParam(value = "dataBase", required = true) String dataBase) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return this.policialMilitarRepository.obterTAFsPorCpf(cpf, sdf.parse(dataBase));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/consulta/estadoEfetivoPorRe")
    public String getEstadoEfetivoPorRe(@RequestParam(value = "re", required = true) String re) {
        return this.policialMilitarRepository.obterEstadoEfetivoPorRe(re);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/consulta/ltsPessoaFamiliaPorCPF")
    public List<Afastamento> getLtsPessoaFamiliaPorCPF(@RequestParam(value = "cpf", required = true) String cpf) {
        return this.policialMilitarRepository.obterLTSPessoaFamiliaPorCPF(cpf);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/consulta/lsvPorCPF")
    public List<Afastamento> getLSVPorCPF(@RequestParam(value = "cpf", required = true) String cpf) {
        return this.policialMilitarRepository.obterLSVPorCPF(cpf);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/consulta/comportamentoPorRE")
    public String getComportamentoPorRE(@RequestParam(value = "re", required = true) String re) {
        return this.policialMilitarRepository.obterComportamento(re);
    }


}
