package com.enatbanksc.casemanagementsystem.case_management.MailNotificationType;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Service
public interface MailNotificationTypeApi {
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    MailNotificationTypeDto createMailNotificationType(@RequestBody @Valid MailNotificationTypeDto mailNotificationTypeDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping("/{mailNotificationTypeId}")
    @ResponseStatus(HttpStatus.OK)
    MailNotificationTypeDto getMailNotificationType(@PathVariable("mailNotificationTypeId") long mailNotificationTypeId);


    @PutMapping("/{mailNotificationTypeId}")
    @ResponseStatus(HttpStatus.OK)
    MailNotificationTypeDto putMailNotificationType(@PathVariable("mailNotificationTypeId") long mailNotificationTypeId, @RequestBody @Valid MailNotificationTypeDto mailNotificationTypeDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @DeleteMapping("/{mailNotificationTypeId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteMailNotificationType(@PathVariable("mailNotificationTypeId") long mailNotificationTypeId);

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<MailNotificationTypeDto>> getMailNotificationTypes(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                        @Valid Pageable pageable,
                                                                                 PagedResourcesAssembler assembler,
                                                                                 JwtAuthenticationToken token,
                                                                                 UriComponentsBuilder uriBuilder,
                                                                                 final HttpServletResponse response);
}

