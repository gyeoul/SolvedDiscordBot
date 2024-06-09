package dev.gyeoul.crawler

import dev.gyeoul.crawler.response.ProblemResponse
import dev.gyeoul.crawler.worker.AcmicpcAPI
import dev.gyeoul.crawler.worker.SolvedAcAPI
import dev.gyeoul.entity.BOJProfile


class CrawlerService {
    fun getProblemInfo(number: Long): ProblemResponse.Problem {
        return SolvedAcAPI().getProblemInfo(number)
    }

    fun getUserByName(name: String): BOJProfile {
        return AcmicpcAPI().getUserByName(name)
    }
}
