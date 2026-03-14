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
            url: "https://github.com/Vignesh-Thangamariappan/ConnectUI-CMP/releases/download/v0.1.4/ConnectUI.xcframework.zip",
            checksum: "0336f9b7e0bcd13304fa0fec926fa50100ee7dee7281b9a81f023287490d691b"
        )
    ]
)
