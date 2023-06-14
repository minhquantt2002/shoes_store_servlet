create
database shoes;
use
shoes;

CREATE TABLE `role`
(
    `id`   INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(128) unique
);

CREATE TABLE `account`
(
    `id`           INT(11) PRIMARY KEY AUTO_INCREMENT,
    `username`     VARCHAR(128) UNIQUE,
    `email`        VARCHAR(128) UNIQUE,
    `password`     VARCHAR(128),
    `role`         VARCHAR(128),
    `gender`       VARCHAR(128),
    `full_name`    VARCHAR(128),
    `image_link`   TEXT,
    `dob`          VARCHAR(128),
    `phone_number` VARCHAR(128)
);

CREATE TABLE `category`
(
    `id`   INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(128) unique,
    `icon` VARCHAR(128)
);

CREATE TABLE `product`
(
    `id`          INT(11) PRIMARY KEY AUTO_INCREMENT,
    `category_id` INT(11),
    `name`        TEXT,
    `created_at`  VARCHAR(128),
    `price`       float(11),
    `cost`        float(11),
    `description` TEXT,
    `image_link`  TEXT,
    `image_list`  TEXT,
    `status`      VARCHAR(128),
    FOREIGN KEY (`category_id`)
        REFERENCES `category` (`id`)
);

CREATE TABLE `product_detail`
(
    `id`         INT(11) PRIMARY KEY AUTO_INCREMENT,
    `product_id` INT(11) NOT NULL,
    `size`       VARCHAR(128),
    `quantity`   INT(11),
    FOREIGN KEY (`product_id`)
        REFERENCES `product` (`id`) ON DELETE CASCADE
);

CREATE TABLE `bill`
(
    `id`              INT(11) PRIMARY KEY AUTO_INCREMENT,
    `status`          VARCHAR(128) NOT NULL,
    `user_id`         INT(11),
    `full_name`       VARCHAR(128),
    `email`           VARCHAR(128),
    `address`         VARCHAR(128),
    `phone_number`    VARCHAR(128),
    `total_amount`    FLOAT(11),
    `pay_method`      VARCHAR(128),
    `note`            TEXT,
    `created_at`      VARCHAR(128),
    `invoice_creator` VARCHAR(128)
);

CREATE TABLE `bill_detail`
(
    `id`         INT(11) PRIMARY KEY AUTO_INCREMENT,
    `bill_id`    INT(11),
    `quantity`   INT(11),
    `product_id` INT(11),
    `amount`     float(11),
    `size`       INT(11),
    FOREIGN KEY (`bill_id`)
        REFERENCES `bill` (`id`),
    FOREIGN KEY (`product_id`)
        REFERENCES `product` (`id`)
);

CREATE TABLE `config`
(
    `id`      VARCHAR(255) primary key,
    `name`    VARCHAR(255),
    `content` TEXT
);
CREATE TABLE `comment`
(
    `id`         INT(11) PRIMARY KEY,
    `content`    TEXT,
    `product_id` INT(11),
    `user_id` INT(11),
    FOREIGN KEY (`product_id`)
        REFERENCES `product` (`id`) ON DELETE CASCADE
);