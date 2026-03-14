// swift-tools-version: 5.7
import PackageDescription

let package = Package(
    name: "ConnectUI",
    platforms: [
        .iOS(.v14)
    ],
    products: [
        // Expose the raw Kotlin Multiplatform framework (UIKit)
        .library(
            name: "ConnectUI",
            targets: ["ConnectUI"]
        ),
        // Expose the native SwiftUI wrapper
        .library(
            name: "ConnectUISwift",
            targets: ["ConnectUISwift"]
        )
    ],
    targets: [
        .target(
            name: "ConnectUISwift",
            dependencies: ["ConnectUI"],
            path: "Sources/ConnectUISwift"
        ),
        .binaryTarget(
            name: "ConnectUI",
            path: "./shared/build/XCFrameworks/release/ConnectUI.xcframework"
        )
    ]
)
