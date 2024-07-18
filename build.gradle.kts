plugins {
    id("io.github.goooler.shadow") version "8.1.8" // Change back once goooler's PR goes through https://github.com/johnrengelman/shadow/pull/876
    id("java")
}

group = "xyz.prorickey"
version = "0.9.2"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.skriptlang.org/releases")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
    implementation("com.github.SkriptLang:Skript:2.9.0")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {
    shadowJar {
        destinationDirectory.set(file("${rootDir}/output"))
        archiveClassifier.set("")
        archiveBaseName.set("skUtilities")
    }

    processResources {
        val props = mapOf("version" to version)
        inputs.properties(props)
        filteringCharset = "UTF-8"
        filesMatching("paper-plugin.yml") {
            expand(props)
        }
    }
}