package id.antasari.uts_mp_230104040118.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Shapes
import androidx.compose.foundation.shape.RoundedCornerShape

private val BrandPrimary = Color(0xFF6C63FF)
private val BrandSecondary = Color(0xFF00BFA6)
private val BrandSurface = Color(0xFFF7F8FC)

private val AppColors = lightColorScheme(
    primary = BrandPrimary,
    onPrimary = Color.White,
    secondary = BrandSecondary,
    surface = BrandSurface,
    background = Color.White
)

private val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(8),
    small = RoundedCornerShape(12),
    medium = RoundedCornerShape(16),
    large = RoundedCornerShape(20),
    extraLarge = RoundedCornerShape(28)
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColors,
        typography = Typography(), // pakai default M3 (sudah bagus)
        shapes = AppShapes,
        content = content
    )
}
