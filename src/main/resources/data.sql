INSERT INTO role (id, role_name, description) VALUES (1001, 'ROOT_USER', 'Root User - Has permission to perform admin tasks');
INSERT INTO role (id, role_name, description) VALUES (1002, 'ADMIN_USER', 'Admin User - Has permission to admin organisation');
INSERT INTO role (id, role_name, description) VALUES (1003, 'STANDARD_USER', 'Standard User - Has no admin rights');

-- USER
-- b-encrypted password
INSERT INTO user (id, password, username) VALUES (1001, '$2y$12$AyfKqP6YvubgFVHp0AGzs.VmrDaoIja3rUWncFkpLBSERGqAY94Vm', 'ldeutsch@optusnet.com.au');
INSERT INTO user (id, password, username) VALUES (1002, '$2y$12$AyfKqP6YvubgFVHp0AGzs.VmrDaoIja3rUWncFkpLBSERGqAY94Vm', 'frank@frankthring.org');
INSERT INTO user (id, password, username) VALUES (1003, '$2y$12$AyfKqP6YvubgFVHp0AGzs.VmrDaoIja3rUWncFkpLBSERGqAY94Vm', 'mike@frankthring.org');
INSERT INTO user (id, password, username) VALUES (1004, '$2y$12$AyfKqP6YvubgFVHp0AGzs.VmrDaoIja3rUWncFkpLBSERGqAY94Vm', 'rmjcoxon@gmail.com');

INSERT INTO user_role(user_id, role_id) VALUES(1001,1001);
INSERT INTO user_role(user_id, role_id) VALUES(1002,1002);
INSERT INTO user_role(user_id, role_id) VALUES(1003,1003);
INSERT INTO user_role(user_id, role_id) VALUES(1004,1001);

-- using big primary key numbers because it looks like autogeneration starts at index 1 for every test producing primary key violations.