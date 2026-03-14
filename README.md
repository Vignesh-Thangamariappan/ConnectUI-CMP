# ConnectUI Compose Multiplatform

This project provides a "Connect" UI Screen built seamlessly using Compose Multiplatform. It allows you to embed this screen into any existing native Android (XML/View natively) and iOS (UIKit natively via Swift Package Manager).

## Features
- Fully matches the Connect Figma specifications.
- **Dynamic Configuration:** Adjust texts, lists, icons, and whether the avatar list is static or scrollable via `ConnectConfig`.
- **Easy Delegation:** Receive tap events natively through the `ConnectDelegate` interface.

## Architecture
The application layout relies entirely on Kotlin Multiplatform (`/shared` directory).
- **Core UI:** Built with Compose Multiplatform in `commonMain` (`ConnectScreen.kt`).
- **Native Wrappers:** `ConnectViewWrapper.kt` for Android and `ConnectViewController.kt` for iOS to allow clean instantiation in purely native legacy applications.

---

Please read the **[Usage.md](Usage.md)** file for a comprehensive guide on integrating the module into native Android layouts and native iOS SwiftUI/UIKit hierarchies.
