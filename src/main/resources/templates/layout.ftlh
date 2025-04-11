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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
                crossorigin="anonymous"></script>
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
        </style>
    </body>
    <body>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Job Search</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="/">Главная Страница</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/resume">Все Резюме</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/vacancy">Все Вакансии</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/profile">Профиль</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/auth/login">Войти</a>
                    </li>
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