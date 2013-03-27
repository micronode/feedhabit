<% feedhabit  = [
    version: '1.0',
    copyrightYear: '2012',
    hostInfo: { InetAddress.localHost.hostAddress },
    memory: [total: {org.apache.commons.io.FileUtils.byteCountToDisplaySize(Runtime.runtime.totalMemory())}, free: {org.apache.commons.io.FileUtils.byteCountToDisplaySize(Runtime.runtime.freeMemory())}],
   ] %>
<div class="footer" style="text-align:center;color:lightgray">
    <p>
        FeedHabit version ${feedhabit.version}. Copyright (c) ${feedhabit.copyrightYear}<br/>
        Host: ${feedhabit.hostInfo()}, Memory: ${feedhabit.memory.free()} / ${feedhabit.memory.total()}
    </p>
</div>
