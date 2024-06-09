package dev.gyeoul.discord

import dev.gyeoul.discord.listener.SlashCommandListener
import mu.KotlinLogging
import org.javacord.api.DiscordApi
import org.javacord.api.DiscordApiBuilder
import org.javacord.api.entity.intent.Intent


class DiscordBot {
    private val discordToken = System.getenv("DISCORD_TOKEN")
    private val serverId = System.getenv("SERVER_ID").toLong()
    private val log = KotlinLogging.logger {}
    private val api: DiscordApi = DiscordApiBuilder()
        .setToken(discordToken)
        .setIntents(
            Intent.MESSAGE_CONTENT,
            Intent.GUILD_PRESENCES,
            Intent.GUILD_MEMBERS
        ).login().join()

    fun run() {
        log.info(api.servers.toString())
        val server = api.getServerById(serverId).get()
        //        api.bulkOverwriteServerApplicationCommands(server, setOf(AddCommand().registerCommand()))
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