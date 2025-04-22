<#import "../layout.ftlh" as main>
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
    </style>

    <section class="container py-5">
        <h2 class="mb-4 text-center text-warning fw-bold">${vacancyDto.name}</h2>

        <div class="card glass-card p-4 shadow-lg">
            <div class="row">
                <div class="col-md-6 mb-3">
                    <p><i class="bi bi-building me-2 text-warning"></i><strong>Компания:</strong> ${author.name}</p>
                    <p><i class="bi bi-currency-dollar me-2 text-success"></i><strong>Зарплата:</strong> ${vacancyDto.salary}</p>
                    <p><i class="bi bi-folder-fill me-2 text-warning"></i><strong>Категория:</strong>
                        ${category.name}</p>
                </div>

                <div class="col-md-6 mb-3">
                    <p><i class="bi bi-bar-chart-fill me-2 text-warning"></i><strong>Опыт от:</strong> ${vacancyDto.expFrom} лет</p>
                    <p><i class="bi bi-bar-chart me-2 text-warning"></i><strong>Опыт до:</strong> ${vacancyDto.expTo} лет</p>
                    <p><i class="bi bi-calendar-plus me-2 text-info"></i><strong>Создано:</strong> ${vacancyDto.formattedCreatedDate}</p>
                    <p><i class="bi bi-clock-history me-2 text-info"></i><strong>Обновлено:</strong> ${vacancyDto.formattedUpdatedTime}</p>
                    <p><i class="bi bi-circle-fill me-2 text-warning"></i><strong>Статус:</strong>
                        <span class="badge ${vacancyDto.isActive?then('bg-success', 'bg-secondary')}">
                            ${vacancyDto.isActive?then('Активна', 'Не активна')}
                        </span>
                    </p>
                </div>
            </div>

            <hr class="my-4 border-warning">

            <div>
                <h4 class="text-warning"><i class="bi bi-info-circle me-2"></i>Описание вакансии</h4>
                <p>${vacancyDto.description}</p>
            </div>
        </div>

        <div class="mt-4 d-flex justify-content-center">
            <a href="/vacancy/respond/${vacancyDto.id}" class="btn btn-outline-primary">
                <i class="bi bi-send-fill me-2"></i>Откликнуться
            </a>
        </div>
    </section>
</@main.layout>
