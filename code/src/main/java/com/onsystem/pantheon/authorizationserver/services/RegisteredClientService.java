package com.onsystem.pantheon.authorizationserver.services;

import com.onsystem.pantheon.authorizationserver.repositories.Oauth2RegisteredRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

public class RegisteredClientService implements RegisteredClientRepository {

    private Oauth2RegisteredRepository oauth2RegisteredRepository;

    public RegisteredClientService(Oauth2RegisteredRepository oauth2RegisteredRepository) {
        this.oauth2RegisteredRepository = oauth2RegisteredRepository;
    }

    @Override
    public void save(RegisteredClient registeredClient) {

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
