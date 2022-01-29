itemcreate schema if not exists orderservice;
use orderservice;

create table if not exists orders (
    order_id int(11) not null auto_increment primary key,
    item_id int(11),
    discount_total decimal(5,2) not null,
    total decimal(5,2) not null
);

create table if not exists item (
    item_id int(11) not null auto_increment primary key,
    item_name varchar(30) not null,
    unit_price decimal(5,2) not null,
    quantity int(11) not null
);