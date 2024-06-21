package com.onsystem.pantheon.authorizationserver.mapper;

import com.onsystem.pantheon.authorizationserver.entities.Oauth2RegisteredClientAuthorizationClientSetting;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@ConditionalOnProperty(name = "auth.mock", havingValue = "false")
@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IMapperClientSettings {


    default ClientSettings toClientSettings(Oauth2RegisteredClientAuthorizationClientSetting oauth2RegisteredClientAuthorizationClientSetting) {
        ClientSettings.Builder builder = ClientSettings.builder()
                .requireAuthorizationConsent(oauth2RegisteredClientAuthorizationClientSetting.getRequireAuthorizationConsent());
        if (StringUtils.hasText(oauth2RegisteredClientAuthorizationClientSetting.getJwtSetUrl())) {
            builder.jwkSetUrl(oauth2RegisteredClientAuthorizationClientSetting.getJwtSetUrl());
        }
        builder.tokenEndpointAuthenticationSigningAlgorithm(oauth2RegisteredClientAuthorizationClientSetting.getTokenEndpointAuthenticationSigningAlgorithm());
        return builder.build();
    }


    default Oauth2RegisteredClientAuthorizationClientSetting oauth2RegisteredClientAuthorizationClientSetting(ClientSettings clientSettings) {
        return Oauth2RegisteredClientAuthorizationClientSetting.builder()
                .requireAuthorizationConsent(clientSettings.isRequireAuthorizationConsent())
                .jwtSetUrl(clientSettings.getJwkSetUrl())
                .tokenEndpointAuthenticationSigningAlgorithm(SignatureAlgorithm.from(clientSettings.getTokenEndpointAuthenticationSigningAlgorithm().getName()))
                .build();
    }
}
