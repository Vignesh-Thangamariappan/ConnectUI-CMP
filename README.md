# ConnectUI Compose Multiplatform

ConnectUI is a beautiful, reusable "Connect" screen library built with **Compose Multiplatform (CMP)**. It is designed to be easily integrated into existing native Android and iOS applications with minimal setup.

## 🚀 Integration

### iOS (Swift Package Manager)

You can integrate ConnectUI into any iOS project (UIKit or SwiftUI) using Swift Package Manager.

1. In Xcode, go to **File > Add Packages...**
2. Enter the repository URL: `https://github.com/Vignesh-Thangamariappan/ConnectUI-CMP`
3. Choose the dependency rule (e.g., Up to Next Major Version).
4. Add the library to your target:
   - **`ConnectUISwift`**: Recommended for modern SwiftUI projects.
   - **`ConnectUI`**: Use for pure UIKit projects.

#### Add to `Package.swift`
If your project is also a Swift Package, add the following to your `dependencies`:
```swift
.package(url: "https://github.com/Vignesh-Thangamariappan/ConnectUI-CMP.git", from: "1.0.0")
```
And then in your target dependencies:
```swift
.product(name: "ConnectUISwift", package: "ConnectUI-CMP") // Or "ConnectUI"
```

> [!IMPORTANT]
> Since this is a CMP project, the binary is not committed to the repo. If you are integrating into a local development environment, you must first run `./gradlew :shared:assembleConnectUIReleaseXCFramework` to generate the framework locally.

### Android

1. Add the following to your `settings.gradle.kts`:
   ```kotlin
   include(":shared")
   project(":shared").projectDir = File("path/to/ConnectUI-CMP/shared")
   ```
2. Add the dependency to your app-level `build.gradle.kts`:
   ```kotlin
   implementation(project(":shared"))
   ```

---

## 🛠 Usage

Detailed code examples for both platforms can be found in **[Usage.md](Usage.md)**.

### Quick Example (SwiftUI)
```swift
import SwiftUI
import ConnectUISwift

struct ContentView: View {
    var body: some View {
        ConnectView(delegate: MyHandler())
    }
}
```

### Quick Example (Android)
```kotlin
val connectView = ConnectViewFactory.createView(
    context = context,
    delegate = myHandler
)
```

## Features
- Fully matches Figma specifications.
- **Static Content:** Fully optimized for quick integration without complex configuration.
- **Native Delegation:** Handle events (avatar taps, button clicks) directly in your native code.
---

## 🛠 Local Environment Setup

If you have multiple Java versions installed (e.g., Java 25 as default) and need to force Gradle to use Java 17 locally without breaking the CI:

1. Create a `gradle.properties` in your local home directory (`~/.gradle/gradle.properties`).
2. Add the following line:
   ```properties
   org.gradle.java.home=/path/to/your/jdk17/Contents/Home
   ```
   *Note: For macOS with Homebrew, the path is typically: `/opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk/Contents/Home`*

Alternatively, set your `JAVA_HOME` environment variable before running gradlew:
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
./gradlew ...
```
