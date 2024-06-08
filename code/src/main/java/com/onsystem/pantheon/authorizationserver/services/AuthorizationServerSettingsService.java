package com.onsystem.pantheon.authorizationserver.services;

import com.onsystem.pantheon.authorizationserver.entities.AuthorizationServerSetting;
import com.onsystem.pantheon.authorizationserver.ifc.IAuthorizationServerSettingsService;
import com.onsystem.pantheon.authorizationserver.repositories.AuthorizationSettingsRepository;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;


public class AuthorizationServerSettingsService implements IAuthorizationServerSettingsService {

    //@Value("{auth.configurationSettingsId}")
    private Integer configurationAuthorizationSettingsId;

    private AuthorizationSettingsRepository authorizationSettingsRepository;


    public AuthorizationServerSettingsService(Integer configurationAuthorizationSettingsId, AuthorizationSettingsRepository authorizationSettingsRepository) {
        this.configurationAuthorizationSettingsId = configurationAuthorizationSettingsId;
        this.authorizationSettingsRepository = authorizationSettingsRepository;
    }

    @Override
    public AuthorizationServerSettings getAuthorizationServerSettings() {
        final AuthorizationServerSetting authorizationServerSettings = authorizationSettingsRepository.findById(configurationAuthorizationSettingsId)
                .orElseThrow();
        return null;
    }
}
