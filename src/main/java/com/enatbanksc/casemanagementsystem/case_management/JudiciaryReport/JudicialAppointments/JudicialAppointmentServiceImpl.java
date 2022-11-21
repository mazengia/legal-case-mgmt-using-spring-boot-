//package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudicialAppointments;
//
//import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.Employee;
//import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudiciaryReportRepository;
//import com.enatbanksc.casemanagementsystem.case_management.config.EmployeeClient;
//import com.enatbanksc.casemanagementsystem.case_management.dto.EmployeeMapper;
//import com.enatbanksc.casemanagementsystem.case_management.exceptions.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.BeanUtils;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//import org.springframework.stereotype.Service;
//
//import static com.enatbanksc.casemanagementsystem.case_management.utils.Util.getEmployeeID;
//import static com.enatbanksc.casemanagementsystem.case_management.utils.Util.getNullPropertyNames;
//
//@Service
//@RequiredArgsConstructor
//public class JudicialAppointmentServiceImpl implements JudicialAppointmentService{
//    private final JudicialAppointmentRepository judicialAppointmentRepository;
//    private final EmployeeMapper employeeMapper;
//    private final EmployeeClient employeeClient;
//
//    private final JudiciaryReportRepository judiciaryReportRepository;
//    @Override
//    public JudicialAppointment createJudicialAppointment(long reportId, JudicialAppointment judicialAppointment, JwtAuthenticationToken token) throws IllegalAccessException {
//        var employeeId = getEmployeeID(token);
//        var maintainer = getEmployee(employeeId);
//        var report = judiciaryReportRepository.findById(reportId).get();
//        judicialAppointment.setMaintained_by(maintainer);
//        judicialAppointment.setJudiciaryReport(report);
//        return judicialAppointmentRepository.save(judicialAppointment);
//    }
//
//    @Override
//    public JudicialAppointment getJudicialAppointment(long id) {
//        return judicialAppointmentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(JudicialAppointment.class, "Judicial Appointment with an id: " + id + " was not found!"));
//    }
//
//    @Override
//    public Page<JudicialAppointment> getJudicialAppointments(Pageable pageable, JwtAuthenticationToken token) {
//        return judicialAppointmentRepository.findAll(pageable);
//    }
//
//    @Override
//    public JudicialAppointment updateJudicialAppointment(long id, JudicialAppointment judicialAppointment, JwtAuthenticationToken token) throws IllegalAccessException {
//        var ja = getJudicialAppointment(id);
//        var employeeId = getEmployeeID(token);
//        if(!ja.getMaintained_by().getEmployeeId().equals(employeeId)){
//            throw new IllegalAccessException("You are not allowed to update this object.");
//        }
//        BeanUtils.copyProperties(judicialAppointment, ja, getNullPropertyNames(judicialAppointment));
//        return judicialAppointmentRepository.save(ja);
//    }
//
//    @Override
//    public void deleteJudicialAppointment(long id, JwtAuthenticationToken token) {
//        judicialAppointmentRepository.deleteById(id);
//    }
//
//    private Employee getEmployee(String employeeId) {
//        return employeeMapper.employeeDtoToEmployee(employeeClient.getEmployeeById(employeeId));
//    }
//}
