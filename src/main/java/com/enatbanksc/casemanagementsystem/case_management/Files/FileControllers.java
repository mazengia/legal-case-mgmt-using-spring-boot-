package com.enatbanksc.casemanagementsystem.case_management.Files;

import com.enatbanksc.casemanagementsystem.case_management._config.utils.PaginatedResultsRetrievedEvent;
import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileControllers implements FilesApi {
    private final FilesService filesService;
    private final FilesMapper filesMapper;
    private final ApplicationEventPublisher eventPublisher;
    private final FilesRepository filesRepository;

//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//      void getFilesById(@PathVariable("id") long id) {
//          filesRepository.getByFileId(id);
//    }


    @Override
    public FilesDto createFiles(long litigationId, String fileCategory, MultipartFile file, JwtAuthenticationToken token) throws IllegalAccessException {
        return filesMapper.toFilesDto(filesService.createFiles(litigationId, fileCategory, file, token));
    }

    @Override
    public void deleteByFileId(long id) {
        filesService.deleteFilesById(id);
    }


     @Override
     public FilesDto getFilesById(long id) {
         return filesMapper.toFilesDto(filesService.getFilesById(id));
     }
    @Override
    public ResponseEntity<PagedModel<FilesDto>> findAllByFileCategory(Pageable pageable,
                                                                      String fileCategory,
                                                                      long id, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                FilesDto.class, uriBuilder, response, pageable.getPageNumber(), filesService.findAllExecutionFilesByExecutionId(pageable,fileCategory,id, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<FilesDto>>(assembler.toModel(filesService.findAllExecutionFilesByExecutionId(pageable,fileCategory,id, token).map(filesMapper::toFilesDto)), HttpStatus.OK);
     }

    @Override
    public FilesDto updateFile(long id, MultipartFile file, FilesDto filesDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return filesMapper.toFilesDto(filesService.updateFiles(id, filesMapper.toFiles(filesDto), file, token));
    }

//    @Override
//    public void DeleteAppeal(String fileName) throws IllegalAccessException {
//        attachedFilesService.deleteFiles(fileName);
//    }

    @Override
    public ResponseEntity<PagedModel<FilesDto>> getAllFiles(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                FilesDto.class, uriBuilder, response, pageable.getPageNumber(), filesService.getAllFiles(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<FilesDto>>(assembler.toModel(filesService.getAllFiles(pageable, token).map(filesMapper::toFilesDto)), HttpStatus.OK);
    }
}
