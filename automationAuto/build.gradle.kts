plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.assertj:assertj-core:3.27.7")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.22.3")
    implementation("io.rest-assured:rest-assured:6.2.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.test {
    useJUnitPlatform()
    finalizedBy("printAfterTests")
}

tasks.register("runAllTests") {
    group = "verification"

    dependsOn(tasks.test)
}

tasks.test {
    useJUnitPlatform {
        val tags = providers.gradleProperty("testTags").orNull
        if (tags != null) {
            includeTags(*tags.split(",").map { it.trim() }.toTypedArray())
        }
    }
}

tasks.register("printAfterTests") {
    group = "verification"

    doLast {
        println("Test run is over")
    }
}