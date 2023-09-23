create table if not exists flat
(
    id             bigint auto_increment
        primary key,
    number          int         not null,
    rooms           int         not null,
    square          int          not null,
    house          bigint       not null
);