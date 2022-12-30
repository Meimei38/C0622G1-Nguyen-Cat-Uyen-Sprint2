DROP DATABASE IF EXISTS yarn_shop ;
CREATE DATABASE yarn_shop;
USE yarn_shop;
CREATE TABLE category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);
CREATE TABLE brand (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);
CREATE TABLE discount (
    id INT PRIMARY KEY AUTO_INCREMENT,
    discount_percent INT,
    start_date VARCHAR(255),
    end_date VARCHAR(255),
    is_delete INT
);
CREATE TABLE product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    origin VARCHAR(255),
    color VARCHAR(255),
    size VARCHAR(255),
    quantity VARCHAR(255),
    brand_id INT,
    descriptions TEXT,
    is_delete INT,
    category_id INT,
    discount_id INT,
    FOREIGN KEY (category_id)
        REFERENCES category (id),
    FOREIGN KEY (brand_id)
        REFERENCES brand (id),
    FOREIGN KEY (discount_id)
        REFERENCES discount (id)
);
CREATE TABLE image (
    id INT PRIMARY KEY AUTO_INCREMENT,
    image_name VARCHAR(255),
    product_id INT,
    is_delete INT,
    FOREIGN KEY (product_id)
        REFERENCES product (id)
);
CREATE TABLE account (
    id INT PRIMARY KEY AUTO_INCREMENT,
    is_delete INT,
    password VARCHAR(255),
    username VARCHAR(255)
);
CREATE TABLE role (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);
CREATE TABLE acount_role (
    id INT PRIMARY KEY AUTO_INCREMENT,
    account_id INT,
    role_id INT,
    FOREIGN KEY (account_id)
        REFERENCES account (id),
    FOREIGN KEY (role_id)
        REFERENCES role (id)
);
CREATE TABLE customer_type (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);

CREATE TABLE customer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    avatar VARCHAR(255),
    birthday VARCHAR(255),
    email VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    id_card VARCHAR(255),
    phone VARCHAR(255),
    account_id INT,
    address VARCHAR(255),
    customer_type_id INT,
    FOREIGN KEY (account_id)
        REFERENCES account (id),
    FOREIGN KEY (customer_type_id)
        REFERENCES customer_type (id)
);
CREATE TABLE cart_item (
    id INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT,
    customer_id INT,
    quantity INT,
    FOREIGN KEY (product_id)
        REFERENCES product (id),
    FOREIGN KEY (customer_id)
        REFERENCES customer (id)
);

CREATE TABLE shipping_information (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    shipping_address VARCHAR(255),
    shipping_receiver VARCHAR(255),
    receiver_phone VARCHAR(255)
);
CREATE TABLE `order` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    payment_status INT,
    total_bill DOUBLE,
    date_created VARCHAR(255),
    customer_id INT,
    shipping_information_id INT,
    FOREIGN KEY (customer_id)
        REFERENCES customer (id),
    FOREIGN KEY (shipping_information_id)
        REFERENCES shipping_information (id)
);
CREATE TABLE order_detail (
    id INT PRIMARY KEY AUTO_INCREMENT,
    is_delete INT,
    order_id INT,
    product_id INT,
    quantity INT,
    FOREIGN KEY (order_id)
        REFERENCES `order` (id),
    FOREIGN KEY (product_id)
        REFERENCES product (id)
);


