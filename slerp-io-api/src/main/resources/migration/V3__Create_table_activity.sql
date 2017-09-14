CREATE SEQUENCE activity_seq START WITH 1;

CREATE TABLE activity
(
  activity_id bigint NOT NULL DEFAULT nextval('activity_seq'::regclass),
  title character varying(60),
  content text,
  last_update timestamp without time zone NOT NULL DEFAULT now(),
  activity_type character varying(20),
  school_id bigint,
  CONSTRAINT activity_pk PRIMARY KEY (activity_id),
  CONSTRAINT activity_school_fk FOREIGN KEY (school_id)
      REFERENCES school (school_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE activity
  OWNER TO postgres;


