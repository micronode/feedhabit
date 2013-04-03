<div class="search">
    <form action="search.groovy" method="GET">
        <input type="text" name="q" value="${request.getParameter('q') ?: ''}"/>
        <input type="submit" value="Search"/>
    </form>
</div>
