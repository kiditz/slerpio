CREATE SEQUENCE teacher_seq START WITH 1;

-- Table: teacher

-- DROP TABLE teacher;

CREATE TABLE IF NOT EXISTS teacher
(
  teacher_id bigint NOT NULL DEFAULT nextval('teacher_seq'::regclass),
  firstname character varying(60) NOT NULL,
  lastname character varying(60) NOT NULL,
  username character varying(60) NOT NULL,
  day_of_birth date,
  address text,
  postal_code character varying(11),
  active boolean,
  school_id bigint NOT NULL,
  CONSTRAINT teacher_pk PRIMARY KEY (teacher_id),
  CONSTRAINT teacher_fk FOREIGN KEY (school_id)
      REFERENCES school (school_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT teacher_username_uk UNIQUE (username)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE teacher
  OWNER TO postgres;
