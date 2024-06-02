insert into member (id, created_at, deleted, updated_at, login_id, password)
values (1, '2024-05-31 00:00:00.000000', false, null, 'user1',
        '$2a$10$H72/TQEEhkVs1RLg03wl9OT31HSVGcYMs/ftn2u6oRS4iwJCRfZHu'),
       (2, '2024-05-31 00:00:00.000000', false, null, 'user2',
        '$2a$10$T9EPJaXl7UCxp2bFm5w.6edudus2e6LVu/DD1R6qeS.c7q7wHOjHu');

insert into member_role (member_id, role)
values (1, 'ROLE_USER'),
       (2, 'ROLE_USER');
