create table human
(
    id       serial
        primary key,
    name     varchar(50) not null,
    surname  varchar(50) not null,
    username varchar(20) not null
        unique,
    FilePathPhoto    bytea,
    email    varchar(50) not null
        unique,
    password varchar(50) not null
);
alter table human
    owner to postgres;

create table post
(
    id          serial
        primary key,
    author_id   integer
        references human,
    photo       bytea,
    description varchar(250),
    created_at  timestamp
);
alter table post
    owner to postgres;


create table post_comment
(
    id        serial
        primary key,
    author_id integer
        references human,
    post_id   integer
        references post
            on delete cascade,
    text      varchar(255) not null
);
alter table post_comment
    owner to postgres;

create table post_like
(
    author_id  integer not null
        references human,
    post_id    integer not null
        references post
            on delete cascade,
    created_at timestamp,
    constraint post_like_pk
        primary key (author_id, post_id)
);

alter table post_like
    owner to postgres;