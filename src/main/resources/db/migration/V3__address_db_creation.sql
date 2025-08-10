CREATE TABLE address (
    id                       BIGSERIAL PRIMARY KEY NOT NULL,
    street                   VARCHAR(255) NOT NULL,
    number                   VARCHAR(255) NOT NULL,
    complement               VARCHAR(255) NULL,
    neighborhood             VARCHAR(255) NOT NULL,
    city                     VARCHAR(255) NOT NULL,
    state                    VARCHAR(255) NOT NULL,
    country                  VARCHAR(255) NOT NULL,
    postal_code              VARCHAR(255) NOT NULL,
    user_id                  BIGINT NOT NULL,
    created_at               TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at               TIMESTAMP,
    deleted_tmsp             TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);