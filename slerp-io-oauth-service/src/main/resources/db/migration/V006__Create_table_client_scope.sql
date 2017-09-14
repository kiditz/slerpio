DROP SEQUENCE IF EXISTS client_scope_seq;
CREATE SEQUENCE client_scope_seq START WITH 1;

CREATE TABLE client_scope
(
  client_id bigint NOT NULL DEFAULT nextval('client_scope_seq'::regclass),
  scope text NOT NULL  
)