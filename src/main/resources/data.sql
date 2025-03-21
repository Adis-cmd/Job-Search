create table if not exists users
(
    id          BIGINT auto_increment primary key,
    name        varchar(40)  not null,
    surname     varchar(40)  not null,
    age         int          not null,
    email       varchar(100) not null,
    password    varchar(200) not null,
    phoneNumber varchar(40)  not null,
    avatar      varchar(40)  not null,
    accountType varchar(40)  not null
);

INSERT INTO users (name, surname, age, email, password, phoneNumber, avatar, accountType)
VALUES ('Иван', 'Иванов', 30, 'ivan.ivanov@jobseeker.com', 'hashedpassword123', '123-456-7890', 'ivan_avatar.jpg',
        'Соискатель'),
       ('Мария', 'Петрова', 40, 'maria.petrova@employer.com', 'hashedpassword456', '987-654-3210', 'maria_avatar.jpg',
        'Работодатель');


create table if not exists category
(
    id       BIGINT auto_increment primary key,
    name     text not null,
    parentId bigint,
    foreign key (parentId) references category (id)
);

INSERT INTO category (name, parentId)
VALUES ('Бэкенд-разработчик', NULL),
       ('Фронтенд-разработчик', NULL);

INSERT INTO category (name, parentId)
VALUES ('Старший Бэкенд-разработчик', (SELECT id FROM category WHERE name = 'Бэкенд-разработчик' LIMIT 1)),
       ('Младший Бэкенд-разработчик', (SELECT id FROM category WHERE name = 'Бэкенд-разработчик' LIMIT 1)),
       ('Старший Фронтенд-разработчик', (SELECT id FROM category WHERE name = 'Фронтенд-разработчик' LIMIT 1)),
       ('Младший Фронтенд-разработчик', (SELECT id FROM category WHERE name = 'Фронтенд-разработчик' LIMIT 1));

create table if not exists vacancy
(
    id          BIGINT auto_increment primary key,
    name        text      not null,
    description text      not null,
    categoryId  BIGINT    not null,
    salary      double    not null,
    expFrom     int       not null,
    expTo       int       not null,
    isActive    boolean   not null,
    authorId    bigint    not null,
    createdDate timestamp not null,
    updatedTime timestamp not null,
    foreign key (categoryId) references category (id),
    foreign key (authorId) references users (id)
);

INSERT INTO vacancy (name, description, categoryId, salary, expFrom, expTo, isActive, authorId, createdDate,
                     updatedTime)
VALUES ('Старший Бэкенд-разработчик', 'Разработка серверной логики и API',
        (SELECT id FROM category WHERE name = 'Старший Бэкенд-разработчик' LIMIT 1), 60000.00, 5, 10, TRUE,
        (SELECT id FROM users WHERE name = 'Мария' LIMIT 1), NOW(), NOW()),
       ('Младший Фронтенд-разработчик', 'Разработка клиентской части приложений',
        (SELECT id FROM category WHERE name = 'Младший Фронтенд-разработчик' LIMIT 1), 55000.00, 2, 5, TRUE,
        (SELECT id FROM users WHERE name = 'Мария' LIMIT 1), NOW(), NOW());


create table if not exists contact_type
(
    id   BIGINT auto_increment primary key,
    type varchar(40) not null
);

INSERT INTO contact_type (type)
VALUES ('Телефон'),
       ('Электронная почта');

create table if not exists resume
(
    id          BIGINT auto_increment primary key,
    applicantId bigint    not null,
    name        text      not null,
    categoryId  bigint    not null,
    salary      real      not null,
    isActive    boolean   not null,
    createdDate timestamp not null,
    updateTime  timestamp not null,
    foreign key (applicantId) references users (id),
    foreign key (categoryId) references category (id)
);


INSERT INTO resume (applicantId, name, categoryId, salary, isActive, createdDate, updateTime)
VALUES ((SELECT id FROM users WHERE name = 'Иван' LIMIT 1), 'Резюме Ивана Иванова (Бэкенд-разработчик)',
        (SELECT id FROM category WHERE name = 'Младший Бэкенд-разработчик' LIMIT 1), 70000.00, TRUE, NOW(), NOW()),
       ((SELECT id FROM users WHERE name = 'Иван' LIMIT 1), 'Резюме Ивана Иванова (Фронтенд-разработчик)',
        (SELECT id FROM category WHERE name = 'Старший Фронтенд-разработчик' LIMIT 1), 65000.00, TRUE, NOW(), NOW());


create table if not exists contact_info
(
    id         BIGINT auto_increment primary key,
    typeId     BIGINT not null,
    resumeId   BIGINT not null,
    info_value text   not null,
    foreign key (typeId) references contact_type (id),
    foreign key (resumeId) references resume (id)
);

create table if not exists education_info
(
    id          BIGINT auto_increment primary key,
    resumeId    bigint      not null,
    institution varchar(40) not null,
    program     varchar(40) not null,
    startDate   date        not null,
    endDate     date        not null,
    degree      varchar(40) not null,
    foreign key (resumeId) references resume (id)
);

create table if not exists responded_applicant
(
    id           BIGINT auto_increment primary key,
    resumeId     bigint  not null,
    vacancyId    bigint  not null,
    confirmation boolean not null,
    foreign key (resumeId) references resume (id),
    foreign key (vacancyId) references vacancy (id)
);

INSERT INTO responded_applicant (resumeId, vacancyId, confirmation)
VALUES ((SELECT id FROM resume WHERE name = 'Резюме Ивана Иванова (Бэкенд-разработчик)' LIMIT 1),
        (SELECT id FROM vacancy WHERE name = 'Старший Бэкенд-разработчик' LIMIT 1), TRUE);

create table if not exists message
(
    id                  BIGINT auto_increment primary key,
    respondedApplicants BIGINT       not null,
    content             varchar(100) not null,
    timestamp           timestamp    not null,
    foreign key (respondedApplicants) references responded_applicant (id)
);

INSERT INTO message (respondedApplicants, content, timestamp)
VALUES (1, 'Поздравляем! Вы были выбраны на вакансию Бэкенд-разработчика.', NOW());

create table if not exists work_experience_info
(
    id               BIGINT auto_increment primary key,
    resumeId         bigint       not null,
    years            int          not null,
    companyName      varchar(40)  not null,
    position         varchar(100) not null,
    responsibilities varchar(100) not null,
    foreign key (resumeId) references resume (id)
);