DROP SEQUENCE IF EXISTS client_redirect_seq;
CREATE SEQUENCE client_redirect_seq START WITH 1;

CREATE TABLE IF NOT EXISTS client_redirect
(
  client_id bigint NOT NULL DEFAULT nextval('client_redirect_seq'::regclass),
  redirect_uri text NOT NULL,
  CONSTRAINT uq_redirect UNIQUE (client_id, redirect_uri)
)