plugins {
    java
    id("org.springframework.boot") version "4.1.1-SNAPSHOT"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "spring_boot_study"
version = "0.0.1-SNAPSHOT"
description = "spring_boot_study"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    runtimeOnly("com.h2database:h2")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testCompileOnly("org.projectlombok:lombok")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testAnnotationProcessor("org.projectlombok:lombok")

    // 스프링 시큐리티 관련
    implementation("org.springframework.boot:spring-boot-starter-security")
    // 테스트에서 Security 관련 유틸(예: SecurityMockMvcRequestPostProcessors)을 쓰려면 아래도 추가
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
