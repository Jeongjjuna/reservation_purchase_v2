plugins {
    java
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-data-redis")
        implementation("org.redisson:redisson-spring-boot-starter:3.27.1")

        runtimeOnly("com.mysql:mysql-connector-j")
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

project(":reservation-purchase-api") {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
    }
}

project(":reservation-purchase-order-consumer") {
    dependencies {
        implementation(project(":reservation-purchase-api"))
        implementation("org.springframework.boot:spring-boot-starter-web")
    }
}

project(":reservation-purchase-gateway") {
    extra["springCloudVersion"] = "2023.0.3"

    configurations.all {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-data-jpa")
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-data-redis")
        exclude(group = "org.redisson", module = "redisson-spring-boot-starter")
    }

    dependencies {
        implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }
}