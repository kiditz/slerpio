INSERT INTO public.user_profile(
	user_profile_id, phone_number, fullname, gender, address, authority, latitude, longitude, active, active_at, created_at, update_at)
	VALUES (3, '085847452017', 'Hidayanti anatya bastari', 'L', '-', 'TEACHER', 0, 0, 'Y', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO public.school_class(
	school_class_id, name, code, user_profile_id, created_at, update_at)
	VALUES (1,'Kelas IPS 5', '12IPS5',3, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);

INSERT INTO public.user_profile(
	user_profile_id, phone_number, fullname, gender, address, authority, latitude, longitude, active, active_at, created_at, update_at)
	VALUES (1, '087788044374', 'Rifky aditya bastara', 'L', '-', 'STUDENT', 0, 0, 'Y', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);