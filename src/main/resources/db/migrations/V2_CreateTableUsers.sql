CREATE TABLE IF NOT EXISTS users
(
    id              BIGSERIAL NOT NULL,
    login           VARCHAR(255) NOT NULL,
    password        VARCHAR(255) NOT NULL,
    id_role         BIGINT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT fk_users FOREIGN KEY (id_role) REFERENCES role;