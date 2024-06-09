package com.onsystem.pantheon.authorizationserver.config;

import com.onsystem.pantheon.authorizationserver.ifc.IAuthorizationServerSettingsService;
import com.onsystem.pantheon.authorizationserver.mapper.AMapperRegisteredClient;
import com.onsystem.pantheon.authorizationserver.repositories.AuthorizationSettingsRepository;
import com.onsystem.pantheon.authorizationserver.repositories.Oauth2RegisteredRepository;
import com.onsystem.pantheon.authorizationserver.services.AuthorizationServerSettingsService;
import com.onsystem.pantheon.authorizationserver.services.RegisteredClientService;
import com.onsystem.pantheon.authorizationserver.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

@Configuration
@ConditionalOnProperty(name = "auth.mock", havingValue = "false")
public class ConfigBBDD {

    @Value("${auth.configurationSettingsId}")
    private Integer configurationAuthorizationSettingsId;

    @Bean
    public IAuthorizationServerSettingsService authorizationServerSettingsService(AuthorizationSettingsRepository authorizationSettingsRepository) {
        return new AuthorizationServerSettingsService(configurationAuthorizationSettingsId, authorizationSettingsRepository);
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository(
            Oauth2RegisteredRepository oauth2RegisteredRepository,
            AMapperRegisteredClient aMapperRegisteredClient
    ) {
        return new RegisteredClientService(oauth2RegisteredRepository, aMapperRegisteredClient);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
}