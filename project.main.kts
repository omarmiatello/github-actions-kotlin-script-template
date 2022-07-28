#!/usr/bin/env kotlin
@file:Repository("https://repo.maven.apache.org/maven2")
@file:DependsOn("com.github.omarmiatello.kotlin-script-toolbox:zero-setup:0.1.5")
@file:DependsOn("org.jsoup:jsoup:1.15.1")

import com.github.omarmiatello.kotlinscripttoolbox.core.AppendNever
import com.github.omarmiatello.kotlinscripttoolbox.core.BaseScope
import com.github.omarmiatello.kotlinscripttoolbox.core.buildMessages
import com.github.omarmiatello.kotlinscripttoolbox.core.launchKotlinScriptToolbox
import com.github.omarmiatello.kotlinscripttoolbox.gson.writeJson
import com.github.omarmiatello.kotlinscripttoolbox.twitter.twitterUrl
import com.github.omarmiatello.kotlinscripttoolbox.zerosetup.ZeroSetupScope
import com.github.omarmiatello.telegram.ParseMode
import java.util.*
import kotlin.system.exitProcess

// Models (data classes)
data class MyExample(val message: String, val now: Date = Date())

launchKotlinScriptToolbox(
    scope = ZeroSetupScope(
        baseScope = BaseScope.from(filepathPrefix = "data/"),
    ),
    scriptName = "Kotlin Script Toolbox",
) {
    // notification
    sendTelegramMessage("test!", disable_web_page_preview = false, parse_mode = ParseMode.Markdown)
    sendTelegramMessages(
        messages = buildMessages {
            addMessage { text("Message 1") }
            addMessage {
                text("Message 2 ")
                url("https://www.google.com")
            }
            addMessage(appendIf = AppendNever) { text("Message 3") }
        },
        disable_web_page_preview = false,
        parse_mode = ParseMode.Markdown,
    )
    sendTweet("Hi!")
    sendTweets(
        messages = buildMessages {
            addMessage { text("Message 1") }
            addMessage(appendIf = AppendNever) {
                text("Message 2 ")
                twitterUrl("https://www.google.com")
            }
        },
    )

    val myExample = MyExample(message = "Ciao!")
    println("MyExample: $myExample")
    writeJson("example.json", myExample)
}

exitProcess(status = 0)