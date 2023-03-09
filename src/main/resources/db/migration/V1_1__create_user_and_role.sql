CREATE TABLE IF NOT EXISTS t_User (
    user_id serial PRIMARY KEY,
    email varchar(30) UNIQUE NOT NULL,
    name varchar(40) NOT NULL,
    patronymic varchar(40),
    surname varchar(40) NOT NULL,
    password varchar(20) NOT NULL,
    isLocked bool NOT NULL
);

CREATE TABLE IF NOT EXISTS Role (
    role_id serial PRIMARY KEY,
    role_name varchar(60) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS user_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (role_id)
                        REFERENCES Role (role_id),
    FOREIGN KEY (user_id)
                        REFERENCES t_User (user_id)
);