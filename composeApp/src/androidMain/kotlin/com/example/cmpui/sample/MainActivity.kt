package com.example.cmpui.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ui.CMPUIScreen
import com.example.ui.CMPUIDelegate
import com.example.ui.AvatarItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CMPUIScreen(
                delegate = object : CMPUIDelegate {
                    override fun onAvatarTapped(avatar: AvatarItem) {
                        println("Avatar tapped: ${avatar.id}")
                    }
                    override fun onAddTeamMemberClicked() {
                        println("Add team member clicked")
                    }
                }
            )
        }
    }
}
