package com.enatbanksc.casemanagementsystem.case_management.Comment;

import com.enatbanksc.casemanagementsystem.case_management.CaseType.CaseType;
import com.enatbanksc.casemanagementsystem.case_management._exceptions.EntityNotFoundException;
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
    private final CommentRepository commentRepository;
    @Override
    public Comment createComment(Comment comment, JwtAuthenticationToken token) throws IllegalAccessException {

        return commentRepository.save(comment);
    }

    @Override
    public Comment getCommentById(long id) {
        return commentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(CaseType.class, "Comment with an Id " + id + " was not found!"));
    }

    @Override
    public Page<Comment> getComments(Pageable pageable, JwtAuthenticationToken token) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public Comment updateComment(long id, Comment comment, JwtAuthenticationToken token) throws IllegalAccessException {
        var ct = getCommentById(id);
        BeanUtils.copyProperties(comment, ct, getNullPropertyNames(comment));
        return commentRepository.save(ct);
    }
    @Override
    public Page<Comment> getCommentByLitigationId(Pageable pageable, long id, JwtAuthenticationToken token) {
        return  commentRepository.findCommentByLitigation_LitigationId(pageable,id);
    }
    @Override
    public void deleteComment(long id, JwtAuthenticationToken token) {
        commentRepository.deleteById(id);
    }
}
