package com.enatbanksc.casemanagementsystem.case_management.SentEmail;


import org.springframework.scheduling.annotation.Scheduled;

public interface EmailService {
    @Scheduled(cron = "*/10 * * * * *")
    boolean sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);
}
