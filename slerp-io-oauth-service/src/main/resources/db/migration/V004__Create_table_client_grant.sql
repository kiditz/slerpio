DROP SEQUENCE IF EXISTS client_grant_seq;
CREATE SEQUENCE client_grant_seq START WITH 1;

CREATE TABLE IF NOT EXISTS client_grant
(
  client_id bigint NOT NULL DEFAULT nextval('client_grant_seq'::regclass),
  grant_name character varying(100) NOT NULL,
  CONSTRAINT client_uq UNIQUE (client_id, grant_name)
)