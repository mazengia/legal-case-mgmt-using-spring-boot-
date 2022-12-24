package com.enatbanksc.casemanagementsystem.case_management.SentEmail;


import com.enatbanksc.casemanagementsystem.case_management.AuctionType.AuctionType;
import com.enatbanksc.casemanagementsystem.case_management.ForeClosure.ForeClosure;
import com.enatbanksc.casemanagementsystem.case_management.ForeClosure.ForeClosureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

//@RestController
@Service
public class EmailController {
    //    For example, 0 10 8 * * ? means that the task is executed at 08:10:00 every day
    @Autowired
    private EmailService emailService;
    @Autowired
    private ForeClosureRepository foreClosureRepository;
    @Autowired
    private EmailRepository emailRepository;

//    @PostMapping("/sendMail")
//"*/10 * * * * *"     every ten second
//    "0 0 8,10 * * *" = 8 and 10 o'clock of every day.
    @Scheduled(cron = "0 0 2,10 * * *")
    public String sendMail() throws Exception {

        EmailDetails details = new EmailDetails();
        LocalDate today = LocalDate.now();
        var foreClosure = foreClosureRepository.findAll();
        for (ForeClosure foreClosure1 : foreClosure) {

            var auctionType = foreClosure1.getAuctionType();
            for (AuctionType auctionTypeList : auctionType) {
                LocalDate date = LocalDate.parse(auctionTypeList.getDateAuctionAnnounced());
            LocalDate created_at = LocalDate.from(
                    date.minusDays(
                            foreClosure1
                                    .getMortgageDetail()
                                    .getMortgageType()
                                    .getMailNotificationType()
                                    .getNumberOfDays())
            );
            if (today.equals(created_at)) {
                details.setRecipient(foreClosure1.getMaintained_by().getEmail());
//                "mz.tesfa@gmail.com"
                details.setMsgBody("I try to check emil");
                details.setSubject("I'm from cron job");
//                emailService.sendSimpleMail(details);
                if (emailService.sendSimpleMail(details)) {
                    details.setSent(true);
                    details.setForeClosure(foreClosure1);
                    emailRepository.save(details);
                }
                System.out.println(details);
            }
            }
        }
//        return emailService.sendSimpleMail(details);
        return null;
    }

    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody EmailDetails details) {
        emailService.sendMailWithAttachment(details);
        System.out.println(details);
        String status = emailService.sendMailWithAttachment(details);
        return status;
    }
}
