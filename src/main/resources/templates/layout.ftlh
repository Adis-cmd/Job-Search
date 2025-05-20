<#import "spring.ftl" as spring>
<#macro layout>

    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="/static/profileStyle.css">
        <link rel="stylesheet" href="/static/addResumeStyle.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
                crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="../static/scripts/main.js"></script>

    </head>
    <body>

    <style>
        html, body {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            width: 100%;
            background-color: #f9f9f9;
        }

        .container {
            max-width: 100%;
            padding-left: 1rem;
            padding-right: 1rem;
        }


        @media (min-width: 768px) {
            .container {
                max-width: 1140px;
                margin: 0 auto;
            }
        }
        .lang-switcher .dropdown-toggle {
            border-radius: 0.5rem;
            padding: 0.4rem 0.75rem;
            font-weight: 500;
            transition: all 0.3s ease;
        }

        .lang-switcher .dropdown-toggle:hover {
            background-color: #ffc107;
            color: #212529;
        }

        .lang-switcher .dropdown-menu {
            min-width: 160px;
            border-radius: 0.5rem;
            padding: 0.5rem 0;
        }

        .lang-switcher .dropdown-item {
            padding: 0.5rem 1rem;
            font-weight: 500;
        }

        .lang-switcher .dropdown-item:hover {
            background-color: #ffc107;
            color: #212529;
        }

    </style>
    </body>
    <body>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
        <div class="container">
            <a class="navbar-brand d-flex align-items-center gap-2" href="/">
                <i class="bi bi-briefcase-fill fs-4 text-warning"></i>
                <span class="fw-bold"><@spring.message "layout.nav.brand"/></span>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0 align-items-lg-center">
                    <li class="nav-item">
                        <a class="nav-link" href="/"><i
                                    class="bi bi-house-door-fill me-1"></i><@spring.message "layout.nav.home"/></a>
                    </li>
                    <#if SPRING_SECURITY_CONTEXT?? && SPRING_SECURITY_CONTEXT.authentication??>
                        <#assign user = SPRING_SECURITY_CONTEXT.authentication.principal>
                        <#if user??>
                            <#assign roles = user.authorities?map(it -> it.authority)>
                            <#if !(roles?seq_contains("APPLICANT"))>
                                <li class="nav-item">
                                    <a class="nav-link" href="/resume">
                                        <i class="bi bi-person-lines-fill me-1"></i><@spring.message "layout.nav.resume"/>
                                    </a>
                                </li>
                            </#if>
                            <#if (roles?seq_contains("APPLICANT"))>
                                <li class="nav-item">
                                    <a class="nav-link" href="/company">
                                        <i class="bi bi-card-list me-1"></i><@spring.message "layout.nav.company" />
                                    </a>
                                </li>
                            </#if>
                        </#if>
                    </#if>
                    <li class="nav-item">
                        <a class="nav-link" href="/vacancy">
                            <i class="bi bi-card-list me-1"></i><@spring.message "layout.nav.vacancy" />
                        </a>
                    </li>
                    <#if SPRING_SECURITY_CONTEXT?? && SPRING_SECURITY_CONTEXT.authentication??>
                        <li class="nav-item">
                            <a class="nav-link" href="/profile">
                                <i class="bi bi-person-circle me-1"></i><@spring.message "layout.nav.profile" />
                            </a>
                        </li>

                        <#if _csrf??>
                            <form action="/logout" method="post">
                                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                                       value="${(_csrf.token)!'--no-token--'}"/>
                                <button type="submit" class="btn  nav-link text-danger d-inline-flex align-items-center"
                                        id="logout">
                                    <i class="bi bi-box-arrow-right me-1"></i><@spring.message "layout.nav.logout"/>
                                </button>
                            </form>
                        </#if>
                    </#if>

                    <#if !(SPRING_SECURITY_CONTEXT?? && SPRING_SECURITY_CONTEXT.authentication??)>
                        <li class="nav-item">
                            <a class="nav-link" href="/auth/login">
                                <i class="bi bi-box-arrow-in-right me-1"></i><@spring.message "layout.nav.login" />
                            </a>
                        </li>
                    </#if>
                    <div class="dropdown lang-switcher ms-3">
                        <button class="btn btn-outline-warning dropdown-toggle d-flex align-items-center gap-2"
                                type="button"
                                id="langDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="bi bi-globe"></i>
                            <@spring.message "layout.nav.lang.exchange"/>
                        </button>
                        <ul class="dropdown-menu dropdown-menu-dark dropdown-menu-end shadow"
                            aria-labelledby="langDropdown">
                            <li>
                                <a class="dropdown-item d-flex align-items-center gap-2" href="?lang=en">
                                    EN <@spring.message "layout.nav.lang_en"/>
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item d-flex align-items-center gap-2" href="?lang=ru">
                                    RU <@spring.message "layout.nav.lang_ru"/>
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item d-flex align-items-center gap-2" href="?lang=ky">
                                    KY <@spring.message "layout.nav.lang_ky"/>
                                </a>
                            </li>
                        </ul>
                    </div>

                </ul>
            </div>
        </div>
    </nav>


    <div class="container">

        <#nested spring>

    </div>

    </body>
    </html>

</#macro>