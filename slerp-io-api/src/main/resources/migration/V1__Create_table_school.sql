CREATE SEQUENCE school_seq START WITH 1;

CREATE TABLE IF NOT EXISTS school
(
  school_id bigint NOT NULL DEFAULT nextval('school_seq'::regclass),
  name character varying(60) NOT NULL,
  description text NOT NULL,
  latitude numeric,
  longitude numeric,
  address text NOT NULL,
  postal_code character varying(11),
  build_at date,
  CONSTRAINT schoo_pk PRIMARY KEY (school_id)
);
