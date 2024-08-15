CREATE TABLE posts (
                       id UUID PRIMARY KEY,
                       description TEXT NOT NULL,
                       filepath BIT NOT NULL,
                       created_at TIMESTAMP NOT NULL
);
