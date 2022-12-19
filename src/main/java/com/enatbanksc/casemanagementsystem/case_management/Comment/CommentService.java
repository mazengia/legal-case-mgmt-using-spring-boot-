package com.enatbanksc.casemanagementsystem.case_management.Comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    Comment commentOnLitigation(long litigationId, Comment comment
            , JwtAuthenticationToken token
    ) throws IllegalAccessException;
    Comment commentOnJudiciaryReport(long judiciaryId, Comment comment
            , JwtAuthenticationToken token
    ) throws IllegalAccessException;
    Comment getComment(long id);
    Page<Comment> getCommentsByLitigationId(long litigationId, Pageable pageable);
    Page<Comment> getCommentsByJudiciaryReportId(long judiciaryReportId, Pageable pageable);
    Page<Comment> getComments(Pageable pageable
            , JwtAuthenticationToken token
    );
    Comment updateComment(long id, Comment comment
            , JwtAuthenticationToken token
    ) throws IllegalAccessException;
    void deleteComment(long id);
}
