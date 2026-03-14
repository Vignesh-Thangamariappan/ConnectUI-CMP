package com.example.ui

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

object CMPUIViewControllerFactory {
    fun createViewController(
        delegate: CMPUIDelegate? = null
    ): UIViewController {
        return ComposeUIViewController(
            configure = {
                enforceStrictPlistSanityCheck = false
            }
        ) {
            CMPUIScreen(delegate = delegate)
        }
    }
}
