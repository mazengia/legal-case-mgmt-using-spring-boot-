package com.enatbanksc.casemanagementsystem.case_management.settings.MortgageType;

import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management.config.EmployeeClient;
import com.enatbanksc.casemanagementsystem.case_management.dto.EmployeeMapper;
import com.enatbanksc.casemanagementsystem.case_management.exceptions.EntityNotFoundException;
import com.enatbanksc.casemanagementsystem.case_management.settings.MailNotificationType.MailNotificationType;
import com.enatbanksc.casemanagementsystem.case_management.settings.MailNotificationType.MailNotificationTypeService;
import com.enatbanksc.casemanagementsystem.case_management.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import static com.enatbanksc.casemanagementsystem.case_management.utils.Util.getEmployeeID;
import static com.enatbanksc.casemanagementsystem.case_management.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class MortgageTypeServiceImpl implements MortgageTypeService{
    private final MortgageTypeRepository mortgageTypeRepository;
    private final MailNotificationTypeService mailNotificationTypeService;
    private final EmployeeMapper employeeMapper;
    private final EmployeeClient employeeClient;
    @Override
    public MortgageType createMortgageType(MortgageType mortgageType, JwtAuthenticationToken token) throws IllegalAccessException {
       var employeeId = getEmployeeID(token);
       var maintainer = getEmployee(employeeId);
       var nt = mailNotificationTypeService.getMailNotificationType(mortgageType.getMailNotificationType().getMailNotificationTypeId());
       mortgageType.setMailNotificationType(nt);
       mortgageType.setMaintained_by(maintainer);
       return mortgageTypeRepository.save(mortgageType);
    }

    @Override
    public MortgageType getMortgageType(long id) {
        return mortgageTypeRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(MortgageType.class, "Mortgage Type with an id " + id + " was not found!"));
    }

    @Override
    public Page<MortgageType> getMortgageTypes(Pageable pageable, JwtAuthenticationToken token) {
        return mortgageTypeRepository.findAll(pageable);
    }

    @Override
    public MortgageType updateMortgageType(long id, MortgageType mortgageType, JwtAuthenticationToken token) throws IllegalAccessException {
        var mt = getMortgageType(id);
        BeanUtils.copyProperties(mortgageType, mt, getNullPropertyNames(mortgageType));
        return mortgageTypeRepository.save(mt);
    }

    @Override
    public void deleteMortgageType(long id, JwtAuthenticationToken token) {

    }

    private Employee getEmployee(String employeeId) {
        return employeeMapper.employeeDtoToEmployee(employeeClient.getEmployeeById(employeeId));
    }
}
