INSERT INTO users (name, surname, age, email, password, phonenumber, avatar, "accountType")
VALUES
('Иван', 'Иванов', 30, 'ivan.ivanov@jobseeker.com', 'hashedpassword123', '123-456-7890', 'ivan_avatar.jpg', 'Соискатель'),
('Мария', 'Петрова', 40, 'maria.petrova@employer.com', 'hashedpassword456', '987-654-3210', 'maria_avatar.jpg', 'Работодатель');

INSERT INTO category (name, "parentId")
VALUES
('Бэкенд-разработчик', NULL),
('Фронтенд-разработчик', NULL);

INSERT INTO category (name, "parentId")
VALUES
('Старший Бэкенд-разработчик', (SELECT id FROM category WHERE name = 'Бэкенд-разработчик' LIMIT 1)),
('Младший Бэкенд-разработчик', (SELECT id FROM category WHERE name = 'Бэкенд-разработчик' LIMIT 1)),
('Старший Фронтенд-разработчик', (SELECT id FROM category WHERE name = 'Фронтенд-разработчик' LIMIT 1)),
('Младший Фронтенд-разработчик', (SELECT id FROM category WHERE name = 'Фронтенд-разработчик' LIMIT 1));

INSERT INTO vacancy (name, description, "categoryId", salary, "expFrom", "expTo", "isActive", "authorId", "createdDate", "updatedTime")
VALUES
('Старший Бэкенд-разработчик', 'Разработка серверной логики и API',
 (SELECT id FROM category WHERE name = 'Старший Бэкенд-разработчик' LIMIT 1), 60000.00, 5, 10, TRUE,
 (SELECT id FROM users WHERE email = 'maria.petrova@employer.com' LIMIT 1), NOW(), NOW()),
('Младший Фронтенд-разработчик', 'Разработка клиентской части приложений',
 (SELECT id FROM category WHERE name = 'Младший Фронтенд-разработчик' LIMIT 1), 55000.00, 2, 5, TRUE,
 (SELECT id FROM users WHERE email = 'maria.petrova@employer.com' LIMIT 1), NOW(), NOW());

INSERT INTO contact_type (type)
VALUES ('Телефон'), ('Электронная почта');

INSERT INTO resume ("applicantId", name, "categoryId", salary, "isActive", "createdDate", "updateTime")
VALUES
((SELECT id FROM users WHERE name = 'Иван' LIMIT 1), 'Резюме Ивана Иванова (Бэкенд-разработчик)',
 (SELECT id FROM category WHERE name = 'Младший Бэкенд-разработчик' LIMIT 1), 70000.00, TRUE, NOW(), NOW()),
((SELECT id FROM users WHERE name = 'Иван' LIMIT 1), 'Резюме Ивана Иванова (Фронтенд-разработчик)',
 (SELECT id FROM category WHERE name = 'Старший Фронтенд-разработчик' LIMIT 1), 65000.00, TRUE, NOW(), NOW());

INSERT INTO responded_applicant ("resumeId", "vacancyId", confirmation)
VALUES
((SELECT id FROM resume WHERE name = 'Резюме Ивана Иванова (Бэкенд-разработчик)' LIMIT 1),
 (SELECT id FROM vacancy WHERE name = 'Старший Бэкенд-разработчик' LIMIT 1), TRUE);

INSERT INTO message ("respondedApplicants", content, timestamp)
VALUES
(1, 'Поздравляем! Вы были выбраны на вакансию Бэкенд-разработчика.', NOW());
