package com.example.ui

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

object ConnectViewControllerFactory {
    fun createViewController(
        delegate: ConnectDelegate? = null
    ): UIViewController {
        return ComposeUIViewController {
            ConnectScreen(delegate = delegate)
        }
    }
}
