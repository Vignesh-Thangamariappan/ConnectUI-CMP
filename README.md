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
