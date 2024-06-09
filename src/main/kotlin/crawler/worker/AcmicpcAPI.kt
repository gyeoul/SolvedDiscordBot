package dev.gyeoul.crawler.worker

import dev.gyeoul.crawler.JsoupWorker
import dev.gyeoul.entity.BOJProfile
import org.jsoup.Connection
import org.jsoup.nodes.Document

class AcmicpcAPI : JsoupWorker {
    override val baseURL: String
        get() = "https://www.acmicpc.net"

    fun getUserByName(name: String): BOJProfile {
        val doc = getNewConnection("/user/$name")
            .method(Connection.Method.GET)
            .get()
        doc.let {
            return BOJProfile(
                name = getUserName(it),
                bio = getUserBio(it),
                tier = getUserTier(it),
                rank = getUserRank(it),
                problems = getUserProblems(it).toMutableSet()
            )
        }
    }


    private fun getUserName(doc: Document): String = doc.selectFirst("h1")?.text()?.trim() ?: ""
    private fun getUserBio(doc: Document): String = doc.selectFirst("blockquote.no-mathjax")?.ownText() ?: "."
    private fun getUserRank(doc: Document): Int =
        doc.selectFirst("#statics > tbody > tr:nth-child(1) > td")?.text()?.toInt() ?: 0

    private fun getUserProblems(doc: Document): List<Int> = doc.select(".problem-list > a").map {
        it.text().toInt()
    }

    private fun getUserTier(doc: Document): Int {
        val src = doc.select("h1").select("img").attr("src")
        return if (src.isNotBlank() && src.isNotEmpty()) src.split("/").last().split(".").first().toInt() else 0
    }
}