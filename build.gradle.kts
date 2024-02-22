plugins {
    kotlin("jvm") version "1.9.20"
    `kotlin-dsl`
    `maven-publish`
    kotlin("plugin.serialization") version "1.9.20"
}

group = "org.ldemetrios"
version = "1.0.0.20240220.1549"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation(gradleApi())
    implementation("org.ldemetrios:typst4k:1.0")
    implementation("org.ldemetrios:common-utils:1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}


publishing {
    repositories {
        mavenLocal()
    }
}