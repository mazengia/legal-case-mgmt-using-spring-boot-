package com.enatbanksc.casemanagementsystem.case_management._config.db_config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "cmMsServerEntityManager",
        transactionManagerRef = "cmMsServerTransactionManager",
        basePackages = {"com.enatbanksc.casemanagementsystem.case_management.*"}
)

public class CaseManagementDbConfig {

    private final Environment env;

    public CaseManagementDbConfig(Environment env) {
        super();
        this.env = env;
    }


    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource msserverDataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }

    @Primary
    @Bean(name = "cmMsServerEntityManager")
    public LocalContainerEntityManagerFactoryBean msServerEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(msserverDataSource());
        em.setPackagesToScan("com.enatbanksc.casemanagementsystem.case_management.*");
        em.setPersistenceUnitName("case_management");
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
        properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());

        em.setJpaPropertyMap(properties);
        return em;
    }

    @Primary
    @Bean(name = "cmMsServerTransactionManager")
    public PlatformTransactionManager msServerTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(msServerEntityManager().getObject());
        return transactionManager;
    }
}

