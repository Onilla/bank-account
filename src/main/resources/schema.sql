DROP TABLE IF EXISTS tb_users;
DROP TABLE IF EXISTS tb_cards;

create table IF NOT EXISTS tb_users (
                          id uuid default gen_random_uuid()primary key,
                          username varchar(255) not null,
                          surname varchar(255) not null,
                          email varchar(255) not null unique
);

create table IF NOT EXISTS tb_cards (
                          card_id bigserial primary key,
                          card_number varchar(255) not null,
                          payment_system varchar(255) not null,
                          amount decimal not null,
                          expiration_date timestamp not null ,
                          holder_name varchar(255) not null

);


