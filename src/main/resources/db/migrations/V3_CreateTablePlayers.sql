CREATE TABLE IF NOT EXISTS players
(
    id_user         BIGINT NOT NULL,
    sum_games       INT NOT NULL,
    sum_win         INT NOT NULL,
    PRIMARY KEY (id_user)
);

ALTER TABLE players
    ADD CONSTRAINT fk_players FOREIGN KEY (id_user) REFERENCES users;