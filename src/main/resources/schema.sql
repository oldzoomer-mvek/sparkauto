-- Users table for Spring Security JDBC auth
CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL
);

-- Authorities table (roles)
CREATE TABLE IF NOT EXISTS authorities (
    username VARCHAR(50),
    authority VARCHAR(50),
    CONSTRAINT fk_user FOREIGN KEY(username) REFERENCES users(username)
);
