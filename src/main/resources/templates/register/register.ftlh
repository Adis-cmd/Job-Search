<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <style>
        body {
            background: url('https://images.unsplash.com/photo-1521791136064-7986c2920216?auto=format&fit=crop&w=1950&q=80') no-repeat center center fixed;
            background-size: cover;
        }

        .register-wrapper {
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .register-card {
            background: rgba(255, 255, 255, 0.2);
            backdrop-filter: blur(20px);
            -webkit-backdrop-filter: blur(20px);
            border-radius: 20px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
            max-width: 450px;
            width: 100%;
            padding: 2.5rem;
            color: #fff;
        }

        .form-label, .form-control::placeholder, a {
            color: #f1f1f1 !important;
        }

        .form-control {
            background-color: rgba(255, 255, 255, 0.2);
            backdrop-filter: blur(20px);
            -webkit-backdrop-filter: blur(20px);
            border: none;
            border-radius: 12px;
            color: white;
            padding: 12px;
            font-size: 14px;
        }

        .form-control:focus {
            box-shadow: 0 0 0 0.2rem rgba(13, 110, 253, 0.25);
            background-color: rgba(255, 255, 255, 0.3);
        }

        .btn-primary {
            border-radius: 12px;
            font-weight: 600;
        }

        .register-card h3 {
            font-weight: 700;
            margin-bottom: 2rem;
        }

        @media (max-width: 576px) {
            .register-card {
                padding: 2rem 1.5rem;
            }
        }
    </style>

    <section class="register-wrapper">
        <div class="register-card">
            <h3 class="text-center text-light">Создать аккаунт</h3>

            <form method="post" action="/register">
                <input type="hidden" name="userDto.accountType.id" value="${accountType!''}"/>
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                           value="${(_csrf.token)!'csrf-token'}"/>
                </#if>

                <div class="mb-4">
                    <label for="accountType" class="form-label">Тип аккаунта</label>
                    <select id="accountType" name="accountType" required
                            onchange="location.href='/register?accountType='+this.value"
                            class="form-control form-control-lg">
                        <option value="" disabled selected>-- выберите тип аккаунта --</option>
                        <option value="1" <#if accountType == '1'>selected</#if>>Работодатель</option>
                        <option value="2" <#if accountType == '2'>selected</#if>>Соискатель</option>
                    </select>
                </div>

                <#if accountType == "1" || accountType == "2">
                    <div class="mb-4">
                        <label for="firstName" class="form-label">Имя</label>
                        <@spring.formInput "userDto.name" 'id="form3Example3cg" class="form-control form-control-lg"' />
                        <div class="error-block">
                            <@spring.showErrors "<br>" "custom-error" />
                        </div>
                    </div>

                    <#if accountType == "2">
                        <div class="mb-4">
                            <label for="surname" class="form-label">Фамилия</label>
                            <@spring.formInput "userDto.surname" 'id="form3Example3cg" class="form-control form-control-lg"' />
                            <div class="error-block">
                                <@spring.showErrors "<br>" "custom-error" />
                            </div>
                        </div>

                        <div class="mb-4">
                            <label for="age" class="form-label">Возраст</label>
                            <@spring.formInput "userDto.age" 'id="form3Example3cg" class="form-control form-control-lg"' />
                            <div class="error-block">
                                <@spring.showErrors "<br>" "custom-error" />
                            </div>
                        </div>
                    </#if>

                    <div class="mb-4">
                        <label for="email" class="form-label">Email</label>
                        <@spring.formInput "userDto.email" 'id="form3Example3cg" class="form-control form-control-lg"' />
                        <div class="error-block">
                            <@spring.showErrors "<br>" "custom-error" />
                        </div>
                    </div>

                    <div class="mb-4">
                        <label for="password" class="form-label">Пароль</label>
                        <@spring.formInput "userDto.password" 'id="form3Example3cg" class="form-control form-control-lg"' />
                        <div class="error-block">
                            <@spring.showErrors "<br>" "custom-error" />
                        </div>
                    </div>

                    <div class="mb-4">
                        <label for="phone" class="form-label">Номер телефона</label>
                        <@spring.formInput "userDto.phoneNumber" 'id="form3Example3cg" class="form-control form-control-lg"' />
                        <div class="error-block">
                            <@spring.showErrors "<br>" "custom-error" />
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary btn-lg w-100 mb-3">
                        Зарегистрироваться
                    </button>
                </#if>
            </form>

            <p class="text-center mt-3">
                Уже есть аккаунт?
                <a href="/auth/login" class="text-decoration-none fw-semibold">Войти</a>
            </p>
        </div>
    </section>

</@main.layout>
