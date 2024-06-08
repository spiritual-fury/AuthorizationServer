package com.onsystem.pantheon.authorizationserver.config;

import com.onsystem.pantheon.authorizationserver.repositories.AuthorizationSettingsRepository;
import com.onsystem.pantheon.authorizationserver.services.AuthorizationServerSettingsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "mock", havingValue = "false")
public class ConfigBBDD {

    @Value("{auth.configurationSettingsId}")
    private Integer configurationAuthorizationSettingsId;

    @Bean
    public AuthorizationServerSettingsService authorizationServerSettingsService(AuthorizationSettingsRepository authorizationSettingsRepository) {
        return new AuthorizationServerSettingsService(configurationAuthorizationSettingsId, authorizationSettingsRepository);
    }


}
