INSERT INTO vacancy (name, description, categoryId, salary, expFrom, expTo, isActive, authorId, createdDate, updatedTime)
VALUES
    ('Data Scientist', 'Анализ больших данных и построение ML-моделей',
     (SELECT id FROM category WHERE name = 'Младший Фронтенд-разработчик' LIMIT 1), 75000.00, 3, 7, TRUE,
    (SELECT id FROM users WHERE email = 'maria.petrova@employer.com' LIMIT 1), NOW(), NOW()),

('DevOps Engineer', 'Настройка CI/CD и облачной инфраструктуры',
 (SELECT id FROM category WHERE name = 'Младший Бэкенд-разработчик' LIMIT 1), 80000.00, 4, 8, TRUE,
 (SELECT id FROM users WHERE email = 'maria.petrova@employer.com' LIMIT 1), NOW(), NOW()),

('QA Automation Engineer', 'Разработка автотестов на Java/Python',
 (SELECT id FROM category WHERE name = 'Младший Фронтенд-разработчик' LIMIT 1), 65000.00, 2, 5, TRUE,
 (SELECT id FROM users WHERE email = 'maria.petrova@employer.com' LIMIT 1), NOW(), NOW()),

('Product Manager', 'Управление продуктом и разработкой',
 (SELECT id FROM category WHERE name = 'Старший Фронтенд-разработчик' LIMIT 1), 90000.00, 5, 10, TRUE,
 (SELECT id FROM users WHERE email = 'maria.petrova@employer.com' LIMIT 1), NOW(), NOW()),

('UX/UI Designer', 'Создание интерфейсов и пользовательских сценариев',
 (SELECT id FROM category WHERE name = 'Младший Фронтенд-разработчик' LIMIT 1), 70000.00, 3, 6, TRUE,
 (SELECT id FROM users WHERE email = 'maria.petrova@employer.com' LIMIT 1), NOW(), NOW()),

('Android Developer', 'Разработка мобильных приложений под Android',
 (SELECT id FROM category WHERE name = 'Старший Фронтенд-разработчик' LIMIT 1), 85000.00, 4, 7, TRUE,
 (SELECT id FROM users WHERE email = 'maria.petrova@employer.com' LIMIT 1), NOW(), NOW()),

('iOS Developer', 'Разработка приложений для экосистемы Apple',
 (SELECT id FROM category WHERE name = 'Старший Фронтенд-разработчик' LIMIT 1), 88000.00, 4, 7, TRUE,
 (SELECT id FROM users WHERE email = 'maria.petrova@employer.com' LIMIT 1), NOW(), NOW()),

('Security Engineer', 'Анализ уязвимостей и защита систем',
 (SELECT id FROM category WHERE name = 'Младший Бэкенд-разработчик' LIMIT 1), 95000.00, 5, 9, TRUE,
 (SELECT id FROM users WHERE email = 'maria.petrova@employer.com' LIMIT 1), NOW(), NOW()),

('Technical Writer', 'Создание технической документации',
 (SELECT id FROM category WHERE name = 'Младший Бэкенд-разработчик' LIMIT 1), 55000.00, 2, 5, TRUE,
 (SELECT id FROM users WHERE email = 'maria.petrova@employer.com' LIMIT 1), NOW(), NOW()),

('Database Administrator', 'Оптимизация и поддержка БД',
 (SELECT id FROM category WHERE name = 'Старший Фронтенд-разработчик' LIMIT 1), 78000.00, 4, 8, TRUE,
 (SELECT id FROM users WHERE email = 'maria.petrova@employer.com' LIMIT 1), NOW(), NOW()),

('Fullstack Developer', 'Разработка клиентской и серверной частей',
 (SELECT id FROM category WHERE name = 'Младший Бэкенд-разработчик' LIMIT 1), 92000.00, 5, 8, TRUE,
 (SELECT id FROM users WHERE email = 'maria.petrova@employer.com' LIMIT 1), NOW(), NOW()),

('Cloud Architect', 'Проектирование облачных решений',
 (SELECT id FROM category WHERE name = 'Старший Фронтенд-разработчик' LIMIT 1), 110000.00, 7, 12, TRUE,
 (SELECT id FROM users WHERE email = 'maria.petrova@employer.com' LIMIT 1), NOW(), NOW()),

('HR IT Recruiter', 'Подбор IT-специалистов',
 (SELECT id FROM category WHERE name = 'Старший Фронтенд-разработчик' LIMIT 1), 60000.00, 2, 5, TRUE,
 (SELECT id FROM users WHERE email = 'maria.petrova@employer.com' LIMIT 1), NOW(), NOW());


INSERT INTO resume (applicantId, name, categoryId, salary, isActive, createdDate, updateTime)
VALUES
    ((SELECT id FROM users WHERE name = 'Иван' LIMIT 1), 'Резюме Марии Петровой (Data Scientist)',
    (SELECT id FROM category WHERE name = 'Старший Фронтенд-разработчик' LIMIT 1), 85000.00, TRUE, NOW(), NOW()),

((SELECT id FROM users WHERE name = 'Иван' LIMIT 1), 'Резюме Алексея Смирнова (DevOps Engineer)',
 (SELECT id FROM category WHERE name = 'Старший Фронтенд-разработчик' LIMIT 1), 95000.00, TRUE, NOW(), NOW()),

((SELECT id FROM users WHERE name = 'Иван' LIMIT 1), 'Резюме Елены Козловой (QA Automation)',
 (SELECT id FROM category WHERE name = 'Младший Бэкенд-разработчик' LIMIT 1), 75000.00, TRUE, NOW(), NOW()),

((SELECT id FROM users WHERE name = 'Иван' LIMIT 1), 'Резюме Дмитрия Федорова (Product Manager)',
 (SELECT id FROM category WHERE name = 'Младший Бэкенд-разработчик' LIMIT 1), 110000.00, TRUE, NOW(), NOW()),

((SELECT id FROM users WHERE name = 'Иван' LIMIT 1), 'Резюме Анны Васнецовой (UX/UI Designer)',
 (SELECT id FROM category WHERE name = 'Старший Фронтенд-разработчик' LIMIT 1), 80000.00, TRUE, NOW(), NOW()),

((SELECT id FROM users WHERE name = 'Иван' LIMIT 1), 'Резюме Сергея Попова (Android Developer)',
 (SELECT id FROM category WHERE name = 'Старший Фронтенд-разработчик' LIMIT 1), 90000.00, TRUE, NOW(), NOW()),

((SELECT id FROM users WHERE name = 'Иван' LIMIT 1), 'Резюме Ольги Соколовой (iOS Developer)',
 (SELECT id FROM category WHERE name = 'Младший Фронтенд-разработчик' LIMIT 1), 92000.00, TRUE, NOW(), NOW()),

((SELECT id FROM users WHERE name = 'Иван' LIMIT 1), 'Резюме Артема Волкова (Security Engineer)',
 (SELECT id FROM category WHERE name = 'Младший Фронтенд-разработчик' LIMIT 1), 105000.00, TRUE, NOW(), NOW()),

((SELECT id FROM users WHERE name = 'Иван' LIMIT 1), 'Резюме Натальи Беловой (Technical Writer)',
 (SELECT id FROM category WHERE name = 'Младший Бэкенд-разработчик' LIMIT 1), 65000.00, TRUE, NOW(), NOW()),

((SELECT id FROM users WHERE name = 'Иван' LIMIT 1), 'Резюме Павла Громова (Database Administrator)',
 (SELECT id FROM category WHERE name = 'Младший Фронтенд-разработчик' LIMIT 1), 88000.00, TRUE, NOW(), NOW()),

((SELECT id FROM users WHERE name = 'Иван' LIMIT 1), 'Резюме Виктории Орловой (Fullstack Developer)',
 (SELECT id FROM category WHERE name = 'Младший Фронтенд-разработчик' LIMIT 1), 115000.00, TRUE, NOW(), NOW()),

((SELECT id FROM users WHERE name = 'Иван' LIMIT 1), 'Резюме Андрея Никитина (Cloud Architect)',
 (SELECT id FROM category WHERE name = 'Младший Бэкенд-разработчик' LIMIT 1), 130000.00, TRUE, NOW(), NOW()),

((SELECT id FROM users WHERE name = 'Иван' LIMIT 1), 'Резюме Татьяны Морозовой (HR IT Recruiter)',
 (SELECT id FROM category WHERE name = 'Младший Бэкенд-разработчик' LIMIT 1), 70000.00, TRUE, NOW(), NOW());