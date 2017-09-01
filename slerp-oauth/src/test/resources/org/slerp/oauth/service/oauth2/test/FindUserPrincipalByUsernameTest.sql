/*
TODO : Handle Sql File*/

INSERT INTO user_principal (user_id, username, hashed_password, account_non_expired, account_non_locked, credentials_non_expired) VALUES 
(3, 'kiditz', 'rioters7', true, true, true);

INSERT INTO user_authority (authority_id, authority, user_id) VALUES 
(1, 'TEACHER', 3),
(2, 'STUDENT', 3);