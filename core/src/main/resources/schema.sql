drop database if exists `spring-rabbitmq-banking`; -- todo - remove

create database if not exists `spring-rabbitmq-banking`;

use `spring-rabbitmq-banking`;

create table if not exists user(
    id bigint unsigned auto_increment not null,
    first_name varchar(55) not null,
    last_name varchar(55) not null,
    email varchar(255) not null,
    created datetime not null,
    modified datetime not null,
    primary key(id),
    unique(email)
);

create table if not exists account(
    id bigint unsigned auto_increment not null,
    balance decimal(10,2) not null,
    user_id bigint unsigned not null,
    created datetime not null,
    modified datetime not null,
    primary key (id),
    constraint fk_user_account foreign key (user_id) references `user`(id) on update CASCADE
);

create table if not exists currency(
    id smallint unsigned auto_increment not null,
    country varchar(100) not null,
    code varchar(3) not null,
    name varchar(100) not null,
    number varchar(3) not null,
    primary key(id),
    constraint uc_currency unique(country, code, number)
);

create table if not exists debit_transaction(
    id bigint unsigned auto_increment not null,
    amount decimal(10, 2) not null,
    account_id bigint unsigned not null,
    currency_id smallint unsigned not null,
    exchange_rate decimal(14,10) not null,
    created datetime not null,
    modified datetime not null,
    primary key(id),
    constraint fk_currency_debit_transaction foreign key (currency_id) references currency(id)
    on update CASCADE,
    constraint fk_account_debit_transaction foreign key (account_id) references account(id)
    on update CASCADE
);

create table if not exists transfer_transaction(
    id bigint unsigned auto_increment not null,
    amount decimal(10, 2) not null,
    receiver_account_id bigint unsigned not null,
    account_id bigint unsigned not null,
    currency_id smallint unsigned not null,
    exchange_rate decimal(14,10) not null,
    created datetime not null,
    modified datetime not null,
    primary key(id),
    constraint fk_currency_transfer_transaction foreign key (currency_id) references currency(id)
    on update CASCADE,
    constraint fk_account_transfer_transaction foreign key (account_id) references account(id)
    on update CASCADE
);

