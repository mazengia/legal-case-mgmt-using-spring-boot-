package com.enatbanksc.casemanagementsystem.case_management._config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJpaRepositories(repositoryBaseClass = NaturalRepositoryImpl.class, basePackages = "com.enatbanksc.casemanagementsystem")
public class JpaEnversConfiguration {
    @Bean
    AuditorAware<String> auditorProvider(){
        return new AuditorAwareImpl();
    }
}
