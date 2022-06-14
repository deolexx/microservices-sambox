CREATE USER user1 WITH PASSWORD 'user1' CREATEDB;
CREATE DATABASE currency_db
    WITH
    OWNER = user1
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;