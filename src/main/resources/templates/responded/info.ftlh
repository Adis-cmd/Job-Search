<#import "../layout.ftlh" as main>
<#import "../page/page.ftlh" as p>

<@main.layout ; spring>

    <#if SPRING_SECURITY_CONTEXT?? && SPRING_SECURITY_CONTEXT.authentication??>
        <#assign user = SPRING_SECURITY_CONTEXT.authentication.principal>
        <#if user??>
            <#assign roles = user.authorities?map(it -> it.authority)>
        </#if>
    </#if>

    <style>
        .responded-container {
            max-width: 1000px;
            margin: 3rem auto;
        }
        .vacancy-card {
            background-color: #212529;
            border: 1px solid rgba(245, 197, 24, 0.15);
            border-radius: 1rem;
            padding: 1.5rem 2rem;
            margin-bottom: 2rem;
            box-shadow: 0 0 15px rgba(245, 197, 24, 0.08);
            animation: fadeIn 0.5s ease-in-out;
        }
        .vacancy-title {
            font-size: 1.8rem;
            font-weight: 700;
            color: #f5c518;
            margin-bottom: 0.5rem;
        }
        .vacancy-subtitle {
            font-size: 1rem;
            color: #ced4da;
            margin-bottom: 1.5rem;
        }
        .status-badge {
            font-size: 0.9rem;
            padding: 0.5rem 1rem;
            border-radius: 50px;
        }
        .btn-outline-gold {
            border: 2px solid #f5c518;
            color: #f5c518;
        }
        .btn-outline-gold:hover {
            background-color: #f5c518;
            color: #1f1f1f;
        }
        .btn-outline-cyan {
            border: 2px solid #0dcaf0;
            color: #0dcaf0;
        }
        .btn-outline-cyan:hover {
            background-color: #0dcaf0;
            color: #1f1f1f;
        }
        .empty-card {
            background-color: #212529;
            border: 1px solid rgba(245, 197, 24, 0.15);
            border-radius: 1rem;
            padding: 2rem;
            box-shadow: 0 0 15px rgba(245, 197, 24, 0.08);
            text-align: center;
            animation: fadeIn 0.5s ease-in-out;
        }
        .empty-card i {
            font-size: 3rem;
            color: #f5c518;
        }
        .empty-card p {
            color: #ced4da;
            font-size: 1.2rem;
            margin-top: 1rem;
        }
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(10px); }
            to { opacity: 1; transform: translateY(0); }
        }
    </style>

    <div class="responded-container">

        <#if roles?seq_contains("APPLICANT")>
            <#if page.hasContent()>
                <#list page.content as respondedApplicant>
                    <div class="vacancy-card">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <div>
                                <div class="vacancy-title">${respondedApplicant.vacancyId.name}</div>
                                <div class="vacancy-subtitle">Откликнутое резюме: ${respondedApplicant.resumeId.name}</div>
                            </div>
                            <div>
                                <#if respondedApplicant.confirmation>
                                    <span class="badge bg-success status-badge">
                                        <i class="bi bi-check-circle"></i> <@spring.message "responded.info.confirmed"/>
                                    </span>
                                <#else>
                                    <span class="badge bg-secondary status-badge">
                                        <i class="bi bi-hourglass-split"></i> <@spring.message "responded.info.noConfirmed"/>
                                    </span>
                                </#if>
                            </div>
                        </div>
                        <div class="d-flex gap-3">
                            <a href="/vacancy/info/${respondedApplicant.vacancyId.id}" class="btn btn-outline-gold">
                                <i class="bi bi-eye"></i> <@spring.message "responded.info.button"/>
                            </a>
                            <a href="/chat/${respondedApplicant.id}" class="btn btn-outline-cyan">
                                <i class="bi bi-chat-dots"></i> <@spring.message "chat.write"/>
                            </a>
                        </div>
                    </div>
                </#list>
                <@p.pager url=url currentPage=page />
            <#else>
                <div class="empty-card">
                    <i class="bi bi-file-earmark-x"></i>
                    <p><@spring.message "responded.notRespond"/>.</p>
                </div>
            </#if>

        <#elseif roles?seq_contains("EMPLOYEE")>
            <#if employee.hasContent()>
                <#list employee.content as respondedApplicant>
                    <div class="vacancy-card">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <div>
                                <div class="vacancy-title">${respondedApplicant.resumeId.name}</div>
                                <div class="vacancy-subtitle">Отклик на вакансию: ${respondedApplicant.vacancyId.name}</div>
                            </div>
                            <div>
                                <#if respondedApplicant.confirmation>
                                    <span class="badge bg-success status-badge">
                                        <i class="bi bi-check-circle"></i> <@spring.message "responded.info.confirmed"/>
                                    </span>
                                <#else>
                                    <span class="badge bg-secondary status-badge">
                                        <i class="bi bi-hourglass-split"></i> <@spring.message "responded.info.noConfirmed"/>
                                    </span>
                                </#if>
                            </div>
                        </div>
                        <div class="d-flex gap-3">
                            <a href="/resume/info/${respondedApplicant.resumeId.id}" class="btn btn-outline-gold">
                                <i class="bi bi-eye"></i> Посмотреть резюме
                            </a>
                            <a href="/chat/${respondedApplicant.id}" class="btn btn-outline-cyan">
                                <i class="bi bi-chat-dots"></i> Написать в чат
                            </a>
                        </div>
                    </div>
                </#list>
                <@p.pager url=url currentPage=employee />
            <#else>
                <div class="empty-card">
                    <i class="bi bi-file-earmark-x"></i>
                    <p>Пока нет откликов на ваши вакансии.</p>
                </div>
            </#if>
        </#if>

    </div>

</@main.layout>
