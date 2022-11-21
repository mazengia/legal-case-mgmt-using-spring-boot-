package com.enatbanksc.casemanagementsystem.case_management.Comment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController implements CommentApi{
    @Override
    public CommentDto commentOnLitigation(long litigationId, CommentDto commentDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return null;
    }

    @Override
    public CommentDto commentOnJudiciaryReport(long reportId, CommentDto commentDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return null;
    }

    @Override
    public CommentDto getComment(long id) {
        return null;
    }

    @Override
    public CommentDto updateComment(long id, CommentDto commentDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return null;
    }

    @Override
    public ResponseEntity<PagedModel<CommentDto>> getCommentsByLitigation(long litigationId, Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        return null;
    }

    @Override
    public ResponseEntity<PagedModel<CommentDto>> getCommentsByJudiciaryReportId(long judiciaryReportId, Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        return null;
    }
}
