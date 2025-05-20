<#import "../layout.ftlh" as main>
<#import "../page.ftlh" as p>
<@main.layout ; spring>
    <style>
        body {
            color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #ffffff;
            margin: 0;
            padding: 0;
        }

        h2 {
            color: #f5c518;
            letter-spacing: 1px;
            font-size: 2rem;
            font-weight: bold;
            text-align: center;
            margin-bottom: 1.5rem;
        }

        .glass-card {
            background: rgba(33, 37, 41, 0.85);
            backdrop-filter: blur(12px);
            border-radius: 16px;
            transition: all 0.3s ease;
            box-shadow: 0 8px 30px rgba(0, 0, 0, 0.6);
            border: 1px solid rgba(245, 197, 24, 0.1);
            color: #f8f9fa;
            padding: 1.5rem;
        }

        .glass-card:hover {
            transform: translateY(-6px);
            box-shadow: 0 12px 30px rgba(245, 197, 24, 0.2);
            border-color: #f5c518;
        }

        .vacancy-card:nth-child(even) {
            background-color: #343a40;
            border: 1px solid rgba(245, 197, 24, 0.2);
        }

        .card-title {
            font-size: 1.8rem;
            color: #f5c518;
            font-weight: bold;
            margin-bottom: 1rem;
        }

        p {
            margin-bottom: 0.75rem;
            color: #ced4da;
        }

        .btn-outline-primary {
            border-radius: 30px;
            transition: 0.4s;
            border: 2px solid #f5c518;
            color: #f5c518;
            background-color: transparent;
            font-weight: bold;
            padding: 0.5rem 1.25rem;
        }

        .btn-outline-primary:hover {
            background-color: #f5c518;
            color: #212529;
            border-color: #f5c518;
            box-shadow: 0 0 20px #f5c51899;
        }

        @media (max-width: 768px) {
            .card-title {
                font-size: 1.4rem;
            }

            .glass-card {
                padding: 1.2rem;
            }

            .card-body {
                flex-direction: column !important;
                align-items: flex-start !important;
            }
        }

        .search-container {
            position: relative;
            width: 100%;
            max-width: 450px;
            margin: 3rem auto;
        }

        .search-input {
            width: 100%;
            padding: 0.75rem 2.75rem 0.75rem 1.25rem;
            background-color: #343a40;
            border: 1px solid rgba(245, 197, 24, 0.1);
            border-radius: 12px;
            color: #f8f9fa;
            font-size: 1.1rem;
            box-sizing: border-box;
            transition: 0.3s;
            font-weight: 500;
        }

        .search-input:focus {
            border-color: #f5c518;
            outline: none;
        }

        .search-input {
            padding-left: 2.75rem;
        }

        .search-icon {
            position: absolute;
            top: 50%;
            left: 15px;
            transform: translateY(-50%);
            color: #f5c518;
            cursor: pointer;
        }

        .search-results {
            position: absolute;
            top: 100%;
            left: 0;
            width: 100%;
            background-color: #343a40;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.5);
            max-height: 350px;
            overflow-y: auto;
            display: none;
            z-index: 1000;
            margin-top: 0.5rem;
            opacity: 0;
            transition: opacity 0.3s ease-in-out;
        }

        .search-results.active {
            opacity: 1;
            display: block;
        }

        .search-result-item {
            padding: 1rem 1.5rem;
            color: #f5c518;
            cursor: pointer;
            border-bottom: 1px solid rgba(245, 197, 24, 0.1);
            transition: background-color 0.3s, padding-left 0.3s;
            font-size: 1.1rem;
        }

        .search-result-item:hover {
            background-color: rgba(245, 197, 24, 0.15);
            padding-left: 1.25rem;
        }

        .search-result-item.active {
            background-color: rgba(245, 197, 24, 0.2);
        }

        .search-result-item:active {
            background-color: rgba(245, 197, 24, 0.3);
        }

        .search-result-item.no-result {
            color: #6c757d;
            font-style: italic;
            text-align: center;
        }

        .search-results.active {
            display: block;
        }

        .search-result-item:active {
            background-color: rgba(245, 197, 24, 0.3);
        }

        .search-results {
            opacity: 0;
            transition: opacity 0.3s ease-in-out;
        }

        .search-results.active {
            opacity: 1;
        }

    </style>

    <section class="container py-5">
        <h2 class="mb-5 text-center fw-bold"><@spring.message "allVacancy.title" /></h2>

        <div class="sort-by d-flex justify-content-end mb-4">
            <label for="sortOptions" class="me-2 align-self-center"><@spring.message "allVacancy.sort.title" /></label>
            <select id="sortOptions" name="sort" class="form-select w-auto bg-dark text-warning border-warning"
                    onchange="window.location.href=this.value;">
                <option disabled selected hidden><@spring.message "allVacancy.sort.text" /></option>
                <option value="${url}?page=${page.number}&size=${page.size}&sort=createdDate,desc"><@spring.message "allVacancy.sort.new" />
                </option>
                <option value="${url}?page=${page.number}&size=${page.size}&sort=createdDate,asc"><@spring.message "allVacancy.sort.old" />
                </option>
                <option value="${url}?page=${page.number}&size=${page.size}&sort=salary,desc"><@spring.message "allVacancy.sort.highSalary" /></option>
                <option value="${url}?page=${page.number}&size=${page.size}&sort=salary,asc"><@spring.message "allVacancy.sort.lowSalary" /></option>
                <option value="${url}?page=${page.number}&size=${page.size}&sort=responseCount,asc"><@spring.message "allVacancy.sort.fewerResponses" />
                </option>
                <option value="${url}?page=${page.number}&size=${page.size}&sort=responseCount,desc"><@spring.message "allVacancy.sort.moreResponses" />
                </option>
            </select>
        </div>

        <div class="search-container">
            <input type="text" id="searchInput" placeholder="<@spring.message "vacancy.search" />" class="search-input">
            <div id="searchResults" class="search-results"></div>
        </div>

        <div class="vacancies-container d-flex flex-column gap-4">
            <#list page.content as vacancy>
                <div class="card vacancy-card glass-card w-100 border-0 shadow-sm">
                    <div class="card-body p-4 d-flex flex-column flex-md-row justify-content-between align-items-start gap-3">
                        <div class="flex-grow-1">
                            <h4 class="card-title mb-3">
                                <i class="bi bi-briefcase-fill me-2"></i>${vacancy.name}
                            </h4>
                            <#assign currentAuthor = author?filter(u -> u.id == vacancy.authorId)?first>
                            <p>
                                <i class="bi bi-building me-1 text-warning"></i><strong><@spring.message "allVacancy.company" /></strong> ${currentAuthor.name}
                            </p>
                            <p>
                                <i class="bi bi-currency-dollar me-1 text-success"></i><strong><@spring.message "allVacancy.salary" /></strong> ${vacancy.salary}
                            </p>
                            <#assign vacancyCategory = category?filter(c -> c.id == vacancy.categoryId)?first>
                            <p>
                                <i class="bi bi-folder-fill me-1 text-warning"></i><strong><@spring.message "allVacancy.category" /></strong> ${vacancyCategory.name}
                            </p>
                        </div>
                        <div class="d-flex align-self-md-center">
                            <a href="/vacancy/info/${vacancy.id}" class="btn btn-outline-primary btn-sm">
                                <i class="bi bi-eye me-1"></i> <@spring.message "allVacancy.info" />
                            </a>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </section>
    <@p.pager url=url currentPage=page />

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const searchInput = document.getElementById('searchInput');
            const searchResults = document.getElementById('searchResults');
            const searchIcon = document.querySelector('.search-icon');
            let debounceTimer;

            searchInput.addEventListener('input', function (e) {
                clearTimeout(debounceTimer);

                const query = e.target.value.trim();

                if (query.length < 2) {
                    searchResults.style.display = 'none';
                    return;
                }

                debounceTimer = setTimeout(() => {
                    fetchVacancies(query);
                }, 300);
            });

            searchIcon.addEventListener('click', function () {
                const query = searchInput.value.trim();
                if (query.length >= 2) {
                    fetchVacancies(query);
                } else {
                    searchInput.focus();
                }
            });

            function fetchVacancies(query) {
                console.log("Starting search with query:", query);
                searchResults.innerHTML = '<div class="search-result-item">Поиск...</div>';
                searchResults.classList.add('active');
                searchResults.style.display = 'block';

                fetch('/vacancy/search?query=' + encodeURIComponent(query))
                    .then(response => {
                        console.log("Response status:", response.status);
                        return response.json();
                    })
                    .then(vacancies => {
                        console.log('Received vacancies:', vacancies);
                        displayResults(vacancies);
                    })
                    .catch(error => {
                        console.error('Full error:', error);
                        searchResults.innerHTML = '<div class="search-result-item">Error: ' + error.message + '</div>';
                        searchResults.style.display = 'block';
                    });
            }

            function displayResults(vacancies) {
                if (vacancies.length === 0) {
                    searchResults.innerHTML = '<div class="search-result-item no-result">Ничего не найдено</div>';
                    searchResults.classList.add('active');
                    return;
                }

                let resultsHtml = '';
                vacancies.forEach(vacancy => {
                    resultsHtml += `
            <div class="search-result-item"
                 onclick="window.location.href='/vacancy/info/` + vacancy.id + `'"
                 style="padding: 0.5rem 1rem; color: #f5c518; cursor: pointer; border-bottom: 1px solid rgba(245, 197, 24, 0.1);">
                ` + vacancy.name + `
            </div>
        `;
                });

                searchResults.innerHTML = resultsHtml;
                searchResults.classList.add('active');
            }



            document.addEventListener('click', function (e) {
                if (!searchInput.contains(e.target) && !searchIcon.contains(e.target) && !searchResults.contains(e.target)) {
                    searchResults.style.display = 'none';
                }
            });
            searchInput.focus();
        });
    </script>

</@main.layout>
