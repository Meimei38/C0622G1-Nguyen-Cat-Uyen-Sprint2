DROP DATABASE IF EXISTS yarn_shop;
CREATE DATABASE yarn_shop;
USE yarn_shop;
CREATE TABLE category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);
CREATE TABLE shipping_fee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fee DOUBLE
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
CREATE TABLE yarn_group (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(2),
    description VARCHAR(255)
);
CREATE TABLE product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    origin VARCHAR(255),
    brand_id INT,
    descriptions TEXT,
    is_delete INT,
    category_id INT,
    discount_id INT,
    weight INT,
    yarn_group_id INT,
    FOREIGN KEY (category_id)
        REFERENCES category (id),
    FOREIGN KEY (brand_id)
        REFERENCES brand (id),
    FOREIGN KEY (discount_id)
        REFERENCES discount (id),
    FOREIGN KEY (yarn_group_id)
        REFERENCES yarn_group (id)
);
CREATE TABLE image (
    id INT PRIMARY KEY AUTO_INCREMENT,
    image_url VARCHAR(255),
    product_detail_id INT,
    is_delete INT,
    FOREIGN KEY (product_detail_id)
        REFERENCES product_detail(id)
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
    address_detail VARCHAR(255),
    city VARCHAR(255),
    ward VARCHAR(255),
    district VARCHAR(255),
    country VARCHAR(255),
    customer_type_id INT,
    FOREIGN KEY (account_id)
        REFERENCES account (id),
    FOREIGN KEY (customer_type_id)
        REFERENCES customer_type (id)
);
CREATE TABLE voucher (
    id INT PRIMARY KEY AUTO_INCREMENT,
    voucher_code INT,
    discount_amount DOUBLE,
    customer_id INT,
    start_date VARCHAR(255),
    end_date VARCHAR(255),
    is_delete INT,
    FOREIGN KEY (customer_id)
        REFERENCES customer (id)
);
CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    avatar VARCHAR(255),
    birthday VARCHAR(255),
    email VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    id_card VARCHAR(255),
    phone VARCHAR(255),
    account_id INT,
    address_detail VARCHAR(255),
    city VARCHAR(255),
    ward VARCHAR(255),
    district VARCHAR(255),
    country VARCHAR(255),
    FOREIGN KEY (account_id)
        REFERENCES account (id)
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
CREATE TABLE payment (
    id INT PRIMARY KEY AUTO_INCREMENT,
    payment_status INT,
    total_bill DOUBLE,
    date_created VARCHAR(255),
    customer_id INT,
    shipping_information_id INT,
    shipping_fee_id INT,
    FOREIGN KEY (customer_id)
        REFERENCES customer (id),
    FOREIGN KEY (shipping_information_id)
        REFERENCES shipping_information (id),
    FOREIGN KEY (shipping_fee_id)
        REFERENCES shipping_fee (id)
);
CREATE TABLE order_detail (
    id INT PRIMARY KEY AUTO_INCREMENT,
    is_delete INT,
    payment_id INT,
    product_detail_id INT,
    quantity INT,
    FOREIGN KEY (payment_id)
        REFERENCES payment (id),
    FOREIGN KEY (product_detail_id)
        REFERENCES product_detail (id)
);
CREATE TABLE product_detail (
    id INT PRIMARY KEY AUTO_INCREMENT,
    product_id VARCHAR(50),
    color VARCHAR(50),
    quantity INT,
    delete_status BIT,
    FOREIGN KEY (product_id)
        REFERENCES product (id)
);



