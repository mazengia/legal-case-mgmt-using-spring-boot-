package com.enatbanksc.casemanagementsystem.case_management.Comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {

    Comment createComment(Comment comment, JwtAuthenticationToken token) throws IllegalAccessException;
    Comment getCommentById(long id);
    Page<Comment> getComments(Pageable pageable, JwtAuthenticationToken token);
    Comment updateComment(long id, Comment comment, JwtAuthenticationToken token) throws IllegalAccessException;
    void deleteComment(long id, JwtAuthenticationToken token);
    Page<Comment> getCommentByLitigationId(Pageable pageable, long id, JwtAuthenticationToken token);

}
