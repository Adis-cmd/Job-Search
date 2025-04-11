<#import "spring.ftl" as spring>
<#macro layout>

    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
                crossorigin="anonymous"></script>
        <script src="../static/scripts/main.js"></script>
        <title>Job Search</title>
    </head>
    <body>

        <style>
            body {
                background-color: #f4f6f9;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }

            .navbar {
                position: sticky;
                top: 0;
                z-index: 1030;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }

            .navbar-brand {
                font-weight: bold;
                font-size: 1.4rem;
            }

            .nav-link {
                font-size: 1rem;
                transition: color 0.3s ease;
            }

            .nav-link:hover {
                color: #ffc107 !important;
            }

            main.container {
                background-color: #fff;
                padding: 2rem;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0,0,0,0.05);
                margin-top: 2rem;
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