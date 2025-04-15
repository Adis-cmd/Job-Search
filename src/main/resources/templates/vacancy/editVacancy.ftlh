<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <h1>Редактирование вакансии:</h1>

    <#if vacancyDto??>
        <form method="post" action="/vacancy/edit/${vacancyDto.id!''}">
            <input type="hidden" name="id" value="${vacancyDto.id!''}">
            <div class="form-group">
                <label for="name">Название вакансии:</label>
                <@spring.formInput "vacancyDto.name" 'id="name" placeholder="Введите название" class="form-control"' />
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>
            </div>

            <div class="form-group">
                <label for="description">Описание вакансии:</label>
                <@spring.formTextarea "vacancyDto.description" 'id="description" rows="5" class="form-control"' />
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
                                <#if (vacancyDto.categoryId?? && vacancyDto.categoryId == cat.id)>selected</#if>>${cat.name}</option>
                    </#list>
                </select>
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>
            </div>
            <div class="form-group">
                <label for="salary">Заработная плата:</label>
                <@spring.formInput "vacancyDto.salary" 'id="salary" placeholder="Введите оклад" class="form-control"' />
                <div class="error-block">
                    <@spring.showErrors "vacancyDto.salary" "custom-error" />
                </div>
            </div>


            <div class="form-group">
                <label>Требуемый опыт:</label>
                <div class="experience-input">
                    <label for="expFrom">От</label>
                    <@spring.formInput "vacancyDto.expFrom" 'id="expFrom" type="number" min="0" step="1" class="form-control-sm" style="width:60px;"' />
                    <label for="expTo">До</label>
                    <@spring.formInput "vacancyDto.expTo" 'id="expTo" type="number" min="0" step="1" class="form-control-sm" style="width:60px;"' />
                </div>
                <div class="error-block">
                    <@spring.showErrors "vacancyDto.expFrom" "custom-error" />
                    <@spring.showErrors "vacancyDto.expTo" "custom-error" />
                </div>
            </div>


            <div class="form-group">
                <label>Статус вакансии:</label>
                <div>
                    <label>
                        <input type="radio" name="isActive" value="true"
                               <#if vacancyDto.isActive?? && vacancyDto.isActive == true>checked</#if>> Активно
                    </label>
                    <label>
                        <input type="radio" name="isActive" value="false"
                               <#if vacancyDto.isActive?? && vacancyDto.isActive == false>checked</#if>> Не активно
                    </label>
                    <div class="error-block">
                        <@spring.showErrors "<br>" "custom-error" />
                    </div>
                </div>
            </div>

            <div class="buttons">
                <button type="submit" class="btn">Сохранить изменение</button>
            </div>
        </form>
    <#else>
        <p>Вакансия не найдена.</p>
    </#if>



</@main.layout>
