import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json
import org.mnode.newsagent.*

ratpack {
  handlers {
    get('feed') {
      def reader = new FeedReaderImpl()
      def callback = new FeedCallbackImpl()
      reader.read(new URL('http://figurate.org/feed.xml'), callback)
      render json(callback.feed)
    }
  }
}
