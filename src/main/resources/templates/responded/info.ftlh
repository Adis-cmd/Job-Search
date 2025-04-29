<#import "../layout.ftlh" as main>
<#import "../page.ftlh" as p>
<@main.layout>
    <style>
        .responded-container {
            max-width: 1000px;
            margin: 3rem auto;
            padding: 2rem;
            background: rgba(33, 37, 41, 0.95);
            border-radius: 20px;
            box-shadow: 0 0 20px rgba(245, 197, 24, 0.15);
        }

        .responded-card {
            display: flex;
            align-items: center;
            margin-bottom: 2rem;
            gap: 2rem;
            background-color: #1f1f1f;
            border-radius: 20px;
            padding: 2rem;
            box-shadow: 0 0 20px rgba(245, 197, 24, 0.15);
        }

        .responded-vacancy {
            display: flex;
            flex-direction: column;
        }

        .responded-vacancy h3 {
            font-size: 1.5rem;
            color: #f5c518;
            margin-bottom: 0.5rem;
        }

        .muted-text {
            color: #ced4da;
            margin-bottom: 1rem;
        }

        .btn {
            padding: 0.5rem 1rem;
            border-radius: 12px;
            text-decoration: none;
            font-weight: 600;
            transition: 0.3s;
            display: inline-block;
            margin-top: 0.5rem;
        }

        .btn.outline {
            border: 2px solid #f5c518;
            background-color: transparent;
            color: #f5c518;
        }



    </style>

    <div class="responded-container">
        <#if page.hasContent()>
            <#list page.content as respondedApplicant>
                <div class="responded-card">
                    <div class="responded-vacancy">
                        <h3>Вакансия: ${respondedApplicant.vacancyId.name}</h3>
                        <p class="muted-text">Резюме: ${respondedApplicant.resumeId.name}</p>
                        <p class="muted-text">Статус:
                            <#if respondedApplicant.confirmation>
                                Подтвержден
                            <#else>
                                Не подтвержден
                            </#if>
                        </p>
                        <a href="/vacancy/info/${respondedApplicant.vacancyId.id}" class="btn outline">Перейти к вакансии</a>
                    </div>
                </div>
            </#list>
        <#else>
            <p>У вас нет откликов.</p>
        </#if>
    </div>
    <@p.pager url=url currentPage=page />

</@main.layout>
