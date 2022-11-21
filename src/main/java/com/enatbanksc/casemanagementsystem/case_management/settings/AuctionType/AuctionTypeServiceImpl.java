package com.enatbanksc.casemanagementsystem.case_management.settings.AuctionType;

import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management.config.EmployeeClient;
import com.enatbanksc.casemanagementsystem.case_management.dto.EmployeeMapper;
import com.enatbanksc.casemanagementsystem.case_management.exceptions.EntityNotFoundException;
import com.enatbanksc.casemanagementsystem.case_management.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import static com.enatbanksc.casemanagementsystem.case_management.utils.Util.getEmployeeID;
import static com.enatbanksc.casemanagementsystem.case_management.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class AuctionTypeServiceImpl implements AuctionTypeService{
    private final EmployeeMapper employeeMapper;
    private final EmployeeClient employeeClient;
    private final AuctionTypeRepository auctionTypeRepository;
    @Override
    public AuctionType createAuctionType(AuctionType auctionType, JwtAuthenticationToken token) throws IllegalAccessException {
        var employeeId = getEmployeeID(token);
        var maintainer = getEmployee(employeeId);
        auctionType.setAuctionTypeColor(Util.getRandomColor());
        auctionType.setMaintained_by(maintainer);
        return auctionTypeRepository.save(auctionType);
    }

    @Override
    public AuctionType getAuctionType(long auctionTypeId) {
        return auctionTypeRepository.findById(auctionTypeId).orElseThrow(()->new EntityNotFoundException(AuctionType.class, "Auction type with that is " + auctionTypeId + " was not found!"));
    }

    @Override
    public Page<AuctionType> getAuctionTypes(Pageable pageable, JwtAuthenticationToken token) {
        return auctionTypeRepository.findAll(pageable);
    }

    @Override
    public AuctionType updateAuctionType(long auctionTypeId, AuctionType auctionType, JwtAuthenticationToken token) throws IllegalAccessException {
        var at = getAuctionType(auctionTypeId);
        BeanUtils.copyProperties(auctionType, at, getNullPropertyNames(auctionType));
        return auctionTypeRepository.save(at);
    }

    @Override
    public void deleteExpense(long id, JwtAuthenticationToken token) {
        auctionTypeRepository.deleteById(id);
    }

    private Employee getEmployee(String employeeId) {
        return employeeMapper.employeeDtoToEmployee(employeeClient.getEmployeeById(employeeId));
    }
}
