DROP SEQUENCE IF EXISTS client_seq;
CREATE SEQUENCE client_seq START WITH 1;

CREATE TABLE IF NOT EXISTS client
(
  id bigint NOT NULL DEFAULT nextval('client_seq'::regclass),
  client_id character varying(60) NOT NULL,
  client_secret character varying(60) NOT NULL,
  CONSTRAINT service_client_pkey PRIMARY KEY (id)
)