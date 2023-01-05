package com.enatbanksc.casemanagementsystem.case_management.Files;

import com.enatbanksc.casemanagementsystem.case_management.Appeal.AppealServiceImpl;
import com.enatbanksc.casemanagementsystem.case_management.Files.fileUploadToFolder.FileInfo;
import com.enatbanksc.casemanagementsystem.case_management.Files.fileUploadToFolder.FilesStorageService;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.LitigationServiceImpl;
import com.enatbanksc.casemanagementsystem.case_management.MortgageType.MortgageDetail.MortgageDetailServiceImpl;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.EmployeeClient;
import com.enatbanksc.casemanagementsystem.case_management._exceptions.EntityNotFoundException;
import com.enatbanksc.casemanagementsystem.case_management.dto.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

import static com.enatbanksc.casemanagementsystem.case_management._config.utils.Util.getEmployeeID;
import static com.enatbanksc.casemanagementsystem.case_management._config.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class AttachedFilesServiceImpl implements AttachedFilesService {
    private final AttachedFilesRepository attachedFilesRepository;
    private final FilesStorageService storageService;
    private final EmployeeMapper employeeMapper;
    private final LitigationServiceImpl litigationService;
    private final MortgageDetailServiceImpl mortgageDetailService;
    private final AppealServiceImpl appealService;
    private final EmployeeClient employeeClient;

    @Override
    public AttachedFiles createFiles(long id, String fileCategory, MultipartFile file, JwtAuthenticationToken token) throws IllegalAccessException {
        AttachedFiles attachedFiles = new AttachedFiles();
        if (attachedFilesRepository.existsAttachedFilesByFileName(file.getOriginalFilename())) {
            throw new IllegalAccessException("File is already exist ");
        } else {
            if (Objects.equals(fileCategory, "appeal")) {
                if (appealService.getAppealById(id) == null || file.isEmpty()) {
                    throw new IllegalAccessException("You are not allowed to upload files for Appeal ");
                } else {
                    attachedFiles.setFileName(file.getOriginalFilename());
                    attachedFiles.setAppeal(appealService.getAppealById(id));
                    attachedFilesRepository.save(attachedFiles);
                    try {
                        return storageService.save(file);
                    } catch (Exception e) {
                        throw new IllegalAccessException(e.getMessage().toString());
                    }

                }
            }
            if (Objects.equals(fileCategory, "foreclosure")) {
                if (mortgageDetailService.getMortgageDetail(id) == null || file.isEmpty()) {
                    throw new IllegalAccessException("You are not allowed to upload files for foreclosure ");
                } else {
                    attachedFiles.setFileName(file.getOriginalFilename());
                    attachedFiles.setMortgageDetail(mortgageDetailService.getMortgageDetail(id));

                    attachedFilesRepository.save(attachedFiles);
                    try {
                        return storageService.save(file);
                    } catch (Exception e) {
                        throw new IllegalAccessException(e.getMessage().toString());
                    }
                }
            }
            if (Objects.equals(fileCategory, "litigation")) {
                if (litigationService.getLitigation(id) == null || file.isEmpty()) {
                    throw new IllegalAccessException("You are not allowed to upload files for litigation ");
                } else {
                    attachedFiles.setFileName(file.getOriginalFilename());
                    attachedFiles.setLitigation(litigationService.getLitigation(id));
                    attachedFilesRepository.save(attachedFiles);
                    try {
                        return storageService.save(file);
                    } catch (Exception e) {
                        throw new IllegalAccessException(e.getMessage().toString());
                    }

                }
            }
        }
        return null;
    }

    @Override
    public AttachedFiles getFilesById(long id) {
        return attachedFilesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(AttachedFiles.class, "  Type with an id: " + id + " was not found!"));
    }

    @Override
    public ResponseEntity<List<FileInfo>> findAllByFileCategory(Pageable pageable, String fileCategory, long id, JwtAuthenticationToken token) {
        Page<AttachedFiles> returnValue = null;
        if (Objects.equals(fileCategory, "appeal")) {
            returnValue = attachedFilesRepository.findAllByAppealAppealIdOrderByCreatedAtDesc(pageable, id);
        }
        if (Objects.equals(fileCategory, "foreclosure")) {
            returnValue = attachedFilesRepository.findAllByMortgageDetailMortgageDetailIdOrderByCreatedAtDesc(pageable, id);
        }
        if (Objects.equals(fileCategory, "litigation")) {

            returnValue=  attachedFilesRepository.findAllByLitigationLitigationIdOrderByCreatedAtDesc(pageable, id);
        }
        return (ResponseEntity<List<FileInfo>>) returnValue;
    }

    @Override
    public Page<AttachedFiles> getAllFiles(Pageable pageable, JwtAuthenticationToken token) {
        return attachedFilesRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public AttachedFiles updateFiles(long id, AttachedFiles attachedFiles, MultipartFile file, JwtAuthenticationToken token) throws IllegalAccessException {
        var et = getFilesById(id);
        var employeeId = getEmployeeID(token);
        if (!et.getMaintained_by().getEmployeeId().equals(employeeId)) {
            throw new IllegalAccessException("You are not allowed to update this object.");
        }
        BeanUtils.copyProperties(file, et, getNullPropertyNames(file));
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
