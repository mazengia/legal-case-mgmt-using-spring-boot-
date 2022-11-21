package com.enatbanksc.casemanagementsystem.case_management.settings.MailNotificationType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public interface MailNotificationTypeService {
    MailNotificationType createMailNotificationType(MailNotificationType mailNotificationType, JwtAuthenticationToken token) throws IllegalAccessException;
    MailNotificationType getMailNotificationType(long mailNotificationTypeId);
    Page<MailNotificationType> getMailNotificationTypes(Pageable pageable, JwtAuthenticationToken token);
    MailNotificationType updateMailNotificationType(long mailNotificationTypeId, MailNotificationType mailNotificationType, JwtAuthenticationToken token) throws IllegalAccessException;
    void deleteMailNotificationType(long mailNotificationTypeId);
}

