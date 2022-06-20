# github-actions-kotlin-script-template
GitHub Actions support Kotlin Script - see
https://github.com/actions/virtual-environments/issues/3687

Let's try it!

| IntelliJ IDEA 2022.1 | GitHub Actions |
| --- | --- |
| <img width="888" alt="github-actions-kotlin-script-template in IntelliJ IDEA 2022.1" src="https://user-images.githubusercontent.com/4026448/174341248-1e23fe8f-0e37-4b43-8645-9f56c150822b.png"> | <img width="495" alt="GitHub Actions" src="https://user-images.githubusercontent.com/4026448/174345054-30bd737f-7e41-4d73-99b1-72ad966a5af5.png"> |

# How to use this GitHub Template


## Step 1 - Clone
Just click on [![Use this template](https://img.shields.io/badge/-Use%20this%20template-brightgreen)](https://github.com/omarmiatello/github-actions-kotlin-script-template/generate) button to create a new repo starting from this template.

## Step 2 - Update the script content

Update [example.main.kts](example.main.kts). Optionally you could change the file name.

### Example 1: Example with external libraries:
```kotlin
#!/usr/bin/env kotlin
@file:Repository("https://repo.maven.apache.org/maven2")
@file:Repository("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
@file:DependsOn("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.3")

import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.html
import kotlinx.html.p
import kotlinx.html.stream.createHTML
import java.util.Date

val addressee = "World"
println(createHTML().html {
    body {
        h1 { +"Hello, $addressee!" }
        p { +"Last run: ${Date()}" }
    }
})
```

Result:
```html
<html>
  <body>
    <h1>Hello, World!</h1>
    <p>Last run: Mon Jun 20 17:12:31 CEST 2022</p>
  </body>
</html>
```

### Step 3 - Update main.yml (Optional)
Update [.github/workflows/main.yml](.github/workflows/main.yml)

```yaml
# Update the file name here
run: kotlinc -script ./example.main.kts
```

```yaml
# Update the commit message
git commit -m "Run script (using Github Action)"
```

### Step 4 - Launch this Action!  🎉
Go to the Actions tab in GitHub. Have fun!

## Other examples

### Example 2: `KotlinScriptToolbox` utils
```kotlin
#!/usr/bin/env kotlin
@file:Repository("https://repo.maven.apache.org/maven2")
@file:Repository("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
@file:DependsOn("com.github.omarmiatello.kotlin-script-toolbox:zero-setup:0.0.3")
@file:DependsOn("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.3")

import com.github.omarmiatello.kotlinscripttoolbox.core.launchKotlinScriptToolbox
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.html
import kotlinx.html.p
import kotlinx.html.stream.createHTML
import java.util.*

launchKotlinScriptToolbox(scriptName = "My example") {
    val addressee = "World"
    val res = createHTML().html {
        body {
            h1 { +"Hello, $addressee!" }
            p { +"Last run: ${Date()}" }
        }
    }
    println(res)
    writeText("example.html", res)
}
```

Result:
```html
🏁 My example - Start!
<html>
  <body>
    <h1>Hello, World!</h1>
    <p>Last run: Mon Jun 20 17:07:49 CEST 2022</p>
  </body>
</html>
🎉 My example - Completed in 23ms
```

### Example 3: data classes + read/write files + read system property

```kotlin
#!/usr/bin/env kotlin
@file:Repository("https://repo.maven.apache.org/maven2")
@file:DependsOn("com.github.omarmiatello.kotlin-script-toolbox:zero-setup:0.0.3")
@file:DependsOn("com.github.omarmiatello.telegram:client-jvm:6.0")
@file:DependsOn("io.ktor:ktor-client-okhttp-jvm:2.0.2")  // required for com.github.omarmiatello.telegram:client

import com.github.omarmiatello.kotlinscripttoolbox.core.launchKotlinScriptToolbox
import com.github.omarmiatello.kotlinscripttoolbox.zerosetup.readJsonOrNull
import com.github.omarmiatello.kotlinscripttoolbox.zerosetup.writeJson
import com.github.omarmiatello.telegram.TelegramClient

data class DataExample(val p1: Int = 1)

launchKotlinScriptToolbox(scriptName = "My example") {

    // Set up: Telegram notification
    val telegramClient = TelegramClient(apiKey = readSystemPropertyOrNull("TELEGRAM_BOT_APIKEY")!!)
    val defaultChatId = readSystemPropertyOrNull("TELEGRAM_CHAT_ID")!!
    suspend fun sendTelegramMessage(text: String, chatId: String = defaultChatId) {
        println("💬 $text")
        telegramClient.sendMessage(chat_id = chatId, text = text)
    }
    sendTelegramMessage("Ciao!")

    // read secrets from system property or local.properties
    readSystemPropertyOrNull(propertyName = "SECRET_API_KEY")
    
    // read/write text
    writeText(pathname = "file.txt", text = "Ciao!")
    val content1: String? = readTextOrNull(pathname = "file.txt")

    // read/write object
    writeJson(pathname = "file.json", obj = DataExample(p1 = 2))
    val content2: DataExample? = readJsonOrNull(pathname = "file.json")
}
```

### Example 4: Stadia Games API (parse + write file + send notification)

See: [https://github.com/omarmiatello/stadia-games-api/blob/main/update-api.main.kts](https://github.com/omarmiatello/stadia-games-api/blob/main/update-api.main.kts)
