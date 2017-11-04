INSERT INTO public.user_profile(
	user_profile_id, phone_number, fullname, gender, address, authority, latitude, longitude, active, active_at, created_at, update_at)
	VALUES (1,'087788044374', 'Rifky aditya bastara', 'L', 'JAKARTA', 'TEACHER', 0, 0, 'Y', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO public.school_class(
	school_class_id, name, code, user_profile_id, created_at, update_at)
	VALUES (1,'Kelas IPS 5', '12IPS5',1, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);

INSERT INTO public.school_class(
	school_class_id, name, code, user_profile_id, created_at, update_at)
	VALUES (2,'Kelas IPA 2', '12IPA2',1, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);