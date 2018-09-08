<nav class="navbar navbar-expand-lg navbar-light bg-light" >
    <a class="navbar-brand" href="/">Im Ilya</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/parts_list/">Table parts</a>
            </li>
        </ul>
    </div>
    <!-- Search form -->
    <form class="form-inline" <#--action="/parts_list/" method="get"-->>
        <input class="form-control mr-sm-2" type="search" name="search" value="${search?ifExists}" placeholder="Поиск по таблице" aria-label="Search">
        <button class="btn btn-light my-2 my-sm-0" type="submit"><i>Искать</i></button>
    </form>
</nav>