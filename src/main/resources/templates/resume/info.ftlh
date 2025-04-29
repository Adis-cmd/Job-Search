<#import "../layout.ftlh" as main>
<#import "../page.ftlh" as p>
<@main.layout>
    <style>
        body {
            color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        h2 {
            color: #f5c518;
            letter-spacing: 1px;
        }

        .glass-card {
            background: rgba(33, 37, 41, 0.95);
            backdrop-filter: blur(8px);
            border-radius: 16px;
            transition: all 0.4s ease;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.6);
            border: 1px solid rgba(245, 197, 24, 0.15);
            color: #f8f9fa;
        }

        .glass-card:hover {
            transform: translateY(-2px);
            box-shadow: 0 12px 28px rgba(245, 197, 24, 0.15);
            border-color: #f5c518;
        }

        p {
            margin-bottom: 0.5rem;
            color: #ced4da;
        }

        .badge {
            font-size: 0.85rem;
            padding: 0.4em 0.7em;
            border-radius: 12px;
            font-weight: 600;
        }

        .btn-outline-primary {
            border-radius: 12px;
            transition: 0.3s;
            border: 2px solid #f5c518;
            color: #f5c518;
            background-color: transparent;
        }

        .btn-outline-primary:hover {
            background-color: #f5c518;
            color: #212529;
            border-color: #f5c518;
            box-shadow: 0 0 10px #f5c51899;
        }

        @media (max-width: 768px) {
            h2 {
                font-size: 1.5rem;
            }

            .glass-card {
                padding: 1rem;
            }
        }

        .work-item, .education-item {
            font-size: 1.1rem;
        }
    </style>

    <section class="container py-5">
        <h2 class="mb-4 text-center text-warning fw-bold">${resumeDto.name}</h2>

        <#if error??>
            <div class="alert alert-danger" role="alert">
                ${error}
            </div>
        </#if>

        <div class="card glass-card p-4 shadow-lg">
            <div class="row">
                <div class="col-md-6 mb-3">
                    <p><i class="bi bi-person-fill me-2 text-warning"></i><strong>Имя кандидата:</strong> ${applicant.name}</p>
                    <p><i class="bi bi-currency-dollar me-2 text-success"></i><strong>Желаемая зарплата:</strong> ${resumeDto.salary}</p>
                    <p><i class="bi bi-folder-fill me-2 text-warning"></i><strong>Категория резюме:</strong> ${category.name}</p>
                </div>

                <div class="col-md-6 mb-3">
                    <p><i class="bi bi-calendar-plus me-2 text-info"></i><strong>Дата создания:</strong> ${resumeDto.formattedCreatedDate}</p>
                    <p><i class="bi bi-clock-history me-2 text-info"></i><strong>Последнее обновление:</strong> ${resumeDto.formattedUpdatedTime}</p>
                    <p><i class="bi bi-circle-fill me-2 text-warning"></i><strong>Статус:</strong>
                        <span class="badge ${resumeDto.isActive?then('bg-success', 'bg-secondary')}">
                            ${resumeDto.isActive?then('Активно', 'Не активно')}
                        </span>
                    </p>
                </div>
            </div>

            <hr class="my-4 border-warning">

            <div>
                <h4 class="text-warning"><i class="bi bi-info-circle me-2"></i>Опыт работы</h4>
                <#if workExperiences?? && (workExperiences?size > 0)>
                    <#list workExperiences.content as work>
                        <p class="work-item"><strong>Компания:</strong> ${work.companyName}</p>
                        <p><strong>Должность:</strong> ${work.position}</p>
                        <p><strong>Обязанности:</strong> ${work.responsibilities}</p>
                        <p><strong>Работал:</strong> ${work.years}
                            <#if work.years == 1>
                                год
                            <#else>
                                года
                            </#if>
                        </p>
                        <hr class="border-secondary">
                    </#list>


                <#else>
                    <p>Нет данных об опыте работы.</p>
                </#if>
                <@p.pager url=url currentPage=workExperiences />
            </div>

            <hr class="my-4 border-warning">

            <div>
                <h4 class="text-warning"><i class="bi bi-mortarboard-fill me-2"></i>Образование</h4>
                <#if educationInfos?? && (educationInfos?size > 0)>
                    <#list educationInfos.content as edu>
                        <p class="education-item"><strong>Учебное заведение:</strong> ${edu.institution}</p>
                        <p><strong>Программа обучения:</strong> ${edu.program}</p>
                        <p><strong>Степень:</strong> ${edu.degree}</p>
                        <p><strong>Период обучения:</strong> ${edu.startDate} — ${edu.endDate}</p>
                        <hr class="border-secondary">
                    </#list>
                <#else>
                    <p>Нет данных об образовании.</p>
                </#if>
                <@p.pager url=url currentPage=educationInfos />
            </div>
        </div>

        <div class="mt-4 d-flex justify-content-center gap-3">
            <a href="/chat/start/${resumeDto.applicantId}" class="btn btn-outline-primary">
                <i class="bi bi-chat-dots-fill me-2"></i> Начать чат
            </a>
        </div>
    </section>
</@main.layout>
