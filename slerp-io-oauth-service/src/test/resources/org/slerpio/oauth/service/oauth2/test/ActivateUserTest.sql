
INSERT INTO user_principal (user_id, username, hashed_password, account_non_expired, account_non_locked, credentials_non_expired, enabled, email, activation_code) VALUES 
(2, 'kiditz', '0x00ff', true,  true,  true, false, 'kiditzbastara@gmail.com', 'psAux257n');

INSERT INTO user_authority (authority_id, authority, user_id) VALUES 
(2, 'TEACHER', 2);