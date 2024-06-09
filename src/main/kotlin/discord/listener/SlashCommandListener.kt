package dev.gyeoul.discord.listener

import dev.gyeoul.crawler.CrawlerService
import mu.KotlinLogging
import org.javacord.api.entity.message.embed.EmbedBuilder
import org.javacord.api.event.interaction.SlashCommandCreateEvent
import org.javacord.api.listener.interaction.SlashCommandCreateListener
import java.awt.Color


class SlashCommandListener : SlashCommandCreateListener {
    private val log = KotlinLogging.logger {}
    override fun onSlashCommandCreate(event: SlashCommandCreateEvent?) {
        if (event == null) return

        val slashCommandInteraction = event.slashCommandInteraction
        log.info {
            slashCommandInteraction.user.name + " -> " + slashCommandInteraction.fullCommandName + slashCommandInteraction.arguments.map {
                it.getArgumentByIndex(
                    1
                )
            }
        }
        slashCommandInteraction.respondLater()
            .thenAccept {
                //                interactionOriginalResponseUpdater
                //                    .setContent("사용자 정보를 조회합니다...")
                //                    .update()

                val messageBuilder = slashCommandInteraction.createFollowupMessageBuilder()
                val content = when (slashCommandInteraction.fullCommandName) {
                    "boj add url" -> {
                        slashCommandInteraction.getArgumentByName("url").get().stringValue.get()
                    }

                    "boj add number" -> {
                        slashCommandInteraction.getArgumentByName("number").get().longValue.get()
                    }

                    "boj user info" -> {
                        try {
                            val name = slashCommandInteraction.getArgumentByName("name").get().stringValue.get()
                            val user = CrawlerService().getUserByName(name)
                            val embed = EmbedBuilder()
                                .setTitle(user.name)
                                .setDescription(user.bio)
                                .addField("티어", "${user.tier}", true)
                                .addField("시도한 문제", "${user.problems.count()}", true)
                                .addField("랭킹", "${user.rank}", true)
                                .setColor(Color.ORANGE)
                            messageBuilder.addEmbeds(embed)
                            ""
                        } catch (e: Exception) {
                            log.warn { e.message }
                            "사용자 정보 조회에 실패했습니다."
                        }
                    }

                    else -> {
                        "등록되지 않은 명령입니다."
                    }
                }


                messageBuilder
                    .setContent("$content")
                    .send()
            }


        //        event.interaction
        //            .createImmediateResponder()
        //            .setContent("$content")
        //            .respond();
    }
}