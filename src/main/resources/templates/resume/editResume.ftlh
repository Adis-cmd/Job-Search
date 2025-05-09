<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <div class="resume-container">
        <h1><@spring.message "resume.edit.title"/></h1>

        <form method="post" action="/resume/edit/${resumeDto.id}">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>
            </#if>
            <input type="hidden" name="id" value="${resumeDto.id!''}">
            <div class="form-group">
                <label for="name"><@spring.message "resume.edit.name"/>:</label>
                <@spring.formInput "resumeDto.name" 'id="name" placeholder="Введите название" class="form-control"' />
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>
            </div>


            <div class="form-group">
                <label for="categoryId"><@spring.message "resume.edit.category"/>:</label>
                <select id="categoryId" name="categoryId" class="form-control">
                    <option value=""><@spring.message "resume.edit.selected"/></option>
                    <#list category as cat>
                        <option value="${cat.id}"
                                <#if (resumeDto.categoryId?? && resumeDto.categoryId == cat.id)>selected</#if>>
                            ${cat.name}
                        </option>
                    </#list>
                </select>
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>
            </div>


            <div class="form-group">
                <label for="salary"><@spring.message "resume.edit.salary"/>:</label>
                <@spring.formInput "resumeDto.salary" 'id="salary" placeholder="Введите оклад" class="form-control"' />
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>
            </div>
            <div class="form-group">
                <label><@spring.message "resume.edit.status"/>:</label>
                <div>
                    <label>
                        <input type="radio" name="isActive" value="true"
                               <#if resumeDto.isActive?? && resumeDto.isActive == true>checked</#if>> <@spring.message "resume.edit.statusActive"/>
                    </label>
                    <label>
                        <input type="radio" name="isActive" value="false"
                               <#if resumeDto.isActive?? && resumeDto.isActive == false>checked</#if>> <@spring.message "resume.edit.statusDisable"/>
                    </label>
                    <div class="error-block">
                        <@spring.showErrors "<br>" "custom-error" />
                    </div>
                </div>
            </div>


            <div class="form-group">
                <button type="submit" class="submit-button"><@spring.message "resume.edit.button"/></button>
            </div>
        </form>
    </div>

</@main.layout>
