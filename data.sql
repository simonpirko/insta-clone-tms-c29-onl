

create table accounts
(
    id       serial
        constraint accounts_pk
            primary key,
    username varchar not null
        constraint accounts_pk_2
            unique,
    password varchar not null,
    email    varchar not null
);

alter table accounts
    owner to postgres;

