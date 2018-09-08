<nav aria-label="Page navigation example">
    <ul class="pagination">
        <li class="page-item">
            <a class="page-link" href="/parts_list/?page=<#if page!=0>${page - 1}<#else>0</#if>
                                                <#if sorted??>&sorted=${sorted}</#if>
                                              <#--&search=${search!""}-->" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
                <span class="sr-only">Previous</span>
            </a>
        </li>
        <li class="page-item"><a class="page-link"
                                 href="/parts_list/?page=0
                                 <#if sorted??>&sorted=${sorted}</#if>
                                 <#--&search=${search!""}-->">1</a></li>
        <li class="page-item"><a class="page-link"
                                 href="/parts_list/?page=1
                                 <#if sorted??>&sorted=${sorted}</#if>
                              <#--&search=${search!""}-->">2</a></li>
        <li class="page-item"><a class="page-link"
                                 href="/parts_list/?page=2
                                 <#if sorted??>&sorted=${sorted}</#if>
                                <#--&search=${search!""}-->">3</a></li>
        <li class="page-item">
            <a class="page-link" href="/parts_list/?page=${page + 1}
            <#if sorted??>&sorted=${sorted}</#if> <#--&search=${search!""}-->" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
                <span class="sr-only">Next</span>
            </a>
        </li>
    </ul>
</nav>