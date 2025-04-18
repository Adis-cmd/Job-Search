<#import "../layout.ftlh" as main>
<@main.layout>
    <section class="container py-5">
        <h2 class="mb-4 text-center">Список всех вакансий</h2>

        <div class="row g-4">
            <#list vacancies as vacancy>
                <div class="col-md-6 col-lg-4">
                    <div class="card h-100 shadow-sm border-0 vacancy-card">
                        <div class="card-body">
                            <h5 class="card-title">${vacancy.name}</h5>
                            <p class="mb-1"><strong>Опыт от:</strong> ${vacancy.expFrom}</p>
                            <p class="mb-1"><strong>Опыт до:</strong> ${vacancy.expTo}</p>
                            <p class="mb-1"><strong>Компания:</strong> ${vacancy.authorId.name}</p>
                            <p class="mb-1"><strong>Создано:</strong> ${vacancy.createdDate}</p>
                            <p class="mb-1"><strong>Обновлено:</strong> ${vacancy.updatedTime}</p>

                            <p class="mb-1">
                                <strong>Статус:</strong>
                                <span class="badge ${vacancy.isActive?then('bg-success', 'bg-secondary')}">
                                ${vacancy.isActive?then('Активна', 'Не активна')}
                            </span>
                            </p>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </section>
</@main.layout>
