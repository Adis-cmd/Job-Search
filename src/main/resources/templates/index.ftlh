<#import "layout.ftlh" as main>
<@main.layout ; sping>
    <style>
        body {
            background-color: #212529;
            color: #f8f9fa;
        }

        .hero h1 {
            font-size: 3rem;
            font-weight: 700;
        }

        .hero p {
            font-size: 1.2rem;
            color: #f8f9fa;
        }

        .features {
            padding: 4rem 0;
            background-color: #1a1d20;
        }

        .card {
            background-color: rgba(33, 37, 41, 0.95);
            color: #f8f9fa;
            border: 1px solid rgba(245, 197, 24, 0.15);
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.5);
            transition: transform 0.3s ease;
        }

        .card:hover {
            transform: translateY(-5px);
            border-color: #f5c518;
            box-shadow: 0 10px 25px rgba(245, 197, 24, 0.2);
        }

        .card-img-top {
            border-radius: 12px 12px 0 0;
            height: 180px;
            object-fit: cover;
        }

        footer {
            background-color: #121416;
            padding: 2rem 0;
            color: #adb5bd;
            text-align: center;
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
            transform: translateY(-4px);
            box-shadow: 0 12px 28px rgba(245, 197, 24, 0.15);
            border-color: #f5c518;
        }

        .carousel-item {
            padding: 20px 0;
        }

        .carousel-control-prev-icon,
        .carousel-control-next-icon {
            background-size: 100% 100%;
            width: 2rem;
            height: 2rem;
        }
    </style>

    <#if message??>
        <div class="alert alert-warning d-flex align-items-center gap-2 p-3 rounded-3 mt-4">
            <i class="bi bi-exclamation-triangle-fill text-dark fs-5"></i>
            <span>${message}</span>
        </div>
    </#if>

    <section class="container py-5">
        <h2 class="mb-4 text-center fw-bold" style="color: #f5c518;"><@sping.message "index.h2.header" /></h2>

        <div id="featureCarousel" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner">

                <div class="carousel-item active">
                    <div class="card text-white glass-card mx-auto" style="max-width: 700px;">
                        <img src="https://images.unsplash.com/photo-1510511459019-5dda7724fd87?auto=format&fit=crop&w=800&q=80"
                             class="card-img-top" alt="Security">
                        <div class="card-body">
                            <h5 class="card-title text-warning"><@sping.message "index.carousel.title" /></h5>
                            <p class="card-text"><@sping.message "index.carousel.text" /></p>
                        </div>
                    </div>
                </div>

                <div class="carousel-item">
                    <div class="card text-white glass-card mx-auto" style="max-width: 700px;">
                        <img src="https://images.unsplash.com/photo-1519389950473-47ba0277781c?auto=format&fit=crop&w=800&q=80"
                             class="card-img-top" alt="AI Matching">
                        <div class="card-body">
                            <h5 class="card-title text-warning"><@sping.message "index.carousel.title.vacancy" /></h5>
                            <p class="card-text"><@sping.message "index.carousel.text.vacancy" /></p>
                        </div>
                    </div>
                </div>

                <div class="carousel-item">
                    <div class="card text-white glass-card mx-auto" style="max-width: 700px;">
                        <img src="https://images.unsplash.com/photo-1593642532400-2682810df593?auto=format&fit=crop&w=800&q=80"
                             class="card-img-top" alt="Mobile Friendly">
                        <div class="card-body">
                            <h5 class="card-title text-warning"><@sping.message "index.carousel.body.title" /></h5>
                            <p class="card-text"><@sping.message "index.carousel.body.text" /></p>
                        </div>
                    </div>
                </div>

            </div>

            <button class="carousel-control-prev" type="button" data-bs-target="#featureCarousel" data-bs-slide="prev">
                <span class="carousel-control-prev-icon bg-dark rounded-circle" aria-hidden="true"></span>
                <span class="visually-hidden">Предыдущий</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#featureCarousel" data-bs-slide="next">
                <span class="carousel-control-next-icon bg-dark rounded-circle" aria-hidden="true"></span>
                <span class="visually-hidden">Следующий</span>
            </button>
        </div>
    </section>
    <section class="features container">
        <div class="row text-center g-4">
            <div class="col-md-4">
                <div class="card p-4 h-100">
                    <img src="https://images.pexels.com/photos/3184303/pexels-photo-3184303.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
                         class="card-img-top" alt="Companies">
                    <div class="card-body">
                        <h5 class="card-title text-warning"><@sping.message "index.card.title.companies" /></h5>
                        <p class="card-text"><@sping.message "index.card.text.companies" /></p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card p-4 h-100">
                    <img src="https://images.pexels.com/photos/3184307/pexels-photo-3184307.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
                         class="card-img-top" alt="Applicants">
                    <div class="card-body">
                        <h5 class="card-title text-warning"><@sping.message "index.cart.title.applicants" /></h5>
                        <p class="card-text"><@sping.message "index.cart.text.applicants" /></p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card p-4 h-100">
                    <img src="https://images.pexels.com/photos/3184311/pexels-photo-3184311.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
                         class="card-img-top"
                         alt="Vacancies">
                    <div class="card-body">
                        <h5 class="card-title text-warning"><@sping.message "index.cart.title.vacancies" /></h5>
                        <p class="card-text"><@sping.message "index.cart.text.vacancies" /></p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <footer>
        <div class="container">
            <p>© ${.now?string["yyyy"]} <@sping.message "index.footer.rights" /> </p>
            <p><@sping.message "index.footer.social" />>:
                <a href="#" style="color:#f5c518;">VK</a> |
                <a href="#" style="color:#f5c518;">Instagram</a> |
                <a href="#" style="color:#f5c518;">Telegram</a>
            </p>
        </div>
    </footer>
</@main.layout>
