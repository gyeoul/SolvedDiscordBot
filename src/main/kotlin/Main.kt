package dev.gyeoul

import dev.gyeoul.discord.DiscordBot
import mu.KotlinLogging


private val log = KotlinLogging.logger {}
fun main() {
    log.info("Server Started")
    DiscordBot().run()
}