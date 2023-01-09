package com.enatbanksc.casemanagementsystem.case_management.Files;

import com.enatbanksc.casemanagementsystem.case_management.Appeal.AppealServiceImpl;
import com.enatbanksc.casemanagementsystem.case_management.Executions.ExecutionsService;
import com.enatbanksc.casemanagementsystem.case_management.Files.fileUploadToFolder.FilesStorageService;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.LitigationServiceImpl;
import com.enatbanksc.casemanagementsystem.case_management.MortgageDetail.MortgageDetailServiceImpl;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.EmployeeClient;
import com.enatbanksc.casemanagementsystem.case_management.dto.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static com.enatbanksc.casemanagementsystem.case_management._config.utils.Util.getEmployeeID;
import static com.enatbanksc.casemanagementsystem.case_management._config.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class FilesServiceImpl implements FilesService {
    private final FilesRepository filesRepository;

    private final Path root = Paths.get("uploads");
    private final FilesStorageService storageService;
    private final EmployeeMapper employeeMapper;
    private final LitigationServiceImpl litigationService;
    private final ExecutionsService executionsService;
    private final MortgageDetailServiceImpl mortgageDetailService;
    private final AppealServiceImpl appealService;
    private final EmployeeClient employeeClient;

    @Override
    public Files createFiles(long id, String fileCategory, MultipartFile file, JwtAuthenticationToken token) throws IllegalAccessException {
        Files files = new Files();
        if (filesRepository.existsAttachedFilesByFileName(file.getOriginalFilename())) {
            throw new IllegalAccessException("File is already exist ");
        } else {
            if (Objects.equals(fileCategory, "appeal")) {
                if (appealService.getAppealById(id) == null || file.isEmpty()) {
                    throw new IllegalAccessException("You are not allowed to upload files for Appeal ");
                } else {
                    files.setFileName(file.getOriginalFilename());
                    files.setAppeal(appealService.getAppealById(id));
                    filesRepository.save(files);
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
                    files.setFileName(file.getOriginalFilename());
                    files.setMortgageDetail(mortgageDetailService.getMortgageDetail(id));

                    filesRepository.save(files);
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
                    files.setFileName(file.getOriginalFilename());
                    files.setLitigation(litigationService.getLitigation(id));
                    filesRepository.save(files);
                    try {
                        return storageService.save(file);
                    } catch (Exception e) {
                        throw new IllegalAccessException(e.getMessage().toString());
                    }

                }
            }
            if (Objects.equals(fileCategory, "executions")) {
                if (executionsService.getExecutionsById(id) == null || file.isEmpty()) {
                    throw new IllegalAccessException("You are not allowed to upload files for litigation ");
                } else {
                    files.setFileName(file.getOriginalFilename());
                    files.setExecutions(executionsService.getExecutionsById(id));
                    filesRepository.save(files);
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
    public Files getFilesById(long id) {
        return filesRepository.getByFileId(id);
    }

    @Override
    public Page<Files> findAllExecutionFilesByExecutionId(Pageable pageable, String fileCategory, long id, JwtAuthenticationToken token) {
        Page<Files> returnValue = null;
        if (Objects.equals(fileCategory, "appeal")) {
            return filesRepository.findAllByAppealAppealIdAndAppealAppealIdNotNullOrderByCreatedAtDesc(pageable, id);
        }
        if (Objects.equals(fileCategory, "foreclosure")) {
            return filesRepository.findAllByMortgageDetailMortgageDetailIdOrderByCreatedAtDesc(pageable, id);
        }
        if (Objects.equals(fileCategory, "litigation")) {

            return filesRepository.findAllByLitigationLitigationIdAndLitigationLitigationIdNotNullOrderByCreatedAtDesc(pageable, id);
        }
        if (Objects.equals(fileCategory, "executions")) {

            return filesRepository.findAllByExecutionsExecutionsIdAndExecutionsExecutionsIdNotNullOrderByCreatedAtDesc(pageable, id);
        }
        return returnValue;
    }

    @Override
    public Page<Files> getAllFiles(Pageable pageable, JwtAuthenticationToken token) {
        return filesRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public Files updateFiles(long id, Files files, MultipartFile file, JwtAuthenticationToken token) throws IllegalAccessException {
        var et = getFilesById(id);
        var employeeId = getEmployeeID(token);
        if (!et.getMaintained_by().getEmployeeId().equals(employeeId)) {
            throw new IllegalAccessException("You are not allowed to update this object.");
        }
        BeanUtils.copyProperties(file, et, getNullPropertyNames(file));
        return filesRepository.save(et);
//        return null;
    }

    @Override
    public void deleteFiles(String fileName) {
        filesRepository.deleteByFileName(fileName);
//        return null;
    }

    @Override
    public void deleteFilesById(long id) {
        System.out.println("id");
        System.out.println(id);
        System.out.println("id");
        filesRepository.deleteByFileId(id);
//        Files files = getFilesById(id);
//        String fileName = files.getFileName();
//        try {
//            if (FileSystemUtils.deleteRecursively(root.resolve(fileName))) {
//                filesRepository.deleteByFileId(id);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }

    private Employee getEmployee(String employeeId) {
        return employeeMapper.employeeDtoToEmployee(employeeClient.getEmployeeById(employeeId));
    }
}
