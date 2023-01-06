package com.enatbanksc.casemanagementsystem.case_management.Files;

import com.enatbanksc.casemanagementsystem.case_management._exceptions.EntityNotFoundException;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
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
import javax.validation.Valid;

public interface FilesApi {
    @PostMapping("/{fileCategory}/{litigationId}")
    @ResponseStatus(HttpStatus.CREATED)
    FilesDto createFiles(@PathVariable("litigationId") long litigationId, @PathVariable("fileCategory") String fileCategory, @RequestParam("file") MultipartFile file, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    FilesDto getFilesById(@PathVariable("id") long id);

    @GetMapping("/foreclosure/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<FilesDto>> findAllByFileCategory(@Parameter(description = "pagination object", schema = @Schema(implementation = Pageable.class))
                                                               @Valid Pageable pageable,
                                                               @PathVariable("id") long id,
                                                               PagedResourcesAssembler assembler,
                                                               JwtAuthenticationToken token,
                                                               UriComponentsBuilder uriBuilder,
                                                               final HttpServletResponse response);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    FilesDto updateFile(@PathVariable("id") long expenseId, @RequestParam("file") MultipartFile file, @RequestBody @Valid FilesDto filesDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteByFileId(@PathVariable("id") long id) throws EntityNotFoundException;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<FilesDto>> getAllFiles(@Parameter(description = "pagination object", schema = @Schema(implementation = Pageable.class))
                                                     @Valid Pageable pageable,
                                                     PagedResourcesAssembler assembler,
                                                     JwtAuthenticationToken token,
                                                     UriComponentsBuilder uriBuilder,
                                                     final HttpServletResponse response);
}
