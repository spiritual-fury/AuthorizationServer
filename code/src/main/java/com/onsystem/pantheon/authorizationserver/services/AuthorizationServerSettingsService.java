package com.onsystem.pantheon.authorizationserver.services;

import com.onsystem.pantheon.authorizationserver.entities.AuthorizationServerSetting;
import com.onsystem.pantheon.authorizationserver.ifc.IAuthorizationServerSettingsService;
import com.onsystem.pantheon.authorizationserver.repositories.AuthorizationSettingsRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;


public class AuthorizationServerSettingsService implements IAuthorizationServerSettingsService {

    private final Integer configurationAuthorizationSettingsId;
    private final AuthorizationSettingsRepository authorizationSettingsRepository;

    public AuthorizationServerSettingsService(Integer configurationAuthorizationSettingsId, AuthorizationSettingsRepository authorizationSettingsRepository) {
        this.configurationAuthorizationSettingsId = configurationAuthorizationSettingsId;
        this.authorizationSettingsRepository = authorizationSettingsRepository;
    }

    @Override
    public AuthorizationServerSettings getAuthorizationServerSettings() {
        final AuthorizationServerSetting authorizationServerSettings = authorizationSettingsRepository.findById(configurationAuthorizationSettingsId)
                .orElseThrow();
        return map(authorizationServerSettings);
    }

    private @NotNull AuthorizationServerSettings map(final @NotNull AuthorizationServerSetting authorizationServerSetting) {
        return AuthorizationServerSettings.builder()
                //.issuer(authorizationServerSetting.getIssuer())
                .authorizationEndpoint(authorizationServerSetting.getAuthorizationEndpoint())
                .deviceAuthorizationEndpoint(authorizationServerSetting.getDeviceAuthorizationEndpoint())
                .deviceVerificationEndpoint(authorizationServerSetting.getDeviceVerificationEndpoint())
                .tokenEndpoint(authorizationServerSetting.getTokenEndpoint())
                .jwkSetEndpoint(authorizationServerSetting.getJwkSetEndpoint())
                .tokenRevocationEndpoint(authorizationServerSetting.getTokenRevocationEndpoint())
                .tokenIntrospectionEndpoint(authorizationServerSetting.getTokenIntrospectionEndpoint())
                .oidcClientRegistrationEndpoint(authorizationServerSetting.getOidcClientRegistrationEndpoint())
                .oidcUserInfoEndpoint(authorizationServerSetting.getOidcUserInfoEndpoint())
                .oidcLogoutEndpoint(authorizationServerSetting.getOidcLogoutEndpoint())
                .build();

    }

}
