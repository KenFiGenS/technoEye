CREATE TABLE cameras (
     id BIGSERIAL PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     url VARCHAR(255) NOT NULL UNIQUE,
     is_active BOOLEAN NOT NULL,
     location VARCHAR(255)
);