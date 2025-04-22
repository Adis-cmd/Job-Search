<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <style>
        body {
            color: white;
            font-family: Arial, sans-serif;
        }

        h1 {
            color: #f5c518;
            letter-spacing: 1px;
            font-size: 30px;
            margin-top: 15px;
        }

        .card {
            background-color: #222;
            border-radius: 10px;
            padding: 30px;
            max-width: 800px;
            margin: 30px auto;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
            color: yellow;
        }

        input[type="text"],
        select,
        textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            background-color: #333;
            color: white;
        }

        select:disabled {
            background-color: #555;
            color: #777;
        }

        .experience-input {
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        .experience-input label {
            margin-right: 10px;
        }

        .switch {
            position: relative;
            display: inline-block;
            width: 60px;
            height: 34px;
        }

        .switch input {
            opacity: 0;
            width: 0;
            height: 0;
        }

        .switch-label {
            margin-left: 10px;
            vertical-align: middle;
        }

        .buttons {
            text-align: center;
        }

        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: yellow;
            color: black;
            text-decoration: none;
            border-radius: 4px;
            margin-right: 10px;
            border: none;
            cursor: pointer;
        }

        .btn:hover {
            background-color: #ffcc00;
        }
    </style>

    <h1>Редактирование вакансии:</h1>

    <#if vacancyDto??>
        <div class="card">
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
                        <option value="" selected disabled>Выберите категорию</option>
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
        </div>
    <#else>
        <p>Вакансия не найдена.</p>
    </#if>

</@main.layout>
