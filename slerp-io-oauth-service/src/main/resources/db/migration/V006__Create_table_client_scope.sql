DROP SEQUENCE IF EXISTS client_scope_seq;
CREATE SEQUENCE client_scope_seq START WITH 1;

CREATE TABLE IF NOT EXISTS client_redirect
(
  client_id bigint NOT NULL DEFAULT nextval('client_grant_seq'::regclass),
  scope text NOT NULL  
)