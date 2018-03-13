insert into users (user_id, username, password, email, first_name, last_name, active) values (nextval('users_user_id_seq'), 'stason1o', 'qwerty123', 'stanislav.bogdanschi@endava.com', 'stas', 'bogdanschi', true);
insert into users (user_id, username, password, email, first_name, last_name, active) values (nextval('users_user_id_seq'), 'MyUser', 'password', 'email@domain.com', 'firstName', 'lastName', true);
insert into users (user_id, username, password, email, first_name, last_name, active) values (nextval('users_user_id_seq'), 'Stason1o', '$2a$10$PwLXCu//RdcMvqcb62JIfexzVPRSjOnvuSc/Pu.d5ECWxSxHd7PHy', 'stas1@mail.ru', 'stas', 'bogdanschi', true);
insert into users (user_id, username, password, email, first_name, last_name, active) values (nextval('users_user_id_seq'), 'Testusername', '$2a$10$27Wy6Ct.SXwFIsCdW8H7juxkFWgYI2.fxjJU7V4d0b9GoTVEZlJBm', 'test@mail.ru', 'Test', 'TestName', true);
insert into users (user_id, username, password, email, first_name, last_name, active) values (nextval('users_user_id_seq'), 'testUser', '$2a$10$HxjCzjGcpLINUMj5y92M5.UJuW8dL3S1AiUI2oWGbc2SyfQGYAjAu', 'test1@mail.ru', 'stas', 'bogdanschi', true);
insert into users (user_id, username, password, email, first_name, last_name, active) values (nextval('users_user_id_seq'), 'dcrasnojon', '$2a$10$aNli.aQVouf69SRQSzGWceQyngZlyZnsTZHR/WNPNZq7C12sYv5M2', 'dumitrita.crasnojon@endava.com', 'Dumitrita', 'Crasnojon', true);
insert into users (user_id, username, password, email, first_name, last_name, active) values (nextval('users_user_id_seq'), 'newnew', '$2a$10$Br1/UtG0U6pIZqI0YQ4zfOCsmS7IERmzuOnziwVnrNdY.BdOBwaIe', 'new@email.com', 'New', 'NewLAstNAme', true);
insert into users (user_id, username, password, email, first_name, last_name, active) values (nextval('users_user_id_seq'), 'dgurjui', '$2a$10$tror.4eynVV9DkTHc.HZduhaJ4O1AOjDfaV2U7n2Drb3h58sn1A/O', 'DimaGurjui@mail.ru', 'Dima', 'Gurjui', true);
insert into users (user_id, username, password, email, first_name, last_name, active) values (nextval('users_user_id_seq'), 'dgurjui1', '$2a$10$XNEx1yziF8CLYw6T6M.OBep8HVm10Qlkhs38DHDgw9eaWDbuu7Sia', 'DimaGurjui1@mail.ru', 'Dima', 'Gurjui', true);

insert into roles (role_id, role_type) values (1, 'ADMIN');
insert into roles (role_id, role_type) values (2, 'USER');

insert into user_roles(id_user, id_role) values (1, 1);
insert into user_roles(id_user, id_role) values (1, 2);
insert into user_roles(id_user, id_role) values (3, 1);
insert into user_roles(id_user, id_role) values (3, 2);
insert into user_roles(id_user, id_role) values (2, 2);
insert into user_roles(id_user, id_role) values (2, 1);
insert into user_roles(id_user, id_role) values (4, 2);
insert into user_roles(id_user, id_role) values (5, 2);
insert into user_roles(id_user, id_role) values (6, 2);
insert into user_roles(id_user, id_role) values (7, 2);
insert into user_roles(id_user, id_role) values (8, 2);
insert into user_roles(id_user, id_role) values (9, 2);