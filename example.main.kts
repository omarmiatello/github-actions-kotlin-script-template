#!/usr/bin/env kotlin
@file:Repository("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
@file:DependsOn("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.3")

import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.html
import kotlinx.html.p
import kotlinx.html.stream.createHTML
import java.io.File
import java.util.Date

println("🏁 Start")
val startTime = System.currentTimeMillis()

val addressee = "World"
val res = createHTML().html {
    body {
        h1 { +"Hello, $addressee!" }
        p { +"Last run: ${Date()}" }
    }
}
File("example.html").writeText(res)

println("🎉 Completed in ${System.currentTimeMillis() - startTime}ms - Example generated!")
