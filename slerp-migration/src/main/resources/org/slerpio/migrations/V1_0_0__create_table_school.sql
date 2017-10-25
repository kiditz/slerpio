DROP SEQUENCE IF EXISTS school_seq;
CREATE SEQUENCE school_seq START WITH 1;
DROP TABLE IF EXISTS school;
CREATE TABLE school (
  school_id bigint not null default nextval('school_seq'),
  name varchar (100) not null,
  description text,
  address varchar (20),
  latitude numeric,
  longitude numeric,
  active varchar (1) not null,
  active_at timestamp not null,
  created_at timestamp not null,  
  update_at timestamp,  
  CONSTRAINT school_pkey PRIMARY KEY (school_id)  
);