package com.onsystem.pantheon.authorizationserver.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "oauth2_registered_client_token_settings")
public class Oauth2RegisteredClientTokenSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "authorization_code_time_to_live", nullable = false)
    private Double authorizationCodeTimeToLive;

    @NotNull
    @Column(name = "access_token_time_to_live", nullable = false)
    private Double accessTokenTimeToLive;

    @NotNull
    @Column(name = "device_code_time_to_live", nullable = false)
    private Double deviceCodeTimeToLive;

    @NotNull
    @Column(name = "reuse_refresh_tokens", nullable = false)
    private Boolean reuseRefreshTokens = false;
    @NotNull
    @Column(name = "refresh_token_time_to_live", nullable = false)
    private Double refreshTokenTimeToLive;

/*
 TODO [Reverse Engineering] create field to map the 'access_token_format' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "access_token_format", columnDefinition = "oauth2_token_format not null")
    private Object accessTokenFormat;
*/
/*
 TODO [Reverse Engineering] create field to map the 'id_token_signature_algorithm' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "id_token_signature_algorithm", columnDefinition = "signature_algorithm not null")
    private Object idTokenSignatureAlgorithm;
*/
}