package com.enatbanksc.casemanagementsystem.case_management.Comment;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toComment(CommentDto commentDto);
    CommentDto toCommentDto(Comment comment);
}
