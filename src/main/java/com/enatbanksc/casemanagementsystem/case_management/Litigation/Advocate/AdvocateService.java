package com.enatbanksc.casemanagementsystem.case_management.Litigation.Advocate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public interface AdvocateService {
    Advocate createAdvocate(Advocate advocate, JwtAuthenticationToken token);
    Advocate updateAdvocate(long id, Advocate advocate, JwtAuthenticationToken token) throws IllegalAccessException;
    Advocate getAdvocate(long id);
    Page<Advocate> getAdvocates(Pageable pageable, JwtAuthenticationToken token);
    void deleteAdvocate(long id);
}
