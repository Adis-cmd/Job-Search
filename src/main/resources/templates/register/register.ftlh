<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/static/registerStyle.css">
    <title>Регистрация</title>
</head>
<body>

<div class="navbar">
    <div class="nav-left">
        <a href="/">💼 Job Search</a>
    </div>
    <div class="nav-right">
        <a href="/">Главная</a>
        <a href="/auth/login">Войти</a>
    </div>
</div>

<div class="register-container">
    <div class="register-header">
        <h1>Создать аккаунт</h1>
    </div>

    <form method="post" action="/register">
        <div class="form-group">
            <label for="accountType">Тип аккаунта</label>
            <select id="accountType" name="accountType"  required
                    onchange="location.href='/register?accountType='+this.value">
                <option value="" disabled selected>-- выберите тип аккаунта --</option>
                <option value="1" <#if accountType == '1'>selected</#if>>Работодатель</option>
                <option value="2" <#if accountType == '2'>selected</#if>>Соискатель</option>
            </select>
        </div>

        <#if accountType == "1" || accountType == "2">
        <label for="firstName">Имя</label>
        <input type="text" id="firstName" name="name" required>

        <#if accountType == "2">
            <label for="surname">Фамилия</label>
            <input type="text" id="surname" name="surname" required>
        </#if>

        <#if accountType == "2">
            <label for="age">Год рождения</label>
            <input type="number" id="age" name="age" min="1900" max="2025" required>
        </#if>
        <label for="email">Email</label>
        <input type="email" id="email" name="email" required>


        <label for="password">Пароль</label>
        <input type="password" id="password" name="password" required>

        <label for="phone">Номер телефона</label>
        <input type="tel" id="phone" name="phoneNumber" required>

        <button type="submit">Зарегистрироваться</button>
        </#if>
    </form>

    <div class="login-link">
        Уже есть аккаунт? <a href="/auth/login">Войти</a>
    </div>
</div>
</body>
</html>