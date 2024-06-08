package com.onsystem.pantheon.authorizationserver.config;

import com.onsystem.pantheon.authorizationserver.ifc.IAuthorizationServerSettingsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;

@Configuration
@EnableWebSecurity
public class GlobalConfig {


    @Bean
    public AuthorizationServerSettings authorizationServerSettings(IAuthorizationServerSettingsService iAuthorizationServerSettingsService) {
        return iAuthorizationServerSettingsService.getAuthorizationServerSettings();
    }

}
