<#import "../layout.ftlh" as main>
<#import "../page.ftlh" as p>

<#if SPRING_SECURITY_CONTEXT?? && SPRING_SECURITY_CONTEXT.authentication??>
    <#assign user = SPRING_SECURITY_CONTEXT.authentication.principal>
    <#if user??>
        <#assign roles = user.authorities?map(it -> it.authority)>
        <#assign userName = user.getUsername()>

        <@main.layout ; spring>
            <style>
                body {
                    color: #f8f9fa;
                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                }

                .profile-container {
                    max-width: 1000px;
                    margin: 3rem auto;
                    padding: 2rem;
                    background: rgba(33, 37, 41, 0.95);
                    border-radius: 20px;
                    box-shadow: 0 0 20px rgba(245, 197, 24, 0.15);
                }

                .profile-card {
                    display: flex;
                    align-items: center;
                    margin-bottom: 2rem;
                    gap: 2rem;
                    background-color: #1f1f1f;
                    border-radius: 20px;
                    padding: 2rem;
                    box-shadow: 0 0 20px rgba(245, 197, 24, 0.15);
                }

                .profile-avatar img {
                    width: 120px;
                    height: 120px;
                    border-radius: 50%;
                    object-fit: cover;
                    border: 3px solid #f5c518;
                    box-shadow: 0 0 10px #f5c51844;
                }

                .profile-details h1 {
                    font-size: 2rem;
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

                .btn.primary {
                    background-color: #f5c518;
                    color: #212529;
                    box-shadow: 0 0 10px #f5c51866;
                }

                .btn.outline {
                    border: 2px solid #f5c518;
                    background-color: transparent;
                    color: #f5c518;
                }

                .btn.outline:hover,
                .btn.primary:hover {
                    box-shadow: 0 0 12px #f5c51899;
                    transform: translateY(-2px);
                }

                .section-header {
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                    margin-top: 3rem;
                    margin-bottom: 1.5rem;
                }

                .section-header h2 {
                    color: #f5c518;
                    font-size: 1.5rem;
                }

                .card-grid {
                    display: grid;
                    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
                    gap: 1.5rem;
                }

                .info-card {
                    background-color: #1f1f1f;
                    border: 1px solid rgba(245, 197, 24, 0.2);
                    border-radius: 12px;
                    padding: 1.2rem;
                    transition: 0.3s;
                    box-shadow: 0 6px 16px rgba(0, 0, 0, 0.4);
                }

                .info-card h3 {
                    color: #f5c518;
                    margin-bottom: 0.5rem;
                }

                .info-card p {
                    color: #ced4da;
                    margin: 0.3rem 0;
                }

                .info-card .actions {
                    margin-top: 1rem;
                    display: flex;
                    gap: 0.5rem;
                    flex-wrap: wrap;
                }

                .btn.small {
                    font-size: 0.85rem;
                    padding: 0.4rem 0.8rem;
                }

                @media (max-width: 768px) {
                    .profile-card {
                        flex-direction: column;
                        align-items: flex-start;
                    }

                    .section-header {
                        flex-direction: column;
                        align-items: flex-start;
                        gap: 1rem;
                    }

                    .profile-avatar img {
                        width: 100px;
                        height: 100px;
                    }
                }
            </style>

            <div class="profile-container">
                <div class="profile-card">
                    <div class="profile-avatar">
                        <img src="<#if page.avatar?starts_with('http')>${page.avatar}<#else>users/photos/${page.avatar}</#if>"
                             alt="Профильное фото">
                    </div>
                    <div class="profile-details">
                        <h1>${page.name} <#if page.surname??>${page.surname}</#if></h1>
                        <#if page.age??>
                            <p class="muted-text">${page.age} <@spring.message "profile.age" /></p>
                        </#if>
                        <a href="/profile/edit" class="btn outline"><@spring.message "profile.edit"/></a>
                        <#if roles?seq_contains("APPLICANT")>
                            <div style="margin-top: 0.5rem;">
                                <a href="/responded/info" class="btn outline"><@spring.message "profile.responded"/></a>
                            </div>
                        </#if>
                    </div>
                </div>

                <div class="profile-sections">

                    <#if roles?seq_contains("APPLICANT")>
                        <div class="section-header">
                            <h2><@spring.message "profile.resume"/></h2>
                            <a href="/resume/add" class="btn primary"><@spring.message "profile.created.resume"/></a>
                        </div>
                        <div class="card-grid">
                            <#list resumePage.content as resume>
                                <div class="info-card">
                                    <h3>${resume.name}</h3>
                                    <p><@spring.message "profile.created.resume.vacancy.date"/> ${resume.formattedCreatedDate}</p>
                                    <p><@spring.message "profile.updated.resume.vacancy.date"/> ${resume.formattedUpdatedTime}</p>
                                    <div class="actions">
                                        <a href="/resume/update/${resume.id}" class="btn small primary"><@spring.message "profile.updated.resume"/></a>
                                        <a href="/resume/edit/${resume.id}" class="btn small outline"><@spring.message "profile.edit"/></a>
                                    </div>
                                </div>
                            </#list>
                        </div>
                        <@p.pager url=url currentPage=resumePage />
                    </#if>

                    <#if roles?seq_contains("EMPLOYEE")>
                        <div class="section-header">
                            <h2><@spring.message "profile.vacancy"/></h2>
                            <a href="/vacancy/add" class="btn primary"><@spring.message "profile.created.vacancy"/></a>
                        </div>
                        <div class="card-grid">
                            <#list vacancyPage.content as vacancy>
                                <div class="info-card">
                                    <h3>${vacancy.name}</h3>
                                    <p><@spring.message "profile.created.resume.vacancy.date"/>: ${vacancy.formattedCreatedDate}</p>
                                    <p><@spring.message "profile.updated.resume.vacancy.date"/>: ${vacancy.formattedUpdatedTime}</p>
                                    <div class="actions">
                                        <a href="/vacancy/update/${vacancy.id}" class="btn small primary">Обновить</a>
                                        <a href="/vacancy/edit/${vacancy.id}"
                                           class="btn small outline"><@spring.message "profile.edit"/></a>
                                    </div>
                                </div>
                            </#list>
                        </div>
                        <@p.pager url=url currentPage=vacancyPage />
                    </#if>

                </div>
            </div>

        </@main.layout>
    </#if>
</#if>

