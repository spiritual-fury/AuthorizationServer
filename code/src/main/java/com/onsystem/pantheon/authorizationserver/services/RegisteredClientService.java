package com.onsystem.pantheon.authorizationserver.services;

import com.onsystem.pantheon.authorizationserver.mapper.AMapperRegisteredClient;
import com.onsystem.pantheon.authorizationserver.repositories.Oauth2RegisteredRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

public class RegisteredClientService implements RegisteredClientRepository {

    private Oauth2RegisteredRepository oauth2RegisteredRepository;

    private AMapperRegisteredClient aMapperRegisteredClient;

    public RegisteredClientService(Oauth2RegisteredRepository oauth2RegisteredRepository, AMapperRegisteredClient aMapperRegisteredClient) {
        this.oauth2RegisteredRepository = oauth2RegisteredRepository;
        this.aMapperRegisteredClient = aMapperRegisteredClient;
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        //TODO validacion safe?

    }

    @Override
    public RegisteredClient findById(String id) {
        return null;
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return null;
    }


}
