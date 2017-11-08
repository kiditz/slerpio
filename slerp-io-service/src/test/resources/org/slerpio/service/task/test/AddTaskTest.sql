INSERT INTO school (school_id, name, address, longitude, latitude, active, active_at, created_at, description, update_at) VALUES 
(1, 'SMAN 95 JAKARTA', 'JAKARTA BARAT', 0, 0, 'Y',  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,'SMAN 95 JAKARTA', CURRENT_TIMESTAMP);

INSERT INTO user_profile
(user_profile_id, phone_number, fullname, gender, address, authority, latitude, longitude, active, active_at, created_at, update_at)
VALUES (1,'087788044374', 'Rifky aditya bastara', 'L', 'JAKARTA', 'TEACHER', 0, 0, 'Y', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO school_class
(school_class_id, name, code, user_profile_id, created_at, update_at)
	VALUES (1,'Kelas IPS 5', '12IPS5',1, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
