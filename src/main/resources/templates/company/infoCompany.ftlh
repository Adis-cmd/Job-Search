<#import "../layout.ftlh" as main>
<#import "../page.ftlh" as p>
<@main.layout>
    <style>
        body {
            color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        .company-container {
            max-width: 1000px;
            margin: 3rem auto;
            padding: 2rem;
            background: rgba(33, 37, 41, 0.95);
            border-radius: 20px;
            box-shadow: 0 0 20px rgba(245, 197, 24, 0.15);
        }

        .company-header {
            display: flex;
            align-items: center;
            gap: 1.5rem;
            margin-bottom: 2rem;
        }

        .company-avatar img {
            width: 120px;
            height: 120px;
            border-radius: 50%;
            object-fit: cover;
            border: 3px solid #f5c518;
        }

        .company-info h1 {
            font-size: 2rem;
            color: #f5c518;
        }

        .muted-text {
            color: #ced4da;
        }

        .section-header {
            margin-top: 2rem;
            margin-bottom: 1rem;
            color: #f5c518;
            font-size: 1.5rem;
        }

        .vacancy-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 1.5rem;
        }

        .vacancy-card {
            background-color: #1f1f1f;
            border: 1px solid rgba(245, 197, 24, 0.2);
            border-radius: 12px;
            padding: 1.2rem;
            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.4);
        }

        .vacancy-card h3 {
            color: #f5c518;
            margin-bottom: 0.5rem;
        }

        .vacancy-card p {
            color: #ced4da;
            margin: 0.3rem 0;
        }
    </style>

    <div class="company-container">
        <div class="company-header">
            <div class="company-avatar">
                <img src="/users/photos/${user.avatar}" alt="Логотип компании">
            </div>
            <div class="company-info">
                <h1>${user.name}</h1>
                <#if user.email??>
                    <p class="muted-text">Email: ${user.email}</p>
                </#if>
                <#if user.phone??>
                    <p class="muted-text">Телефон: ${user.phoneNumber}</p>
                </#if>
            </div>
        </div>

        <div>
            <div class="section-header">Вакансии компании</div>
            <div class="vacancy-grid">
                <#list vacancyDtos.content as vacancy>
                    <div class="vacancy-card">
                        <h3>${vacancy.name}</h3>
                        <p>Создано: ${vacancy.formattedCreatedDate}</p>
                        <p>Обновлено: ${vacancy.formattedUpdatedTime}</p>
                        <p>${vacancy.description?truncate(120, "...")}</p>
                    </div>
                </#list>
            </div>
            <@p.pager url="/info/${user.id}" currentPage=vacancyDtos />
        </div>
    </div>
</@main.layout>
