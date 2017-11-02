DROP TABLE IF EXISTS profile_has_school;
CREATE TABLE profile_has_school (
  school_id bigint,
  profile_id bigint,
  CONSTRAINT profile_has_school_pkey PRIMARY KEY (school_id, profile_id),
  CONSTRAINT fk_user_profile_school_id FOREIGN KEY (school_id)
        REFERENCES  school (school_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
        
  CONSTRAINT fk_user_profile_profile_id FOREIGN KEY (profile_id)
        REFERENCES  user_profile (profile_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION  
);