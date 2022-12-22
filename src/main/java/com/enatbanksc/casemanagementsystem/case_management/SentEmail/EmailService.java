package com.enatbanksc.casemanagementsystem.case_management.SentEmail;



public interface EmailService {
    void sendSimpleMail(EmailDetails details);
    void sendMailWithAttachment(EmailDetails details);
}
