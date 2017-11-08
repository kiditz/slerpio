
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
INSERT INTO user_profile (user_profile_id, phone_number, fullname, gender, address, authority, latitude, longitude, active, active_at, created_at, update_at) VALUES
(8, '085847452018', 'Ezy Animasi', '-', '-', 'STUDENT', 0, 0, 'Y', '2017-11-05 19:57:47.984', '2017-11-05 19:57:47.984', '2017-11-05 19:57:47.984');

INSERT INTO school_class (school_class_id, name, code, user_profile_id, created_at, update_at, school_id) VALUES 
(3, 'Kelas IPA 1', 'A0FEBC5', 3, '2017-11-04 17:11:00.765', '2017-11-04 17:11:00.765', 1);
INSERT INTO school_class (school_class_id, name, code, user_profile_id, created_at, update_at, school_id) VALUES 
(6, 'Kelas IPA 3', 'A0FEBC6', 3, '2017-11-04 17:11:00.765', '2017-11-04 17:11:00.765', 1);
INSERT INTO school_class (school_class_id, name, code, user_profile_id, created_at, update_at, school_id) VALUES 
(9, 'Kelas IPS 5', 'EFB0269B', 3, '2017-11-04 21:35:30.06', '2017-11-04 21:35:30.06', 1);
INSERT INTO school_class (school_class_id, name, code, user_profile_id, created_at, update_at, school_id) VALUES 
(11, 'Kelas IPA 1', '08AC4CC5', 5, '2017-11-05 14:27:12.305', '2017-11-05 14:27:12.305', 2);


INSERT INTO user_school_at (school_id, user_profile_id) VALUES 
(1, 3);
INSERT INTO user_school_at (school_id, user_profile_id) VALUES
(1, 4);
INSERT INTO user_school_at (school_id, user_profile_id) VALUES
(1, 5);
INSERT INTO user_school_at (school_id, user_profile_id) VALUES
(1, 6);
INSERT INTO user_school_at (school_id, user_profile_id) VALUES
(1, 8);



INSERT INTO task (task_id, title, description, school_class_id, created_at, update_at, user_profile_id, school_id) VALUES 
(1, 'Tugas Matematika', 'Tugas Matmatika wajib di kumpulkan pada hari senin', 3, '2017-11-07 22:08:32.763', '2017-11-07 22:08:32.763', 3, 1);
INSERT INTO task (task_id, title, description, school_class_id, created_at, update_at, user_profile_id, school_id) VALUES
(8, 'Tugas bahasa indonesia', 'Tugas bahasa indonesia', 3, '2017-11-07 23:44:16.167', '2017-11-07 23:44:16.167', 3, 1);


INSERT INTO task_question (task_question_id, question, task_id, answered_key, created_at, update_at) VALUES 
(1, '1 + 1', 1, '2', '2017-11-07 22:08:32.858', '2017-11-07 22:08:32.858');
INSERT INTO task_question (task_question_id, question, task_id, answered_key, created_at, update_at) VALUES 
(2, '2 * 2 + 7 / 2', 1, '10', '2017-11-07 22:08:32.861', '2017-11-07 22:08:32.861');
INSERT INTO task_question (task_question_id, question, task_id, answered_key, created_at, update_at) VALUES 
(3, '1 * 2 = ?', 8, '4', '2017-11-07 23:44:16.253', '2017-11-07 23:44:16.253');
INSERT INTO task_question (task_question_id, question, task_id, answered_key, created_at, update_at) VALUES 
(4, 'apakah rumus persegi', 8, 's x s', '2017-11-07 23:44:16.259', '2017-11-07 23:44:16.259');
INSERT INTO task_question (task_question_id, question, task_id, answered_key, created_at, update_at) VALUES 
(5, 'cobalah bermain &gt; /!*&nbsp;', 8, 'batas waktu', '2017-11-07 23:44:16.261', '2017-11-07 23:44:16.261');


INSERT INTO task_answer (task_answer_id, answer, task_question_id, created_at, update_at) VALUES
(2, '1', 1, '2017-11-07 22:08:32.86', '2017-11-07 22:08:32.86');
INSERT INTO task_answer (task_answer_id, answer, task_question_id, created_at, update_at) VALUES
(3, '2', 1, '2017-11-07 22:08:32.86', '2017-11-07 22:08:32.86');
INSERT INTO task_answer (task_answer_id, answer, task_question_id, created_at, update_at) VALUES
(4, '4', 2, '2017-11-07 22:08:32.861', '2017-11-07 22:08:32.861');
INSERT INTO task_answer (task_answer_id, answer, task_question_id, created_at, update_at) VALUES
(5, '5.5', 2, '2017-11-07 22:08:32.861', '2017-11-07 22:08:32.861');
INSERT INTO task_answer (task_answer_id, answer, task_question_id, created_at, update_at) VALUES
(6, '8', 2, '2017-11-07 22:08:32.861', '2017-11-07 22:08:32.861');
INSERT INTO task_answer (task_answer_id, answer, task_question_id, created_at, update_at) VALUES
(7, '10', 2, '2017-11-07 22:08:32.861', '2017-11-07 22:08:32.861');
INSERT INTO task_answer (task_answer_id, answer, task_question_id, created_at, update_at) VALUES
(9, '3', 3, '2017-11-07 23:44:16.256', '2017-11-07 23:44:16.256');
INSERT INTO task_answer (task_answer_id, answer, task_question_id, created_at, update_at) VALUES
(10, '4', 3, '2017-11-07 23:44:16.256', '2017-11-07 23:44:16.256');
INSERT INTO task_answer (task_answer_id, answer, task_question_id, created_at, update_at) VALUES
(11, '5', 3, '2017-11-07 23:44:16.256', '2017-11-07 23:44:16.256');
INSERT INTO task_answer (task_answer_id, answer, task_question_id, created_at, update_at) VALUES
(12, '2', 3, '2017-11-07 23:44:16.257', '2017-11-07 23:44:16.257');
INSERT INTO task_answer (task_answer_id, answer, task_question_id, created_at, update_at) VALUES
(13, 'p x l', 4, '2017-11-07 23:44:16.259', '2017-11-07 23:44:16.259');
INSERT INTO task_answer (task_answer_id, answer, task_question_id, created_at, update_at) VALUES
(14, 's x s', 4, '2017-11-07 23:44:16.259', '2017-11-07 23:44:16.259');
INSERT INTO task_answer (task_answer_id, answer, task_question_id, created_at, update_at) VALUES
(15, 'p x t', 4, '2017-11-07 23:44:16.259', '2017-11-07 23:44:16.259');
INSERT INTO task_answer (task_answer_id, answer, task_question_id, created_at, update_at) VALUES
(16, 'batas waktu', 5, '2017-11-07 23:44:16.261', '2017-11-07 23:44:16.261');