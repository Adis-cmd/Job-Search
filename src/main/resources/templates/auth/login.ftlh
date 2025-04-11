<#import "../layout.ftlh" as main>
<@main.layout>
    <section class="d-flex justify-content-center align-items-center"
             style="min-height: 100vh; background-color: #f8f9fa;">

        <div class="card shadow-lg p-4" style="max-width: 900px; width: 100%; border-radius: 16px;">
            <div class="row g-0">
                <!-- Левая картинка -->
                <div class="col-lg-6 d-none d-lg-block">
                    <img src="https://mdbootstrap.com/img/new/ecommerce/vertical/004.jpg" alt="Login image"
                         class="img-fluid rounded-start" style="height: 100%; object-fit: cover;">
                </div>

                <div class="col-lg-6">
                    <div class="card-body px-4">

                        <h3 class="mb-4 text-center">Вход в аккаунт</h3>

                        <form id="login-form" action="/auth/login" method="post">

                            <div class="form-outline mb-4">
                                <label class="form-label" for="form2Example1">Email</label>
                                <input type="email" id="form2Example1" class="form-control form-control-lg"
                                       name="username" required/>
                            </div>

                            <div class="form-outline mb-3">
                                <label class="form-label" for="form2Example2">Пароль</label>
                                <input type="password" id="form2Example2" class="form-control form-control-lg"
                                       name="password" required/>
                            </div>
                            <div class="d-flex justify-content-end mb-3">
                                <a href="/auth/forgot-password" class="small text-muted">Забыли пароль?</a>
                            </div>

                            <button type="submit" class="btn btn-primary btn-lg btn-block w-100">
                                Войти
                            </button>


                        </form>

                        <p class="text-center mt-4">
                            Нет аккаунта?
                            <a href="/register" class="text-decoration-none">Зарегистрироваться</a>
                        </p>

                    </div>
                </div>
            </div>
        </div>

    </section>
</@main.layout>
