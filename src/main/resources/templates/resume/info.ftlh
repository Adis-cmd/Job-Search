<#import "../layout.ftlh" as main>
<#import "../page/page.ftlh" as p>
<@main.layout ; spring>
    <style>
        body {
            color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        h2 {
            color: #f5c518;
            letter-spacing: 1px;
        }

        .alert-dark {
            background: rgba(52, 58, 64, 0.85);
            border: 1px solid rgba(245, 197, 24, 0.15);
            color: #ced4da;
            backdrop-filter: blur(6px);
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
                    <p>
                        <i class="bi bi-person-fill me-2 text-warning"></i><strong><@spring.message "resume.info.applicantName"/>
                            :</strong> ${applicant.name}</p>
                    <p>
                        <i class="bi bi-currency-dollar me-2 text-success"></i><strong><@spring.message "resume.info.salary"/>
                            :</strong> ${resumeDto.salary}</p>
                    <p>
                        <i class="bi bi-folder-fill me-2 text-warning"></i><strong><@spring.message "resume.info.category"/>
                            :</strong> ${category.name}</p>
                </div>

                <div class="col-md-6 mb-3">
                    <p>
                        <i class="bi bi-calendar-plus me-2 text-info"></i><strong><@spring.message "resume.info.created"/>
                            :</strong> ${resumeDto.formattedCreatedDate}</p>
                    <p>
                        <i class="bi bi-clock-history me-2 text-info"></i><strong><@spring.message "resume.info.updated"/>
                            :</strong> ${resumeDto.formattedUpdatedTime}</p>
                    <p>
                        <i class="bi bi-circle-fill me-2 text-warning"></i><strong><@spring.message "resume.info.status"/>
                            :</strong>
                        <span class="badge ${resumeDto.isActive?then('bg-success', 'bg-secondary')}">
                            ${resumeDto.isActive?then(springMacroRequestContext.getMessage("vacancy.info.active"),
                            springMacroRequestContext.getMessage("vacancy.info.noActive"))}
                        </span>
                    </p>
                </div>
            </div>

            <hr class="my-4 border-warning">

            <div>
                <h4 class="text-warning"><i
                            class="bi bi-info-circle me-2"></i><@spring.message "resume.info.workExperience"/></h4>
                <#if workExperiences?? && (workExperiences.content?size > 0)>
                    <#list workExperiences.content as work>
                        <p class="work-item"><strong><@spring.message "resume.info.workCompany"/>
                                :</strong> ${work.companyName}</p>
                        <p><strong><@spring.message "resume.info.workPosition"/>:</strong> ${work.position}</p>
                        <p><strong><@spring.message "resume.info.workResponsibilities"/>
                                :</strong> ${work.responsibilities}</p>
                        <p><strong><@spring.message "resume.info.workYears"/>:</strong> ${work.years}
                            <#if work.years == 1>
                                <@spring.message "resume.info.year"/>
                            <#else>
                                <@spring.message "resume.info.years"/>
                            </#if>
                        </p>
                        <hr class="border-secondary">
                    </#list>
                    <@p.pager url=url currentPage=workExperiences />
                <#else>
                    <div class="alert alert-dark d-flex align-items-center gap-2 p-3 rounded-3">
                        <i class="bi bi-person-workspace text-warning fs-5"></i>
                        <span><@spring.message "resume.info.workNotFound"/>.</span>
                    </div>
                </#if>
            </div>

            <hr class="my-4 border-warning">

            <div>
                <h4 class="text-warning"><i
                            class="bi bi-mortarboard-fill me-2"></i><@spring.message "resume.info.education"/></h4>
                <#if educationInfos?? && (educationInfos.content?size > 0)>
                    <#list educationInfos.content as edu>
                        <p class="education-item"><strong><@spring.message "resume.info.educationInstitution"/>
                                :</strong> ${edu.institution}</p>
                        <p><strong><@spring.message "resume.info.educationProgram"/>:</strong> ${edu.program}</p>
                        <p><strong><@spring.message "resume.info.educationDegree"/>:</strong> ${edu.degree}</p>
                        <p><strong><@spring.message "resume.info.educationDate"/>:</strong> ${edu.startDate}
                            — ${edu.endDate}</p>
                        <hr class="border-secondary">
                    </#list>
                    <@p.pager url=url currentPage=educationInfos />
                <#else>
                    <div class="alert alert-dark d-flex align-items-center gap-2 p-3 rounded-3">
                        <i class="bi bi-journal-x text-warning fs-5"></i>
                        <span><@spring.message "resume.info.educationNotFound"/>.</span>
                    </div>
                </#if>
            </div>
        </div>

    </section>
</@main.layout>
