create table posts
(
    id          serial,
    description varchar   not null,
    filepath    bytea     not null,
    created_at  timestamp not null
);
