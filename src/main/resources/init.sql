-- Полет
CREATE TABLE IF NOT EXISTS flight
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    description TEXT,
    date        DATE,
    place       VARCHAR(40)
);

-- Пилот
CREATE TABLE IF NOT EXISTS pilot
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Связь многие-ко-многим Пилот-Полет
CREATE TABLE IF NOT EXISTS pilot_flight
(
    pilot_id  BIGINT REFERENCES pilot,
    flight_id BIGINT REFERENCES flight,
    PRIMARY KEY (pilot_id, flight_id)
);

-- Видео полета
CREATE TABLE IF NOT EXISTS video
(
    id        BIGSERIAL PRIMARY KEY,
    name      VARCHAR(50)  NOT NULL,
    file_path VARCHAR(100) NOT NULL,
    flight_id BIGINT UNIQUE REFERENCES flight
);

-- Нарушение
CREATE TABLE IF NOT EXISTS violation
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    description TEXT,
    time        TIME,
    flight_id   BIGINT REFERENCES flight
);

-- Карточка нарушения (violation)
CREATE TABLE IF NOT EXISTS card
(
    id           BIGSERIAL PRIMARY KEY,
    file_path    VARCHAR(100) NOT NULL,
    violation_id BIGINT UNIQUE REFERENCES violation
);
