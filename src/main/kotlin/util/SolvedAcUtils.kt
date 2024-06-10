package dev.gyeoul.util

import java.awt.Color

class SolvedAcUtils {
    fun convertTierToColor(tier: String): Color {
        val t = if (tier.contains("_")) tier.split("_")[0] else tier
        return when (t) {
            "BRONZE" -> Color(173, 86, 0)
            "SILVER" -> Color(0x43, 0x5F, 0x7A)
            "GOLD" -> Color(0xEC, 0x99, 0x02)
            "PLATINUM" -> Color(0x27, 0xE2, 0xA4)
            "DIAMOND" -> Color(0x01, 0xB4, 0xFC)
            "RUBY" -> Color(0xFF, 0x02, 0x62)
            else -> Color(0x2D, 0x2D, 0x2D)
        }
    }

    fun convertLevelToTier(level: Int): String {
        fun c(level: Int): String {
            return when (level % 5) {
                1 -> "I"
                2 -> "II"
                3 -> "III"
                4 -> "IV"
                else -> "V"
            }
        }
        return when (level) {
            in 1..5 -> "BRONZE_${c(level)}"
            in 6..10 -> "SILVER_${c(level)}"
            in 11..15 -> "GOLD_${c(level)}"
            in 16..20 -> "PLATINUM_${c(level)}"
            in 21..25 -> "DIAMOND_${c(level)}"
            in 26..30 -> "RUBY_${c(level)}"
            else -> "UNRATED"
        }
    }
}
