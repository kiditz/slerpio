INSERT INTO school (school_id, name, description, address, latitude, longitude, active, active_at, created_at, update_at) VALUES 
(1, 'SMA 95 JAKARTA', 'SMA 95 JAKARTA', 'Jalan 1 Maret', 0, 0, 'Y', '2017-11-03 15:18:42.294918', '2017-11-03 15:18:42.294918', '2017-11-03 15:18:42.294918');
INSERT INTO school (school_id, name, description, address, latitude, longitude, active, active_at, created_at, update_at) VALUES 
(2, 'SMA 56 JAKARTA', 'SMA 56 JAKARTA', 'Jalan Raya Menceng', 0, 0, 'Y', '2017-11-03 15:19:04.914625', '2017-11-03 15:19:04.914625', '2017-11-03 15:19:04.914625');
INSERT INTO school (school_id, name, description, address, latitude, longitude, active, active_at, created_at, update_at) VALUES 
(3, 'SMPN 190 JAKARTA', 'SMPN 56 JAKARTA', 'Jalan Raya Prepedan', 0, 0, 'Y', '2017-11-03 15:20:04.070163', '2017-11-03 15:20:04.070163', '2017-11-03 15:20:04.070163');

INSERT INTO user_profile (user_profile_id, phone_number, fullname, gender, address, authority, latitude, longitude, active, active_at, created_at, update_at) VALUES 
(3, '087788044374', 'Hidayanti Anatya', '-', '-', 'TEACHER', 0, 0, 'Y', '2017-11-03 22:21:27.088', '2017-11-03 22:21:27.088', '2017-11-03 22:21:27.088');
INSERT INTO user_profile (user_profile_id, phone_number, fullname, gender, address, authority, latitude, longitude, active, active_at, created_at, update_at) VALUES
(4, '085847452017', 'Rifky Aditya Bastara', '-', '-', 'STUDENT', 0, 0, 'Y', '2017-11-04 22:01:34.965', '2017-11-04 22:01:34.965', '2017-11-04 22:01:34.965');
INSERT INTO user_profile (user_profile_id, phone_number, fullname, gender, address, authority, latitude, longitude, active, active_at, created_at, update_at) VALUES
(5, '089652594718', 'Reny Rufaidah', '-', '-', 'STUDENT', 0, 0, 'Y', '2017-11-04 23:02:07.986', '2017-11-04 23:02:07.986', '2017-11-04 23:02:07.986');
INSERT INTO user_profile (user_profile_id, phone_number, fullname, gender, address, authority, latitude, longitude, active, active_at, created_at, update_at) VALUES
(6, '085847452004', 'Syehren Aisyahanti', '-', '-', 'STUDENT', 0, 0, 'Y', '2017-11-05 01:54:32.98', '2017-11-05 01:54:32.98', '2017-11-05 01:54:32.98');
INSERT INTO user_profile (user_profile_id, phone_number, fullname, gender, address, authority, latitude, longitude, active, active_at, created_at, update_at) VALUES
(7, '08887245632', 'Ezy Animi Ahmad', 'P', 'Jakarta', 'STUDENT', 0, 0, 'Y', '2017-11-05 07:36:05.672685', '2017-11-05 07:36:05.672685', '2017-11-05 07:36:05.672685');


INSERT INTO school_class (school_class_id, name, code, user_profile_id, created_at, update_at, school_id) VALUES
(3, 'Kelas IPA 1', 'A0FEBC5', 3, '2017-11-04 17:11:00.765', '2017-11-04 17:11:00.765', 1);
INSERT INTO school_class (school_class_id, name, code, user_profile_id, created_at, update_at, school_id) VALUES
(6, 'Kelas IPA 3', 'A0FEBC6', 3, '2017-11-04 17:11:00.765', '2017-11-04 17:11:00.765', 1);
INSERT INTO school_class (school_class_id, name, code, user_profile_id, created_at, update_at, school_id) VALUES
(9, 'Kelas IPS 5', 'EFB0269B', 3, '2017-11-04 21:35:30.06', '2017-11-04 21:35:30.06', 1);
INSERT INTO school_class (school_class_id, name, code, user_profile_id, created_at, update_at, school_id) VALUES
(11, 'Kelas IPA 1', '08AC4CC5', 5, '2017-11-05 14:27:12.305', '2017-11-05 14:27:12.305', 2);




INSERT INTO class_student (school_class_id, user_profile_id) VALUES 
(3, 4);
INSERT INTO class_student (school_class_id, user_profile_id) VALUES
(3, 5);
INSERT INTO class_student (school_class_id, user_profile_id) VALUES
(3, 6);
INSERT INTO class_student (school_class_id, user_profile_id) VALUES
(6, 7);


 
