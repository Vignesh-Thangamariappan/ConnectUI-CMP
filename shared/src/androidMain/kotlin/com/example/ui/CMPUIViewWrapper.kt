package com.example.ui

import android.content.Context
import android.view.View
import androidx.compose.ui.platform.ComposeView

object CMPUIViewFactory {
    fun createView(
        context: Context,
        delegate: CMPUIDelegate? = null
    ): View {
        return ComposeView(context).apply {
            setContent {
                CMPUIScreen(delegate = delegate)
            }
        }
    }
}
