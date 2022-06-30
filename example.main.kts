#!/usr/bin/env kotlin
@file:Repository("https://repo.maven.apache.org/maven2")
@file:DependsOn("com.github.omarmiatello.kotlin-script-toolbox:zero-setup:0.1.0")
@file:DependsOn("org.jsoup:jsoup:1.15.1")

import com.github.omarmiatello.kotlinscripttoolbox.core.BaseScope
import com.github.omarmiatello.kotlinscripttoolbox.core.launchKotlinScriptToolbox
import com.github.omarmiatello.kotlinscripttoolbox.gson.writeJson
import com.github.omarmiatello.kotlinscripttoolbox.zerosetup.ZeroSetupScope
import java.util.Date
import kotlin.system.exitProcess

// Models (data classes)
data class MyExample(val message: String, val now: Date = Date())

launchKotlinScriptToolbox(
    scope = ZeroSetupScope(
        baseScope = BaseScope.fromDefaults(filepathPrefix = "data/"),
    ),
    scriptName = "Kotlin Script Toolbox",
) {
    // notification
    sendTelegramMessage("test!")
    sendTweet("Hi !")

    val myExample = MyExample(message = "Ciao!")
    println("MyExample: $myExample")
    writeJson("example.json", myExample)
}

exitProcess(status = 0)