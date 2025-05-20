<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Окак Кошка</title>
    <style>
        body {
            margin: 0;
            padding: 20px;
            background: linear-gradient(to bottom, #000, #111);
            color: #fff;
            font-family: Arial, sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
            user-select: none;
            text-align: center;
            min-height: 100vh;
            justify-content: center;
            overflow: hidden;
        }

        .card {
            display: flex;
            align-items: center;
            background: rgba(40, 40, 40, 0.95);
            padding: 35px;
            border-radius: 25px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.8);
            max-width: 85vw;
            gap: 40px;
            opacity: 0;
            transform: translateY(30px);
            transition: opacity 1s ease, transform 1s ease;
        }

        img {
            max-width: 600px;
            width: 55%;
            height: auto;
            border-radius: 30px;
            box-shadow: 0 0 30px rgba(0, 0, 0, 0.9);
        }

        .info {
            flex: 1;
            text-align: left;
        }

        .info h3, .info h3, .info p {
            opacity: 0;
            transform: translateX(-30px);
            transition: opacity 0.8s ease, transform 0.8s ease;
        }

        .info h3 {
            font-size: 4rem;
            font-weight: bold;
            color: #ff9900;
            margin-bottom: 25px;
            transition-delay: 0.5s;
        }

        .info h3 {
            font-size: 1.8rem;
            margin-bottom: 15px;
            color: #ff9900;
            transition-delay: 0.8s;
        }

        .info p {
            font-size: 1.4rem;
            color: #ddd;
            margin-bottom: 12px;
            transition-delay: 1.1s;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background: #ff9900;
            color: #000;
            text-decoration: none;
            border-radius: 10px;
            font-size: 1.2rem;
            opacity: 0;
            transition: opacity 0.8s ease, background 0.3s;
            transition-delay: 1.4s;
        }

        a:hover {
            background: #ff6600;
        }
    </style>
</head>
<body>

<div class="card">
    <img src="/static/фотка.webp" alt="Окак Кошка в капюшоне"/>

    <div class="info">
        <#if message??>
            <h3>${message}</h3>
        </#if>
        <#if reason??>
            <h3>Ошибка: ${reason}</h3>
        </#if>
        <#if status??>
            <p>Статус: ${status}</p>
        </#if>
        <#if details??>
            <p>Детали: ${details}</p>
        </#if>
        <a href="/">Назад на главную</a>
    </div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        document.querySelector(".card").style.opacity = "1";
        document.querySelector(".card").style.transform = "translateY(0)";

        const elements = document.querySelectorAll(".info h3, .info h3, .info p, a");
        elements.forEach((el, index) => {
            setTimeout(() => {
                el.style.opacity = "1";
                el.style.transform = "translateX(0)";
            }, index * 300);
        });
    });
</script>

</body>
</html>
