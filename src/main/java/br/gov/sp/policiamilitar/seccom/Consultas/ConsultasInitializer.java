package br.gov.sp.policiamilitar.seccom.Consultas;

import br.gov.sp.policiamilitar.seccom.Consultas.entity.Interessado;
import br.gov.sp.policiamilitar.seccom.Consultas.repository.InteressadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ConsultasInitializer {

    @Autowired
    InteressadoRepository interessadoRepository;

    @PostConstruct
    private void init() {

        System.out.println("Iniciado Com Sucesso");
        Interessado inter = interessadoRepository.buscar(127671);
        System.out.println(inter.toString());

    }

}
