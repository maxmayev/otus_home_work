create table USER_MESSAGES
(
    id         bigserial    not null primary key,
    name       varchar(45),
    auto_number varchar(45),
    message_text   varchar(255),
    good_or_bad  int,
    destination         varchar(45)
);

create table USER_AUTH
(
    id       bigserial    not null primary key,
    username varchar(45),
    password varchar(255)
);