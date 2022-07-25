buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Build.androidLibrary)
        classpath(Build.kotlinGradlePlugin)
        classpath(Build.androidBuildTools)
        classpath(Build.hiltAndroid)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}