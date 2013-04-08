<% feedhabit  = [
    version: '1.0',
    copyrightYear: '2013',
    hostInfo: { InetAddress.localHost.hostAddress },
    memory: [total: {org.apache.commons.io.FileUtils.byteCountToDisplaySize(Runtime.runtime.totalMemory())}, free: {org.apache.commons.io.FileUtils.byteCountToDisplaySize(Runtime.runtime.freeMemory())}],
    subscriptions: request.jcrSession.rootNode['mn:subscriptions'].nodes.toList().size(),
    articles: { def count = 0; request.jcrSession.rootNode['mn:subscriptions'].traverse { if (it['mn:seen']) count++ }; count},
   ] %>
<div class="footer" style="text-align:center;color:lightgray">
    <p>
        FeedHabit version ${feedhabit.version}. Copyright (c) ${feedhabit.copyrightYear}<br/>
        Host: ${feedhabit.hostInfo()}, Memory: ${feedhabit.memory.free()} / ${feedhabit.memory.total()}<br/>
        Subscriptions: ${feedhabit.subscriptions}, Articles: ${feedhabit.articles()}
    </p>
</div>
