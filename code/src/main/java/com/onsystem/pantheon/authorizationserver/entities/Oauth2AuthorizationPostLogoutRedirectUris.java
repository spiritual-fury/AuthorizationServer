package com.onsystem.pantheon.authorizationserver.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "oauth2_authorization_post_logout_redirect_uris")
public class Oauth2AuthorizationPostLogoutRedirectUris {
    @EmbeddedId
    private Oauth2AuthorizationPostLogoutRedirectUrisId id;

    @MapsId("id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Oauth2RegisteredClient id1;

}