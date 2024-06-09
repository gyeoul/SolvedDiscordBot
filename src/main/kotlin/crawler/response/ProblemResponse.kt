package dev.gyeoul.crawler.response

class ProblemResponse {
    data class Problem(
        val problemId: Long,
        val titleKo: String,
        val titles: List<Title>,
        val isSolvable: Boolean,
        val isPartial: Boolean,
        val acceptedUserCount: Long,
        val level: Long,
        val votedUserCount: Long,
        val sprout: Boolean,
        val givesNoRating: Boolean,
        val isLevelLocked: Boolean,
        val averageTries: Double,
        val official: Boolean,
        val tags: List<Tag>,
        val metadata: Metadata
    )

    class Metadata

    data class Tag(
        val key: String,
        val isMeta: Boolean,
        val bojTagID: Long,
        val problemCount: Long,
        val displayNames: List<DisplayName>,
        val aliases: List<Alias>
    )

    data class Alias(
        val alias: String
    )

    data class DisplayName(
        val language: String,
        val name: String,
        val short: String
    )

    data class Title(
        val language: String,
        val languageDisplayName: String,
        val title: String,
        val isOriginal: Boolean
    )
}