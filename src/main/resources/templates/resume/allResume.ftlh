<#import "../layout.ftlh" as main>
<#import "../page.ftlh" as p>
<@main.layout>

    <title>Все Резюме</title>

    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            color: #f8f9fa;
        }

        h2 {
            text-align: center;
            font-size: 28px;
            margin-bottom: 40px;
            color: #f5c518;
            letter-spacing: 1px;
        }

        .resume-card {
            background: rgba(33, 37, 41, 0.95);
            border-radius: 16px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.6);
            border: 1px solid rgba(245, 197, 24, 0.15);
            transition: all 0.4s ease;
            color: #f8f9fa;
        }

        .resume-card:hover {
            transform: translateY(-4px);
            box-shadow: 0 12px 28px rgba(245, 197, 24, 0.15);
            border-color: #f5c518;
        }

        .resume-card:nth-child(even) {
            background-color: #2a2f34;
            border: 1px solid rgba(245, 197, 24, 0.1);
        }

        .card-body {
            padding: 24px 28px;
            display: flex;
            flex-direction: column;
            gap: 12px;
        }

        .card-title {
            font-size: 1.6rem;
            color: #f5c518;
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
            padding: 10px 18px;
            font-size: 14px;
            font-weight: 600;
            text-decoration: none;
            display: inline-block;
            width: fit-content;
        }

        .btn-outline-primary:hover {
            background-color: #f5c518;
            color: #212529;
            border-color: #f5c518;
            box-shadow: 0 0 10px #f5c51899;
        }

        @media (max-width: 768px) {
            .card-body {
                padding: 1rem;
            }

            .card-body {
                flex-direction: column !important;
                align-items: flex-start !important;
            }

            .card-title {
                font-size: 1.3rem;
            }
        }
    </style>

    <section class="container py-5">
        <h2 class="mb-5 fw-bold">Список всех резюме</h2>

        <div class="d-flex flex-column gap-4">
            <#list page.content as resume>
                <div class="card resume-card w-100 border-0 shadow-sm">
                    <div class="card-body d-flex flex-column flex-md-row justify-content-between align-items-start gap-3">

                        <div class="flex-grow-1">
                            <h4 class="card-title mb-3">
                                <i class="bi bi-person-fill me-2"></i>${resume.name}
                            </h4>

                            <p>
                                <i class="bi bi-cash-coin me-1 text-warning"></i><strong>Зарплата:</strong> ${resume.salary}
                            </p>
                            <p>
                                <i class="bi bi-circle-fill me-1 text-warning"></i><strong>Статус:</strong>
                                <span class="badge ${resume.isActive?then('bg-success', 'bg-secondary')}">
                                    ${resume.isActive?then('Активна', 'Не активна')}
                                </span>
                            </p>
                            <p>
                                <i class="bi bi-calendar-plus me-1 text-warning"></i><strong>Создано:</strong> ${resume.formattedCreatedDate}
                            </p>
                            <p>
                                <i class="bi bi-clock-history me-1 text-warning"></i><strong>Обновлено:</strong> ${resume.formattedUpdatedTime}
                            </p>

                            <#assign currentCategory = category?filter(c -> c.id == resume.categoryId)?first>
                            <p>
                                <i class="bi bi-folder-fill me-1 text-warning"></i><strong>Категория:</strong> ${currentCategory.name}
                            </p>
                        </div>

                        <div class="d-flex align-self-md-center">
                            <a href="/resume/info/${resume.id}" class="btn btn-outline-primary btn-sm">
                                <i class="bi bi-eye me-1"></i> Посмотреть
                            </a>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </section>
    <@p.pager url=url currentPage=page />
</@main.layout>