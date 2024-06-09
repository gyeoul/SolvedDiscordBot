package dev.gyeoul.database

import dev.gyeoul.crawler.CrawlerService
import dev.gyeoul.database.entity.UserVO
import dev.gyeoul.entity.BOJProfile
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.toRealmList
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.math.abs


class Database {
    private val realm: Realm

    init {
        val config: RealmConfiguration = RealmConfiguration.create(schema = setOf(UserVO::class))
        realm = Realm.open(config)
    }


    fun updateProblems(user: BOJProfile) = realm.writeBlocking {

        val q = realm.query<UserVO>("name == $0", user.name).find()
        try {
            when (q.firstOrNull()) {
                null -> copyToRealm(UserVO(user))

                else -> findLatest(q.first())?.let {
                    it.problems = user.problems.toRealmList()
                }
            }
        } catch (e: Exception) {
            return@writeBlocking false
        }
        true
    }

    fun getUserInfo(name: String): UserVO {
        val q = realm.query<UserVO>("name == $0", name).find()
        return if (q.firstOrNull() != null
            && abs(
                Duration.between(
                    LocalDateTime.now(),
                    LocalDateTime.ofEpochSecond(q.first().updateDate, 0, ZoneOffset.UTC)
                ).toHours()
            ) > 20
        ) {
            q.first()
        } else {
            val user = CrawlerService().getUserByName("name")
            val vo = UserVO(user)
            realm.writeBlocking {
                copyToRealm(vo)
            }
            vo
        }
    }
}