-- This script was generated by the ERD tool in pgAdmin 4.
-- Please log an issue at https://redmine.postgresql.org/projects/pgadmin4/issues/new if you find any bugs, including reproduction steps.


CREATE DATABASE management;
\c management;
CREATE SCHEMA IF NOT EXISTS public;
CREATE SCHEMA IF NOT EXISTS users;
CREATE SCHEMA IF NOT EXISTS applications;
CREATE SCHEMA IF NOT EXISTS enterprise;


CREATE TABLE IF NOT EXISTS users."user"
(
    id_user        SERIAL,
    name           character varying(50) COLLATE pg_catalog."default"  NOT NULL,
    surname        character varying(50) COLLATE pg_catalog."default"  NOT NULL,
    email          character varying(100) COLLATE pg_catalog."default" NOT NULL UNIQUE,
    login          character varying(30) COLLATE pg_catalog."default"  NOT NULL UNIQUE,
    password       character varying(255) COLLATE pg_catalog."default" NOT NULL,
    high_date      timestamp                                           NOT NULL,
    high_id_user   integer references users.user (id_user),
    delete_date    timestamp,
    delete_id_user integer references users.user (id_user),
    CONSTRAINT user_pk PRIMARY KEY (id_user)
);

CREATE TABLE IF NOT EXISTS applications.application
(
    id_application SERIAL,
    name           character varying(100) COLLATE pg_catalog."default" NOT NULL,
    description    character varying(255) COLLATE pg_catalog."default",
    high_date      timestamp                                           NOT NULL,
    high_id_user   integer                                             NOT NULL references users.user (id_user),
    delete_date    timestamp,
    delete_id_user integer references users.user (id_user),
    CONSTRAINT applications_pk PRIMARY KEY (id_application)
    );


--SCHEME AUTHORIZATION
CREATE SCHEMA IF NOT EXISTS "authorization";

CREATE TABLE "authorization".authorization_server_settings
(
    id                                serial,
    issuer                            varchar(255),
    authorization_endpoint            varchar(255) NOT NULL,
    device_authorization_endpoint     varchar(255) NOT NULL,
    device_verification_endpoint      varchar(255) NOT NULL,
    token_endpoint                    varchar(255) NOT NULL,
    jwk_set_endpoint                  varchar(255) NOT NULL,
    token_revocation_endpoint         varchar(255) NOT NULL,
    token_introspection_endpoint      varchar(255) NOT NULL,
    oidc_client_registration_endpoint varchar(255) NOT NULL,
    oidc_user_info_endpoint           varchar(255) NOT NULL,
    oidc_logout_endpoint              varchar(255) NOT NULL,
    constraint authorization_server_settings_pk PRIMARY KEY (id)
);

CREATE TYPE signature_algorithm AS ENUM ('RS256', 'RS384', 'RS512','ES256','ES384','ES512','PS256','PS384','PS512');
CREATE TYPE oauth2_token_format AS ENUM ('self-contained','reference');

create table "authorization".oauth2_registered_client_token_settings
(
    id                              serial,
    authorization_code_time_to_live float               NOT NULL,
    access_token_time_to_live       float               NOT NULL,
    access_token_format             oauth2_token_format NOT NULL,
    device_code_time_to_live        float               NOT NULL,
    reuse_refresh_tokens            bool                NOT NULL,
    refresh_token_time_to_live      float               NOT NULL,
    id_token_signature_algorithm    signature_algorithm NOT NULL,
    constraint oauth2_registered_client_token_settings_pk PRIMARY KEY (id)
);

create table "authorization".oauth2_registered_client_authorization_client_settings
(
    id                                              serial,
    require_proof_key                               bool DEFAULT false,
    require_authorization_consent                   bool DEFAULT true,
    jwt_set_url                                     varchar(1000),
    token_endpoint_authentication_signing_algorithm varchar(100),
    constraint oauth2_registered_client_authorization_client_settings_pk PRIMARY KEY (id)
);

CREATE TABLE "authorization".oauth2_registered_client
(
    id                        serial                                                   NOT NULL,
    application_id            int references applications.application (id_application) NOT NULL,
    user_id                   int references "users"."user" (id_user)                  NOT NULL,
    client_id_issued_at       timestamp     DEFAULT CURRENT_TIMESTAMP                  NOT NULL,
    client_name               varchar(200)                                             NOT NULL,
    post_logout_redirect_uris varchar(1000) DEFAULT NULL,
    client_settings_id        int references "authorization".oauth2_registered_client_authorization_client_settings (id),
    token_settings_id         int references "authorization".oauth2_registered_client_token_settings (id),
    PRIMARY KEY (id)
);

create table "authorization".oauth2_authorization_post_logout_redirect_uris
(
    id  int references "authorization".oauth2_registered_client (id),
    url varchar(1000) NOT NULL,
    constraint oauth2_authorization_post_logout_redirect_uris_pk PRIMARY KEY (id, url)
);

create table "authorization".oauth2_authorization_redirect_uris
(
    id  int references "authorization".oauth2_registered_client (id),
    url varchar(1000) NOT NULL,
    constraint oauth2_authorization_redirect_uris_pk PRIMARY KEY (id, url)
);

create table "authorization".oauth2_authorization_methods
(
    id                          serial,
    authorization_methods_value varchar(200),
    constraint authorization_methods_pk PRIMARY KEY (id)
);

create table "authorization".oauth2_registered_client_authorization_methods
(
    oauth2_registered_client_id int references "authorization".oauth2_registered_client (id),
    authorization_methods_id    int references "authorization".oauth2_authorization_methods (id),
    constraint registered_client_authorization_methods_pk PRIMARY KEY (oauth2_registered_client_id, authorization_methods_id)
);

create table "authorization".oauth2_authorization_grant_types
(
    id                             serial,
    authorization_grant_type_value varchar(200),
    constraint authorization_grant_types_pk PRIMARY KEY (id)
);

create table "authorization".oauth2_registered_client_authorization_grant_types
(
    oauth2_registered_client_id  int references "authorization".oauth2_registered_client (id),
    authorization_grant_types_id int references "authorization".oauth2_authorization_grant_types (id),
    constraint registered_client_authorization_grant_types_pk PRIMARY KEY (oauth2_registered_client_id, authorization_grant_types_id)
);

create table "authorization".scope
(
    id   serial,
    name varchar(255),
    PRIMARY KEY (id)
);

create table "authorization".oauth2_registered_client_scopes
(
    id_registered_client int references management."authorization".oauth2_registered_client (id) NOT NULL,
    id_scope             int references management."authorization".scope (id)                    NOT NULL,
    PRIMARY KEY (id_registered_client, id_scope)
)