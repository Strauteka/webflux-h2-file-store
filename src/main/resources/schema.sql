--DROP TABLE COFFEE IF EXISTS;
CREATE TABLE IF NOT EXISTS COFFEE
(
    id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    coffee VARCHAR(255),
    price  DOUBLE,
    PRIMARY KEY (id),
    UNIQUE KEY coffee_coffee_unique(coffee)
);