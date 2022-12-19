package com.enatbanksc.casemanagementsystem.case_management.Comment;

import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudiciaryReportRepository;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.LitigationRepository;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.LitigationEmployee;
import com.enatbanksc.casemanagementsystem.case_management._config.EmployeeClient;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Util;
import com.enatbanksc.casemanagementsystem.case_management._exceptions.EntityNotFoundException;
import com.enatbanksc.casemanagementsystem.case_management.dto.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import static com.enatbanksc.casemanagementsystem.case_management._config.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final EmployeeMapper employeeMapper;
    private final EmployeeClient employeeClient;
    private final CommentRepository commentRepository;
    private final LitigationRepository litigationRepository;
    private final JudiciaryReportRepository judiciaryReportRepository;
    @Override
    public Comment commentOnLitigation(long litigationId, Comment comment
            , JwtAuthenticationToken token
    ) throws IllegalAccessException {
        var l = litigationRepository.findById(litigationId).get();
        var employeeId = Util.getEmployeeID(token);
        var sender = getLitigationEmployee(employeeId);
        comment.setLitigation(l);
        comment.setSender(sender);
        return commentRepository.save(comment);
    }

    @Override
    public Comment commentOnJudiciaryReport(long judiciaryId, Comment comment
            , JwtAuthenticationToken token
    ) throws IllegalAccessException {
        var j = judiciaryReportRepository.findById(judiciaryId).get();
        var employeeId = "null";
                Util.getEmployeeID(token);
        var sender = getLitigationEmployee(employeeId);
        comment.setJudiciaryReport(j);
        comment.setSender(sender);
        return commentRepository.save(comment);
    }

    @Override
    public Comment getComment(long id) {
        return commentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(Comment.class, "Comment with and Id " + id + " was not found"));
    }

    @Override
    public Page<Comment> getCommentsByLitigationId(long litigationId, Pageable pageable) {
        return commentRepository.findByLitigation_litigationId(litigationId, pageable);
    }

    @Override
    public Page<Comment> getCommentsByJudiciaryReportId(long judiciaryReportId, Pageable pageable) {
        return commentRepository.findByJudiciaryReport_reportId(judiciaryReportId, pageable);
    }

    @Override
    public Page<Comment> getComments(Pageable pageable
            , JwtAuthenticationToken token
    ) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public Comment updateComment(long id, Comment comment
            , JwtAuthenticationToken token
    ) throws IllegalAccessException {
        var c = getComment(id);
        BeanUtils.copyProperties(comment, c
                , getNullPropertyNames(comment)
        );
        return commentRepository.save(c);
    }

    @Override
    public void deleteComment(long id) {

    }

    private LitigationEmployee getLitigationEmployee(String employeeId) {
        return employeeClient.getEmployee(employeeId);
    }
}
