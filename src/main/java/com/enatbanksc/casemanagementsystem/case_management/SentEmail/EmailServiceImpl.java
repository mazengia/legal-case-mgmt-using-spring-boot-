package com.enatbanksc.casemanagementsystem.case_management.SentEmail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
@Service
@RequiredArgsConstructor
public class EmailServiceImpl  implements EmailService{

    private final EmailRepository emailRepository;

    public JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;
    public boolean sendSimpleMail(EmailDetails details)
    {

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
            javaMailSender.send(mailMessage);
//            return "Mail Sent Successfully...";
        }
        catch (Exception e) {
//            return "Error while Sending Mail";
        }
        return false;
    }
    public String sendMailWithAttachment(EmailDetails details)
    {
        // Creating a mime message
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(details.getSubject());
            FileSystemResource file  = new FileSystemResource( new File(details.getAttachment()));

            mimeMessageHelper.addAttachment(file.getFilename(), file);
            javaMailSender.send(mimeMessage);
            System.out.println("Mail sent Successfully");
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {
            return "Error while sending mail!!!";
        }
    }

    @Override
    public Page<EmailDetails> getEmails(Pageable pageable, JwtAuthenticationToken token) {
        return emailRepository.findAllByOrderByCreatedAtDesc(pageable);
    }
}
