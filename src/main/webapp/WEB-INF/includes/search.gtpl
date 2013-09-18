<div class="search">
    <a href="http://feedhabit.com" style="border:0"><img src="/images/fh48.png" style="float:left;margin:auto"/></a>
    <form action="search.groovy" method="GET">
        <input type="text" name="q" value="${request.getParameter('q') ?: ''}" placeholder="Search.."/>
        <input type="submit" value="Search"/>
    </form>
    <a class="subscribe">Subscriptions..</a>
</div>
