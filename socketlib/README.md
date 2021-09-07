# I need to introduce headers folder for PocketSocket lib because on issue with KMP:
    https://youtrack.jetbrains.com/issue/KT-42167

# We need to use a workarund to build on CI/CD:

```kotlin
tasks.named<org.jetbrains.kotlin.gradle.tasks.DefFileTask>("generateDefPocketSocket").configure {
        doLast {
            outputFile.writeText(
                """
            language = Objective-C
            headers = "$projectDir/socketlib/PocketSocketHeaders/PSWebSocket.h"
            """
            )
        }
    }
```
# Headers folder where copied from PocketSocket git repository:

```kotlin
pod("PocketSocket") {
            source = git("https://github.com/zwopple/PocketSocket") {
                tag = "1.0.1"
            }
        }
```