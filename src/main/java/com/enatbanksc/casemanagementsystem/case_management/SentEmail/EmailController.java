package com.enatbanksc.casemanagementsystem.case_management.SentEmail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@EnableScheduling
//@RestController
public class EmailController   {

    @Autowired
    private EmailService emailService;


    @Scheduled(cron = "0 */2 * ? * *")
//    @PostMapping("/sendMail")
    public void sendMail( ) {
        EmailDetails details=new EmailDetails();
        details.setRecipient("mz.tesfa@gmail.com");
        details.setMsgBody("im try to check emil");
        details.setSubject("im from cron job");
        emailService.sendSimpleMail(details);
        System.out.println(details);
//        return emailService.sendSimpleMail(details);
    }

    @PostMapping("/sendMailWithAttachment")
    public void sendMailWithAttachment(@RequestBody EmailDetails details)
    {
        emailService.sendMailWithAttachment(details);
        System.out.println(details);
//        String status = emailService.sendMailWithAttachment(details);
//        return status;
    }
}
