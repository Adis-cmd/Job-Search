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
            background: rgba(33, 37, 41, 0.95);
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
            color: #f5c518;
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

        .switch input {
            opacity: 0;
            width: 0;
            height: 0;
        }

        .buttons {
            text-align: center;
        }

        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #f5c518;
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

    <h1><@spring.message "vacancy.created.title"/>:</h1>


    <div class="card">
        <form method="post" action="/vacancy/add">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>
            </#if>
            <div class="form-group">
                <label for="name"><@spring.message "vacancy.created.name"/>:</label>
                <@spring.formInput "vacancyDto.name" 'id="name" class="form-control form-control-lg"' />
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>
            </div>

            <div class="form-group">
                <label for="categoryId"><@spring.message "resume.created.category"/>:</label>
                <select id="categoryId" name="categoryId" class="form-control">
                    <option value="" selected disabled><@spring.message "resume.select.category"/></option>
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
                <label for="salary"><@spring.message "vacancy.created.salary"/>:</label>
                <@spring.formInput "vacancyDto.salary" 'id="salary" class="form-control"' />
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>
            </div>

            <div class="form-group">
                <label for="description"><@spring.message "vacancy.created.description"/>:</label>
                <@spring.formTextarea "vacancyDto.description" 'id="description" rows="5" class="form-control"' />
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>
            </div>

            <div class="form-group">
                <label><@spring.message "vacancy.created.exp"/>:</label>
                <div class="experience-input">
                    <label for="expFrom"><@spring.message "vacancy.created.from"/></label>
                    <@spring.formInput "vacancyDto.expFrom" 'id="expFrom" type="number" style="width: 60px;" min="0" step="1"' />
                    <div class="error-block">
                        <@spring.showErrors "<br>" "custom-error" />
                    </div>
                    <label for="expTo"><@spring.message "vacancy.created.to"/></label>
                    <@spring.formInput "vacancyDto.expTo" 'id="expTo" type="number" style="width: 60px;" min="0" step="1"' />
                    <div class="error-block">
                        <@spring.showErrors "<br>" "custom-error" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label><@spring.message "vacancy.created.status"/>:</label>
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
                <button type="submit" class="btn"><@spring.message "vacancy.created.button"/></button>
            </div>
        </form>
    </div>

</@main.layout>
