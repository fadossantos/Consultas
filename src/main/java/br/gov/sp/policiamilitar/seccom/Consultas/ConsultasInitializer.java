package br.gov.sp.policiamilitar.seccom.Consultas;

import br.gov.sp.policiamilitar.seccom.Consultas.entity.Interessado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ConsultasInitializer {


    @PostConstruct
    private void init() {

        System.out.println("Iniciado Com Sucesso");

    }

}
