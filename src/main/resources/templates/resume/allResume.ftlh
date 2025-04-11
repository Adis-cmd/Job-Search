<#import "../layout.ftlh" as main>
<@main.layout>
    <section class="container py-5">
        <h2 class="mb-4 text-center">Список всех резюме</h2>

        <div class="row g-4">
            <#list resumes as resume>
                <div class="col-md-6 col-lg-4">
                    <div class="card h-100 shadow-sm resume-card">
                        <div class="card-body">
                            <h5 class="card-title">${resume.name}</h5>
                            <p class="mb-1"><strong>Зарплата:</strong> ${resume.salary}</p>
                            <p class="mb-1"><strong>Статус:</strong> <#if resume.isActive?? && resume.isActive>Активно<#else>Не активно</#if></p>
                            <p class="mb-1"><strong>Создано:</strong> ${resume.createdDate}</p>
                            <p><strong>Обновлено:</strong> ${resume.updateTime}</p>
                        </div>
                        <div class="card-footer bg-white border-top-0 d-flex justify-content-between align-items-center">
                            <a href="/resume/view/${resume.id}" class="btn btn-sm btn-primary">Посмотреть</a>
                            <span class="badge bg-light text-dark">${resume.category!''}</span>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </section>
</@main.layout>
