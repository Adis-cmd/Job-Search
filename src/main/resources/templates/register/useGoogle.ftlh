<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <style>
        body {
            background: #f5f7fa;
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
        }

        .register-wrapper {
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 2rem;
        }

        .register-card {
            background: #ffffff;
            border-radius: 16px;
            box-shadow: 0 6px 20px rgba(0, 0, 0, 0.05);
            max-width: 450px;
            width: 100%;
            padding: 2.5rem;
            color: #333;
        }

        .register-card h3 {
            font-weight: 700;
            margin-bottom: 1.5rem;
            color: #222;
        }

        .form-label {
            font-weight: 600;
            color: #555;
            margin-bottom: 0.5rem;
            display: block;
        }

        .form-control {
            background-color: #f8f9fa;
            border: 1px solid #ced4da;
            border-radius: 10px;
            padding: 12px;
            font-size: 15px;
            color: #333;
            transition: border-color 0.3s, box-shadow 0.3s;
        }

        .form-control:focus {
            border-color: #0d6efd;
            box-shadow: 0 0 0 0.2rem rgba(13, 110, 253, 0.15);
            background-color: #ffffff;
        }

        .btn-primary {
            background-color: #0d6efd;
            border: none;
            border-radius: 10px;
            font-weight: 600;
            padding: 12px;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .btn-primary:hover {
            background-color: #0b5ed7;
        }

        .text-link {
            color: #0d6efd;
            text-decoration: none;
            font-weight: 600;
        }

        .text-link:hover {
            text-decoration: underline;
        }

        .error-block {
            color: #d9534f;
            font-size: 13px;
            margin-top: 0.3rem;
        }

        @media (max-width: 576px) {
            .register-card {
                padding: 2rem 1.5rem;
            }
        }
    </style>

    <section class="register-wrapper">
        <div class="register-card">
            <h3 class="text-center">Создать аккаунт</h3>

            <form method="post" action="/register/choose-account-type">
                <input type="hidden" name="userDto.accountType.id" value="${accountType!''}"/>
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                           value="${(_csrf.token)!'csrf-token'}"/>
                </#if>

                <div class="mb-4">
                    <label for="accountType" class="form-label">Тип аккаунта</label>
                    <select id="accountType" name="accountType" required
                            onchange="location.href='/register/choose-account-type?accountType='+this.value"
                            class="form-control">
                        <option value="" disabled selected>-- выберите тип аккаунта --</option>
                        <option value="1" <#if accountType == '1'>selected</#if>>Работодатель</option>
                        <option value="2" <#if accountType == '2'>selected</#if>>Соискатель</option>
                    </select>
                </div>

                <#if accountType == "1" || accountType == "2">
                    <div class="mb-4">
                        <label for="firstName" class="form-label">Имя</label>
                        <@spring.formInput "userDto.name" 'class="form-control"' />
                        <div class="error-block">
                            <@spring.showErrors "<br>" "custom-error" />
                        </div>
                    </div>

                    <#if accountType == "2">
                        <div class="mb-4">
                            <label for="surname" class="form-label">Фамилия</label>
                            <@spring.formInput "userDto.surname" 'class="form-control"' />
                            <div class="error-block">
                                <@spring.showErrors "<br>" "custom-error" />
                            </div>
                        </div>

                        <div class="mb-4">
                            <label for="age" class="form-label">Возраст</label>
                            <@spring.formInput "userDto.age" 'class="form-control"' />
                            <div class="error-block">
                                <@spring.showErrors "<br>" "custom-error" />
                            </div>
                        </div>

                        <div class="mb-4">
                            <label for="email" class="form-label">Email</label>
                            <input type="text"
                                   name="email"
                                   class="form-control"
                                   value="${userDto.email}"
                                   readonly />
                            <div class="error-block">
                                <@spring.showErrors "<br>" "custom-error" />
                            </div>
                        </div>

                        <div class="mb-4">
                            <label for="password" class="form-label">Пароль</label>
                            <@spring.formPasswordInput "userDto.password" 'class="form-control"' />
                            <div class="error-block">
                                <@spring.showErrors "<br>" "custom-error" />
                            </div>
                        </div>
                    </#if>

                    <div class="mb-4">
                        <label for="phone" class="form-label">Номер телефона</label>
                        <@spring.formInput "userDto.phoneNumber" 'class="form-control"' />
                        <div class="error-block">
                            <@spring.showErrors "<br>" "custom-error" />
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary w-100 mb-3">
                        Зарегистрироваться
                    </button>
                </#if>
            </form>

            <p class="text-center mt-3">
                Уже есть аккаунт?
                <a href="/auth/login" class="text-link">Войти</a>
            </p>
        </div>
    </section>

</@main.layout>
