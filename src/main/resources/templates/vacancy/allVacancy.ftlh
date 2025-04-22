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
            transform: translateY(-4px);
            box-shadow: 0 12px 28px rgba(245, 197, 24, 0.15);
            border-color: #f5c518;
        }

        .vacancy-card:nth-child(even) {
            background-color: #2a2f34;
            border: 1px solid rgba(245, 197, 24, 0.1);
        }

        .card-title {
            font-size: 1.6rem;
            color: #f5c518;
        }

        p {
            margin-bottom: 0.5rem;
            color: #ced4da;
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
            .card-title {
                font-size: 1.3rem;
            }

            .glass-card {
                padding: 1rem;
            }

            .card-body {
                flex-direction: column !important;
                align-items: flex-start !important;
            }
        }    </style>

    <section class="container py-5">
        <h2 class="mb-5 text-center fw-bold">Вакансии</h2>

        <div class="sort-by d-flex justify-content-end mb-4">
            <label for="sortOptions" class="me-2 align-self-center">Сортировать по:</label>
            <select id="sortOptions" name="sort" class="form-select w-auto bg-dark text-warning border-warning"
                    onchange="window.location.href=this.value;">
                <option disabled selected hidden>Выберите способ</option>
                <option value="${url}?page=${page.number}&size=${page.size}&sort=createdDate,desc">Сначала новые
                </option>
                <option value="${url}?page=${page.number}&size=${page.size}&sort=createdDate,asc">Сначала старые
                </option>
                <option value="${url}?page=${page.number}&size=${page.size}&sort=salary,desc">Высокая зарплата</option>
                <option value="${url}?page=${page.number}&size=${page.size}&sort=salary,asc">Низкая зарплата</option>
                <option value="${url}?page=${page.number}&size=${page.size}&sort=responseCount,asc">Меньше откликов
                </option>
                <option value="${url}?page=${page.number}&size=${page.size}&sort=responseCount,desc">Больше откликов
                </option>
            </select>
        </div>

        <div class="d-flex flex-column gap-4">
            <#list page.content as vacancy>
                <div class="card vacancy-card glass-card w-100 border-0 shadow-sm">
                    <div class="card-body p-4 d-flex flex-column flex-md-row justify-content-between align-items-start gap-3">
                        <div class="flex-grow-1">
                            <h4 class="card-title mb-3">
                                <i class="bi bi-briefcase-fill me-2"></i>${vacancy.name}
                            </h4>

                            <#assign currentAuthor = author?filter(u -> u.id == vacancy.authorId)?first>
                            <p>
                                <i class="bi bi-building me-1 text-warning"></i><strong>Компания:</strong> ${currentAuthor.name}
                            </p>
                            <p>
                                <i class="bi bi-currency-dollar me-1 text-success"></i><strong>Зарплата:</strong> ${vacancy.salary}
                            </p>
                            <#assign vacancyCategory = category?filter(c -> c.id == vacancy.categoryId)?first>
                            <p>
                                <i class="bi bi-folder-fill me-1 text-warning"></i><strong>Категория:</strong> ${vacancyCategory.name}
                            </p>
                        </div>

                        <div class="d-flex align-self-md-center">
                            <a href="/vacancy/info/${vacancy.id}" class="btn btn-outline-primary btn-sm">
                                <i class="bi bi-eye me-1"></i> Подробнее
                            </a>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </section>

    <@p.pager url=url currentPage=page />
</@main.layout>
