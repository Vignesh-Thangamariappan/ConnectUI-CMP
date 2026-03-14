# SKILL: CMPUI Integration

This skill provides instructions for AI agents to integrate the `CMPUI` library into native Android or iOS applications.

## 🛠 Prerequisites

- **Java Version**: Ensure the project uses **Java 17**. If the host environment has Java 25+, you must force Java 17 for Gradle:
  ```bash
  export JAVA_HOME=$(/usr/libexec/java_home -v 17)
  ```
- **Repository URL**: `https://github.com/Vignesh-Thangamariappan/CMPUI-CMP`

## 📱 iOS Integration (Swift Package Manager)

To integrate into an iOS app using SPM:

1. **Add Package**: Add the repository URL to your Xcode project.
2. **Select Target**:
   - `CMPUISwift`: For SwiftUI projects (recommended).
   - `CMPUI`: For pure UIKit projects.
3. **Add via Package.swift**:
   ```swift
   .package(url: "https://github.com/Vignesh-Thangamariappan/CMPUI-CMP.git", from: "1.0.0")
   ```
4. **Usage (SwiftUI)**:
   ```swift
   import SwiftUI
   import CMPUISwift

   struct MyView: View {
       var body: some View {
           CMPUIView(delegate: MyHandler())
       }
   }

   class MyHandler: CMPUIDelegate {
       func onAvatarTapped(avatar: AvatarItem) { /* Handle tap */ }
       func onAddTeamMemberClicked() { /* Handle click */ }
   }
   ```
4. **Usage (UIKit)**:
   ```swift
   import CMPUI

   let vc = CMPUIViewControllerFactory.shared.createViewController(delegate: myHandler)
   present(vc, animated: true)
   ```

## 🤖 Android Integration

To integrate into an Android app:

1. **Clone/Submodule**: Add this repo as a submodule or copy the `shared` module.
2. **Settings**: Add `include(":shared")` to `settings.gradle.kts`.
3. **Dependency**: Add `implementation(project(":shared"))` to your app's `build.gradle.kts`.
4. **Usage**:
   ```kotlin
   val cmpuiView = CMPUIViewFactory.createView(
       context = context,
       delegate = object : CMPUIDelegate {
           override fun onAvatarTapped(avatar: AvatarItem) { /* Handle tap */ }
           override fun onAddTeamMemberClicked() { /* Handle click */ }
       }
   )
   container.addView(cmpuiView)
   ```

## ⚠️ Important Notes

- **XCFramework Generation**: If integrating locally and the binary is missing, run:
  ```bash
  ./gradlew :shared:assembleCMPUIReleaseXCFramework
  ```
- **Static UI**: The `CMPUIScreen` is currently static. All data (avatars, features) are hardcoded for simplicity.
- **Delegation**: Always implement `CMPUIDelegate` to handle interactions.
