# JobSearch — Платформа для поиска работы

Веб-платформа, которая соединяет работодателей и соискателей, предоставляя удобные инструменты для поиска работы и талантов.

---

## Основные функции

### Для соискателей
- Создание и управление резюме с опытом работы и навыками
- Поиск вакансий по категориям и критериям
- Просмотр профилей компаний
- Система чатов — прямое общение с работодателями
- Персональный профиль

### Для работодателей
- Размещение вакансий и управление объявлениями
- Поиск кандидатов и просмотр резюме
- Корпоративный профиль компании
- Система чатов с кандидатами

---

## 🛠 Технологический стек

| Слой | Технология |
|------|-----------|
| Backend | Java 21, Spring Boot |
| База данных | PostgreSQL 16 |
| Миграции БД | Liquibase |
| Безопасность | Spring Security, OAuth2 (Google) |
| Контейнеризация | Docker, Docker Compose |
| Интернационализация | Spring i18n |
| Email | Gmail SMTP |
| Шаблоны | FreeMarker |
| Логирование | Spring Logging |

---

## 🌐 Поддерживаемые языки

- 🇷🇺 Русский
- 🇬🇧 English
- 🇰🇬 Кыргызча

---

## 🔐 Безопасность

- Аутентификация через форму входа и **Google OAuth2**
- Role-based access control (RBAC)
- CSRF защита
- Хеширование паролей BCrypt

### Роли пользователей
- `ROLE_APPLICANT` — соискатели
- `ROLE_EMPLOYER` — работодатели

---

## 📋 API Endpoints

### Резюме
| Метод  | URL                            | Описание           |
|--------|--------------------------------|--------------------|
| GET    | `/resumes/search`              | Поиск резюме       |
| GET    | `/resumes/search/{categoryId}` | Поиск по категории |
| PUT    | `/resumes/update/{resumeId}`   | Обновление резюме  |
| DELETE | `/resumes/delete/{resumeId}`   | Удаление резюме    |

### Вакансии
| Метод | URL               | Описание          |
|-------|-------------------|-------------------|
| GET   | `/vacancy/search` | Поиск вакансий    |

### Пользователи
| Метод | URL                        | Описание            |
|-------|----------------------------|---------------------|
| GET   | `/users/search/{vacancyId}`| Поиск по вакансии   |
| GET   | `/users/email/{email}`     | Поиск по email      |
| GET   | `/users/name/{name}`       | Поиск по имени      |
| POST  | `/users/photos`            | Загрузка фото       |

### Чат
| Метод | URL                                          | Описание               |
|-------|----------------------------------------------|------------------------|
| POST  | `/api/chat/{respondedApplicantId}/send`      | Отправка сообщения     |
| GET   | `/api/chat/{respondedApplicantId}/messages`  | Получение сообщений    |

### Авторизация
| Метод | URL              | Описание      |
|-------|------------------|---------------|
| POST  | `/auth/register` | Регистрация   |

---

## ⚙️ Установка и запуск

### Требования
- Docker
- Docker Compose

### Запуск

1. Клонируй репозиторий:
```bash
git clone https://github.com/Adis-cmd/Job-Search.git
cd Job-Search
```

2. Создай файл `.env` в корне проекта:
```env
GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret
GOOGLE_EMAIL=your_gmail@gmail.com
GOOGLE_PASSWORD=your_gmail_app_password
```

3. Запусти через Docker Compose:
```bash
docker-compose up --build
```

Приложение будет доступно по адресу: `http://localhost:8099`

---

## 🗄️ База данных

PostgreSQL 16 запускается в отдельном контейнере.

Основные таблицы:
- `users` — пользователи
- `resumes` — резюме соискателей
- `vacancies` — вакансии работодателей
- `messages` — чаты
- `work_experience` — опыт работы
- `education_info` — образование

---

## 📞 Контакты

GitHub: [Adis-cmd](https://github.com/Adis-cmd)
