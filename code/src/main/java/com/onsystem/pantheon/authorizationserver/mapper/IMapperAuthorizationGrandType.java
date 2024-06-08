package com.onsystem.pantheon.authorizationserver.mapper;

import com.onsystem.pantheon.authorizationserver.entities.Oauth2AuthorizationGrantType;
import org.mapstruct.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.util.Streamable;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {})
@ConditionalOnProperty(name = "auth.mock", havingValue = "false")
@Component
public interface IMapperAuthorizationGrandType {

    @Mapping(target = "value", source = "authorizationGrantTypeValue")
    AuthorizationGrantType toAuthorizationGrantType(Oauth2AuthorizationGrantType authorizationGrantType);

    @InheritConfiguration
    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    Collection<AuthorizationGrantType> toAuthorizationGrantTypes(Collection<Oauth2AuthorizationGrantType> authorizationGrantTypes);
}
