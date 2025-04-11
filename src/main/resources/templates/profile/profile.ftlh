<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Профиль пользователя</title>
    <link rel="stylesheet" href="/static/profileStyle.css">
</head>
<body>
<div class="container">
    <div class="profile-header">
        <div class="profile-info">
            <img src="https://via.placeholder.com/80" alt="Профильное фото">
            <div>
                <h2>${user.name}</h2>
                <#if user.surname?? && user.surname?has_content>
                    <p>${user.surname}</p>
                    <p>${user.age} лет</p>
                </#if>

                <a href="/profile/edit" class="action-button edit-profile-button">Редактировать профиль</a>
            </div>
        </div>

        <div class="profile-actions">
            <#if user.surname?? && user.surname?has_content>
                <a href="/resume/create" class="action-button">Создать резюме</a>
                <a href="/responses" class="action-button">Отклики</a>
            <#else>
                <a href="/vacancy/create" class="action-button">Создать вакансию</a>
            </#if>
        </div>
    </div>


    <#if user.surname?? && user.surname?has_content>
        <!-- Соискатель -->
        <div class="resumes-section">
            <h3>Мои резюме</h3>
            <div class="resume-cards">
                <#list resumes as resume>
                    <div class="resume-card">
                        <h4>${resume.name}</h4>
                        <p>${resume.createdDate}</p>
                        <p>Дата обновления</p>
                        <p>${resume.updateTime}</p>
                        <a href="/resume/update/${resume.id}" class="resume-link">Обновить</a>
                        <a href="/resume/edit/${resume.id}" class="edit-link">Редактировать</a>
                    </div>
                </#list>
            </div>
        </div>
    <#else>
        <div class="resumes-section">
            <h3>Мои вакансии</h3>
            <div class="resume-cards">
                <#list vacancies as vacancy>
                    <div class="resume-card">
                        <h4>${vacancy.name}</h4>
                        <p>Дата создания</p>
                        <p>${vacancy.createdDate}</p>
                        <p>Дата обновления</p>
                        <p>${vacancy.updatedTime}</p>
                        <a href="/vacancy/update/${vacancy.id}" class="resume-link">Обновить</a>
                        <a href="/vacancy/edit/${vacancy.id}" class="edit-link">Редактировать</a>
                    </div>
                </#list>
            </div>
        </div>
    </#if>
</div>
</body>
</html>
