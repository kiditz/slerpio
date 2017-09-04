CREATE SEQUENCE student_seq START WITH 1;

CREATE TABLE IF NOT EXISTS student
(
  student_id bigint NOT NULL DEFAULT nextval('student_seq'::regclass),
  firstname character varying(60) NOT NULL,
  lastname character varying(60) NOT NULL,
  username character varying(60) NOT NULL,
  start_date date,
  end_date date,
  day_of_birth date,
  parent_name character varying(60),
  address text,
  postal_code character varying(11),
  active boolean,
  school_id bigint NOT NULL,
  class_id bigint,
  CONSTRAINT student_pk PRIMARY KEY (student_id),
  CONSTRAINT student_class_fk FOREIGN KEY (class_id)
      REFERENCES school_class (school_class_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT student_school_fk FOREIGN KEY (school_id)
      REFERENCES school (school_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT student_username_uk UNIQUE (username)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE student
  OWNER TO postgres;
