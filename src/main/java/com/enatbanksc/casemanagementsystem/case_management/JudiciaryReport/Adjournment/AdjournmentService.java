package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.Adjournment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public interface AdjournmentService {
    Adjournment createAdjournment(
            Adjournment adjournment,
            JwtAuthenticationToken token
    );

    Adjournment getAdjournment(long id);

    Adjournment updateAdjournment(long id,
                                  Adjournment adjournment,
                                  JwtAuthenticationToken token
    ) throws IllegalAccessException;

    Page<Adjournment> getAdjournment(Pageable pageable,
                                     JwtAuthenticationToken token
    );

    void deleteAdjournment(long id);

}
