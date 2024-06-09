package dev.gyeoul.database.entity

import dev.gyeoul.entity.BOJProfile
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


import java.time.LocalDateTime
import java.time.ZoneOffset


class UserVO() : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var name: String = ""
    var bio: String = "."
    var tier: Int = 0
    var rank: Int = 0
    var problems: RealmList<Int> = realmListOf()
    var updateDate: Long = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)

    constructor(profile: BOJProfile) : this() {
        name = profile.name
        bio = profile.bio
        tier = profile.tier
        rank = profile.rank
        problems = profile.problems.toRealmList()

    }
}