CREATE SEQUENCE school_class_seq START WITH 1;

CREATE TABLE IF NOT EXISTS school_class
(
  school_class_id bigint NOT NULL DEFAULT nextval('school_class_seq'::regclass),
  name character varying(60),
  description text NOT NULL,
  school_id bigint NOT NULL,
  CONSTRAINT school_class_pk PRIMARY KEY (school_class_id),
  CONSTRAINT school_class_school_fk FOREIGN KEY (school_id)
      REFERENCES school (school_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE school_class
  OWNER TO postgres;
