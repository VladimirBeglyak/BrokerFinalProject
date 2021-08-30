CREATE DATABASE broker;

CREATE TABLE stocks
(
    id       SERIAL PRIMARY KEY,
    ticker   VARCHAR(32)  NOT NULL UNIQUE,
    name     VARCHAR(128) NOT NULL,
    cost     DECIMAL      NOT NULL,
    dividend DECIMAL,
    currency VARCHAR(32)  NOT NULL
);

CREATE TABLE clients
(
    id          SERIAL PRIMARY KEY,
    first_name  VARCHAR(128)        NOT NULL,
    last_name   VARCHAR(128)        NOT NULL,
    father_name VARCHAR(128),
    birthday    DATE                NOT NULL,
    passport_id VARCHAR(128) UNIQUE NOT NULL,
    password    VARCHAR(128)        NOT NULL,
    email       VARCHAR(128)        NOT NULL UNIQUE,
    role        VARCHAR(128)        NOT NULL,
    stock_id BIGINT REFERENCES stocks (id)
);

DROP TABLE clients CASCADE ;

CREATE TABLE currencies
(
    id     BIGSERIAL PRIMARY KEY,
    ticker VARCHAR(128) NOT NULL UNIQUE,
    name   VARCHAR(128) NOT NULL
);

CREATE TABLE currencies_clients
(
    currencies_id BIGINT REFERENCES currencies (id) NOT NULL,
    amount        DECIMAL,
    clients_id    BIGINT REFERENCES clients (id)    NOT NULL
);

CREATE TABLE stocks_clients
(
    stock_id   BIGINT REFERENCES stocks (id)  NOT NULL,
    amount     BIGINT                         NOT NULL,
    clients_id BIGINT REFERENCES clients (id) NOT NULL
);


SELECT c.id,
       first_name,
       last_name,
       father_name,
       birthday,
       passport_id,
       password,
       email,
       role,
       s.id,
       s.name,
       s.ticker,
       s.cost,
       s.dividend,
       s.currency
FROM clients c
         JOIN stocks s on s.id = c.stock_id
WHERE c.id = 2;

SELECT c.id,
       first_name,
       last_name,
       father_name,
       birthday,
       passport_id,
       password,
       email,
       role,
       s.id,
       s.ticker,
       s.name,
       s.cost,
       s.currency,
       s.dividend
FROM clients c
         JOIN stocks s on c.stock_id = s.id
WHERE c.id = 2;

SELECT c.id,
       first_name,
       last_name,
       father_name,
       birthday,
       passport_id,
       password,
       email,
       role,
       cc.amount,
       currencies.name,
       currencies.ticker
FROM clients c
         JOIN currencies_clients cc ON c.id = cc.clients_id
         JOIN currencies ON cc.currencies_id = currencies.id
WHERE c.id = 2;

SELECT clients.id,
       first_name,
       last_name,
       father_name,
       birthday,
       passport_id,
       password,
       email,
       role,
       stock_id
       /*s.id,
       s.ticker,
       s.name,
       s.dividend,
       s.cost,
       s.currency*/
FROM clients
         JOIN stocks s on s.id = clients.stock_id
WHERE clients.id=?;

SELECT
clients.id,
       first_name,
       last_name,
       father_name,
       birthday,
       passport_id,
       password,
       email,
       role,
       stock_id,
       s.id,
       s.ticker,
       s.name,
       s.currency,
       s.cost,
       s.dividend
FROM clients
JOIN stocks s on s.id = clients.stock_id
WHERE clients.id=3


