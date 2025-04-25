<#import "../layout.ftlh" as main>
<@main.layout>
    <style>
        body {
            background: url('https://images.unsplash.com/photo-1521791136064-7986c2920216?auto=format&fit=crop&w=1950&q=80') no-repeat center center fixed;
            background-size: cover;
        }

        .login-wrapper {
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .login-card {
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

        .form-label, .form-control::placeholder, .text-muted, a {
            color: #f1f1f1 !important;
        }

        .form-control {
            background-color: rgba(255, 255, 255, 0.1);
            border: none;
            border-radius: 12px;
            color: white;
        }

        .form-control:focus {
            box-shadow: 0 0 0 0.2rem rgba(13, 110, 253, 0.25);
            background-color: rgba(255, 255, 255, 0.2);
        }

        .btn-primary {
            border-radius: 12px;
            font-weight: 600;
        }

        .login-card h3 {
            font-weight: 700;
            margin-bottom: 2rem;
        }

        @media (max-width: 576px) {
            .login-card {
                padding: 2rem 1.5rem;
            }
        }
    </style>

    <section class="login-wrapper">
        <div class="login-card">
            <h3 class="text-center text-light">Вход в аккаунт</h3>
            <form id="login-form" action="/auth/login" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                           value="${(_csrf.token)!'csrf-token'}"/>
                </#if>
                <div class="mb-4">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" id="email" name="username" class="form-control form-control-lg" placeholder="Введите email" required>
                </div>

                <div class="mb-3">
                    <label for="password" class="form-label">Пароль</label>
                    <input type="password" id="password" name="password" class="form-control form-control-lg" placeholder="Введите пароль" required>
                </div>

                <div class="d-flex justify-content-end mb-3">
                    <a href="/auth/forgot-password" class="small text-muted">Забыли пароль?</a>
                </div>

                <button type="submit" class="btn btn-primary btn-lg w-100 mb-3">
                    Войти
                </button>
            </form>

            <p class="text-center mt-3">
                Нет аккаунта?
                <a href="/register" class="text-decoration-none fw-semibold">Зарегистрироваться</a>
            </p>
        </div>
    </section>
</@main.layout>
