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
            text-align: center;
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
            margin-bottom: 20px;
        }
        p {
            color: #555;
            font-size: 18px;
            margin-bottom: 30px;
            word-break: break-word;
        }
        a.button {
            display: inline-block;
            padding: 12px 25px;
            background: #3498db;
            color: #fff;
            border-radius: 8px;
            text-decoration: none;
            transition: background 0.3s ease;
        }
        a.button:hover {
            background: #2980b9;
        }
    </style>

<div class="card" id="tokenCard">
    <h2>Ваш токен для сброса пароля:</h2>
    <p>${token}</p>

    <a href="/auth/reset_password?token=${token}" class="button"><@spring.message "auth.reset.password"/></a>
</div>


<script>
    document.addEventListener("DOMContentLoaded", () => {
        const card = document.getElementById("tokenCard");
        setTimeout(() => {
            card.classList.add("show");
        }, 300);
    });
</script>


</@main.layout>