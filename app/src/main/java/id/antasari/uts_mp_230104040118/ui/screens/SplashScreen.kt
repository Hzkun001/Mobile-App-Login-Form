package id.antasari.uts_mp_230104040118.ui.screens

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import id.antasari.uts_mp_230104040118.presentation.AuthViewModel
import id.antasari.uts_mp_230104040118.ui.navigation.Routes

@Composable
fun SplashScreen(nav: NavHostController, vm: AuthViewModel) {
    val username by vm.username.collectAsState()

    // Cek remember saat start
    LaunchedEffect(username) {
        if (!username.isNullOrBlank()) {
            nav.navigate(Routes.DASHBOARD) {
                popUpTo(Routes.SPLASH) { inclusive = true }
            }
        }
    }

    // Background gradient lembut
    val gradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
            MaterialTheme.colorScheme.secondary.copy(alpha = 0.08f),
            Color.White
        )
    )

    // Pulse animasi logo
    val infinite = rememberInfiniteTransition(label = "pulse")
    val scale by infinite.animateFloat(
        initialValue = 0.98f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(tween(1200), RepeatMode.Reverse),
        label = "scale"
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(gradient),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                Modifier
                    .size(96.dp)
                    .scale(scale)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.Person, contentDescription = "Logo", tint = MaterialTheme.colorScheme.onPrimary)
            }
            Spacer(Modifier.height(16.dp))
            Text("UTS MP – Form Login Palsu + Remember Me", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(20.dp))

            if (username.isNullOrBlank()) {
                Button(onClick = {
                    nav.navigate(Routes.LOGIN) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                }) { Text("Masuk") }
            } else {
                CircularProgressIndicator()
            }
        }
    }
}
