CREATE TABLE roles
(
    id                       BIGSERIAL    PRIMARY KEY NOT NULL,
    name                     varchar(255) UNIQUE NOT NULL,
    created_at               TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    updated_at               TIMESTAMP,
    deleted_tmsp             TIMESTAMP
);

INSERT INTO public.roles(name) VALUES('ROLE_ADMIN'), ('ROLE_BASIC');