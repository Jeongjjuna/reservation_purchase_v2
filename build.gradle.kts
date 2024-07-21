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
        implementation("org.springframework.boot:spring-boot-starter-web")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

project(":reservation-purchase-api") {
    dependencies {
    }
}

project(":reservation-purchase-api") {
    dependencies {
    }
}

project(":reservation-purchase-gateway") {
    extra["springCloudVersion"] = "2023.0.3"

    configurations {
        all {
            exclude(group = "org.springframework.boot", module = "spring-boot-starter-web")
        }
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