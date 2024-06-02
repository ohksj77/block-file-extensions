create table member_seq (next_val bigint);
insert into member_seq values (3) on duplicate key update next_val = 3;
create table extension (id bigint not null, created_at datetime(6), updated_at datetime(6), member_id bigint not null unique, deleted bit not null comment 'Soft-delete indicator', primary key (id));
create table extension_custom_extensions (extension_id bigint not null, custom_extensions varchar(255));
create table extension_fixed_extensions (extension_id bigint not null, fixed_extensions enum ('BAT','CMD','COM','CPL','EXE','JS','SCR'));
create table extension_seq (next_val bigint);
insert into extension_seq values (1);
create table file (id bigint not null, created_at datetime(6), updated_at datetime(6), member_id bigint not null, name varchar(255) not null, url varchar(255) not null, deleted bit not null comment 'Soft-delete indicator', primary key (id));
create table file_seq (next_val bigint);
insert into file_seq values (1);
create table member (id bigint not null, created_at datetime(6), updated_at datetime(6), login_id varchar(255) not null unique, password varchar(255) not null, deleted bit not null comment 'Soft-delete indicator', primary key (id));
create table member_role (member_id bigint not null, role enum ('ROLE_ADMIN','ROLE_USER'));

alter table extension_custom_extensions add constraint FKjk90wihwfmes7m6dluqkle5af foreign key (extension_id) references extension (id);
alter table extension_fixed_extensions add constraint FKrsm3ntsnjf34da1b0qee7qfio foreign key (extension_id) references extension (id);
alter table member_role add constraint FK34g7epqlcxqloewku3aoqhhmg foreign key (member_id) references member (id);
