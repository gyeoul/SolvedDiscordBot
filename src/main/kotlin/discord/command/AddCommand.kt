package dev.gyeoul.discord.command

import org.javacord.api.interaction.*

class AddCommand {
    fun registerCommand(): SlashCommandBuilder {
        val problemAdd = listOf(
            SlashCommandOption.createWithOptions(
                SlashCommandOptionType.SUB_COMMAND_GROUP,
                "add",
                "문제를 등록합니다",
                getProblemAddOptions(),
            ),
            SlashCommandOption.createWithOptions(
                SlashCommandOptionType.SUB_COMMAND_GROUP,
                "user",
                "사용자를 관리합니다",
                getUserOptions(),
            ),
        )
        return SlashCommand.with(
            "boj", "백준 사이트 관련 커맨드", problemAdd
        )
    }

    private fun getUserOptions(): List<SlashCommandOption> {
        return listOf(
            SlashCommandOption.createWithOptions(
                SlashCommandOptionType.SUB_COMMAND,
                "info",
                "사용자 정보를 조회합니다.",
                SlashCommandOptionBuilder()
                    .setType(SlashCommandOptionType.STRING)
                    .setName("name")
                    .setDescription("사용자의 백준 아이디를 입력해주세요.")
                    .setRequired(true)
            ),
            SlashCommandOption.createWithOptions(
                SlashCommandOptionType.SUB_COMMAND,
                "add",
                "봇에 사용자를 등록합니다.",
                SlashCommandOptionBuilder()
                    .setType(SlashCommandOptionType.STRING)
                    .setName("url")
                    .setDescription("문제 URL 을 입력해주세요")
                    .setRequired(true)
            )
        )
    }

    private fun getProblemAddOptions(): List<SlashCommandOption> {
        return listOf(
            SlashCommandOption.createWithOptions(
                SlashCommandOptionType.SUB_COMMAND,
                "number",
                "문제번호로 등록합니다.",
                SlashCommandOptionBuilder()
                    .setType(SlashCommandOptionType.LONG)
                    .setName("number")
                    .setDescription("문제 번호를 입력해주세요")
                    .setRequired(true)
            ),
            SlashCommandOption.createWithOptions(
                SlashCommandOptionType.SUB_COMMAND,
                "url",
                "url 로 등록합니다.",
                SlashCommandOptionBuilder()
                    .setType(SlashCommandOptionType.STRING)
                    .setName("url")
                    .setDescription("문제 URL 을 입력해주세요")
                    .setRequired(true)
            )
        )
    }
}