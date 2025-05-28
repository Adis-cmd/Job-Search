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
        p {
            color: #555;
            font-size: 16px;
            margin-bottom: 20px;
        }
        input[type="email"] {
            width: 100%;
            padding: 12px 15px;
            border: 1px solid #ccc;
            border-radius: 8px;
            margin-bottom: 20px;
        }
        input[type="submit"] {
            background: #3498db;
            color: #fff;
            border: none;
            padding: 12px 25px;
            border-radius: 8px;
            cursor: pointer;
            width: 100%;
            transition: background 0.3s ease;
        }
        input[type="submit"]:hover {
            background: #2980b9;
        }
        .text-danger {
            color: #e74c3c;
            text-align: center;
            display: block;
            margin-bottom: 10px;
        }
        .text-warning {
            color: #f39c12;
            text-align: center;
            display: block;
            margin-bottom: 10px;
        }
    </style>

    <div class="card" id="forgotCard">
        <h2><@spring.message "forgot.password.form.title"/></h2>

        <#if error??>
            <b class="text-danger"><@spring.message error/></b>
        </#if>
        <#if message??>
            <b class="text-warning"><@spring.message message/></b>
        </#if>

        <form action="/auth/forgot_password" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>
            </#if>

            <p><@spring.message "forgot.password.form.text"/>.</p>

            <input type="email" name="email" placeholder="<@spring.message "forgot.password.form.placeholder.email"/>" required autofocus />

            <input type="submit" value="Отправить" />
        </form>
    </div>

    <script>
        document.addEventListener("DOMContentLoaded", () => {
            const card = document.getElementById("forgotCard");
            setTimeout(() => {
                card.classList.add("show");
            }, 300);
        });
    </script>

</@main.layout>
