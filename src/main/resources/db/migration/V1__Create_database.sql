-- DROP SCHEMA IF EXISTS flyway_schema CASCADE;

DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

CREATE SEQUENCE users_user_id_seq START 1;

CREATE TABLE users
(
  user_id BIGINT DEFAULT nextval('users_user_id_seq'),
  username character varying(32) NOT NULL,
  password character varying(60) NOT NULL,
  email character varying(50) NOT NULL,
  first_name character varying(32) NOT NULL,
  last_name character varying(32) NOT NULL,
  active boolean DEFAULT true,
  CONSTRAINT user_pkey PRIMARY KEY (user_id),
  CONSTRAINT user_email_key UNIQUE (email),
  CONSTRAINT user_username_key UNIQUE (username)
);

CREATE TABLE roles
(
  role_id integer NOT NULL,
  role_type character varying(15) NOT NULL,
  CONSTRAINT role_pkey PRIMARY KEY (role_id)
);

CREATE TABLE user_roles
(
  id_user integer NOT NULL,
  id_role integer NOT NULL,
  CONSTRAINT user_role_id_role_fkey FOREIGN KEY (id_role)
  REFERENCES roles (role_id) MATCH SIMPLE
  ON UPDATE CASCADE
  ON DELETE CASCADE,
  CONSTRAINT user_role_id_user_fkey FOREIGN KEY (id_user)
  REFERENCES users (user_id) MATCH SIMPLE
  ON UPDATE CASCADE
  ON DELETE CASCADE
);