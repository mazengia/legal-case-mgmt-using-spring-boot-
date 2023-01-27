package com.enatbanksc.casemanagementsystem.case_management.MailNotificationType;

import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.EmployeeClient;
import com.enatbanksc.casemanagementsystem.case_management._exceptions.EntityNotFoundException;
import com.enatbanksc.casemanagementsystem.case_management.dto.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import static com.enatbanksc.casemanagementsystem.case_management._config.utils.Util.*;

@Service
@RequiredArgsConstructor
public class MailNotificationTypeServiceImpl implements MailNotificationTypeService{
    private final MailNotificationTypeRepository mailNotificationTypeRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeClient employeeClient;

    @Override
    public MailNotificationType createMailNotificationType(MailNotificationType mailNotificationType, JwtAuthenticationToken token) throws IllegalAccessException {
        var employeeId = getEmployeeID(token);
        var maintainer = getEmployee(employeeId);
        if(mailNotificationTypeRepository.existsByMailTypeName(mailNotificationType.getMailTypeName())){
            throw new IllegalAccessException("Expense with name " + mailNotificationType.getMailTypeName() + " already exists!");
        }
        mailNotificationType.setMaintained_by(maintainer);
        mailNotificationType.setColor(getRandomColor());
        return mailNotificationTypeRepository.save(mailNotificationType);
    }

    @Override
    public MailNotificationType getMailNotificationType(long mailNotificationTypeId) {
        return mailNotificationTypeRepository.findById(mailNotificationTypeId).orElseThrow(()-> new EntityNotFoundException(MailNotificationType.class, "Mail Notification Type with id " + mailNotificationTypeId + " was not found!"));
    }

    @Override
    public Page<MailNotificationType> getMailNotificationTypes(Pageable pageable, JwtAuthenticationToken token) {
        return mailNotificationTypeRepository.findAllByDeletedIsFalseOrderByCreatedAtDesc(pageable);
    }

    @Override
    public MailNotificationType updateMailNotificationType(long mailNotificationTypeId, MailNotificationType mailNotificationType, JwtAuthenticationToken token) throws IllegalAccessException {
        var employeeId = getEmployeeID(token);
        var mnt = getMailNotificationType(mailNotificationTypeId);
//        if(!mailNotificationType.getMaintained_by().getEmployeeId().equals(employeeId)){
//            throw  new IllegalAccessException("You are not allowed to update this Mail Notification Type");
//        }
        BeanUtils.copyProperties(mailNotificationType, mnt, getNullPropertyNames(mailNotificationType));
        return mailNotificationTypeRepository.save(mnt);
    }

    @Override
    public void deleteMailNotificationType(long mailNotificationTypeId) {
        mailNotificationTypeRepository.deleteById(mailNotificationTypeId);
    }

    private Employee getEmployee(String employeeId) {
        return employeeMapper.employeeDtoToEmployee(employeeClient.getEmployeeById(employeeId));
    }
}
