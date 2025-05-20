<#macro pager url currentPage>
    <style>
        .pagination {
            display: flex;
            flex-wrap: wrap;
            gap: 0.3rem;
            padding: 1rem 0;
        }

        .page-item {
            list-style: none;
        }

        .page-link {
            color: #f5c518;
            background-color: transparent;
            border: 2px solid #f5c518;
            padding: 0.4rem 0.75rem;
            border-radius: 8px;
            transition: all 0.3s ease;
            font-weight: 500;
            text-decoration: none;
        }

        .page-link:hover {
            background-color: #f5c518;
            color: #212529;
            box-shadow: 0 0 10px #f5c51899;
        }

        .page-item.active .page-link {
            background-color: #f5c518;
            color: #212529;
            font-weight: bold;
            box-shadow: 0 0 10px #f5c518cc;
        }

        .page-item.disabled .page-link {
            color: #6c757d;
            border-color: #6c757d;
            cursor: not-allowed;
        }


        .sort-by label {
            margin-bottom: 0;
        }


    </style>

    <#if currentPage.getTotalPages() gt 7>
        <#assign
        totalPages = currentPage.getTotalPages()
        pageNumber = currentPage.getNumber() + 1

        head = (pageNumber > 4)?then([1, -1], [1, 2, 3])
        tail = (pageNumber < totalPages - 3)?then([-1, totalPages], [totalPages - 2, totalPages - 1, totalPages])
        bodyBefore = (pageNumber > 4 && pageNumber < totalPages - 1)?then([pageNumber - 2, pageNumber - 1], [])
        bodyAfter = (pageNumber > 2 && pageNumber < totalPages - 3)?then([pageNumber + 1, pageNumber + 2], [])

        body = head + bodyBefore + (pageNumber > 3 && pageNumber < totalPages - 2)?then([pageNumber], []) + bodyAfter + tail
        >
    <#else>
        <#assign body = 1..currentPage.getTotalPages()>
    </#if>
    <ul class="pagination">
        <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1">Страницы</a>
        </li>
        <#list body as p>
            <#if (p - 1) == currentPage.getNumber()>
                <li class="page-item active">
                    <a class="page-link" href="#" tabindex="-1">${p}</a>
                </li>
            <#elseif p == -1>
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1">...</a>
                </li>
            <#else>
                <li class="page-item">
                    <a class="page-link"
                       href="${url}?page=${p - 1}&size=${currentPage.getSize()}"
                       tabindex="-1">${p}</a>
                </li>
            </#if>
        </#list>
    </ul>
</#macro>
