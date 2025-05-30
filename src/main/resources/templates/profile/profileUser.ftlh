<#import "../layout.ftlh" as main>
<#import "../page/page.ftlh" as p>

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

                .vacancy-section {
                    display: flex;
                    flex-direction: column;
                    gap: 2rem;
                }

                .pagination-container {
                    display: flex;
                    justify-content: center;
                    margin-top: 1.5rem;
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

                .resume-section {
                    display: flex;
                    flex-direction: column;
                    gap: 2rem;
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
                    border: none;
                    cursor: pointer;
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

                .btn.refresh {
                    background-color: #28a745;
                    color: white;
                    border: 2px solid #28a745;
                }

                .btn.refresh:hover {
                    background-color: #218838;
                    border-color: #218838;
                    box-shadow: 0 0 12px rgba(40, 167, 69, 0.6);
                    transform: translateY(-2px);
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

                .empty-message {
                    padding: 2rem;
                    background-color: #2c2c2c;
                    border-radius: 8px;
                    color: #ccc;
                    text-align: center;
                    font-size: 1.1rem;
                    margin-top: 2rem;
                }

                .empty-message i {
                    font-size: 3rem;
                    color: #f5c518;
                    margin-bottom: 1rem;
                }

                .empty-message span {
                    display: block;
                    font-size: 1.1rem;
                    color: #ccc;
                    margin-top: 1rem;
                }

                .notification-badge {
                    display: inline-block;
                    background-color: #f5c518;
                    color: #212529;
                    padding: 0.4rem 0.8rem;
                    border-radius: 50%;
                    font-weight: 700;
                    min-width: 35px;
                    text-align: center;
                    font-size: 1rem;
                    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                    transition: transform 0.2s ease-in-out;
                }

                .notification-badge:hover {
                    transform: scale(1.1);
                }

                .responded-container {
                    display: flex;
                    align-items: center;
                    gap: 0.5rem;
                    margin-top: 0.5rem;
                }

                .btn-outline {
                    display: inline-block;
                    padding: 0.6rem 1rem;
                    border: 2px solid #004d40;
                    color: #004d40;
                    font-weight: 600;
                    border-radius: 8px;
                    text-decoration: none;
                    transition: all 0.3s ease-in-out;
                }

                .btn-outline:hover {
                    background-color: #004d40;
                    color: #ffffff;
                }

                /* Стили для flash сообщений */
                .alert {
                    padding: 1rem;
                    margin-bottom: 1rem;
                    border-radius: 8px;
                    font-weight: 500;
                }

                .alert.success {
                    background-color: #d4edda;
                    color: #155724;
                    border: 1px solid #c3e6cb;
                }

                .alert.error {
                    background-color: #f8d7da;
                    color: #721c24;
                    border: 1px solid #f5c6cb;
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
                <!-- Flash сообщения -->
                <#if successMessage??>
                    <div class="alert success">
                        ${successMessage}
                    </div>
                </#if>

                <#if errorMessage??>
                    <div class="alert error">
                        ${errorMessage}
                    </div>
                </#if>

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
                        <a href="/profile/edit" class="btn-outline"><@spring.message "profile.edit"/></a>

                        <#if roles?seq_contains("APPLICANT")>
                            <div class="responded-container">
                                <a href="/responded/info" class="btn-outline"><@spring.message "profile.responded"/></a>
                                <div class="notification-badge">${countApplicant}</div>
                            </div>
                        </#if>

                        <#if roles?seq_contains("EMPLOYEE")>
                            <div class="responded-container">
                                <a href="/responded/info" class="btn-outline"><@spring.message "profile.responded"/></a>
                                <div class="notification-badge">${countEmployee}</div>
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

                        <#if resumePage?? && resumePage.content?has_content>
                            <div class="resume-section">
                                <div class="card-grid">
                                    <#list resumePage.content as resume>
                                        <div class="info-card">
                                            <h3>${resume.name}</h3>
                                            <p><@spring.message "profile.created.resume.vacancy.date"/> ${resume.formattedCreatedDate}</p>
                                            <p><@spring.message "profile.updated.resume.vacancy.date"/> ${resume.formattedUpdatedTime}</p>
                                            <div class="actions">
                                                <a href="/resume/edit/${resume.id}" class="btn small outline"><@spring.message "profile.edit"/></a>

                                                <form action="/resume/time/${resume.id}" method="post" style="display: inline;">
                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                    <button type="submit" class="btn small refresh">
                                                        <@spring.message "profile.refresh"/>
                                                    </button>
                                                </form>
                                            </div>
                                        </div>
                                    </#list>
                                </div>

                                <div class="pagination-container">
                                    <@p.pager url=url currentPage=resumePage />
                                </div>
                            </div>
                        <#else>
                            <div class="empty-message">
                                <i class="bi bi-file-earmark-x"></i>
                                <span><@spring.message "profile.resume.notFound" /></span>
                            </div>
                        </#if>
                    </#if>

                    <#if roles?seq_contains("EMPLOYEE")>
                        <div class="section-header">
                            <h2><@spring.message "profile.vacancy"/></h2>
                            <a href="/vacancy/add" class="btn primary"><@spring.message "profile.created.vacancy"/></a>
                        </div>
                        <div class="card-grid">
                            <#if vacancyPage?? && vacancyPage.content?has_content>
                                <div class="vacancy-section">
                                    <div class="card-grid">
                                        <#list vacancyPage.content as vacancy>
                                            <div class="info-card">
                                                <h3>${vacancy.name}</h3>
                                                <p><@spring.message "profile.created.resume.vacancy.date"/>
                                                    : ${vacancy.formattedCreatedDate}</p>
                                                <p><@spring.message "profile.updated.resume.vacancy.date"/>
                                                    : ${vacancy.formattedUpdatedTime}</p>
                                                <div class="actions">
                                                    <a href="/vacancy/edit/${vacancy.id}"
                                                       class="btn small outline"><@spring.message "profile.edit"/></a>

                                                    <form action="/vacancy/time/${vacancy.id}" method="post" style="display: inline;">
                                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                        <button type="submit" class="btn small refresh">
                                                            <@spring.message "profile.refresh"/>
                                                        </button>
                                                    </form>
                                                </div>
                                            </div>
                                        </#list>
                                    </div>

                                    <div class="pagination-container">
                                        <@p.pager url=url currentPage=vacancyPage />
                                    </div>
                                </div>
                            <#else>
                                <div class="empty-message">
                                    <i class="bi bi-file-earmark-x"></i>
                                    <span><@spring.message "profile.vacancy.notFound" /></span>
                                </div>
                            </#if>

                        </div>
                    </#if>

                </div>
            </div>

        </@main.layout>
    </#if>
</#if>