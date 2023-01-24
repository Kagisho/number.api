val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val koinKtor: String by project

plugins {
    application
    kotlin("jvm") version "1.7.22"
    id("io.ktor.plugin") version "2.1.3"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.22"
    id("io.gitlab.arturbosch.detekt").version("1.22.0")
    id("org.jlleitschuh.gradle.ktlint").version("11.0.0")
}

group = "equalexperts.number.api"
version = "0.0.1"
application {
    mainClass.set("io.ktor.server.netty.EngineMain")
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.0")
    implementation("io.micrometer:micrometer-registry-prometheus:1.10.3")
    implementation("io.ktor:ktor-server-core-jvm:2.2.2")
    implementation("io.ktor:ktor-server-netty-jvm:2.2.2")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:2.2.2")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:2.2.2")
    implementation("io.ktor:ktor-server-compression:2.2.2")
    implementation("io.ktor:ktor-server-call-logging:2.2.2")
    implementation("io.ktor:ktor-server-status-pages:2.2.2")
    implementation("io.ktor:ktor-server-double-receive:2.2.2")
    implementation("io.ktor:ktor-server-swagger:2.2.2")
    implementation("io.ktor:ktor-server-call-id:2.2.2")
    implementation("io.ktor:ktor-server-metrics-micrometer:2.2.2")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.2.2")
    implementation("io.ktor:ktor-server-resources:2.2.2")
    implementation("io.insert-koin:koin-ktor:$koinKtor")
    implementation("io.insert-koin:koin-logger-slf4j:$koinKtor")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
    testImplementation("io.ktor:ktor-server-tests-jvm:2.2.2")
    testImplementation("io.ktor:ktor-server-test-host-jvm:2.2.2")
}
