DROP SEQUENCE IF EXISTS user_authority_seq;
CREATE SEQUENCE user_authority_seq START WITH 1;

CREATE TABLE IF NOT EXISTS user_authority
(
  authority_id bigint NOT NULL DEFAULT nextval('user_authority_seq'::regclass),
  authority character varying(60) NOT NULL,
  user_id bigint NOT NULL,
  CONSTRAINT user_authority_pkey PRIMARY KEY (authority_id),
  CONSTRAINT fk_user_principal FOREIGN KEY (user_id)
      REFERENCES user_principal (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);