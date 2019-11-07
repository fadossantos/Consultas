package br.gov.sp.policiamilitar.seccom.Consultas.config;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
    	 ParameterBuilder aParameterBuilder = new ParameterBuilder();
         aParameterBuilder.name("Authorization").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
         List<Parameter> aParameters = new ArrayList<Parameter>();
         aParameters.add(aParameterBuilder.build());
      
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(new HashSet<String>() {
                    {
                        add("application/json");
                        //add("application/xml");
                    }
                })
                .consumes(new HashSet<String>() {
                    {
                        add("application/json");
                        //add("application/xml");
                    }
                })
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().globalOperationParameters(aParameters);
    }

}
