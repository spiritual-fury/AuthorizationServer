package com.onsystem.pantheon.authorizationserver.mapper;

import com.onsystem.pantheon.authorizationserver.entities.Oauth2AuthorizationMethod;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Stream;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {})
@ConditionalOnProperty(name = "auth.mock", havingValue = "false")
@Component
public interface IMapperAuthenticationMethod {


    default Collection<ClientAuthenticationMethod> toClientAuthenticationMethods(Collection<Oauth2AuthorizationMethod> oauth2RegisteredClientAuthorizationMethod) {
        return oauth2RegisteredClientAuthorizationMethod.stream()
                .map(c -> new ClientAuthenticationMethod(c.getAuthorizationMethodsValue()))
                .toList();
    }


}
