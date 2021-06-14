package com.manics.rest.mappers;

import com.manics.rest.model.core.Comment;
import com.manics.rest.rest.request.CommentRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment commentRequestToComment(CommentRequest commentRequest);

}
