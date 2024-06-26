package dev.gyeoul.crawler

import org.jsoup.Connection
import org.jsoup.Jsoup

interface JsoupWorker : CrawlerWorker {

    fun getNewConnection(path: String): Connection {
        val userAgent =
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36"
        val header = mapOf(
            "Accept" to "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
            "Accept-Language" to "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7",
        )
        return Jsoup
            .connect("$baseURL$path")
            .userAgent(userAgent)
            .headers(header)
    }
}