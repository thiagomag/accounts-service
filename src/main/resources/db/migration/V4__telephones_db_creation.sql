CREATE TABLE telephones (
    id              BIGSERIAL PRIMARY KEY,
    country_code    VARCHAR(255) NOT NULL,
    area_code       VARCHAR(255) NOT NULL,
    number          VARCHAR(255) NOT NULL,
    type            VARCHAR(255) NOT NULL,
    user_id         BIGINT NOT NULL,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP,
    deleted_tmsp    TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);