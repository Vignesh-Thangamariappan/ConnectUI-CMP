# SKILL: ConnectUI Integration

This skill provides instructions for AI agents to integrate the `ConnectUI` library into native Android or iOS applications.

## 🛠 Prerequisites

- **Java Version**: Ensure the project uses **Java 17**. If the host environment has Java 25+, you must force Java 17 for Gradle:
  ```bash
  export JAVA_HOME=$(/usr/libexec/java_home -v 17)
  ```
- **Repository URL**: `https://github.com/Vignesh-Thangamariappan/ConnectUI-CMP`

## 📱 iOS Integration (Swift Package Manager)

To integrate into an iOS app using SPM:

1. **Add Package**: Add the repository URL to your Xcode project.
2. **Select Target**:
   - `ConnectUISwift`: For SwiftUI projects (recommended).
   - `ConnectUI`: For pure UIKit projects.
3. **Usage (SwiftUI)**:
   ```swift
   import SwiftUI
   import ConnectUISwift

   struct MyView: View {
       var body: some View {
           ConnectView(delegate: MyHandler())
       }
   }

   class MyHandler: ConnectDelegate {
       func onAvatarTapped(avatar: AvatarItem) { /* Handle tap */ }
       func onAddTeamMemberClicked() { /* Handle click */ }
   }
   ```
4. **Usage (UIKit)**:
   ```swift
   import ConnectUI

   let vc = ConnectViewControllerFactory.shared.createViewController(delegate: myHandler)
   present(vc, animated: true)
   ```

## 🤖 Android Integration

To integrate into an Android app:

1. **Clone/Submodule**: Add this repo as a submodule or copy the `shared` module.
2. **Settings**: Add `include(":shared")` to `settings.gradle.kts`.
3. **Dependency**: Add `implementation(project(":shared"))` to your app's `build.gradle.kts`.
4. **Usage**:
   ```kotlin
   val connectView = ConnectViewFactory.createView(
       context = context,
       delegate = object : ConnectDelegate {
           override fun onAvatarTapped(avatar: AvatarItem) { /* Handle tap */ }
           override fun onAddTeamMemberClicked() { /* Handle click */ }
       }
   )
   container.addView(connectView)
   ```

## ⚠️ Important Notes

- **XCFramework Generation**: If integrating locally and the binary is missing, run:
  ```bash
  ./gradlew :shared:assembleConnectUIReleaseXCFramework
  ```
- **Static UI**: The `ConnectScreen` is currently static. All data (avatars, features) are hardcoded for simplicity.
- **Delegation**: Always implement `ConnectDelegate` to handle interactions.
