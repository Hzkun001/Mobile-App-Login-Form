package id.antasari.uts_mp_230104040118

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import id.antasari.uts_mp_230104040118.data.AuthRepository
import id.antasari.uts_mp_230104040118.ui.navigation.AppNavHost
import id.antasari.uts_mp_230104040118.presentation.AuthVMFactory
import id.antasari.uts_mp_230104040118.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = AuthVMFactory(AuthRepository(this))

        setContent {
            AppTheme {
                val nav = rememberNavController()
                AppNavHost(nav = nav, factory = factory)
            }
        }
    }
}
