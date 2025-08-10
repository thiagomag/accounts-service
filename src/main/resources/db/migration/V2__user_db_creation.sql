CREATE TABLE users
(
    id                       BIGSERIAL       PRIMARY KEY NOT NULL,
    email                    varchar(255)    UNIQUE NOT NULL,
    password                 varchar(100)    NOT NULL,
    name                     varchar(255)    NOT NULL,
    cpf                      varchar(11)     UNIQUE NOT NULL,
    created_at               TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_at               TIMESTAMP,
    deleted_tmsp             TIMESTAMP
);