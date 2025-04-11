<#import "../layout.ftlh" as main>
<link rel="stylesheet" href="/static/editProfileStyle.css">
<@main.layout>
    <section class="container py-5">
        <div class="row justify-content-center">
            <div class="col-md-8 col-lg-6">
                <div class="card shadow-sm p-4">
                    <h3 class="text-center mb-4">Редактирование профиля</h3>

                    <form action="/profile/edit" method="post">
                        <div class="form-group mb-3">
                            <label for="name">Имя</label>
                            <input type="text" class="form-control" id="name" name="name" value="${user.name}" required>
                        </div>

                        <#if user.surname?? && user.surname?has_content>
                            <div class="form-group mb-3">
                                <label for="surname">Фамилия</label>
                                <input type="text" class="form-control" id="surname" name="surname" value="${user.surname}">
                            </div>

                            <div class="form-group mb-3">
                                <label for="age">Возраст</label>
                                <input type="number" class="form-control" id="age" name="age" value="${user.age!}">
                            </div>
                        </#if>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary btn-lg">Сохранить изменения</button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </section>
</@main.layout>
