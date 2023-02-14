use `yarn_shop_data`;
INSERT INTO `yarn_shop_data`.`product_detail` (`id`, `color`, `is_delete`, `quantity`, `product_id`) VALUES ('1', '#e6293c', '0', '10', '1');
INSERT INTO `yarn_shop_data`.`product_detail` (`id`, `color`, `is_delete`, `quantity`, `product_id`) VALUES ('2', '#e9c048', '0', '2', '1');
INSERT INTO `yarn_shop_data`.`product_detail` (`id`, `color`, `is_delete`, `quantity`, `product_id`) VALUES ('3', '#517bc3', '0', '24', '2');
INSERT INTO `yarn_shop_data`.`product_detail` (`id`, `color`, `is_delete`, `quantity`, `product_id`) VALUES ('4', '#a9a11e', '0', '12', '2');
INSERT INTO `yarn_shop_data`.`product_detail` (`id`, `color`, `is_delete`, `quantity`, `product_id`) VALUES ('5', '#9b197a', '0', '11', '3');
INSERT INTO `yarn_shop_data`.`product_detail` (`id`, `color`, `is_delete`, `quantity`, `product_id`) VALUES ('6', '#f1e9db', '0', '3', '3');

-- nhập dữ liệu customer type 
INSERT INTO `yarn_shop_data`.`customer_type` (`id`, `name`) VALUES ('1', 'Member');
-- nhập dữ liệu cho customer 
INSERT INTO `yarn_shop_data`.`customer` (`id`, `address_detail`, `city`, `country`, `date_of_birth`, `district`, `email`, `first_name`, `gender`, `id_card`, `last_name`, `phone`, `ward`, `account_id`, `customer_type_id`) VALUES ('1', '123 Phan Châu Trinh', 'Đà Nẵng', 'Việt Nam', '03/08/1997', 'Thanh Khê', 'uyennguyencat@gmail.com', 'Uyên', '1', '123456789', 'Nguyễn', '0368700506', 'Thạc Gián', '1', '1');
-- nhập dữ liệu cho order_detail 
INSERT INTO `yarn_shop_data`.`order_detail` (`id`, `is_delete`, `quantity`, `customer_id`, `product_detail_id`) VALUES ('1', '0', '12', '1', '1');
INSERT INTO `yarn_shop_data`.`order_detail` (`id`, `is_delete`, `quantity`, `customer_id`, `product_detail_id`) VALUES ('2', '0', '1', '1', '2');

-- Nhập dữ liệu image
INSERT INTO `yarn_shop_data`.`image` (`id`, `image_url`, `product_id`) VALUES ('1', 'https://images.garnstudio.com/img/shademap/air/drops-air1.jpg', '1');
INSERT INTO `yarn_shop_data`.`image` (`id`, `image_url`, `product_id`) VALUES ('2', 'https://images.garnstudio.com/img/shademap/alaska/drops-alaska1.jpg', '2');
INSERT INTO `yarn_shop_data`.`image` (`id`, `image_url`, `product_id`) VALUES ('3', 'https://cdn.yarn.com/product_images/berroco-gaia/63c6fd148cb5b0046ad5eb0d/super_zoom.jpg?c=1674242612&_gl=1*1e4lqc9*_ga*MTg3NTkxOTIxOC4xNjc1Nzg3MDUy*_ga_RF7X1NY198*MTY3NTc4NzA1Mi4xLjAuMTY3NTc4NzA1NC41OC4wLjA.', '3');
INSERT INTO `yarn_shop_data`.`image` (`image_url`, `product_id`) VALUES ('https://images.garnstudio.com/img/shademap/air/drops-air2.jpg', '1');
INSERT INTO `yarn_shop_data`.`image` (`image_url`, `product_id`) VALUES ('https://images.garnstudio.com/img/shademap/air/drops-air3.jpg', '1');
INSERT INTO `yarn_shop_data`.`image` (`image_url`, `product_id`) VALUES ('https://images.garnstudio.com/img/shademap/air/drops-air4.jpg', '1');
INSERT INTO `yarn_shop_data`.`image` (`image_url`, `product_id`) VALUES ('https://images.garnstudio.com/img/shademap/air/drops-air5.jpg', '1');


select order_detail.id, 
order_detail.quantity as order_quantity, 
order_detail.customer_id, product_detail.quantity as total_quantity, 
product_detail.color as color, product.name, product.price, product.discount_id, 
image.image_url as image,
discount.discount_description from order_detail 
join product_detail on order_detail.product_detail_id = product_detail.id
join product on product_detail.product_id = product.id
join customer on order_detail.customer_id = customer.id
join `account` on customer.account_id = `account`.id
join discount on product.discount_id = discount.id 
join image on product.id = image.product_id
where `account`.id = 1;

select * from image where product_id = 1 and is_delete = 0;
select customer.* from customer
join account on customer.account_id = account.id
where account.id = 1;