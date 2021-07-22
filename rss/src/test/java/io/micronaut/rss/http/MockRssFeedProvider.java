package io.micronaut.rss.http;

import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.core.async.publisher.Publishers;
import io.micronaut.rss.RssChannel;
import io.micronaut.rss.RssFeedProvider;
import io.micronaut.rss.RssItem;
import io.micronaut.rss.language.RssLanguage;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @see <a href="https://cyber.harvard.edu/rss/examples/rss2sample.xml">https://cyber.harvard.edu/rss/examples/rss2sample.xml</a>
 */
//tag::class[]
@Singleton
class MockRssFeedProvider implements RssFeedProvider {
    RssChannel rssChannel = RssChannel.builder("Liftoff News", "http://liftoff.msfc.nasa.gov/", "Liftoff to Space Exploration.")
            .language(RssLanguage.LANG_ENGLISH_UNITED_STATES)
            .pubDate(ZonedDateTime.of(LocalDateTime.of(2003, 6, 10, 4, 0, 0), ZoneId.of("GMT")))
            .lastBuildDate(ZonedDateTime.of(LocalDateTime.of(2003, 6, 10, 9, 41, 1), ZoneId.of("GMT")))
            .docs("http://blogs.law.harvard.edu/tech/rss")
            .generator("Weblog Editor 2.0")
            .managingEditor("editor@example.com")
            .webMaster("webmaster@example.com")
            .item(RssItem.builder()
                .title("Star City")
                .link("http://liftoff.msfc.nasa.gov/news/2003/news-starcity.asp")
                .description("How do Americans get ready to work with Russians aboard the International Space Station? They take a crash course in culture, language and protocol at Russia's &lt;a href=\"http://howe.iki.rssi.ru/GCTC/gctc_e.htm\"&gt;Star City&lt;/a&gt;.")
               .pubDate(ZonedDateTime.of(LocalDateTime.of(2003, 6, 3, 9, 39, 21), ZoneId.of("GMT")))
                .guid("http://liftoff.msfc.nasa.gov/2003/06/03.html#item573")
                .build())
            .item(RssItem.builder()
                .description("Sky watchers in Europe, Asia, and parts of Alaska and Canada will experience a &lt;a href=\"http://science.nasa.gov/headlines/y2003/30may_solareclipse.htm\"&gt;partial eclipse of the Sun&lt;/a&gt; on Saturday, May 31st.")
                .pubDate(ZonedDateTime.of(LocalDateTime.of(2003, 5, 30, 11, 6, 42), ZoneId.of("GMT")))
                .guid("http://liftoff.msfc.nasa.gov/2003/05/30.html#item572")
                .build())
            .item(RssItem.builder()
                .title("The Engine That Does More")
                .link("http://liftoff.msfc.nasa.gov/news/2003/news-VASIMR.asp")
                .description("Before man travels to Mars, NASA hopes to design new engines that will let us fly through the Solar System more quickly.  The proposed VASIMR engine would do that.")
                .pubDate(ZonedDateTime.of(LocalDateTime.of(2003, 5, 27, 8, 37, 32), ZoneId.of("GMT")))
                .guid("http://liftoff.msfc.nasa.gov/2003/05/27.html#item571")
            .build())
            .item(RssItem.builder()
                .title("Astronauts' Dirty Laundry")
                .link("http://liftoff.msfc.nasa.gov/news/2003/news-laundry.asp")
                .description("Compared to earlier spacecraft, the International Space Station has many luxuries, but laundry facilities are not one of them.  Instead, astronauts have other options.")
                .pubDate(ZonedDateTime.of(LocalDateTime.of(2003, 5, 20, 8, 56, 02), ZoneId.of("GMT")))
                .guid("http://liftoff.msfc.nasa.gov/2003/05/20.html#item570")
                .build())
            .build();

    @Override
    @SingleResult
    public Publisher<RssChannel> fetch() {
        return Publishers.just(rssChannel);
    }

    @Override
    @SingleResult
    public Publisher<RssChannel> fetchById(Serializable id) {
        if(id != null && id.equals("1")) {
            return Publishers.just(rssChannel);
        }
        return Publishers.empty();
    }
}
//end::class[]
