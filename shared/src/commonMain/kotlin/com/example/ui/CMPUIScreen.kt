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
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
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
            val isCompactLandscape = isLandscape && maxHeight < 500.dp
            
            // Scaling factors
            val widthScale = if (isLandscape) maxWidth / 1024.dp else maxWidth / 375.dp
            val heightScale = if (isLandscape) maxHeight / 800.dp else maxHeight / 667.dp
            
            // In landscape, we MUST respect height more to avoid scrolling
            val layoutScale = (minOf(widthScale, heightScale)).coerceIn(0.65f, 1.2f)
            
            // Font scale in landscape should also consider height to prevent pushing content out
            val fontScale = if (isLandscape) {
                (minOf(widthScale, heightScale * 1.1f)).coerceIn(0.75f, 1.1f)
            } else {
                (widthScale).coerceIn(0.85f, 1.1f)
            }

            if (isLandscape) {
                CMPUIHorizontalLandscapeLayout(features, avatars, layoutScale, fontScale, isCompactLandscape, delegate)
            } else {
                CMPUIVerticalLayout(features, avatars, layoutScale, fontScale, delegate)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CMPUIVerticalLayout(
    features: List<FeatureItem>,
    avatars: List<AvatarItem>,
    layoutScale: Float,
    fontScale: Float,
    delegate: CMPUIDelegate?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = (40 * layoutScale).dp, bottom = (20 * layoutScale).dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CMPUIHeader(fontScale)

        Spacer(modifier = Modifier.height((20 * layoutScale).dp))

        CMPUIAvatars(avatars, layoutScale, delegate)

        Spacer(modifier = Modifier.weight(1f))

        CMPUIDescription(scale = fontScale)

        Spacer(modifier = Modifier.height((20 * layoutScale).dp))

        Column(
            modifier = Modifier
                .padding(horizontal = (24 * fontScale).dp)
        ) {
            features.forEach { feature ->
                FeatureRow(feature = feature, scale = fontScale)
                Spacer(modifier = Modifier.height((12 * layoutScale).dp))
            }
        }

        Spacer(modifier = Modifier.weight(1.5f))

        CMPUITeamMemberButton(layoutScale, delegate)
    }
}

@Composable
fun CMPUIHorizontalLandscapeLayout(
    features: List<FeatureItem>,
    avatars: List<AvatarItem>,
    layoutScale: Float,
    fontScale: Float,
    isCompact: Boolean,
    delegate: CMPUIDelegate?
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = (60 * layoutScale).dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1.2f)
                .fillMaxHeight()
                .padding(vertical = (24 * layoutScale).dp)
        ) {
            CMPUIDescription(isLandscape = true, scale = fontScale)

            Spacer(modifier = Modifier.weight(1f))

            Column {
                features.forEach { feature ->
                    FeatureRow(feature = feature, scale = fontScale)
                    Spacer(modifier = Modifier.height((8 * layoutScale).dp))
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Hide button here if in compact mode (will show on the right instead)
            if (!isCompact) {
                CMPUITeamMemberButton(layoutScale, delegate)
            }
        }

        Spacer(modifier = Modifier.width((40 * layoutScale).dp))

        BoxWithConstraints(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            val constraintsScope = this
            val maxWidthLocal = constraintsScope.maxWidth
            val maxHeightLocal = constraintsScope.maxHeight

            if (isCompact) {
                // In compact mode, show the primary button here prominently
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CMPUITeamMemberButton(layoutScale * 1.2f, delegate) // Slightly larger button
                }
            } else {
                // Calculate scale based on available height (800dp is base height in Figma)
                val baseHeight = 800.dp
                val mockupScale = (maxHeightLocal / baseHeight).coerceIn(0.75f, 1.2f)
                
                // Visual mockup with overlapping phone frames
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    PhoneFrame(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .offset(
                                x = (-(maxWidthLocal * 0.15f)), 
                                y = (-(maxHeightLocal * 0.05f))
                            )
                            .width(280.dp * mockupScale)
                            .aspectRatio(0.46f),
                        title = "Connect",
                        frameType = PhoneFrameType.LIST,
                        scale = mockupScale
                    )
                    
                    PhoneFrame(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .offset(
                                x = (maxWidthLocal * 0.2f), 
                                y = (maxHeightLocal * 0.1f)
                            )
                            .width(280.dp * mockupScale)
                            .aspectRatio(0.46f),
                        title = "Cody Fisher",
                        frameType = PhoneFrameType.CHAT,
                        scale = mockupScale
                    )
                }
            }
        }
    }
}

@Composable
fun CMPUIHeader(scale: Float) {
    Text(
        text = "Connect",
        fontWeight = FontWeight.SemiBold,
        fontSize = (16 * scale).sp,
        modifier = Modifier.fillMaxWidth(),
        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        color = Color(0xFF1A1F23)
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CMPUIAvatars(avatars: List<AvatarItem>, scale: Float, delegate: CMPUIDelegate?) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = (32 * scale).dp),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.spacedBy((16 * scale).dp)
    ) {
        avatars.forEach { avatar ->
            Box(modifier = Modifier.padding(horizontal = (8 * scale).dp)) {
                AvatarView(avatar = avatar, scale = scale) {
                    delegate?.onAvatarTapped(it)
                }
            }
        }
    }
}

@Composable
fun CMPUIDescription(isLandscape: Boolean = false, scale: Float = 1f) {
    Column(modifier = Modifier.padding(horizontal = if (isLandscape) 0.dp else (24 * scale).dp)) {
        Text(
            text = "Connect with your team,\nwherever work takes you",
            fontWeight = FontWeight.SemiBold,
            fontSize = if (isLandscape) (32 * scale).sp else (20 * scale).sp,
            color = Color(0xFF111111),
            lineHeight = if (isLandscape) (40 * scale).sp else (28 * scale).sp
        )

        Spacer(modifier = Modifier.height((16 * scale).dp))

        Text(
            text = "Collaboration works best in one place. Connect keeps conversations flowing, even on the go.",
            fontSize = if (isLandscape) (18 * scale).sp else (14 * scale).sp,
            color = Color(0xFF111111),
            lineHeight = if (isLandscape) (26 * scale).sp else (22 * scale).sp
        )
    }
}

@Composable
fun CMPUITeamMemberButton(scale: Float, delegate: CMPUIDelegate?) {
    val buttonHeight = (56 * scale).dp.coerceAtLeast(48.dp)
    val buttonWidth = (200 * scale).dp.coerceAtLeast(180.dp)
    val fontSize = if (16 * scale < 14) 14.sp else (16 * scale).sp
    
    Button(
        onClick = { delegate?.onAddTeamMemberClicked() },
        modifier = Modifier
            .padding(horizontal = (24 * scale).dp)
            .height(buttonHeight)
            .widthIn(min = buttonWidth),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF111111)),
        shape = RoundedCornerShape(buttonHeight / 2),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add, 
            contentDescription = null, 
            tint = Color.White,
            modifier = Modifier.size(if (20 * scale < 18) 18.dp else (20 * scale).dp)
        )
        Spacer(modifier = Modifier.width((8 * scale).dp))
        Text(
            text = "Team member", 
            color = Color.White, 
            fontSize = fontSize, 
            fontWeight = FontWeight.SemiBold
        )
    }
}

enum class PhoneFrameType {
    LIST, CHAT
}

@Composable
fun PhoneFrame(
    modifier: Modifier = Modifier,
    title: String,
    frameType: PhoneFrameType,
    scale: Float = 1f
) {
    Surface(
        modifier = modifier
            .border((10 * scale).dp, Color(0xFF1A1F23), RoundedCornerShape((36 * scale).dp))
            .clip(RoundedCornerShape((36 * scale).dp)),
        color = Color.White,
        elevation = (12 * scale).dp
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Status bar area
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = (24 * scale).dp, vertical = (14 * scale).dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("9:41", fontSize = (12 * scale).sp, fontWeight = FontWeight.SemiBold)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.AutoMirrored.Filled.List, 
                        contentDescription = null, 
                        modifier = Modifier.size((14 * scale).dp)
                    )
                    Spacer(modifier = Modifier.width((6 * scale).dp))
                    Icon(
                        Icons.Default.Email, 
                        contentDescription = null, 
                        modifier = Modifier.size((14 * scale).dp)
                    )
                }
            }

            // App Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = (20 * scale).dp, vertical = (10 * scale).dp)
            ) {
                Text(
                    text = title,
                    fontSize = (15 * scale).sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
                if (frameType == PhoneFrameType.LIST) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterEnd).size((18 * scale).dp)
                    )
                }
            }

            // Content simulation
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding((16 * scale).dp),
                verticalArrangement = Arrangement.spacedBy((16 * scale).dp)
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
                                    .size((36 * scale).dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFEEEEEE))
                            )
                            Spacer(modifier = Modifier.width((12 * scale).dp))
                            Column {
                                Text(
                                    text = name, 
                                    fontSize = (12 * scale).sp, 
                                    fontWeight = FontWeight.Bold, 
                                    color = Color.Black
                                )
                                Text(
                                    text = msg, 
                                    fontSize = (10 * scale).sp, 
                                    color = Color.Gray, 
                                    maxLines = 1,
                                    lineHeight = (12 * scale).sp
                                )
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
                                .padding(vertical = (2 * scale).dp),
                            contentAlignment = if (isJames) Alignment.CenterStart else Alignment.CenterEnd
                        ) {
                            Surface(
                                color = if (isJames) Color(0xFFF5F5F5) else Color(0xFF1A1F23),
                                shape = RoundedCornerShape((12 * scale).dp),
                                modifier = Modifier.widthIn(max = (180 * scale).dp)
                            ) {
                                Text(
                                    text = msg,
                                    fontSize = (10 * scale).sp,
                                    modifier = Modifier.padding((8 * scale).dp),
                                    color = if (isJames) Color.Black else Color.White,
                                    lineHeight = (12 * scale).sp
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
fun FeatureRow(feature: FeatureItem, scale: Float = 1f) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = (24 * scale).dp),
        verticalAlignment = Alignment.Top
    ) {
        val icon: ImageVector = when(feature.iconId) {
            "chat" -> Icons.Default.Email
            "messages" -> Icons.AutoMirrored.Filled.List
            "people" -> Icons.Default.Person
            else -> Icons.Default.Add
        }
        
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size((24 * scale).dp)
        )
        Spacer(modifier = Modifier.width((16 * scale).dp))
        Column {
            Text(
                text = feature.title, 
                fontWeight = FontWeight.Bold, 
                fontSize = (16 * scale).sp, 
                color = Color.Black
            )
            Spacer(modifier = Modifier.height((6 * scale).dp))
            Text(
                text = feature.description, 
                fontSize = (16 * scale).sp, 
                color = Color.Black.copy(alpha = 0.6f), 
                lineHeight = (22 * scale).sp
            )
        }
    }
}

@Composable
fun AvatarView(avatar: AvatarItem, scale: Float = 1f, onClick: (AvatarItem) -> Unit) {
    Box(
        modifier = Modifier
            .size((72 * scale).dp)
            .clip(CircleShape)
            .clickable { onClick(avatar) }
            .background(Color(0xFFE0E0E0)) // Fallback if no image mechanism
            .border((2 * scale).dp, Color.White, CircleShape)
    ) {
        // ... (remaining contents remain independent of scale for simplicity or can be refined)
        
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
                    .size((16 * scale).dp)
                    .clip(CircleShape)
                    .background(statusColor)
                    .border((2 * scale).dp, Color.White, CircleShape)
                    .align(Alignment.BottomEnd)
                    .offset(x = (-(4 * scale)).dp, y = (-(4 * scale)).dp)
            )
        }
    }
}
