<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <section class="container py-5">
        <div class="row justify-content-center">
            <div class="col-md-8 col-lg-6">
                <div class="card shadow-sm p-4">
                    <h3 class="text-center mb-4">Редактирование профиля</h3>

                    <form action="/profile/edit" method="post" enctype="multipart/form-data">
                        <div class="form-group mb-3">
                            <label for="name">Имя</label>
                            <@spring.formInput "user.name" 'id="form3Example3cg" class="form-control form-control-lg"' />
                            <div class="error-block">
                                <@spring.showErrors "<br>" "custom-error" />
                            </div>
                        </div>

                        <#if user.surname?? && user.surname?has_content>
                            <div class="form-group mb-3">
                                <label for="surname">Фамилия</label>
                                <@spring.formInput "user.surname" 'id="form3Example3cg" class="form-control form-control-lg"' />
                                <div class="error-block">
                                    <@spring.showErrors "<br>" "custom-error" />
                                </div>
                            </div>


                            <div class="form-group mb-3">
                                <label for="age">Возраст</label>
                                <@spring.formInput "user.age" 'id="form3Example3cg" class="form-control form-control-lg"' />
                                <div class="error-block">
                                    <@spring.showErrors "<br>" "custom-error" />
                                </div>
                            </div>
                        </#if>

                        <img src="users/photos/${user.avatar}" alt="Профильное фото">

                        <div class="form-group mb-4">
                            <label for="avatar">Фото профиля</label>
                            <input type="file" class="form-control" id="avatar" name="file" accept=".jpg,.jpeg,.png">
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary btn-lg">Сохранить изменения</button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </section>
</@main.layout>