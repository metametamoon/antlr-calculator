import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    id("antlr")
    application
}

group = "com.github.metametamoon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    antlr("org.antlr:antlr4:4.11.1")
    implementation("com.michael-bull.kotlin-result:kotlin-result:1.1.16")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

tasks.named<AntlrTask>("generateGrammarSource").configure {
    arguments.addAll(listOf("-package", "antlrGeneratedSource", "-visitor", "-no-listener"))
    outputDirectory = File("src/main/java/antlrGeneratedSource")
}

tasks.named("compileKotlin").configure {
    dependsOn("generateGrammarSource")
}