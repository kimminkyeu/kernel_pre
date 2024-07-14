CREATE DATABASE IF NOT EXISTS `BOARD_00`;

USE `BOARD_00`;

CREATE TABLE IF NOT EXISTS `BOARDS` (
    `board_id`   BIGINT          NOT NULL AUTO_INCREMENT,
    `category`   VARCHAR(20)     NOT NULL,

    PRIMARY KEY (`board_id`)
);

CREATE TABLE IF NOT EXISTS `ARTICLES` (
    `article_id`       BIGINT          NOT NULL AUTO_INCREMENT,

    `writer_name`      VARCHAR(20)     NOT NULL,
    `writer_email`     VARCHAR(100)    NOT NULL,
    `password`         VARCHAR(20)     NOT NULL,
    `title`            VARCHAR(255)    NOT NULL,
    `body`             VARCHAR(255)    NOT NULL,

    `created_at`       TIMESTAMP       NOT NULL,
    `updated_at`       TIMESTAMP       NOT NULL,

    `board_id`         BIGINT          NOT NULL,

    PRIMARY KEY (`article_id`),
    FOREIGN KEY (`board_id`) REFERENCES BOARDS(`board_id`)
);

CREATE TABLE IF NOT EXISTS `COMMENTS` (
    `comment_id`       BIGINT          NOT NULL AUTO_INCREMENT,

    `writer_name`      VARCHAR(20)     NOT NULL,
    `body`             VARCHAR(255)    NOT NULL,

    `created_at`       TIMESTAMP       NOT NULL,
    `updated_at`       TIMESTAMP       NOT NULL,

    `article_id`       BIGINT          NOT NULL,

    PRIMARY KEY (`comment_id`),
    FOREIGN KEY (`article_id`) REFERENCES ARTICLES(`article_id`)
);