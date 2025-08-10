CREATE TABLE user_roles (
    id                       BIGSERIAL PRIMARY KEY,
    user_id                  BIGINT NOT NULL,
    role_id                  BIGINT NOT NULL,
    created_at               TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at               TIMESTAMP,
    deleted_tmsp             TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id),
    UNIQUE (user_id, role_id, deleted_tmsp)
);