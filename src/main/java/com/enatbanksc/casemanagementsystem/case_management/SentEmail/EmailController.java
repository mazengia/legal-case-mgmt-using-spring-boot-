package com.enatbanksc.casemanagementsystem.case_management.SentEmail;


import com.enatbanksc.casemanagementsystem.case_management.ForeClosure.ForeClosureRepository;
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
    private final ForeClosureRepository foreClosureRepository;
    private final EmailRepository emailRepository;
    private final EmailMapper emailMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Scheduled(cron = "0 0 2,10 * * *")
    public String sendMail() throws Exception {
        System.out.println("meee");
        EmailDetails details = new EmailDetails();
        LocalDate today = LocalDate.now();
        var foreClosure = foreClosureRepository.findAll();
//        for (ForeClosure foreClosure1 : foreClosure) {
//            var auctionType = foreClosure1.getAuctionType();
//            for (AuctionType auctionTypeList : auctionType) {
//                LocalDate date = LocalDate.parse(auctionTypeList.getDateAuctionAnnounced());
//                LocalDate created_at = LocalDate.from(
//                        date.minusDays(
//                                foreClosure1
//                                        .getMortgageDetail()
//                                        .getMortgageType()
//                                        .getMailNotificationType()
//                                        .getNumberOfDays())
//                );
//                if (today.equals(created_at)) {
//                    details.setRecipient(foreClosure1.getMaintained_by().getEmail());
////                "mz.tesfa@gmail.com"
//                    details.setMsgBody("I try to check emil");
//                    details.setSubject("I'm from cron job");
//                    if (emailService.sendSimpleMail(details)) {
//                        details.setSent(true);
//                        details.setForeClosure(foreClosure1);
//                        emailRepository.save(details);
//                    }
//                    System.out.println(details);
//                }
//            }
//        }
        return null;
    }

    //    @Scheduled(cron = "*/10 * * * * *")
    public String sendMailWithAttachment(@RequestBody EmailDetails details) {
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
