CREATE TABLE IF NOT EXISTS Droids(
    name VARCHAR(50),
    model VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Response_Errors(
    message VARCHAR(200),
    datetime TIMESTAMP
);