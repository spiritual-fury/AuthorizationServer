package com.onsystem.pantheon.authorizationserver.mapper;

import com.onsystem.pantheon.authorizationserver.entities.Oauth2RegisteredClient;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Component;


@ConditionalOnProperty(name = "auth.mock", havingValue = "false")
@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AMapperRegisteredClient {

    @Autowired
    private IMapperAuthenticationMethod iMapperAuthenticationMethod;
    @Autowired
    private IMapperAuthorizationGrandType iMapperAuthorizationGrandType;
    @Autowired
    private IMapperClientSettings iMapperClientSettings;
    @Autowired
    private IMapperScope iMapperScope;



    public RegisteredClient toRegisteredClient(Oauth2RegisteredClient oauth2RegisteredClient) {
        return RegisteredClient.withId(String.valueOf(oauth2RegisteredClient.getId()))
                .clientId(oauth2RegisteredClient.getUser().getLogin())
                .clientName(oauth2RegisteredClient.getClientName())
                .clientIdIssuedAt(oauth2RegisteredClient.getClientIdIssuedAt())
                .clientSecret(oauth2RegisteredClient.getUser().getPassword())
                .clientSecretExpiresAt(null) //TODO
                .clientSettings(iMapperClientSettings.toClientSettings(oauth2RegisteredClient.getClientSettings()))
                .clientAuthenticationMethods(l -> l.addAll(iMapperAuthenticationMethod.toClientAuthenticationMethods(oauth2RegisteredClient.getAuthorizationMethods())))
                .authorizationGrantTypes(l -> l.addAll(iMapperAuthorizationGrandType.toAuthorizationGrantTypes(oauth2RegisteredClient.getGrantTypes())))
                .scopes(l -> l.addAll(iMapperScope.toStr(oauth2RegisteredClient.getScopes())))
                .build();
    }

    public Oauth2RegisteredClient toOauth2RegisteredClient(RegisteredClient registeredClient) {
        return Oauth2RegisteredClient.builder()
                .clientName(registeredClient.getClientName())
                .clientIdIssuedAt(registeredClient.getClientIdIssuedAt())
                .clientSettings(iMapperClientSettings.oauth2RegisteredClientAuthorizationClientSetting(registeredClient.getClientSettings()))
                .authorizationMethods(iMapperAuthenticationMethod.toOauth2AuthorizationMethods(registeredClient.getClientAuthenticationMethods()))
                .grantTypes(iMapperAuthorizationGrandType.toOauth2AuthorizationGrantType(registeredClient.getAuthorizationGrantTypes()))
                .scopes(iMapperScope.oauth2Scopes(registeredClient.getScopes()))
                .build();
    }


}


