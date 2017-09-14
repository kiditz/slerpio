/*
TODO : Handle Sql File*/

INSERT INTO user_principal (user_id, username, hashed_password, account_non_expired, account_non_locked, credentials_non_expired, enabled, email) VALUES 
(3, 'kiditz', '0xff00', true,  true,  true, true, 'kiditzbastara@gmail.com');

INSERT INTO user_authority (authority_id, authority, user_id) VALUES (1, 'TEACHER', 3);
INSERT INTO user_authority (authority_id, authority, user_id) VALUES (2, 'STUDENT', 3);