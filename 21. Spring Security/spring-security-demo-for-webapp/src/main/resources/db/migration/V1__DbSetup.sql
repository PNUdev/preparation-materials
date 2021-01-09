create table if not exists user
(
  id       bigint       not null auto_increment,
  username varchar(255) not null,
  password varchar(255) not null,
  role     varchar(255) not null,
  active   bit          not null,
  primary key (id)
);