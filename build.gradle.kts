plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "fr.twizox"
version = "1.0"

java.targetCompatibility = JavaVersion.VERSION_19
java.sourceCompatibility = JavaVersion.VERSION_19

repositories {
    mavenCentral()
    // spigot repository
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    compileOnly("org.spigotmc:spigot-api:1.19.4-R0.1-SNAPSHOT")

}

tasks.shadowJar {
    minimize()
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}