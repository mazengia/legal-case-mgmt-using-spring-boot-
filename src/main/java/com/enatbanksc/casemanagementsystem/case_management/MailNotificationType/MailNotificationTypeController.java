package com.enatbanksc.casemanagementsystem.case_management.MailNotificationType;

import com.enatbanksc.casemanagementsystem.case_management._config.utils.PaginatedResultsRetrievedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@Service
@RestController
@RequestMapping("/api/v1/mail-notification-types")
@RequiredArgsConstructor
public class MailNotificationTypeController implements MailNotificationTypeApi{
    private final MailNotificationTypeService mailNotificationTypeService;
    private final MailNotificationTypeMapper mailNotificationTypeMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public MailNotificationTypeDto createMailNotificationType(MailNotificationTypeDto mailNotificationTypeDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return mailNotificationTypeMapper.toMailNotificationTypeDto(mailNotificationTypeService.createMailNotificationType(mailNotificationTypeMapper.toMailNotificationType(mailNotificationTypeDto), token));
    }

    @Override
    public MailNotificationTypeDto getMailNotificationById(long mailNotificationTypeId) {
        return mailNotificationTypeMapper.toMailNotificationTypeDto(mailNotificationTypeService.getMailNotificationById(mailNotificationTypeId));
    }

    @Override
    public MailNotificationTypeDto putMailNotificationType(long mailNotificationTypeId, MailNotificationTypeDto mailNotificationTypeDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return mailNotificationTypeMapper.toMailNotificationTypeDto(mailNotificationTypeService.updateMailNotificationType(mailNotificationTypeId, mailNotificationTypeMapper.toMailNotificationType(mailNotificationTypeDto), token));
    }

    @Override
    public void deleteMailNotificationType(long mailNotificationTypeId) {
        mailNotificationTypeService.deleteMailNotificationType(mailNotificationTypeId);
    }

    @Override
    public ResponseEntity<PagedModel<MailNotificationTypeDto>> getMailNotificationTypes(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                MailNotificationTypeDto.class, uriBuilder, response, pageable.getPageNumber(), mailNotificationTypeService.getMailNotificationTypes(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<MailNotificationTypeDto>>(assembler.toModel(mailNotificationTypeService.getMailNotificationTypes(pageable, token).map(mailNotificationTypeMapper::toMailNotificationTypeDto)), HttpStatus.OK);
    }
}
