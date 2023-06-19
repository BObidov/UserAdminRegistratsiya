package com.company.userandadminauthorities.service.mapper;

import com.company.userandadminauthorities.dto.UserDto;
import com.company.userandadminauthorities.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class UserMapper {


    public abstract User toEntity(UserDto dto);

    public abstract UserDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateUserDto(UserDto dto, @MappingTarget User user);


}
