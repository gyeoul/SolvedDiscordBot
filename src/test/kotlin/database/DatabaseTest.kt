package database

import dev.gyeoul.crawler.CrawlerService
import dev.gyeoul.database.Database
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DatabaseTest {

    val db = Database()

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun updateProblems() {
        val user = CrawlerService().getUserByName("gyeoul")
        db.updateProblems(user)
    }

    @Test
    fun getUserInfo() {
        val name = "gyeoul"
        db.getUserInfo(name)
    }
}