package com.onsystem.pantheon.authorizationserver.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

import static com.onsystem.pantheon.authorizationserver.Constans.SCHEME_AUTHORIZATION;

@Getter
@Setter
@Entity
@Table(schema = SCHEME_AUTHORIZATION,name = "oauth2_registered_client")
public class Oauth2RegisteredClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "client_id_issued_at", nullable = false)
    private Instant clientIdIssuedAt;

    @Size(max = 200)
    @NotNull
    @Column(name = "client_name", nullable = false, length = 200)
    private String clientName;

    @Size(max = 1000)
    @ColumnDefault("NULL")
    @Column(name = "post_logout_redirect_uris", length = 1000)
    private String postLogoutRedirectUris;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_settings_id")
    private Oauth2RegisteredClientAuthorizationClientSetting clientSettings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "token_settings_id")
    private Oauth2RegisteredClientTokenSetting tokenSettings;

}