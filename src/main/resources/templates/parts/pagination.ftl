<nav aria-label="Page navigation example">
    <ul class="pagination">
        <li class="page-item">
            <a class="page-link" <#--href="/books?page=${page - 1} " -->aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
                <span class="sr-only">Previous</span>
            </a>
        </li>
        <li class="page-item"><a class="page-link" href="/books?page=1">1</a></li>
        <li class="page-item"><a class="page-link" href="/books?page=2">2</a></li>
        <li class="page-item"><a class="page-link" href="/books?page=3">3</a></li>
        <li class="page-item">
            <a class="page-link" <#--href="/books?page=${page + 1}"--> aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
                <span class="sr-only">Next</span>
            </a>
        </li>
    </ul>
</nav>