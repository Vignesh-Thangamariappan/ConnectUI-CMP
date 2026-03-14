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
            url: "https://github.com/Vignesh-Thangamariappan/ConnectUI-CMP/releases/download/v0.1.3/ConnectUI.xcframework.zip",
            checksum: "305b11548d7ed02ecfd9857254c80669ef6129b0b14d62ff726c77a64444dd6d"
        )
    ]
)
