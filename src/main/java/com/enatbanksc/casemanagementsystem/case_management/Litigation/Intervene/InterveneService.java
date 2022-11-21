package com.enatbanksc.casemanagementsystem.case_management.Litigation.Intervene;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InterveneService {
    List<Intervene> createIntervenes(long litigationId, List<Intervene> intervenes, JwtAuthenticationToken token);
    Intervene getIntervene(long id);
    Intervene updateIntervene(long id, Intervene intervene, JwtAuthenticationToken token) throws IllegalAccessException;
    Page<Intervene> getIntervenes(Pageable pageable, JwtAuthenticationToken token);
    void deleteIntervene(long id);

}
