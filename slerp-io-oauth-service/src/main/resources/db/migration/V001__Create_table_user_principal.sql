DROP SEQUENCE IF EXISTS user_principal_seq;
CREATE SEQUENCE user_principal_seq START WITH 1;

CREATE TABLE IF NOT EXISTS user_principal
(
  user_id bigint NOT NULL DEFAULT nextval('user_principal_seq'::regclass),
  username character varying(60) NOT NULL,
  hashed_password bytea NOT NULL,
  enabled boolean NOT NULL DEFAULT false,
  email character varying(200) NOT NULL,
  phone_number character varying(15),
  account_non_expired boolean NOT NULL DEFAULT false,
  account_non_locked boolean NOT NULL DEFAULT false,
  credentials_non_expired boolean NOT NULL DEFAULT false,
  CONSTRAINT user_principal_pkey PRIMARY KEY (user_id),
  CONSTRAINT uq_user UNIQUE (username)
);