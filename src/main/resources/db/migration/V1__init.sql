create table member (id bigint not null, created_at datetime(6), updated_at datetime(6), login_id varchar(255) not null unique, password varchar(255) not null, role enum ('ADMIN','USER') not null, deleted bit not null comment 'Soft-delete indicator', primary key (id));
create table member_seq (next_val bigint);
insert into member_seq values (3) on duplicate key update next_val = 3;
