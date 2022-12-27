package com.enatbanksc.casemanagementsystem.case_management.SentEmail;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public interface EmailService {
    @Scheduled(cron = "*/10 * * * * *")
    boolean sendSimpleMail(EmailDetails details);

    String sendMailWithAttachment(EmailDetails details);

    Page<EmailDetails> getEmails(Pageable pageable, JwtAuthenticationToken token);
}
