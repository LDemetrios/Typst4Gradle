plugins {
    kotlin("jvm") version "1.9.20"
    `kotlin-dsl`
    `maven-publish`
}

group = "org.ldemetrios"
version = "1.0.0.20240219.2327"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation(gradleApi())
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