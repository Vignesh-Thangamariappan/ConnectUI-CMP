package com.example.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalLayoutApi::class)
@Composable

fun CMPUIScreen(
    delegate: CMPUIDelegate? = null
) {
    val features = listOf(
        FeatureItem("chat", "All on the same page", "Chat across desktop and mobile with real-time notifications."),
        FeatureItem("messages", "Conversations that count", "Share messages, files, images, and notes - decisions move faster."),
        FeatureItem("people", "Message. Share. Get things done.", "Start 1-to-1 or group chats to plan, coordinate and stay on track.")
    )

    val avatars = listOf(
        AvatarItem("1", "", AvatarStatus.ONLINE),
        AvatarItem("2", "", null),
        AvatarItem("3", "", AvatarStatus.AWAY),
        AvatarItem("4", "", null),
        AvatarItem("5", "", AvatarStatus.ONLINE)
    )

    MaterialTheme {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFAFAFA))
        ) {
            val isLandscape = maxWidth > 600.dp

            if (isLandscape) {
                CMPUIHorizontalLandscapeLayout(features, avatars, delegate)
            } else {
                CMPUIVerticalLayout(features, avatars, delegate)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CMPUIVerticalLayout(
    features: List<FeatureItem>,
    avatars: List<AvatarItem>,
    delegate: CMPUIDelegate?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 48.dp, bottom = 24.dp)
    ) {
        CMPUIHeader()

        Spacer(modifier = Modifier.height(32.dp))

        CMPUIAvatars(avatars, delegate)

        Spacer(modifier = Modifier.height(32.dp))

        CMPUIDescription()

        Spacer(modifier = Modifier.height(32.dp))

        features.forEach { feature ->
            FeatureRow(feature = feature)
            Spacer(modifier = Modifier.height(24.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        CMPUITeamMemberButton(delegate)
    }
}

@Composable
fun CMPUIHorizontalLandscapeLayout(
    features: List<FeatureItem>,
    avatars: List<AvatarItem>,
    delegate: CMPUIDelegate?
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(vertical = 48.dp)
        ) {
            CMPUIDescription()

            Spacer(modifier = Modifier.height(32.dp))

            features.forEach { feature ->
                FeatureRow(feature = feature)
                Spacer(modifier = Modifier.height(24.dp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            CMPUITeamMemberButton(delegate)
        }

        Spacer(modifier = Modifier.width(48.dp))

        Box(
            modifier = Modifier
                .weight(1.2f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            // Visual mockup with overlapping phone frames
            Box(modifier = Modifier.fillMaxSize()) {
                PhoneFrame(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(x = (-40).dp, y = (-20).dp)
                        .width(280.dp)
                        .aspectRatio(0.5f),
                    title = "Connect",
                    isMain = true
                )
                
                PhoneFrame(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(x = 60.dp, y = 40.dp)
                        .width(280.dp)
                        .aspectRatio(0.5f),
                    title = "Cody Fisher",
                    isMain = false
                )
            }
        }
    }
}

@Composable
fun CMPUIHeader() {
    Text(
        text = "Connect",
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        modifier = Modifier.fillMaxWidth(),
        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        color = Color.Black
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CMPUIAvatars(avatars: List<AvatarItem>, delegate: CMPUIDelegate?) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        avatars.forEach { avatar ->
            Box(modifier = Modifier.padding(horizontal = 8.dp)) {
                AvatarView(avatar = avatar) {
                    delegate?.onAvatarTapped(it)
                }
            }
        }
    }
}

@Composable
fun CMPUIDescription() {
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        Text(
            text = "Connect with your team,\nwherever work takes you",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = Color.Black,
            lineHeight = 40.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Collaboration works best in one place. Connect keeps conversations flowing, even on the go.",
            fontSize = 18.sp,
            color = Color.Black.copy(alpha = 0.7f),
            lineHeight = 26.sp
        )
    }
}

@Composable
fun CMPUITeamMemberButton(delegate: CMPUIDelegate?) {
    Button(
        onClick = { delegate?.onAddTeamMemberClicked() },
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .height(56.dp)
            .widthIn(min = 200.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF111111)),
        shape = RoundedCornerShape(28.dp),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.White)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Team member", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun PhoneFrame(
    modifier: Modifier = Modifier,
    title: String,
    isMain: Boolean
) {
    Surface(
        modifier = modifier
            .border(8.dp, Color.Black, RoundedCornerShape(32.dp))
            .clip(RoundedCornerShape(32.dp)),
        color = Color.White,
        elevation = 8.dp
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Status bar area
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("9:41", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.List, contentDescription = null, modifier = Modifier.size(12.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(Icons.Default.Email, contentDescription = null, modifier = Modifier.size(12.dp))
                }
            }

            // App Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
                if (isMain) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterEnd).size(16.dp)
                    )
                }
            }

            // Content simulation
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                repeat(if (isMain) 5 else 3) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFEEEEEE))
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Box(
                                modifier = Modifier
                                    .width(60.dp)
                                    .height(8.dp)
                                    .background(Color(0xFFEEEEEE), RoundedCornerShape(4.dp))
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(6.dp)
                                    .background(Color(0xFFF5F5F5), RoundedCornerShape(3.dp))
                            )
                        }
                    }
                }
            }
        }
    }
}

data class FeatureItem(
    val iconId: String,
    val title: String,
    val description: String
)

data class AvatarItem(
    val id: String,
    val imageUrl: String,
    val status: AvatarStatus? = null
)

enum class AvatarStatus {
    ONLINE, BUSY, AWAY, DO_NOT_DISTURB
}

@Composable
fun FeatureRow(feature: FeatureItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.Top
    ) {
        val icon: ImageVector = when(feature.iconId) {
            "chat" -> Icons.Default.Email
            "messages" -> Icons.Default.List
            "people" -> Icons.Default.Person
            else -> Icons.Default.Add
        }
        
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = feature.title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = feature.description, fontSize = 16.sp, color = Color.Black.copy(alpha = 0.6f), lineHeight = 22.sp)
        }
    }
}

@Composable
fun AvatarView(avatar: AvatarItem, onClick: (AvatarItem) -> Unit) {
    Box(
        modifier = Modifier
            .size(72.dp)
            .clip(CircleShape)
            .clickable { onClick(avatar) }
            .background(Color(0xFFE0E0E0)) // Fallback if no image mechanism
            .border(2.dp, Color.White, CircleShape)
    ) {
        // Here we would normally use Kamel or Coil multiplatform image.
        // For simplicity in this UI component template, we show a basic shape.
        
        // Status Indicator
        if (avatar.status != null) {
            val statusColor = when (avatar.status) {
                AvatarStatus.ONLINE -> Color(0xFF4CAF50)
                AvatarStatus.BUSY -> Color(0xFFF44336)
                AvatarStatus.AWAY -> Color(0xFFFF9800)
                AvatarStatus.DO_NOT_DISTURB -> Color(0xFFE91E63)
            }
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(statusColor)
                    .border(2.dp, Color.White, CircleShape)
                    .align(Alignment.BottomEnd)
                    .offset(x = (-4).dp, y = (-4).dp)
            )
        }
    }
}
