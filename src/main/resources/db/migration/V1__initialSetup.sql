SET search_path = postgres;
SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

CREATE EXTENSION IF NOT EXISTS plpgsql;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

SET default_tablespace = '';

SET default_with_oids = false;

CREATE TABLE app_role (
    description character varying(255),
    role_name character varying(255),
    role_id uuid DEFAULT uuid_generate_v4() NOT NULL
);

CREATE SEQUENCE app_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE app_user (
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    user_id uuid DEFAULT uuid_generate_v4() NOT NULL
);

CREATE SEQUENCE app_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE user_role (
    role_id uuid,
    user_id uuid
);


ALTER TABLE ONLY app_role
    ADD CONSTRAINT app_role_pkey PRIMARY KEY (role_id);

ALTER TABLE ONLY app_user
    ADD CONSTRAINT app_user_pkey PRIMARY KEY (user_id);

CREATE INDEX fki_user_role_role_id_fkey ON user_role USING btree (role_id);

CREATE INDEX fki_user_role_user_id_fkey ON user_role USING btree (user_id);

ALTER TABLE ONLY user_role
    ADD CONSTRAINT user_role_role_id_fkey FOREIGN KEY (role_id) REFERENCES app_role(role_id);

ALTER TABLE ONLY user_role
    ADD CONSTRAINT user_role_user_id_fkey FOREIGN KEY (user_id) REFERENCES app_user(user_id);

