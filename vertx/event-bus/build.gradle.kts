plugins {
    java
}

group = "org.greenrivers.vertx"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.vertx:vertx-core:3.8.4")
    implementation("ch.qos.logback:logback-classic:1.2.3")

    annotationProcessor("org.projectlombok:lombok:1.18.10")
    compileOnly("org.projectlombok:lombok:1.18.10")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.10")
    testCompileOnly("org.projectlombok:lombok:1.18.10")
    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
}

tasks.create<JavaExec>("run") {
    main = project.properties.getOrDefault("mainClass", "chapter2.hello.HelloVerticle") as String
    classpath = sourceSets["main"].runtimeClasspath
    systemProperties["vertx.logger-delegate-factory-class-name"] = "io.vertx.core.logging.SLF4JLogDelegateFactory"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_12
}

tasks.withType<JavaCompile>().configureEach {
    options.apply {
        encoding = "UTF-8"
    }
}
