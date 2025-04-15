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
                            <@spring.formInput "userDto.name", 'class="form-control form-control-lg"' />
                            <div class="text-danger">
                                <@spring.showErrors "<br>" />
                            </div>
                        </div>

                        <#if userDto.surname?? && userDto.surname?has_content>
                            <div class="form-group mb-3">
                                <label for="surname">Фамилия</label>
                                <@spring.formInput "userDto.surname", 'class="form-control form-control-lg"' />
                                <div class="text-danger">
                                    <@spring.showErrors "<br>" />
                                </div>
                            </div>

                            <div class="form-group mb-3">
                                <label for="age">Возраст</label>
                                <@spring.formInput "userDto.age", 'class="form-control form-control-lg"' />
                                <div class="text-danger">
                                    <@spring.showErrors "<br>" />
                                </div>
                            </div>
                        </#if>

                        <div class="form-group mb-4">
                            <label for="avatar">Фото профиля</label>
                            <#if userDto.avatar?? && userDto.avatar?has_content>
                                <div class="mb-2">
                                    <img src="/users/photos/${userDto.avatar}" alt="Профильное фото" class="img-thumbnail" style="max-width: 150px;">
                                </div>
                            </#if>
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