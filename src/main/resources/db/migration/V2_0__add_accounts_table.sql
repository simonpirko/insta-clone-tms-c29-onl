create table accounts
(
    id       serial
        constraint accounts_pk
            primary key,
    username varchar not null
        constraint accounts_pk_2
            unique,
    password varchar not null,
    email varchar not null,
    name varchar,
    website varchar,
    about varchar,
    gender varchar,
    avatar bytea

);

alter table accounts
    owner to postgres;