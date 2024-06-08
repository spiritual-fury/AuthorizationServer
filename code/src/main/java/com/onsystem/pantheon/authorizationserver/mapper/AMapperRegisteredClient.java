package com.onsystem.pantheon.authorizationserver.mapper;

import com.onsystem.pantheon.authorizationserver.entities.Oauth2RegisteredClient;
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
                .build();
    }


}


