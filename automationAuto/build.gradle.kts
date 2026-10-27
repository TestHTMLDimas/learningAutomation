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

tasks.register("printAfterTests") {
    group = "verification"

    doLast {
        println("Test run is over")
    }}