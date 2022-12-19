package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport;

import com.enatbanksc.casemanagementsystem.case_management.Expense.ExpenseDetails.ExpenseDetailRepository;
import com.enatbanksc.casemanagementsystem.case_management.Intervene.Intervene;
import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.Adjournment.AdjournmentRepository;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.LitigationRepository;
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
public class JudiciaryReportServiceImpl implements JudiciaryReportService{
    private final JudiciaryReportRepository judiciaryReportRepository;
    private final EmployeeClient employeeClient;
    private final EmployeeMapper employeeMapper;

    private final LitigationRepository litigationRepository;
    private final AdjournmentRepository adjournmentRepository;

    private final ExpenseDetailRepository expenseDetailRepository;
    @Override
    public JudiciaryReport createJudiciaryReport( JudiciaryReport judiciaryReport, JwtAuthenticationToken token) {
//        var l = litigationRepository.findById(litigationId).get();
        var employeeId = getEmployeeID(token);
        var maintainer = getEmployee(employeeId);
        judiciaryReport.setMaintained_by(maintainer);
//        judiciaryReport.setLitigation(l);
        var jr = judiciaryReportRepository.save(judiciaryReport);
        return jr;
    }

    @Override
    public JudiciaryReport getJudiciaryReport(long id) {
        return judiciaryReportRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(Intervene.class, "Judiciary Report with that id " + id + " was not found!"));
    }

    @Override
    public JudiciaryReport updateJudiciaryReport(long id, JudiciaryReport judiciaryReport, JwtAuthenticationToken token) throws IllegalAccessException {
        var jr = getJudiciaryReport(id);
        var employeeId = getEmployeeID(token);
        if(judiciaryReport.getMaintained_by().getEmployeeId().equals(employeeId)){
            throw new IllegalAccessException("You are not allowed to update this Judiciary Report");
        }
        BeanUtils.copyProperties(judiciaryReport, jr, getNullPropertyNames(judiciaryReport));
        return judiciaryReportRepository.save(jr);
    }

    @Override
    public Page<JudiciaryReport> getJudiciaryReports(Pageable pageable, JwtAuthenticationToken token) {
        return judiciaryReportRepository.findAll(pageable);
    }

    @Override
    public void deleteJudiciaryReport(long id) {
        judiciaryReportRepository.deleteById(id);
    }

    private Employee getEmployee(String employeeId) {
        return employeeMapper.employeeDtoToEmployee(employeeClient.getEmployeeById(employeeId));
    }
}
