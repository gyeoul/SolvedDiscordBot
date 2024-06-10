package dev.gyeoul

import dev.gyeoul.discord.DiscordBot
import mu.KotlinLogging


private val log = KotlinLogging.logger {}
fun main() {
    log.info("Server Started")
    try {
        val bot = DiscordBot()
        bot.run()
    } catch (e: Exception) {
        log.error(e) { "Error: " + e.message }
    }
}