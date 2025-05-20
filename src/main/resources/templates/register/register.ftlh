<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <style>
        body {
            background: #ffffff;
            font-family: 'Roboto', sans-serif;
            color: #333;
        }

        .login-card {
            background: #212529;
            backdrop-filter: blur(18px);
            -webkit-backdrop-filter: blur(18px);
            border-radius: 20px;
            box-shadow: 0 12px 40px rgba(0, 0, 0, 0.35);
            max-width: 450px;
            width: 100%;
            padding: 3rem 2.5rem;
            color: #fff;
        }

        .form-label, .form-control::placeholder, .text-muted, a {
            color: #f1f1f1 !important;
        }

        .login-wrapper {
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 20px;
        }

        .form-control {
            background-color: rgba(255, 255, 255, 0.08);
            border: 1px solid rgba(255, 255, 255, 0.2);
            border-radius: 12px;
            color: #fff;
            transition: background-color 0.3s ease;
        }

        .form-control:focus {
            background-color: rgba(255, 255, 255, 0.16);
            box-shadow: 0 0 0 0.2rem rgba(13, 110, 253, 0.3);
            border-color: #ddd;
        }

        .btn-primary {
            border-radius: 12px;
            font-weight: 600;
            padding: 0.75rem;
            background: linear-gradient(45deg, #1d976c, #93f9b9);
            border: none;
            transition: all 0.3s ease;
        }

        .btn-primary:hover {
            background: linear-gradient(45deg, #93f9b9, #1d976c);
            transform: translateY(-2px);
        }

        .login-card h3 {
            font-weight: 800;
            text-align: center;
            font-size: 2rem;
            margin-bottom: 2rem;
            text-shadow: 0 2px 5px rgba(0, 0, 0, 0.4);
        }

        @media (max-width: 576px) {
            .login-card {
                padding: 2rem 1.5rem;
            }
        }
    </style>

    <div class="login-wrapper">
        <div class="login-card">
            <h3><@spring.message "register.title" /></h3>
            <form action="/register" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                           value="${(_csrf.token)!'csrf-token'}"/>
                </#if>

                <div class="mb-3">
                    <label for="accountType" class="form-label"><@spring.message "register.accountType" /></label>
                    <select class="form-control" id="accountType" name="accountType">
                        <option value="1" <#if accountType?string == "1">selected</#if>><@spring.message "register.accountType.employee" /></option>
                        <option value="2" <#if accountType?string == "2">selected</#if>><@spring.message "register.accountType.applicant"></option>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="name" class="form-label"><@spring.message "register.name" /></label>
                    <@spring.formInput "userDto.name" 'type="text" id="name" name="name" class="form-control"'/>
                    <@spring.showErrors "<br>" "error text-danger"/>
                </div>

                <div class="mb-3" id="surnameField">
                    <label for="surname" class="form-label"><@spring.message "register.surname" /></label>
                    <@spring.formInput "userDto.surname" 'type="text" id="surname" name="surname" class="form-control"'/>
                    <@spring.showErrors "<br>" "error text-danger"/>
                </div>

                <div class="mb-3" id="ageField">
                    <label for="age" class="form-label"><@spring.message "register.ageg" /></label>
                    <@spring.formInput "userDto.age" 'type="tel" id="age" name="age" class="form-control"'/>
                    <@spring.showErrors "<br>" "error text-danger"/>
                </div>

                <div class="mb-3" id="emailField">
                    <label for="email" class="form-label"><@spring.message "login.register.email" /></label>
                    <@spring.formInput "userDto.email" 'type="email" id="email" name="email" class="form-control"'/>
                    <@spring.showErrors "<br>" "error text-danger"/>
                </div>

                <div class="mb-3">
                    <label for="password" class="form-label"><@spring.message "login.register.password" /></label>
                    <@spring.formPasswordInput "userDto.password" 'type="password" id="password" name="password" class="form-control"'/>
                    <@spring.showErrors "<br>" "error text-danger"/>
                </div>

                <div class="mb-3">
                    <label for="phoneNumber" class="form-label"><@spring.message "register.phoneNumber" /></label>
                    <@spring.formInput "userDto.phoneNumber" 'type="tel" id="phoneNumber" name="phoneNumber" class="form-control"'/>
                    <@spring.showErrors "<br>" "error text-danger"/>
                </div>

                <button type="submit" class="btn btn-primary btn-lg w-100 mb-3"><@spring.message "login.register" /></button>
            </form>

            <p class="text-center mt-3">
                Уже зарегистрированы?
                <a href="/login" class="text-decoration-none fw-semibold"><@spring.message "login.register.button" /></a>
            </p>
        </div>
    </div>

    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const accountTypeSelect = document.getElementById('accountType');
            const nameInput = document.getElementById('name');
            const surnameField = document.getElementById('surnameField');
            const emailField = document.getElementById('emailField');
            const ageField = document.getElementById('ageField');

            function toggleFields() {
                if (accountTypeSelect.value === '1') {
                    nameInput.previousElementSibling.textContent = '<@spring.message "register.name.employee" />';
                    surnameField.style.display = 'none';
                    ageField.style.display = 'none'
                } else {
                    nameInput.previousElementSibling.textContent = '<@spring.message "register.name" />';
                    surnameField.style.display = 'block';
                    ageField.style.display = 'block'
                }
            }

            toggleFields();
            accountTypeSelect.addEventListener('change', toggleFields);
        });
    </script>

</@main.layout>
