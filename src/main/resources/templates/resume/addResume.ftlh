<#import "../layout.ftlh" as main>
<@main.layout ; spring>
<title>Создание резюме</title>

<form method="post" action="/resume/add">
    <#if _csrf??>
        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
               value="${(_csrf.token)!'csrf-token'}"/>
    </#if>
    <div class="resume-container">
        <h1><@spring.message "resume.created.title"/></h1>

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
                <option value=""><@spring.message "resume.select.category"/></option>
                <#list category as cat>
                    <option value="${cat.id}"
                            <#if (resumeDto.categoryId?? && resumeDto.categoryId == cat.id)>selected</#if>>${cat.name}</option>
                </#list>
            </select>
        </div>

        <div class="form-group">
            <label for="salary"><@spring.message "resume.created.salary"/>:</label>
            <@spring.formInput "resumeDto.salary" 'id="salary" class="form-control"' />
            <div class="error-block">
                <@spring.showErrors "<br>" "custom-error" />
            </div>
        </div>

        <div class="form-group">
            <label><@spring.message "resume.created.experience"/>:</label>
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
        </div>

        <div class="form-group">
            <label><@spring.message "resume.created.education"/>:</label>
            <@spring.formInput "resumeDto.educationInfos[0].institution" 'placeholder="Учебное заведение" class="form-control"' />
            <div class="error-block">
                <@spring.showErrors "<br>" "custom-error" />
            </div>
            <@spring.formInput "resumeDto.educationInfos[0].program" 'placeholder="Программа обучения" class="form-control"' />
            <div class="error-block">
                <@spring.showErrors "<br>" "custom-error" />
            </div>
            <@spring.formInput "resumeDto.educationInfos[0].degree" 'placeholder="Степень (например: Бакалавр)" class="form-control"' />
            <div class="error-block">
                <@spring.showErrors "<br>" "custom-error" />
            </div>
            <div class="form-group">
                <label><@spring.message "resume.created.education.startDate"/>:</label>
                <input type="date" name="educationInfos[0].startDate" placeholder="Дата начала" class="form-control"/>
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>
            </div>
            <div class="form-group">
                <label><@spring.message "resume.created.education.endDate"/>:</label>
                <input type="date" name="educationInfos[0].endDate" placeholder="Дата окончания" class="form-control"/>
                <div class="error-block">
                    <@spring.showErrors "<br>" "custom-error" />
                </div>
            </div>

        </div>

        <div class="form-group">
            <label><@spring.message "resume.created.contactInfo"/>:</label>

            <input type="hidden" name="contactInfos[0].typeId" value="1"/>
            <@spring.formInput "resumeDto.contactInfos[0].value" 'placeholder="+996 999 555 555" class="form-control"' />
            <div class="error-block">
                <@spring.showErrors "<br>" "custom-error" />
            </div>

            <input type="hidden" name="contactInfos[1].typeId" value="2"/>
            <@spring.formInput "resumeDto.contactInfos[1].value" 'placeholder="example@mail.com" type="email" class="form-control"' />

            <div class="error-block">
                <@spring.showErrors "<br>" "custom-error" />
            </div>

            <input type="hidden" name="contactInfos[2].typeId" value="3"/>
            <@spring.formInput "resumeDto.contactInfos[2].value" 'placeholder="https://t.me/username" type="url" class="form-control"' />
            <div class="error-block">
                <@spring.showErrors "<br>" "custom-error" />
            </div>

            <input type="hidden" name="contactInfos[3].typeId" value="4"/>
            <@spring.formInput "resumeDto.contactInfos[3].value" 'placeholder="https://facebook.com/yourprofile" type="url" class="form-control"' />
            <div class="error-block">
                <@spring.showErrors "<br>" "custom-error" />
            </div>

            <input type="hidden" name="contactInfos[4].typeId" value="4"/>
            <@spring.formInput "resumeDto.contactInfos[4].value" 'placeholder="https://linkedin.com/in/yourprofile" type="url" class="form-control"' />
            <div class="error-block">
                <@spring.showErrors "<br>" "custom-error" />
            </div>
        </div>


        <div class="form-group">
            <label><@spring.message "resume.created.status"/>:</label>
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
            <button type="submit" class="submit-button"><@spring.message "resume.created.button"/></button>
        </div>

    </div>

    </@main.layout>
