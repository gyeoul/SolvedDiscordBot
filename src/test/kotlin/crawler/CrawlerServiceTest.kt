package crawler

import dev.gyeoul.crawler.CrawlerService
import org.junit.jupiter.api.Test

internal class CrawlerServiceTest {
    private val crawlerService: CrawlerService = CrawlerService()

    @Test
    fun testGetProblemInfo() {
        val result = crawlerService.getProblemInfo(1000L)
        println(result.titleKo)
        println(result.problemId)
        println(result.level)
        println(result.tags.map { tags ->
            tags.displayNames.filter { tag -> tag.language == "ko" }
                .map { tag -> tag.name }
        }.flatten())
    }

    @Test
    fun testGetUserByName() {
        val result = crawlerService.getUserByName("gyeoul")
        println(result)
    }
}

