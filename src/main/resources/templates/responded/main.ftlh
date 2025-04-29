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
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.6);
            border: 1px solid rgba(245, 197, 24, 0.15);
            color: #f8f9fa;
            transition: all 0.4s ease;
        }

        .glass-card:hover {
            transform: translateY(-4px);
            box-shadow: 0 12px 28px rgba(245, 197, 24, 0.15);
            border-color: #f5c518;
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
            box-shadow: 0 0 10px #f5c51899;
        }

        p {
            margin-bottom: 0.5rem;
            color: #ced4da;
        }
    </style>

    <section class="container py-5">

        <h2 class="mb-5 text-center fw-bold">Отклик на вакансию</h2>

        <div class="card glass-card w-100 border-0 shadow-sm mb-5">
            <div class="card-body p-4">
                <h4 class="card-title mb-3">
                    <i class="bi bi-briefcase-fill me-2"></i>${vacancy.name}
                </h4>
                <p><i class="bi bi-info-circle-fill me-1 text-warning"></i>${vacancy.description}</p>
            </div>
        </div>

        <h2 class="mb-4">Ваши резюме:</h2>

        <#if page?size == 0>
            <p>У вас пока нет резюме для отклика.</p>
        <#else>
            <div class="d-flex flex-column gap-4">
                <#list page.content as res>
                    <div class="card glass-card w-100 border-0 shadow-sm">
                        <div class="card-body p-4 d-flex flex-column flex-md-row justify-content-between align-items-start gap-3">
                            <div>
                                <h4 class="card-title mb-3">
                                    <i class="bi bi-person-fill me-2"></i>${res.name}
                                </h4>
                                <p><strong>Дата создания:</strong> ${res.formattedCreatedDate}</p>
                                <p><strong>Категория:</strong> ${category.name}</p>
                                <p><strong>Заработная плата:</strong> ${res.salary}</p>
                            </div>
                            <div class="align-self-md-center">
                                <form method="post" action="/responded/send">
                                    <#if _csrf??>
                                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                               value="${(_csrf.token)!'csrf-token'}"/>
                                    </#if>
                                    <input type="hidden" name="vacancyId" value="${vacancy.id}">
                                    <input type="hidden" name="resumeId" value="${res.id}">
                                    <button type="submit" class="btn btn-outline-primary btn-sm"
                                            <#if respondedResumeIds?seq_contains(res.id)>
                                                disabled="disabled"
                                            </#if>
                                    >
                                        <#if !(respondedResumeIds?seq_contains(res.id))>
                                            <i class="bi bi-send me-1"></i> Откликнуться этим резюме
                                        <#else>
                                            <p class="text-success fw-semibold">
                                                <i class="bi bi-check-circle-fill me-1"></i> Уже откликались
                                            </p>
                                        </#if>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </#if>

    </section>
    <@p.pager url=url currentPage=page />
</@main.layout>
