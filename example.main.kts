#!/usr/bin/env kotlin
@file:Repository("https://repo.maven.apache.org/maven2")
@file:DependsOn("com.github.omarmiatello.kotlin-script-toolbox:zero-setup:0.0.3")
@file:DependsOn("com.github.omarmiatello.telegram:client-jvm:6.0")
@file:DependsOn("io.ktor:ktor-client-okhttp-jvm:2.0.2")  // required for com.github.omarmiatello.telegram:client
@file:DependsOn("org.jsoup:jsoup:1.15.1")

import com.github.omarmiatello.kotlinscripttoolbox.core.launchKotlinScriptToolbox
import com.github.omarmiatello.kotlinscripttoolbox.zerosetup.gson
import com.github.omarmiatello.kotlinscripttoolbox.zerosetup.readJsonOrNull
import com.github.omarmiatello.kotlinscripttoolbox.zerosetup.writeJson
import com.github.omarmiatello.telegram.TelegramClient
import org.jsoup.Jsoup
import java.net.URL
import java.util.Date
import kotlin.system.exitProcess

// Models (data classes)
data class MyExample(val message: String, val now: Date = Date())

launchKotlinScriptToolbox(
    scriptName = "Update for Stadia Games API",
    filepathPrefix = "data/",
) {
    val myExample = MyExample(message = "Ciao!")
    println("MyExample: $myExample")
    writeJson("example.json", myExample)
}

exitProcess(status = 0)