package com.enatbanksc.casemanagementsystem.case_management.ForeClosure;

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
public class ForeClosureServiceImpl implements ForeClosureService {

    private final ForeClosureRepository foreClosureRepository;


    @Override
    public ForeClosure createForeClosure(ForeClosure foreClosure
            , JwtAuthenticationToken token
    ) {

        return   foreClosureRepository.save(foreClosure);
    }

    @Override
    public ForeClosure getForeClosure(long id) {
        return foreClosureRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(ForeClosure.class, "ForeClosure with that id " + id + " was not found!"));
    }

    @Override
    public ForeClosure updateForeClosure(long id, ForeClosure foreClosure
            , JwtAuthenticationToken token
    ) throws IllegalAccessException {
        var i = getForeClosure(id);
        BeanUtils.copyProperties(foreClosure, i, getNullPropertyNames(foreClosure));
        return foreClosureRepository.save(i);
    }

    @Override
    public Page<ForeClosure> getForeClosure(Pageable pageable
            , JwtAuthenticationToken token
    ) {
        return foreClosureRepository.findAll(pageable);
    }

    @Override
    public void deleteForeClosure(long id) {
        foreClosureRepository.deleteById(id);
    }

}

