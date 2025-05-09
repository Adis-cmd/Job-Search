<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <div class="card shadow-sm p-4">
        <h3 class="text-center mb-4"><@spring.message "profile.edit.title"/></h3>

        <form action="/profile/edit" method="post" enctype="multipart/form-data">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>
            </#if>

            <div class="form-group mb-3">
                <label for="name"><@spring.message "profile.edit.name"/></label>
                <@spring.formInput "userDto.name", 'class="form-control form-control-lg"' />
                <div class="text-danger">
                    <@spring.showErrors "userDto.name" "<br>" />
                </div>
            </div>

            <#if userDto.surname?? && userDto.surname?has_content>
                <div class="form-group mb-3">
                    <label for="surname"><@spring.message "profile.edit.surname"/></label>
                    <@spring.formInput "userDto.surname", 'class="form-control form-control-lg"' />
                    <div class="text-danger">
                        <@spring.showErrors "userDto.surname" "<br>" />
                    </div>
                </div>

                <div class="form-group mb-3">
                    <label for="age"><@spring.message "profile.edit.age"/></label>
                    <@spring.formInput "userDto.age", 'class="form-control form-control-lg"' />
                    <div class="text-danger">
                        <@spring.showErrors "userDto.age" "<br>" />
                    </div>
                </div>
            </#if>

            <div class="form-group mb-4">
                <label for="avatar"><@spring.message "profile.edit.avatar"/></label>
                <#if userDto.avatar?? && userDto.avatar?has_content>
                    <div class="profile-avatar">
                        <img src="<#if userDto.avatar?starts_with('http')>${userDto.avatar}<#else>users/photos/${userDto.avatar}</#if>" alt="Профильное фото">
                    </div>

                </#if>
                <input type="file" class="form-control" id="avatar" name="file" accept=".jpg,.jpeg,.png">
                <div class="text-danger">
                    <@spring.showErrors "file" "<br>" />
                </div>
            </div>

            <div class="d-grid">
                <button type="submit" class="btn btn-primary btn-lg"><@spring.message "profile.edit.button"/></button>
            </div>
        </form>
    </div>

</@main.layout>
