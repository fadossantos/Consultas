package br.gov.sp.policiamilitar.seccom.Consultas.controller;


import br.gov.sp.policiamilitar.seccom.Consultas.entity.FotoInteressado;
import br.gov.sp.policiamilitar.seccom.Consultas.entity.Interessado;
import br.gov.sp.policiamilitar.seccom.Consultas.repository.InteressadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsultaController {

    @Autowired
    InteressadoRepository interessadoRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/consulta/interessado")
    public Interessado getInteressado(@RequestParam(value = "re", required = true) int re) {
        return this.interessadoRepository.buscar(re);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/consulta/fotoInteressado")
    public FotoInteressado getFotoInteressado(@RequestParam(value = "re", required = true) int re) {
        return this.interessadoRepository.obterFotoPorRe(re);
    }
}
