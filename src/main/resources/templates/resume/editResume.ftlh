<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <div class="resume-container">
        <h1>Редактирование резюме</h1>

        <form method="post" action="/resume/edit/${resumeDto.id}">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>
            </#if>
            <input type="hidden" name="id" value="${resumeDto.id!''}">
            <div class="form-group">
                <label for="name">Название резюме:</label>
                <@spring.formInput "resumeDto.name" 'id="name" placeholder="Введите название" class="form-control"' />
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>
            </div>


            <div class="form-group">
                <label for="categoryId">Категория:</label>
                <select id="categoryId" name="categoryId" class="form-control">
                    <option value="">Выберите категорию</option>
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
                <label for="salary">Ожидаемый уровень оклада:</label>
                <@spring.formInput "resumeDto.salary" 'id="salary" placeholder="Введите оклад" class="form-control"' />
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>
            </div>
            <div class="form-group">
                <label>Статус вакансии:</label>
                <div>
                    <label>
                        <input type="radio" name="isActive" value="true"
                               <#if resumeDto.isActive?? && resumeDto.isActive == true>checked</#if>> Активно
                    </label>
                    <label>
                        <input type="radio" name="isActive" value="false"
                               <#if resumeDto.isActive?? && resumeDto.isActive == false>checked</#if>> Не активно
                    </label>
                    <div class="error-block">
                        <@spring.showErrors "<br>" "custom-error" />
                    </div>
                </div>
            </div>


            <div class="form-group">
                <button type="submit" class="submit-button">Отправить</button>
            </div>
        </form>
    </div>

</@main.layout>
