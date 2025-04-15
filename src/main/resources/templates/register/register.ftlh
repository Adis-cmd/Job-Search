<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <div class="register-container">
        <div class="register-header">
            <h1>Создать аккаунт</h1>
        </div>


        <form method="post" action="/register">
            <div class="form-group">
                <label for="accountType">Тип аккаунта</label>
                <select id="accountType" name="accountType" required
                        onchange="location.href='/register?accountType='+this.value">
                    <option value="" disabled selected>-- выберите тип аккаунта --</option>
                    <option value="1" <#if accountType == '1'>selected</#if>>Работодатель</option>
                    <option value="2" <#if accountType == '2'>selected</#if>>Соискатель</option>
                </select>
            </div>

            <#if accountType == "1" || accountType == "2">
                <label for="firstName">Имя</label>
                <@spring.formInput "userDto.name" 'id="form3Example3cg" class="form-control form-control-lg"' />
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>

                <#if accountType == "2">
                    <label for="surname">Фамилия</label>
                    <@spring.formInput "userDto.surname" 'id="form3Example3cg" class="form-control form-control-lg"' />
                    <div class="error-block">
                        <@spring.showErrors "<br>" "custom-error" />
                    </div>
                </#if>
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>

                <#if accountType == "2">
                    <label for="age">Год рождения</label>
                    <@spring.formInput "userDto.age" 'id="form3Example3cg" class="form-control form-control-lg"' />
                    <div class="error-block">
                        <@spring.showErrors "<br>" "custom-error" />
                    </div>
                </#if>
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>

                <label for="email">Email</label>
                <@spring.formInput "userDto.email" 'id="form3Example3cg" class="form-control form-control-lg"' />
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>


                <label for="password">Пароль</label>
                <@spring.formInput "userDto.password" 'id="form3Example3cg" class="form-control form-control-lg"' />
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>

                <label for="phone">Номер телефона</label>
                <@spring.formInput "userDto.phoneNumber" 'id="form3Example3cg" class="form-control form-control-lg"' />
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>

                <button type="submit">Зарегистрироваться</button>
            </#if>
        </form>

        <div class="login-link">
            Уже есть аккаунт? <a href="/auth/login">Войти</a>
        </div>
    </div>

</@main.layout>