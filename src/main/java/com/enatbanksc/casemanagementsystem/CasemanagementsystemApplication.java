package com.enatbanksc.casemanagementsystem;

import com.enatbanksc.casemanagementsystem.case_management.SentEmail.EmailDetails;
import com.enatbanksc.casemanagementsystem.case_management._config.AuditorAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class CasemanagementsystemApplication {
	@Bean
	AuditorAware<String> auditorProvider(){
		return new AuditorAwareImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(CasemanagementsystemApplication.class, args);
	}

}
