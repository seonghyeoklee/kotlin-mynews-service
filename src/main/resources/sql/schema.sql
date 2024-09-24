create table if not exists `my-news`.news
(
    id             bigint auto_increment primary key,
    title          varchar(255) not null,
    link           varchar(255) not null,
    search_keyword varchar(255) not null,
    created_at     datetime(6)  null,
    updated_at     datetime(6)  null
);
