package dev.gyeoul.entity

data class BOJProfile(
    val name: String,
    val bio: String,
    val tier: Int = 0,
    val rank: Int = 0,
    val problems: MutableSet<Int> = mutableSetOf(),
)
