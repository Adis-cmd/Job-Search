<#import "../layout.ftlh" as main>
<#import "../page/page.ftlh" as p>

<@main.layout ; spring>

    <title><@spring.message "company.title" /></title>

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

        .company-card {
            background: rgba(33, 37, 41, 0.95);
            border-radius: 16px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.6);
            border: 1px solid rgba(245, 197, 24, 0.15);
            transition: all 0.4s ease;
            color: #f8f9fa;
        }

        .company-card:hover {
            transform: translateY(-4px);
            box-shadow: 0 12px 28px rgba(245, 197, 24, 0.15);
            border-color: #f5c518;
        }

        .card-body {
            padding: 24px 28px;
            display: flex;
            flex-direction: column;
            gap: 12px;
        }

        .company-name {
            font-size: 1.6rem;
            font-weight: bold;
            color: #f5c518;
        }

        p {
            margin-bottom: 0.5rem;
            color: #ced4da;
        }

        .avatar-img {
            width: 100px;
            height: 100px;
            object-fit: cover;
            border-radius: 50%;
            border: 2px solid #f5c518;
            margin-bottom: 15px;
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
        }
    </style>

    <section class="container py-5">
        <h2 class="mb-5 fw-bold"><@spring.message "company.all" /></h2>

        <div class="d-flex flex-column gap-4">
            <#list page.content as company>
                <div class="card company-card w-100 border-0 shadow-sm">
                    <div class="card-body d-flex flex-column flex-md-row align-items-start gap-4">
                        <div class="text-center">
                            <img src="users/photos/${company.avatar}" alt="Аватар компании" class="avatar-img">
                        </div>
                        <div class="flex-grow-1">
                            <div class="company-name mb-2">${company.name}</div>
                            <p><i class="bi bi-envelope-fill text-warning me-1"></i> ${company.email}</p>
                            <p><i class="bi bi-telephone-fill text-warning me-1"></i> ${company.phoneNumber}</p>
                        </div>
                        <div class="align-self-center">
                            <a href="/company/info/${company.id}" class="btn btn-outline-primary">
                                <i class="bi bi-eye me-1"></i> <@spring.message "company.view" />
                            </a>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </section>
    <@p.pager url=url currentPage=page />
</@main.layout>
