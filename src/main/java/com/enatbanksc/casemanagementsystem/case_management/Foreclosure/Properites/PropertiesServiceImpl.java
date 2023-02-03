package com.enatbanksc.casemanagementsystem.case_management.Foreclosure.Properites;

import com.enatbanksc.casemanagementsystem.case_management.Executions.JudgmentCreditorGetter.JudgmentCreditorGetter;
import com.enatbanksc.casemanagementsystem.case_management.Foreclosure.Foreclosure;
import com.enatbanksc.casemanagementsystem.case_management._exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.enatbanksc.casemanagementsystem.case_management._config.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class PropertiesServiceImpl implements PropertiesService {
    private final PropertiesRepository mortgageDetailRepository;
    @Override
    public Iterable<Properties> createProperties(List<Properties> properties, JwtAuthenticationToken token) throws IllegalAccessException {

        return    mortgageDetailRepository.saveAll(properties);
    }

    @Override
    public Properties getPropertiesById(long id) {
        return mortgageDetailRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Foreclosure.class, "Mortgage Detail with an id " + id + " was not found!"));
    }

    @Override
    public Page<Properties> getPropertiesByMortgageDetailId(Pageable pageable, long id) {
        return mortgageDetailRepository.findPropertiesByForeclosureForeclosureIdAndDeletedIsFalseOrderByIdDesc(pageable, id);

    }

    @Override
    public Page<Properties> getAllProperties(Pageable pageable) {
        return mortgageDetailRepository.findAllByDeletedIsFalseOrderByIdDesc(pageable);
    }

    @Override
    public List<Properties> updateProperties(long id, List<Properties> properties, JwtAuthenticationToken token) throws IllegalAccessException {
        for (Properties properties1 : properties) {
            var JudgmentCreditorGetterId = properties1.getId();
            var a = getPropertiesById( JudgmentCreditorGetterId);
            BeanUtils.copyProperties(properties1, a, getNullPropertyNames(properties1));
            mortgageDetailRepository.save(a);
        }
        return null;
    }

    @Override
    public void deletePropertiesById(long id) {

        mortgageDetailRepository.deleteById(id);
    }





}
