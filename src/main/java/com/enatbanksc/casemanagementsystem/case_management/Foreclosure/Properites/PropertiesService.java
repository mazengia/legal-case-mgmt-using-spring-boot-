package com.enatbanksc.casemanagementsystem.case_management.Foreclosure.Properites;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PropertiesService {
    Iterable<Properties> createProperties(List<Properties> properties, JwtAuthenticationToken token) throws IllegalAccessException;

    Properties getPropertiesById(long id);

    Page<Properties> getPropertiesByMortgageDetailId(Pageable pageable,   long id);
    Page<Properties> getAllProperties(Pageable pageable);

    List<Properties> updateProperties(long id, List<Properties> properties, JwtAuthenticationToken token) throws IllegalAccessException;

    void deletePropertiesById(long id);

  }
