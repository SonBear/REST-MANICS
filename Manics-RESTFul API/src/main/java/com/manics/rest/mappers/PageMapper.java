package com.manics.rest.mappers;

import com.manics.rest.model.core.Page;
import com.manics.rest.rest.request.PageRequest;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PageMapper {
    public abstract Page pageRequestToPage(PageRequest request);
}
