package com.enatbanksc.casemanagementsystem.case_management.SentEmail;


import com.enatbanksc.casemanagementsystem.case_management.AuctionType.AuctionType;
import com.enatbanksc.casemanagementsystem.case_management.AuctionType.AuctionTypeRepository;
import com.enatbanksc.casemanagementsystem.case_management.MailNotificationType.MailNotificationTypeRepository;
import com.enatbanksc.casemanagementsystem.case_management.MortgageType.MortgageDetail.MortgageDetail;
import com.enatbanksc.casemanagementsystem.case_management.MortgageType.MortgageDetail.MortgageDetailRepository;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.PaginatedResultsRetrievedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/emails")
@RequiredArgsConstructor
@Service
public class EmailController implements EmailApi {
    //    @Scheduled(cron = "*/10 * * * * *")    every ten second
    //    @Scheduled(cron = "0 0 2,10 * * *")= 2 and 10 o'clock of every day.
    //    @Scheduled(cron = "*/10 * * * * *")
    //    For example, 0 10 8 * * ? means that the task is executed at 08:10:00 every day
    private final EmailService emailService;
    private final EmailRepository emailRepository;
    private final AuctionTypeRepository auctionTypeRepository;
    private final MortgageDetailRepository mortgageDetailRepository;
    private final MailNotificationTypeRepository mailNotificationTypeRepository;
    private final EmailMapper emailMapper;
    private final ApplicationEventPublisher eventPublisher;

    EmailDetails details = new EmailDetails();

    @Scheduled(cron = "0 0 2,10 * * *")
//    @Scheduled(cron = "*/10 * * * * *")
//    @PostMapping("/sendMail")
//    @RequestBody EmailDetails details
    public boolean sendMail() throws Exception {
        details.setRecipient("mz.tesfa@gmail.com");
        details.setMsgBody("I try to check emil");
        details.setSubject("I'm from cron job");
        emailService.sendSimpleMail(details);
        return true;

    }
    @Scheduled(cron = "0 0 2,10 * * *")
    public String sendToAuctions() throws Exception {
        LocalDate today = LocalDate.now();
        var auctions = auctionTypeRepository.findAll();
        var mortgageDetails = mortgageDetailRepository.findAll();
        var mail = mailNotificationTypeRepository.findById(1L);

        for (AuctionType auctionTypeList : auctions) {
            LocalDate date = LocalDate.parse(auctionTypeList.getDateAuctionWillBeConducted());
            LocalDate created_at = LocalDate.from(date.minusDays(mail.get().getNumberOfDays())
            );
            if (today.equals(created_at) && !auctionTypeList.getMortgageDetail().getMaintained_by().getEmail().isEmpty()) {
                details.setRecipient(auctionTypeList.getMortgageDetail().getMaintained_by().getEmail());
//                "mz.tesfa@gmail.com"
                details.setMsgBody("I try to check emil getDateAuctionWillBeConducted");
                details.setSubject("I'm from cron job");
                if (!emailService.sendSimpleMail(details).isEmpty()) {
                    details.setSent(true);
                    details.setMortgageDetail(auctionTypeList.getMortgageDetail());
                    emailRepository.save(details);
                }
                System.out.println(details);
            }
        }
        for (MortgageDetail mortgageDetail : mortgageDetails) {
            if (!mortgageDetail.getDateLegalNoticeServed().isEmpty()) {
                LocalDate date = LocalDate.parse(mortgageDetail.getDateLegalNoticeServed());
                LocalDate created_at = LocalDate.from(date.minusDays(mail.get().getNumberOfDays()));
                if (today.equals(created_at)&& !mortgageDetail.getMaintained_by().getEmail().isEmpty()) {
                    details.setRecipient(mortgageDetail.getMaintained_by().getEmail());
//                "mz.tesfa@gmail.com"
                    details.setMsgBody("I try to check emil getDateLegalNoticeServed");
                    details.setSubject("I'm from cron job");
                    if (!emailService.sendSimpleMail(details).isEmpty()) {
                        details.setSent(true);
                        details.setMortgageDetail(mortgageDetail);
                        emailRepository.save(details);
                    }
                    System.out.println(details);
                }
            }
        }


        return null;
    }

    //        @Scheduled(cron = "*/10 * * * * *")
    public String sendMailWithAttachment(@RequestBody EmailDetails details) {
        details.setRecipient("mz.tesfa@gmail.com");
        details.setMsgBody("I try to check emil");
        details.setSubject("I'm from cron job");
        emailService.sendMailWithAttachment(details);
        System.out.println(details);
        String status = emailService.sendMailWithAttachment(details);
        return status;
    }


    @Override
    public ResponseEntity<PagedModel<EmailDto>> getSentEmails(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                EmailDto.class, uriBuilder, response, pageable.getPageNumber(), emailService.getEmails(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<EmailDto>>(assembler.toModel(emailService.getEmails(pageable, token).map(emailMapper::toMEmailDto)), HttpStatus.OK);

    }
}
