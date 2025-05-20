<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <title>Создание резюме</title>

    <form method="post" action="/resume/add" class="auth-form">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>
        </#if>

        <#if errorMessage??>
            <div class="alert alert-danger mb-3">${errorMessage}</div>
        </#if>

        <div class="form-group">
            <label for="name"><@spring.message "resume.created.name"/>:</label>
            <@spring.formInput "resumeDto.name" 'id="name" class="form-control form-control-lg"' />
            <div class="error-block">
                <@spring.showErrors "<br>" "custom-error" />
            </div>
        </div>
        <div class="form-group">
            <label for="categoryId"><@spring.message "resume.created.category"/>:</label>
            <select id="categoryId" name="categoryId" class="form-control">
                <#list category as cat>
                    <option value="${cat.id}"
                            <#if (resumeDto.categoryId?? && resumeDto.categoryId == cat.id)>selected</#if>>${cat.name}</option>
                </#list>
            </select>
        </div>

        <div class="form-group mb-3">
            <label for="salary" class="form-label"><@spring.message "resume.created.salary"/></label>
            <@spring.formInput "resumeDto.salary" 'id="salary" type="number" class="form-control" min="0" step="0.01"' />
            <div class="error-block">
                <@spring.showErrors "<br>" "custom-error" />
            </div>
        </div>

        <div class="form-group mb-3">
            <label class="form-label"><@spring.message "resume.created.experience"/></label>
            <div id="work-experience-container">
                <div class="work-experience-item mb-3">
                    <@spring.formInput "resumeDto.workExperiences[0].years" 'placeholder="Год работы" class="form-control"' />
                    <div class="error-block">
                        <@spring.showErrors "<br>" "custom-error" />
                    </div>
                    <@spring.formInput "resumeDto.workExperiences[0].companyName" 'placeholder="Название компании" class="form-control"' />
                    <div class="error-block">
                        <@spring.showErrors "<br>" "custom-error" />
                    </div>
                    <@spring.formInput "resumeDto.workExperiences[0].position" 'placeholder="Должность" class="form-control"' />
                    <div class="error-block">
                        <@spring.showErrors "<br>" "custom-error" />
                    </div>
                    <@spring.formTextarea "resumeDto.workExperiences[0].responsibilities" 'placeholder="Обязанности" class="form-control"' />
                    <div class="error-block">
                        <@spring.showErrors "<br>" "custom-error" />
                    </div>
                    <button type="button" class="btn btn-danger btn-sm mt-2 delete-work-experience">Удалить
                    </button>

                </div>
            </div>
            <button type="button" id="add-work-experience" class="btn btn-link text-secondary mt-2">+ Добавить
                опыт работы
            </button>
        </div>

        <div class="form-group mb-3">
            <label class="form-label"><@spring.message "resume.created.education"/>:</label>
            <div id="education-container">
                <div class="education-item mb-3">
                    <input name="educationInfos[0].institution" placeholder="Учебное заведение"
                           class="form-control mb-2"/>
                    <input name="educationInfos[0].program" placeholder="Программа обучения"
                           class="form-control mb-2"/>
                    <input name="educationInfos[0].degree" placeholder="Степень" class="form-control mb-2"/>
                    <input type="date" name="educationInfos[0].startDate" class="form-control mb-2"/>
                    <input type="date" name="educationInfos[0].endDate" class="form-control mb-2"/>
                    <button type="button" class="btn btn-danger btn-sm mt-2 delete-education">Удалить</button>
                </div>
            </div>
            <button type="button" id="add-education" class="btn btn-link text-secondary mt-2">+ Добавить
                образование
            </button>
        </div>

        <div class="form-group mb-3">
            <label class="form-label"><@spring.message "resume.created.contactInfo"/>:</label>
            <div class="contact-info-fields">
                <input type="hidden" name="contactInfos[0].typeId" value="1"/>
                <@spring.formInput "resumeDto.contactInfos[0].value" 'placeholder="+996 999 555 555" class="form-control mb-2"' />
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>
                <input type="hidden" name="contactInfos[1].typeId" value="2"/>
                <@spring.formInput "resumeDto.contactInfos[1].value" 'placeholder="example@mail.com" type="email" class="form-control mb-2"' />
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>
                <input type="hidden" name="contactInfos[2].typeId" value="3"/>
                <@spring.formInput "resumeDto.contactInfos[2].value" 'placeholder="https://t.me/username" type="url" class="form-control mb-2"' />
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>
                <input type="hidden" name="contactInfos[3].typeId" value="4"/>
                <@spring.formInput "resumeDto.contactInfos[3].value" 'placeholder="https://facebook.com/yourprofile" type="url" class="form-control mb-2"' />
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>
                <input type="hidden" name="contactInfos[4].typeId" value="5"/>
                <@spring.formInput "resumeDto.contactInfos[4].value" 'placeholder="https://linkedin.com/in/yourprofile" type="url" class="form-control mb-2"' />
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>
            </div>
        </div>

        <div class="form-group mb-3">
            <label class="form-label"><@spring.message "resume.created.status"/>:</label>
            <div class="d-flex">
                <label class="mr-4">
                    <input type="radio" name="isActive" value="true"
                           <#if resumeDto.isActive?? && resumeDto.isActive == true>checked</#if>> <@spring.message "vacancy.edit.statusActive"/>
                </label>
                <label>
                    <input type="radio" name="isActive" value="false"
                           <#if resumeDto.isActive?? && resumeDto.isActive == false>checked</#if>> <@spring.message "vacancy.edit.statusDisable"/>
                </label>
            </div>
        </div>

        <div class="form-group text-center mt-4">
            <button type="submit" class="btn btn-primary btn-lg w-100"><@spring.message "resume.created.button"/></button>
        </div>
        </div>
        </div>
    </form>

    <script>
        let workExperienceIndex = 1;
        let educationIndex = 1;

        document.getElementById('add-work-experience').addEventListener('click', () => {
            const container = document.getElementById('work-experience-container');
            const newItem = document.createElement('div');
            newItem.className = 'work-experience-item mb-3';
            newItem.innerHTML =
                '<input name="workExperiences[' + workExperienceIndex + '].years" placeholder="Год работы" class="form-control mb-2"/>' +
                '<input name="workExperiences[' + workExperienceIndex + '].companyName" placeholder="Название компании" class="form-control mb-2"/>' +
                '<input name="workExperiences[' + workExperienceIndex + '].position" placeholder="Должность" class="form-control mb-2"/>' +
                '<textarea name="workExperiences[' + workExperienceIndex + '].responsibilities" placeholder="Обязанности" class="form-control mb-2"></textarea>' +
                '<button type="button" class="btn btn-danger btn-sm mt-2 delete-work-experience">Удалить</button>';

            container.appendChild(newItem);
            workExperienceIndex++;
        });

        document.getElementById('add-education').addEventListener('click', () => {
            const container = document.getElementById('education-container');
            const newItem = document.createElement('div');
            newItem.className = 'education-item mb-3';
            newItem.innerHTML =
                '<input name="educationInfos[' + educationIndex + '].institution" placeholder="Учебное заведение" class="form-control mb-2"/>' +
                '<input name="educationInfos[' + educationIndex + '].program" placeholder="Программа обучения" class="form-control mb-2"/>' +
                '<input name="educationInfos[' + educationIndex + '].degree" placeholder="Степень" class="form-control mb-2"/>' +
                '<input type="date" name="educationInfos[' + educationIndex + '].startDate" class="form-control mb-2"/>' +
                '<input type="date" name="educationInfos[' + educationIndex + '].endDate" class="form-control mb-2"/>' +
                '<button type="button" class="btn btn-danger btn-sm mt-2 delete-education">Удалить</button>';

            container.appendChild(newItem);
            educationIndex++;
        });

        document.addEventListener('click', function (e) {
            if (e.target && e.target.classList.contains('delete-work-experience')) {
                e.target.parentElement.remove();
            }
        });

        document.addEventListener('click', function (e) {
            if (e.target && e.target.classList.contains('delete-education')) {
                e.target.parentElement.remove();
            }
        });
    </script>

    <style>
        .auth-form {
            background-color: #f9f9f9;
            border-radius: 8px;
            padding: 30px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        .auth-form-container {
            max-width: 500px;
            margin: 0 auto;
            background: #fff;
            border-radius: 8px;
            padding: 30px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        .auth-form h1 {
            font-size: 24px;
            font-weight: 600;
            color: #333;
        }

        .form-group .form-label {
            font-weight: 600;
            color: #555;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }

        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #004085;
        }

        .btn-link {
            font-weight: 600;
            color: #007bff;
        }

        .btn-link:hover {
            color: #0056b3;
        }

        .form-control {
            border-radius: 8px;
            box-shadow: none;
        }
    </style>



</@main.layout>
