package com.enatbanksc.casemanagementsystem.case_management.ForeClosure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public interface ForeClosureService {
    ForeClosure createForeClosure(
            ForeClosure foreClosure
            , JwtAuthenticationToken token
    );

    ForeClosure getForeClosure(long id);

    ForeClosure updateForeClosure(long id, ForeClosure foreClosure
            , JwtAuthenticationToken token
    ) throws IllegalAccessException;

    Page<ForeClosure> getForeClosure(Pageable pageable
            , JwtAuthenticationToken token
    );

    void deleteForeClosure(long id);

}
