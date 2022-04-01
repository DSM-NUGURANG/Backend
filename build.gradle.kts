import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    kotlin("plugin.jpa") version "1.6.10"
}

group = "com.dohyeon5626"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    // data jpa
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")

    // template engine
    implementation ("org.springframework.boot:spring-boot-starter-mustache")

    // spring security
    implementation ("org.springframework.boot:spring-boot-starter-security")

    // jwt
    implementation ("io.jsonwebtoken:jjwt:0.9.1")

    // redis
    implementation ("org.springframework.boot:spring-boot-starter-data-redis")
    implementation ("it.ozimov:embedded-redis:0.7.2")

    // validation
    implementation ("org.springframework.boot:spring-boot-starter-validation")

    // configure
    annotationProcessor ("org.springframework.boot:spring-boot-configuration-processor")

    // starter web
    implementation ("org.springframework.boot:spring-boot-starter-web")

    // kotlin
    implementation ("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation ("org.jetbrains.kotlin:kotlin-reflect")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // mysql
    runtimeOnly ("mysql:mysql-connector-java")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
