package id.antasari.uts_mp_230104040118.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import id.antasari.uts_mp_230104040118.presentation.AuthViewModel
import id.antasari.uts_mp_230104040118.ui.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(nav: NavHostController, vm: AuthViewModel) {
    val ctx = LocalContext.current

    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var remember by rememberSaveable { mutableStateOf(true) } // default on biar dosen notice fitur
    var showPassword by rememberSaveable { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf<String?>(null) }
    var busy by remember { mutableStateOf(false) }

    val bg = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.surface,
            Color.White
        )
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(bg)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(6.dp),
            shape = MaterialTheme.shapes.large
        ) {
            Column(Modifier.padding(20.dp)) {
                Text("Masuk", style = MaterialTheme.typography.headlineSmall)
                Text("Akun demo: mhs / 123", color = Color.Gray)

                Spacer(Modifier.height(16.dp))

                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it; errorMsg = null },
                    label = { Text("Username") },
                    singleLine = true,
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(imeAction = ImeAction.Next),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(10.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it; errorMsg = null },
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { showPassword = !showPassword }) {
                            Icon(
                                if (showPassword) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                                contentDescription = if (showPassword) "Sembunyikan" else "Tampilkan"
                            )
                        }
                    },
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                        keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    Switch(checked = remember, onCheckedChange = { remember = it })
                    Text("Ingat saya")
                    Spacer(Modifier.weight(1f))
                    Text(
                        "Lupa password?",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            Toast.makeText(ctx, "Demo saja 😄", Toast.LENGTH_SHORT).show()
                        }
                    )
                }

                errorMsg?.let {
                    Spacer(Modifier.height(8.dp))
                    AssistChip(
                        onClick = { errorMsg = null },
                        label = { Text(it) },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.08f),
                            labelColor = MaterialTheme.colorScheme.error
                        )
                    )
                }

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (username.isBlank() || password.isBlank()) {
                            errorMsg = "Username dan password wajib diisi."; return@Button
                        }
                        busy = true
                        vm.login(
                            username = username.trim(),
                            password = password,
                            remember = remember,
                            onSuccess = {
                                busy = false
                                nav.navigate(Routes.DASHBOARD) {
                                    popUpTo(Routes.LOGIN) { inclusive = true }
                                }
                            },
                            onError = { msg ->
                                busy = false
                                errorMsg = msg
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    enabled = !busy
                ) {
                    AnimatedContent(
                        targetState = busy,
                        transitionSpec = { fadeIn(tween(150)) togetherWith fadeOut(tween(150)) },
                        label = "login-btn"
                    ) { isBusy ->
                        if (isBusy) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            Spacer(Modifier.width(10.dp))
                            Text("Memeriksa…")
                        } else {
                            Text("Login")
                        }
                    }
                }

                Spacer(Modifier.height(6.dp))
                Text(
                    "Dengan masuk, kamu menyetujui syarat & ketentuan demo ini.",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
