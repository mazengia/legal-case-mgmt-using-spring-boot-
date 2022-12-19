package com.enatbanksc.casemanagementsystem.case_management.Litigation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public interface LitigationService {
    Litigation createLitigation(Litigation litigation, JwtAuthenticationToken token) throws IllegalAccessException;
    Litigation getLitigation(long id);
    Page<Litigation> getLitigations(Pageable pageable);
    Litigation updateLitigation(long id, Litigation litigation, JwtAuthenticationToken token) throws IllegalAccessException;

    Litigation addIntervene(long id, Litigation litigation, JwtAuthenticationToken token) throws  IllegalAccessException;
    void deleteLitigation(long id, JwtAuthenticationToken token);

}
