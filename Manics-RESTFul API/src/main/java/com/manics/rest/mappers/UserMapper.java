package com.manics.rest.mappers;

import com.manics.rest.model.User;
import com.manics.rest.model.request.UserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userRequestToUser(UserRequest userRequest);

}
