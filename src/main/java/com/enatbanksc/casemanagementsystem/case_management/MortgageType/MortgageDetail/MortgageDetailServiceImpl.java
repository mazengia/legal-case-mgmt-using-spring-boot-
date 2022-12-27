package com.enatbanksc.casemanagementsystem.case_management.MortgageType.MortgageDetail;

import com.enatbanksc.casemanagementsystem.case_management._exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import static com.enatbanksc.casemanagementsystem.case_management._config.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class MortgageDetailServiceImpl implements MortgageDetailService {
    private final MortgageDetailRepository mortgageDetailRepository;

    @Override
    public MortgageDetail createMortgageDetail(MortgageDetail mortgageDetail, JwtAuthenticationToken token) throws IllegalAccessException {
        System.out.println(mortgageDetail);
        return mortgageDetailRepository.save(mortgageDetail);
    }

    @Override
    public MortgageDetail getMortgageDetail(long id) {
        return mortgageDetailRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MortgageDetail.class, "Mortgage Detail with an id " + id + " was not found!"));
    }

    @Override
    public Page<MortgageDetail> getMortgageDetail(Pageable pageable, JwtAuthenticationToken token) {
        return mortgageDetailRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public MortgageDetail updateMortgageDetail(long id, MortgageDetail mortgageDetail, JwtAuthenticationToken token) throws IllegalAccessException {
        var mt = getMortgageDetail(id);
        BeanUtils.copyProperties(mortgageDetail, mt, getNullPropertyNames(mortgageDetail));
        return mortgageDetailRepository.save(mt);
    }

    @Override
    public void deleteMortgageDetail(long id, JwtAuthenticationToken token) {

    }

}
