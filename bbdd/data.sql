insert into management."authorization".authorization_server_settings
(issuer, authorization_endpoint, device_authorization_endpoint,
 device_verification_endpoint, token_endpoint, jwk_set_endpoint,
 token_revocation_endpoint, token_introspection_endpoint,
 oidc_client_registration_endpoint, oidc_user_info_endpoint,
 oidc_logout_endpoint)
values (null, '/oauth2/authorize',
        '/oauth2/device_authorization', '/oauth2/device_verification',
        '/oauth2/token', '/oauth2/jwks',
        '/oauth2/revoke', '/oauth2/introspect',
        '/connect/register', '/userinfo',
        '/connect/logout');

insert into management."authorization".oauth2_authorization_methods
(authorization_methods_value)
values ('client_secret_basic'),
       ('client_secret_post'),
       ('client_secret_jwt'),
       ('private_key_jwt'),
       ('none');

insert into management."authorization".oauth2_authorization_grant_types
(authorization_grant_type_value)
VALUES ('authorization_code'),
       ('refresh_token'),
       ('client_credentials'),
       ('password'),
       ('urn:ietf:params:oauth:grant-type:jwt-bearer'),
       ('urn:ietf:params:oauth:grant-type:device_code');

insert into management."authorization".oauth2_registered_client_authorization_client_settings
(require_proof_key, require_authorization_consent,
 jwt_set_url, token_endpoint_authentication_signing_algorithm)
values (true, null, null, null);

--insert into management."authorization".oauth2_registered_client_token_settings
--    (authorization_code_time_to_live, access_token_time_to_live,
--     access_token_format, device_code_time_to_live, reuse_refresh_tokens,
--     refresh_token_time_to_live, id_token_signature_algorithm)
--values ();

insert into management.users."user"
(name, surname, email,
 login, password,
 high_date, high_id_user)
VALUES ('srvauthorizationserver', 'srvauthorizationserver', 'srvauthorizationserver@protom.me',
        'srvauthorizationserver', '{noop}password',
        CURRENT_TIMESTAMP, null);

insert into management.applications.application
(name, description,
 high_date, high_id_user)
VALUES ('authorization-server', 'AuthorizationServer',
        CURRENT_TIMESTAMP, 1);

insert into management."authorization".oauth2_registered_client
(application_id, user_id,
 client_name, client_settings_id,
 token_settings_id)
VALUES (1, 1, 'oauthauthorizationserver', 1, null);

