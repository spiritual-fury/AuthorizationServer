package com.onsystem.pantheon.authorizationserver.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import static com.onsystem.pantheon.authorizationserver.Constans.SCHEME_AUTHORIZATION;

@Getter
@Setter
@Entity
@Table(schema = SCHEME_AUTHORIZATION,name = "oauth2_registered_client_authorization_client_settings")
public class Oauth2RegisteredClientAuthorizationClientSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ColumnDefault("false")
    @Column(name = "require_proof_key")
    private Boolean requireProofKey;

    @ColumnDefault("true")
    @Column(name = "require_authorization_consent")
    private Boolean requireAuthorizationConsent;

    @Size(max = 1000)
    @Column(name = "jwt_set_url", length = 1000)
    private String jwtSetUrl;

    @Size(max = 100)
    @Column(name = "token_endpoint_authentication_signing_algorithm", length = 100)
    private String tokenEndpointAuthenticationSigningAlgorithm;

}