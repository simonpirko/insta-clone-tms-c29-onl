create table accounts
(
    id       serial
        constraint accounts_pk
            primary key,
    username varchar not null,
    email    varchar not null,
    password varchar not null
);

alter table accounts
    owner to postgres;

