INSERT INTO ekaoqin.authority (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO ekaoqin.authority (ID, NAME) VALUES (2, 'ROLE_ADMIN');

INSERT INTO ekaoqin.user (ID, USER_NAME, PASSWORD, FIRSTNAME, LASTNAME, FULL_NAME, EMAIL, IS_ENABLED, LASTPASSWORDRESETDATE) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin','admin', 'admin@admin.com', 1, '2016-01-01');
INSERT INTO ekaoqin.user (ID, USER_NAME, PASSWORD, FIRSTNAME, LASTNAME, FULL_NAME, EMAIL, IS_ENABLED, LASTPASSWORDRESETDATE) VALUES (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user','user', 'enabled@user.com', 1, '2016-01-01');

INSERT INTO ekaoqin.user_authority (USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO ekaoqin.user_authority (USER_ID, AUTHORITY_ID) VALUES (2, 1);
