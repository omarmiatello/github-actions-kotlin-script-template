#!/usr/bin/env kotlin
@file:Repository("https://repo.maven.apache.org/maven2")
@file:DependsOn("com.github.omarmiatello.kotlin-script-toolbox:zero-setup:0.0.8")
@file:DependsOn("org.jsoup:jsoup:1.15.1")

import com.github.omarmiatello.kotlinscripttoolbox.core.launchKotlinScriptToolbox
import com.github.omarmiatello.kotlinscripttoolbox.gson.writeJson
import com.github.omarmiatello.kotlinscripttoolbox.telegram.sendTelegramMessage
import com.github.omarmiatello.kotlinscripttoolbox.telegram.setupTelegram
import com.github.omarmiatello.kotlinscripttoolbox.twitter.sendTweet
import com.github.omarmiatello.kotlinscripttoolbox.zerosetup.launchKotlinScriptToolboxZeroSetup
import java.util.Date
import kotlin.system.exitProcess

// Models (data classes)
data class MyExample(val message: String, val now: Date = Date())

launchKotlinScriptToolbox(
    scriptName = "Update for Stadia Games API",
    filepathPrefix = "data/",
) {
    // notification
    setupTelegram(apiKey = readSystemProperty("TELEGRAM_APIKEY"), defaultChatId = "123456")
    sendTelegramMessage("test!")

    val myExample = MyExample(message = "Ciao!")
    println("MyExample: $myExample")
    writeJson("example.json", myExample)
}

launchKotlinScriptToolboxZeroSetup {
    // no setup needed for Telegram/Twitter
    sendTelegramMessage("Hi everyone!")
    sendTweet("Hi !")
}

exitProcess(status = 0)