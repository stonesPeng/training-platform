CREATE TABLE user_info (
		id                   BIGSERIAL,
    user_name            varchar(64) NOT NULL,
		password             varchar(64) NOT NULL,
		gender               INTEGER,
		age                  INTEGER  NOT NULL
);