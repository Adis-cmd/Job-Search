INSERT INTO responded_applicant (resumeId, vacancyId, confirmation, is_view, IS_VIEW_APPLICANT)
VALUES ((SELECT id FROM resume WHERE name = 'Резюме Ивана Иванова (Бэкенд-разработчик)' LIMIT 1),
       (SELECT id FROM vacancy WHERE name = 'Старший Бэкенд-разработчик' LIMIT 1), TRUE , false, false);

INSERT INTO message (respondedApplicants, content, timestamp)
VALUES (1, 'Поздравляем! Вы были выбраны на вакансию Бэкенд-разработчика.', NOW());