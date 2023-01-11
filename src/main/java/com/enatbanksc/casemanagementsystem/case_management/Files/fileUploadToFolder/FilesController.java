package com.enatbanksc.casemanagementsystem.case_management.Files.fileUploadToFolder;

import com.enatbanksc.casemanagementsystem.case_management.Files.FilesServiceImpl;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.LitigationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/v1/view-files")
@RequiredArgsConstructor
public class FilesController {
    private final FilesStorageService storageService;
    private final LitigationServiceImpl litigationService;
    private final FilesServiceImpl deleteAttachedFilesByFileName;

    @PostMapping("/{litigationId}")
    @ResponseBody
    public Object uploadFile(@PathVariable("litigationId") long litigationId, @RequestParam("file") MultipartFile file) {
        var litigation = litigationService.getLitigationById(litigationId);
        String message = "";
        try {
            message = file.getOriginalFilename();
            return storageService.save(file);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping()
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(
                    FilesController.class,
                    "getFile",
                    path.getFileName().toString()).build().toString();
            return new FileInfo(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok().header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/_/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> readFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        ;

        return ResponseEntity.ok().header(
                HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @DeleteMapping("/{filename:.+}")
    @ResponseBody
    public void deleteFileByFileName(@PathVariable String filename) {
//        if(deleteAttachedFilesByFileName.deleteFiles(filename)!=null) {

          storageService.deleteByFileName(filename);
//        deleteAttachedFilesByFileName.deleteFiles(filename);
//    }

}

    @DeleteMapping()
    @ResponseBody
    public void deleteFile() {
        storageService.deleteAll();
    }
}
