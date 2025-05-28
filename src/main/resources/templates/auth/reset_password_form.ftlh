<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f0f2f5;
            margin: 0;
            padding: 0;
        }
        .card {
            background: #fff;
            padding: 35px;
            border-radius: 15px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.1);
            max-width: 500px;
            margin: 100px auto;
            opacity: 0;
            transform: translateY(20px);
            transition: all 0.6s ease;
        }
        .card.show {
            opacity: 1;
            transform: translateY(0);
        }
        h2 {
            color: #333;
            text-align: center;
            margin-bottom: 25px;
        }
        input[type="password"] {
            width: 100%;
            padding: 12px 15px;
            border: 1px solid #ccc;
            border-radius: 8px;
            margin-bottom: 20px;
        }
        button[type="submit"] {
            background: #3498db;
            color: #fff;
            border: none;
            padding: 12px 25px;
            border-radius: 8px;
            cursor: pointer;
            width: 100%;
            transition: background 0.3s ease;
        }
        button[type="submit"]:hover {
            background: #2980b9;
        }
        .text-danger {
            color: #e74c3c;
            text-align: center;
            display: block;
            margin-bottom: 15px;
        }
    </style>

    <div class="card" id="resetCard">
        <h2><@spring.message "reset.password.title" /></h2>

        <#if error??>
            <b class="text-danger"><@spring.message error/></b>
        </#if>

        <form action="/auth/reset_password" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>
            </#if>

            <input type="hidden" name="token" value="${token!}">

            <input type="password" name="password"
                   placeholder="<@spring.message "reset.password.placeholder" />"
                   required autofocus />

            <button type="submit"><@spring.message "reset.password.button" /></button>
        </form>
    </div>

    <script>
        document.addEventListener("DOMContentLoaded", () => {
            const card = document.getElementById("resetCard");
            setTimeout(() => {
                card.classList.add("show");
            }, 300);
        });
    </script>

</@main.layout>
