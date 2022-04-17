CREATE TABLE users
(
    username VARCHAR(50) NOT NULL,
    password VARCHAR(200) NOT NULL,
    enabled  BOOLEAN     NOT NULL,

    CONSTRAINT pk_users PRIMARY KEY (username)
);
CREATE TABLE authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    constraint fk_authorities_users FOREIGN KEY (username) references users (username)
);
CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);


