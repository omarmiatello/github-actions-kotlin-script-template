# github-actions-kotlin-script-template
GitHub Actions support Kotlin Script - see
https://github.com/actions/virtual-environments/issues/3687

Let's try it!

<img width="888" alt="github-actions-kotlin-script-template in IntelliJ IDEA 2022.1" src="https://user-images.githubusercontent.com/4026448/174341248-1e23fe8f-0e37-4b43-8645-9f56c150822b.png">

# How to use this GitHub Template


## Step 1 - Clone
Just click on [![Use this template](https://img.shields.io/badge/-Use%20this%20template-brightgreen)](https://github.com/omarmiatello/github-actions-kotlin-script-template/generate) button to create a new repo starting from this template.

## Step 2 - Update the script content

Example [example.main.kts](example.main.kts):
```kotlin
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
```

Optionally you could change the file name.

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
