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
            padding-top: 1px;
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

        .btn-google {
            border-radius: 12px;
            font-weight: 600;
            padding: 0.6rem 1rem;
            color: #fff;
            background-color: #db4437;
            border: none;
            display: flex;
            align-items: center;
            gap: 0.5rem;
            transition: all 0.3s ease;
        }

        .btn-google:hover {
            background-color: #c23321;
            transform: translateY(-1px);
        }

        @media (max-width: 576px) {
            .login-card {
                padding: 2rem 1.5rem;
            }
        }
    </style>

    <#if RequestParameters.error??>
        <div class="d-flex justify-content-center mt-4">
            <div class="alert alert-danger shadow rounded-3 text-center w-100" style="max-width: 400px;">
                Неверный логин или пароль. Попробуйте снова.
            </div>
        </div>
    </#if>

    <div class="login-background">
        <section class="login-wrapper">
            <div class="login-card">
                <h3><@spring.message "login.title" /></h3>
                <form id="login-form" action="/auth/login" method="post">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                               value="${(_csrf.token)!'csrf-token'}"/>
                    </#if>
                    <div class="mb-4">
                        <label for="email" class="form-label"><@spring.message "login.register.email" /></label>
                        <input type="email" id="email" name="username" class="form-control form-control-lg"
                               placeholder="<@spring.message "login.placeholder.email" />" required>
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label"><@spring.message "login.register.password" /></label>
                        <input type="password" id="password" name="password" class="form-control form-control-lg"
                               placeholder="<@spring.message "login.placeholder.forPassword" />" required>
                    </div>

                    <div class="d-flex justify-content-end mb-3">
                        <a href="/auth/forgot_password" class="small text-muted"><@spring.message "login.forgot" /></a>
                    </div>

                    <button type="submit" class="btn btn-primary btn-lg w-100 mb-3">
                        <@spring.message "login.register.button" />
                    </button>
                </form>

                <p class="text-center mt-3">
                    <@spring.message "login.noaccount" />
                    <a href="/register"
                       class="text-decoration-none fw-semibold"> <@spring.message "login.register" /></a>
                </p>

                <div class="d-flex justify-content-center mt-3">
                    <a class="btn btn-google" href="/oauth2/authorization/google">
                        <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor"
                             class="bi bi-google" viewBox="0 0 16 16">
                            <path d="M15.545 6.558a9.42 9.42 0 0 1 .139 1.626c0 2.434-.87 4.492-2.384 5.885h.002C11.978 15.292 10.158 16 8 16A8 8 0 1 1 8 0a7.689 7.689 0 0 1 5.352 2.082l-2.284 2.284A4.347 4.347 0 0 0 8 3.166c-2.087 0-3.86 1.408-4.492 3.304a4.792 4.792 0 0 0 0 3.063h.003c.635 1.893 2.405 3.301 4.492 3.301 1.078 0 2.004-.276 2.722-.764h-.003a3.702 3.702 0 0 0 1.599-2.431H8v-3.08h7.545z"/>
                        </svg>
                        <@spring.message "login.google" />
                    </a>
                </div>
            </div>
        </section>
    </div>

</@main.layout>
