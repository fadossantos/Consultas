package br.gov.sp.policiamilitar.seccom.Consultas.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(basePackages = {"br.gov.sp.policiamilitar.seccom.Consultas.entity"},
      entityManagerFactoryRef = "BDCorpDbEntityManager",
      transactionManagerRef = "BDCorpDbTransactionManager")
public class BDCorpDbConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean BDCorpDbEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(BDCorpDbDatasource());
        em.setPackagesToScan(new String[]{"br.gov.sp.policiamilitar.seccom.Consultas.entity"});
        em.setPersistenceUnitName("BDCorpDbEntityManager");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        // properties.put("hibernate.dialect", env.getProperty("db1.jpa.hibernate.dialect"));
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");

        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public JdbcTemplate BdCorpDbTemplate(){
        JdbcTemplate template = new JdbcTemplate(BDCorpDbDatasource());
        return template;
    }

    @Bean
    public DataSource BDCorpDbDatasource() {
        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        // dataSource.setDriverClassName(env.getProperty("db1.datasource.driverClassName"));
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // dataSource.setUrl(env.getProperty("db1.datasource.url"));
        dataSource.setUrl("jdbc:sqlserver://bdcrpp2.policiamilitar.sp.gov.br:65535;databaseName=BDCORP;integratedSecurity=true;authenticationScheme=NativeAuthentication");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager BDCorpDbTransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                BDCorpDbEntityManager().getObject());
        return transactionManager;
    }

}
