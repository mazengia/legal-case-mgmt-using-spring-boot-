package com.enatbanksc.casemanagementsystem.case_management.Files;

import com.enatbanksc.casemanagementsystem.case_management.Files.fileUploadToFolder.FilesStorageService;
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

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.enatbanksc.casemanagementsystem.case_management._config.utils.Util.getEmployeeID;
import static com.enatbanksc.casemanagementsystem.case_management._config.utils.Util.getNullPropertyNames;
@Service
@RequiredArgsConstructor
public class AttachedFilesServiceImpl implements AttachedFilesService {
    private final AttachedFilesRepository attachedFilesRepository;
    private final Path root = Paths.get("uploads");
    private final FilesStorageService storageService;
    private final EmployeeMapper employeeMapper;
    private final EmployeeClient employeeClient;
    @Override
    public AttachedFiles createFiles(AttachedFiles attachedFiles, JwtAuthenticationToken token) throws IllegalAccessException {

        var employeeId = getEmployeeID(token);
        var maintainer = getEmployee(employeeId);
        attachedFiles.setMaintained_by(maintainer);
        String master = attachedFiles.getFileName();
        if (master != null) {
            String target = "C:\\fakepath\\";
            String replacement = "";
            attachedFiles.setFileName(master.replace(target, replacement));
            return   attachedFilesRepository.save(attachedFiles);
        }else {

            return null;
        }
    }

    @Override
    public AttachedFiles getFilesById(long id) {
        return attachedFilesRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(AttachedFiles.class, "  Type with an id: " + id + " was not found!"));
    }

    @Override
    public Page<AttachedFiles> findAllByLitigationAttorneyHandlingTheCaseOrderByCreatedAtDesc(Pageable pageable, String attorneyHandlingTheCase, JwtAuthenticationToken token) {
        return attachedFilesRepository.findAllByLitigationAttorneyHandlingTheCaseOrderByCreatedAtDesc(pageable,attorneyHandlingTheCase);

    }

    @Override
    public Page<AttachedFiles> getAllFiles(Pageable pageable, JwtAuthenticationToken token) {
        return attachedFilesRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public AttachedFiles updateFiles(long id, AttachedFiles attachedFilesType, JwtAuthenticationToken token) throws IllegalAccessException {
        var et = getFilesById(id);
        var employeeId = getEmployeeID(token);
        if(!et.getMaintained_by().getEmployeeId().equals(employeeId)){
            throw new IllegalAccessException("You are not allowed to update this object.");
        }
        BeanUtils.copyProperties(attachedFilesType, et, getNullPropertyNames(attachedFilesType));
        return attachedFilesRepository.save(et);
    }

    @Override
    public void deleteFiles(long id, JwtAuthenticationToken token) {
        attachedFilesRepository.deleteById(id);
    }

    private Employee getEmployee(String employeeId) {
        return employeeMapper.employeeDtoToEmployee(employeeClient.getEmployeeById(employeeId));
    }
}
