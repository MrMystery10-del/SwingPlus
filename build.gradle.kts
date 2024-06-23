import org.gradle.api.publish.PublishingExtension

plugins {
    id("java")
    id("io.freefair.lombok") version "8.6"
    `maven-publish`
}

group = "com.swingplus"
version = "1.0-SNAPSHOT"

configure<PublishingExtension> {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "com.swingplus"
            artifactId = "swingplus"
            version = "0.0.1"
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/MrMystery10-del/SwingPlus")
            credentials {
                val githubUsername: String? = findProperty("gpr.user") as String? ?: System.getenv("GITHUB_USERNAME")
                val githubToken: String? = findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")

                print("Username:" + System.getenv("GITHUB_USERNAME"));
                if (githubUsername == null || githubToken == null) {
                    throw GradleException("GitHub username or token is not set. Please set the GITHUB_USERNAME and GITHUB_TOKEN environment variables.")
                }

                username = githubUsername
                password = githubToken
            }
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
