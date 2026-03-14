package com.example.ui

import android.content.Context
import android.view.View
import androidx.compose.ui.platform.ComposeView

object ConnectViewFactory {
    fun createView(
        context: Context,
        delegate: ConnectDelegate? = null
    ): View {
        return ComposeView(context).apply {
            setContent {
                ConnectScreen(delegate = delegate)
            }
        }
    }
}
