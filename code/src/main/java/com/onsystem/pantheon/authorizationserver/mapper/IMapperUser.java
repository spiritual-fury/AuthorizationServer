package com.onsystem.pantheon.authorizationserver.mapper;

import com.onsystem.pantheon.authorizationserver.dto.UserDTO;
import com.onsystem.pantheon.authorizationserver.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@ConditionalOnProperty(name = "auth.mock", havingValue = "false")
@Component
public interface IMapperUser {


    @Mapping(target = "enabled", defaultValue = "true")
    @Mapping(target = "credentialsNonExpired", defaultValue = "false")
    @Mapping(target = "accountNonLocked", defaultValue = "false")
    @Mapping(target = "accountNonExpired", defaultValue = "false")
    UserDTO toUserDTO(User user);

}
