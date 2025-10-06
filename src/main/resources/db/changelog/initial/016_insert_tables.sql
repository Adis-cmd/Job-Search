-- Вставка пользователей
INSERT INTO users (name, surname, age, email, password, "phoneNumber", avatar, "accountType_id", enabled, language)
VALUES
('Иван', 'Иванов', 30, 'ivan.ivanov@jobseeker.com', '$2a$10$kCmu5DZuEmuyklf9kPEyJ.WT2Kgb6zOp4y7LC4D.oqLkAXmkcnPiu',
 '123-456-7890', 'ivan_avatar.jpg',
 (SELECT id FROM account_type WHERE type LIKE 'APPLICANT' LIMIT 1), true, 'en'),
('Мария', 'Петрова', 40, 'maria.petrova@employer.com', '$2a$10$kRfHTF5z3jRzjlQ2wQzSxu0WfThwcnfK9pO2pZs/.fDojiH9hRHcS',
 '987-654-3210', 'maria_avatar.jpg',
 (SELECT id FROM account_type WHERE type LIKE 'EMPLOYEE' LIMIT 1), true, 'en');

-- Вставка категорий
INSERT INTO category (name)
VALUES
('Старший Бэкенд-разработчик'),
('Младший Бэкенд-разработчик'),
('Старший Фронтенд-разработчик'),
('Младший Фронтенд-разработчик');

-- Вставка вакансий
INSERT INTO vacancy (name, description, "categoryId", salary, "expFrom", "expTo", "isActive", "authorId", "createdDate", "updatedTime")
VALUES
('Старший Бэкенд-разработчик', 'Разработка серверной логики и API',
 (SELECT id FROM category WHERE name = 'Старший Бэкенд-разработчик' LIMIT 1), 60000.00, 5, 10, TRUE,
 (SELECT id FROM users WHERE email = 'maria.petrova@employer.com' LIMIT 1), NOW(), NOW()),
('Младший Фронтенд-разработчик', 'Разработка клиентской части приложений',
 (SELECT id FROM category WHERE name = 'Младший Фронтенд-разработчик' LIMIT 1), 55000.00, 2, 5, TRUE,
 (SELECT id FROM users WHERE email = 'maria.petrova@employer.com' LIMIT 1), NOW(), NOW());

-- Вставка типов контакта
INSERT INTO contact_type (type)
VALUES
('Телефон'),
('Электронная почта'),
('Телеграмм'),
('Facebook'),
('Linked In');

-- Вставка резюме
INSERT INTO resume ("applicantId", name, "categoryId", salary, "isActive", "createdDate", "updateTime")
VALUES
((SELECT id FROM users WHERE name = 'Иван' LIMIT 1), 'Резюме Ивана Иванова (Бэкенд-разработчик)',
 (SELECT id FROM category WHERE name = 'Младший Бэкенд-разработчик' LIMIT 1), 70000.00, TRUE, NOW(), NOW()),
((SELECT id FROM users WHERE name = 'Иван' LIMIT 1), 'Резюме Ивана Иванова (Фронтенд-разработчик)',
 (SELECT id FROM category WHERE name = 'Старший Фронтенд-разработчик' LIMIT 1), 65000.00, TRUE, NOW(), NOW());
