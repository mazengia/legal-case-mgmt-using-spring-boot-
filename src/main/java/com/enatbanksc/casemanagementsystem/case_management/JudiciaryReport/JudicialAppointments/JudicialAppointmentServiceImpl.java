package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudicialAppointments;

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

import static com.enatbanksc.casemanagementsystem.case_management._config.utils.Util.getEmployeeID;
import static com.enatbanksc.casemanagementsystem.case_management._config.utils.Util.getNullPropertyNames;


@Service
@RequiredArgsConstructor
public class JudicialAppointmentServiceImpl implements JudicialAppointmentService {
    private final JudicialAppointmentRepository judicialAppointmentRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeClient employeeClient;

    @Override
    public JudicialAppointment createJudicialAppointment(JudicialAppointment judicialAppointment, JwtAuthenticationToken token) throws IllegalAccessException {
        var employeeId = getEmployeeID(token);
        var maintainer = getEmployee(employeeId);
        judicialAppointment.setMaintained_by(maintainer);
        return judicialAppointmentRepository.save(judicialAppointment);
    }



    @Override
    public JudicialAppointment getJudicialAppointment(long id) {
        return judicialAppointmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(JudicialAppointment.class, "Judicial Appointment with an id: " + id + " was not found!"));
    }

    @Override
    public Page<JudicialAppointment> getJudicialAppointments(Pageable pageable, JwtAuthenticationToken token) {
        return judicialAppointmentRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public Page<JudicialAppointment> getJudiciaryReportByLitigationId(Pageable pageable, long id, JwtAuthenticationToken token) {
        return  judicialAppointmentRepository.findJudicialAppointmentByLitigationLitigationIdOrderByCreatedAtDesc(pageable,id);
    }

    @Override
    public JudicialAppointment updateJudicialAppointment(long id, JudicialAppointment judicialAppointment, JwtAuthenticationToken token) throws IllegalAccessException {
        var ja = getJudicialAppointment(id);
        var employeeId = getEmployeeID(token);
        if (!ja.getMaintained_by().getEmployeeId().equals(employeeId)) {
            throw new IllegalAccessException("You are not allowed to update this object.");
        }
        BeanUtils.copyProperties(judicialAppointment, ja, getNullPropertyNames(judicialAppointment));
        return judicialAppointmentRepository.save(ja);
    }

    @Override
    public void deleteJudicialAppointment(long id, JwtAuthenticationToken token) {
        judicialAppointmentRepository.deleteById(id);
    }

    private Employee getEmployee(String employeeId) {
        return employeeMapper.employeeDtoToEmployee(employeeClient.getEmployeeById(employeeId));
    }









    @Override
    public Page<JudicialAppointment> getJudiciaryReportByExecutionId(Pageable pageable, long id, JwtAuthenticationToken token) {
        return  judicialAppointmentRepository.findJudicialAppointmentByExecutionsExecutionsIdOrderByCreatedAtDesc(pageable,id);
    }
    @Override
    public Page<JudicialAppointment> getJudiciaryReportByExecutionsAttorneyHandlingTheCase(Pageable pageable, String attorney, JwtAuthenticationToken token) {
        return  judicialAppointmentRepository.findJudicialAppointmentByExecutionsAttorneyHandlingTheCaseOrderByCreatedAtDesc(pageable,attorney);
    }
    @Override
    public Page<JudicialAppointment> getJudiciaryReportByLitigationAttorneyHandlingTheCase(Pageable pageable, String attorney, JwtAuthenticationToken token) {
        return  judicialAppointmentRepository.findJudicialAppointmentByLitigationAttorneyHandlingTheCaseOrderByCreatedAtDesc(pageable,attorney);
    }

    @Override
    public Page<JudicialAppointment> getExpensesDetailByExecution(Pageable pageable, JwtAuthenticationToken token) {
        return judicialAppointmentRepository.findAllByExecutionsExecutionsIdNotNullOrderByCreatedAtDesc(pageable);
    }
    @Override
    public Page<JudicialAppointment> getExpensesDetailByLitigation(Pageable pageable, JwtAuthenticationToken token) {
        return judicialAppointmentRepository.findAllByLitigationLitigationIdNotNullOrderByCreatedAtDesc(pageable);
    }
}
