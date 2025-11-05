package id.antasari.uts_mp_230104040118.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import id.antasari.uts_mp_230104040118.presentation.AuthViewModel
import id.antasari.uts_mp_230104040118.ui.navigation.Routes

@Composable
fun DashboardScreen(nav: NavHostController, vm: AuthViewModel) {
    val username by vm.username.collectAsState()
    var showConfirm by remember { mutableStateOf(false) }

    val headerGradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
            Color.Transparent
        )
    )

    Column(
        Modifier
            .fillMaxSize()
            .background(headerGradient)
            .padding(20.dp)
    ) {
        // AppBar sederhana
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text("Dashboard", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
            Spacer(Modifier.weight(1f))
            IconButton(onClick = { showConfirm = true }) {
                Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = "Keluar")
            }
        }

        Spacer(Modifier.height(12.dp))

        // Profile card
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = MaterialTheme.shapes.large,
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                val initials = remember(username) {
                    (username?.trim().orEmpty().firstOrNull()?.uppercaseChar() ?: 'U').toString()
                }
                Box(
                    Modifier
                        .size(84.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        initials,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }
                Spacer(Modifier.height(12.dp))
                Text("Selamat datang, ${username ?: "Tamu"} 👋", style = MaterialTheme.typography.titleMedium)
                Text("Kamu berhasil melewati Splash & Login dengan Remember Me.",
                    color = Color.Gray)

                Spacer(Modifier.height(16.dp))
                OutlinedButton(onClick = { showConfirm = true }) {
                    Icon(Icons.Default.Delete, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Hapus Data Login / Keluar")
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // Info section
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF6F7FF)),
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(Modifier.padding(16.dp)) {
                Text("Info", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(6.dp))
                Text(
                    "- Data Remember Me disimpan di DataStore Preferences.\n" +
                            "- Navigasi Compose tanpa finish().\n" +
                            "- Coba logout untuk menghapus data & kembali ke Login."
                )
            }
        }
    }

    if (showConfirm) {
        AlertDialog(
            onDismissRequest = { showConfirm = false },
            confirmButton = {
                TextButton(onClick = {
                    vm.logout {
                        showConfirm = false
                        nav.navigate(Routes.LOGIN) {
                            popUpTo(Routes.DASHBOARD) { inclusive = true }
                        }
                    }
                }) { Text("Ya, hapus") }
            },
            dismissButton = { TextButton(onClick = { showConfirm = false }) { Text("Batal") } },
            icon = { Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = null) },
            title = { Text("Keluar & Hapus Data?") },
            text = { Text("Data username yang diingat akan dihapus dari penyimpanan.") }
        )
    }
}
