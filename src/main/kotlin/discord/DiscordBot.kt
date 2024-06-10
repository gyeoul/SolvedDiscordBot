package dev.gyeoul.discord

import dev.gyeoul.discord.command.AddCommand
import dev.gyeoul.discord.listener.SlashCommandListener
import mu.KLogger
import mu.KotlinLogging
import org.javacord.api.DiscordApi
import org.javacord.api.DiscordApiBuilder
import org.javacord.api.entity.intent.Intent


class DiscordBot {
    private val log = KotlinLogging.logger {}
    private val discordToken: String
    private val serverId: Long
    private val api: DiscordApi

    init {
        val token = System.getenv("DISCORD_TOKEN") ?: throw IllegalArgumentException("Discord token is null")
        val server = System.getenv("SERVER_ID") ?: throw IllegalArgumentException("Server id is null")
        discordToken = token
        serverId = server.toLong()

        val apiBuilder = DiscordApiBuilder()
            .setToken(discordToken)
            .setIntents(
                Intent.MESSAGE_CONTENT,
                Intent.GUILD_PRESENCES,
                Intent.GUILD_MEMBERS
            )
        api = apiBuilder.login().join() ?:  throw IllegalStateException("Cannot login to Discord")
    }

    fun run() {
        log.info(api.servers.toString())
        val server = api.getServerById(serverId).get()
                api.bulkOverwriteServerApplicationCommands(server, setOf(AddCommand().registerCommand()))
        //        api.bulkOverwriteGlobalApplicationCommands(setOf())
        api.getServerSlashCommands(server).get().map {
            log.info("${it.fullCommandNames} ${it.name}")
        }
        api.addListener(SlashCommandListener())
    }

    fun getApi(): DiscordApi {
        return api
    }

}