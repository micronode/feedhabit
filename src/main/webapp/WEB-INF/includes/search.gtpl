<div class="search">
    <img src="/images/fh48.png" style="float:left;margin:auto"/>
    <form action="search.groovy" method="GET">
        <input type="text" name="q" value="${request.getParameter('q') ?: ''}"/>
        <input type="submit" value="Search"/>
    </form>
</div>
