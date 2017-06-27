INSERT INTO `role`(`role_name`) VALUES
  ('ADMIN'),
  ('COMPANY_OWNER'),
  ('COMPANY_EMPLOYER');

INSERT INTO `company`(`name`, `email`, `address`) VALUES
  ('Company1', 'jobs@epam.com', 'Kyiv, Fiskyltyry 23'),
  ('Company2', 'jobs@luxoft.com', 'Kyiv, Radishcheva 10/14'),
  ('Seven', 'jobs@sevencol.com', 'Lviv, Naykova str');


INSERT INTO `user` (`first_name`, `last_name`, `email`, `phone`, `password`, `user_role_id`, `user_company_id`) VALUES
  ('Ruslan', 'Admin', 'admin@gmail.com', '911', '$2a$10$mlhEEN5YCSvyCqndcPG5d.dq0RfPiX00.oPIYERVjoN.oCqDMkrgO', 1, null),
  ('Doctor', 'Aphra', 'owner@company1.com', '03', '$2a$10$mlhEEN5YCSvyCqndcPG5d.dq0RfPiX00.oPIYERVjoN.oCqDMkrgO', 2, 1),
  ('Han', 'Solo', 'user1@company1.com', '+112', '$2a$10$mlhEEN5YCSvyCqndcPG5d.dq0RfPiX00.oPIYERVjoN.oCqDMkrgO', 3, 1),
  ('Peter', 'Pen', 'user2@company1.com', '+232', '$2a$10$mlhEEN5YCSvyCqndcPG5d.dq0RfPiX00.oPIYERVjoN.oCqDMkrgO', 3, 1),
  ('Luke', 'Skywalker', 'owner@company2.com', 'luke.sky', '$2a$10$mlhEEN5YCSvyCqndcPG5d.dq0RfPiX00.oPIYERVjoN.oCqDMkrgO', 2, 2),
  ('Gyy', 'Pol', 'user1@company2.com', '+11das2', '$2a$10$mlhEEN5YCSvyCqndcPG5d.dq0RfPiX00.oPIYERVjoN.oCqDMkrgO', 3, 2),
  ('Austin', 'Texas', 'user2@company2.com', '+232das', '$2a$10$mlhEEN5YCSvyCqndcPG5d.dq0RfPiX00.oPIYERVjoN.oCqDMkrgO', 3, 2);

