import generator.kindaMain

plugins {
    kotlin("jvm") version "1.9.20"
    `kotlin-dsl`
    `maven-publish`
    kotlin("plugin.serialization") version "1.9.20"
}

group = "org.ldemetrios"
version = "0.3.0"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation(gradleApi())
    implementation("org.ldemetrios:typst4k:+")
    implementation("org.ldemetrios:common-utils:+")
    implementation(kotlin("script-runtime"))
}

tasks.register("generateThemesDSL") {
    doLast {
        kindaMain(rootDir.path)
    }
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

publishing {
    repositories {
        mavenLocal()
    }
}
