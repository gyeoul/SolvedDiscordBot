plugins {
    kotlin("jvm") version "1.9.23"

    id("io.realm.kotlin") version "1.16.0"
}

group = "dev.gyeoul"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    testImplementation(kotlin("test"))

    runtimeOnly("org.apache.logging.log4j:log4j-to-slf4j:2.23.1")
    runtimeOnly("ch.qos.logback:logback-classic:1.5.6")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0-RC")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.9.0-RC")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("com.google.code.gson:gson:2.11.0")


    implementation("org.javacord:javacord:3.8.0")

    //    implementation("org.seleniumhq.selenium:selenium-java:4.21.0")
    implementation("org.jsoup:jsoup:1.17.2")

    //    implementation("org.ktorm:ktorm-core:4.0.0")
    //    implementation("org.ktorm:ktorm-support-sqlite:4.0.0")
    //    implementation("org.xerial:sqlite-jdbc:3.46.0.0")
    implementation("io.realm.kotlin:library-base:1.16.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
}


tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}