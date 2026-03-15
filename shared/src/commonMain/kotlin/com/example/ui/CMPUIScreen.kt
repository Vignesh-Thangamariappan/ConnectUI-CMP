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
        FeatureItem("chat", "All on the same page", "Chat with your team and customers on desktop or mobile with real-time alerts."),
        FeatureItem("messages", "Conversations that count", "Share messages, files, images, and notes—decisions move faster."),
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

        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            features.forEach { feature ->
                FeatureRow(feature = feature)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

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
            .padding(horizontal = 60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(vertical = 60.dp)
        ) {
            CMPUIDescription(isLandscape = true)

            Spacer(modifier = Modifier.height(40.dp))

            Column {
                features.forEach { feature ->
                    FeatureRow(feature = feature)
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            CMPUITeamMemberButton(delegate)
        }

        Spacer(modifier = Modifier.width(60.dp))

        Box(
            modifier = Modifier
                .weight(1.3f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            // Visual mockup with overlapping phone frames
            Box(modifier = Modifier.fillMaxSize()) {
                PhoneFrame(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(x = (-60).dp, y = (-30).dp)
                        .width(280.dp)
                        .aspectRatio(0.46f),
                    title = "Connect",
                    frameType = PhoneFrameType.LIST
                )
                
                PhoneFrame(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(x = 80.dp, y = 50.dp)
                        .width(280.dp)
                        .aspectRatio(0.46f),
                    title = "Cody Fisher",
                    frameType = PhoneFrameType.CHAT
                )
            }
        }
    }
}

@Composable
fun CMPUIHeader() {
    Text(
        text = "Connect",
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        modifier = Modifier.fillMaxWidth(),
        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        color = Color(0xFF1A1F23)
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
fun CMPUIDescription(isLandscape: Boolean = false) {
    Column(modifier = Modifier.padding(horizontal = if (isLandscape) 0.dp else 24.dp)) {
        Text(
            text = "Connect with your team,\nwherever work takes you",
            fontWeight = FontWeight.SemiBold,
            fontSize = if (isLandscape) 32.sp else 20.sp,
            color = Color(0xFF111111),
            lineHeight = if (isLandscape) 40.sp else 28.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Collaboration works best in one place. Connect keeps conversations flowing, even on the go.",
            fontSize = if (isLandscape) 18.sp else 14.sp,
            color = Color(0xFF111111),
            lineHeight = if (isLandscape) 26.sp else 22.sp
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

enum class PhoneFrameType {
    LIST, CHAT
}

@Composable
fun PhoneFrame(
    modifier: Modifier = Modifier,
    title: String,
    frameType: PhoneFrameType
) {
    Surface(
        modifier = modifier
            .border(10.dp, Color(0xFF1A1F23), RoundedCornerShape(36.dp))
            .clip(RoundedCornerShape(36.dp)),
        color = Color.White,
        elevation = 12.dp
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Status bar area
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("9:41", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.List, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(Icons.Default.Email, contentDescription = null, modifier = Modifier.size(14.dp))
                }
            }

            // App Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
                if (frameType == PhoneFrameType.LIST) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterEnd).size(18.dp)
                    )
                }
            }

            // Content simulation
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (frameType == PhoneFrameType.LIST) {
                    val listData = listOf(
                        "Cody Fisher" to "Perfect!",
                        "Dianne Russell" to "Good morning! I've booked a call...",
                        "Floyd Miles" to "The pitch was amazing 💚",
                        "Team Growth 🌲" to "Lucas: Bookings are up this week"
                    )
                    listData.forEach { (name, msg) ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFEEEEEE))
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(text = name, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                                Text(text = msg, fontSize = 10.sp, color = Color.Gray, maxLines = 1)
                            }
                        }
                    }
                } else {
                    val chatData = listOf(
                        true to "Hey Cody 👋",
                        true to "Claudio left a great review, nice job! He's just booked in for another 5 sessions.",
                        false to "James! 😃",
                        false to "Claudio left a great review, nice job! He's just booked in for another 5 sessions."
                    )
                    chatData.forEach { (isJames, msg) ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp),
                            contentAlignment = if (isJames) Alignment.CenterStart else Alignment.CenterEnd
                        ) {
                            Surface(
                                color = if (isJames) Color(0xFFF5F5F5) else Color(0xFF1A1F23),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.widthIn(max = 180.dp)
                            ) {
                                Text(
                                    text = msg,
                                    fontSize = 10.sp,
                                    modifier = Modifier.padding(8.dp),
                                    color = if (isJames) Color.Black else Color.White
                                )
                            }
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
