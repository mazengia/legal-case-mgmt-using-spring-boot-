package com.enatbanksc.casemanagementsystem.case_management.Comment;

import com.enatbanksc.casemanagementsystem.case_management._config.utils.PaginatedResultsRetrievedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController implements CommentApi{

        private final CommentService commentService;
        private final CommentMapper commentMapper;
        private final ApplicationEventPublisher eventPublisher;

    @Override
    public ResponseEntity<PagedModel<CommentDto>>
    getCommentByLitigationId(Pageable pageable, long id, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(CommentDto.class, uriBuilder, response, pageable.getPageNumber(), commentService.getCommentByLitigationId(pageable,id, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<CommentDto>>(assembler.toModel(commentService.getCommentByLitigationId(pageable,id, token).map(commentMapper::toCommentDto)), HttpStatus.OK);


    }



    @Override
        public CommentDto createComment(CommentDto commentDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return commentMapper.toCommentDto(commentService.createComment(commentMapper.toComment(commentDto), token));
    }

        @Override
        public CommentDto getCommentById(long id) {
        return commentMapper.toCommentDto(commentService.getCommentById(id));
    }

    @Override
    public void deleteCommentById(long id) {
        commentService.deleteComment(id);
    }

    @Override
        public CommentDto updateComment(long id, CommentDto commentDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return commentMapper.toCommentDto(commentService.updateComment(id, commentMapper.toComment(commentDto), token));
    }

        @Override
        public ResponseEntity<PagedModel<CommentDto>> getComment(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                CommentDto.class, uriBuilder, response, pageable.getPageNumber(), commentService.getComments(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<CommentDto>>(assembler.toModel(commentService.getComments(pageable, token).map(commentMapper::toCommentDto)), HttpStatus.OK);
    }


}
