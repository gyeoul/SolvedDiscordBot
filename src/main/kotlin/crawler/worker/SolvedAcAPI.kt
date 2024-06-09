package dev.gyeoul.crawler.worker

import com.google.gson.Gson
import dev.gyeoul.crawler.JsoupWorker
import dev.gyeoul.crawler.response.ProblemResponse
import org.jsoup.Connection

class SolvedAcAPI : JsoupWorker {
    override val baseURL: String
        get() = "https://solved.ac/api/v3"


    fun getProblemInfo(number: Long): ProblemResponse.Problem {
        val response = getNewConnection("/problem/show")
            .data("problemId", "$number")
            .ignoreContentType(true)
            .method(Connection.Method.GET)
            .execute()
            .body()

        return Gson().fromJson(response, ProblemResponse.Problem::class.java)
    }
}