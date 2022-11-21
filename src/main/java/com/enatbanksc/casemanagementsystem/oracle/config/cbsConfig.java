//package com.enatbanksc.casemanagementsystem.oracle.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "oracleEntityManager",
//        transactionManagerRef = "oracleTransactionManager",
//        basePackages = "com.enatbanksc.casemanagementsystem.oracle"
//)
//public class cbsConfig {
//
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.oracle.datasource")
//    public DataSource oracleDataSource() {
//        return DataSourceBuilder
//                .create()
//                .build();
//    }
//
//    @Bean(name = "oracleEntityManager")
//    public LocalContainerEntityManagerFactoryBean oracleEntityManager() {
//        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(oracleDataSource());
//        em.setPackagesToScan("com.enatbanksc.casemanagementsystem.oracle");
//        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        final HashMap<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", null);
//        properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
//        em.setJpaPropertyMap(properties);
//
//        return em;
//    }
//
//    @Bean(name = "oracleTransactionManager")
//    public PlatformTransactionManager oracleTransactionManager() {
//        final JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(oracleEntityManager().getObject());
//        return transactionManager;
//    }
//}
