import SwiftUI
import ConnectUI

/// A SwiftUI wrapper bridging the Compose Multiplatform `ConnectScreen`.
public struct ConnectView: UIViewControllerRepresentable {
    public let delegate: ConnectDelegate?

    public init(delegate: ConnectDelegate? = nil) {
        self.delegate = delegate
    }

    public func makeUIViewController(context: Context) -> UIViewController {
        // `ConnectViewControllerFactory` is a Kotlin object, translated to `.shared` in Swift.
        return ConnectViewControllerFactory.shared.createViewController(
            delegate: delegate
        )
    }

    public func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        // Compose tracks its own state, so redrawing is handled internally by Compose.
    }
}
