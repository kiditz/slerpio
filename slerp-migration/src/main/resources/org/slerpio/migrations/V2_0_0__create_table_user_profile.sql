DROP SEQUENCE IF EXISTS user_profile_seq;
CREATE SEQUENCE user_profile_seq START WITH 1;
DROP TABLE IF EXISTS user_profile;
CREATE TABLE user_profile (
  profile_id bigint not null default nextval('user_profile_seq'),
  user_id bigint not null,  
  fullname varchar (100) not null,    
  gender varchar(1) not null,
  address text,
  latitude numeric,
  longitude numeric,
  active varchar (1) not null,
  active_at timestamp not null,    
  created_at timestamp not null,
  update_at timestamp,  
  CONSTRAINT profile_pkey PRIMARY KEY (profile_id),
  CONSTRAINT uq_user_id UNIQUE (user_id)  
  
);