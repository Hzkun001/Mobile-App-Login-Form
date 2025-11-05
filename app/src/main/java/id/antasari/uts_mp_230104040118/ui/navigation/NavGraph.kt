package id.antasari.uts_mp_230104040118.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import id.antasari.uts_mp_230104040118.presentation.AuthVMFactory
import id.antasari.uts_mp_230104040118.presentation.AuthViewModel
import id.antasari.uts_mp_230104040118.ui.screens.DashboardScreen
import id.antasari.uts_mp_230104040118.ui.screens.LoginScreen
import id.antasari.uts_mp_230104040118.ui.screens.SplashScreen

@Composable
fun AppNavHost(nav: NavHostController, factory: AuthVMFactory) {
    val vm: AuthViewModel = viewModel(factory = factory)
    NavHost(navController = nav, startDestination = Routes.SPLASH) {
        composable(Routes.SPLASH) { SplashScreen(nav, vm) }
        composable(Routes.LOGIN) { LoginScreen(nav, vm) }
        composable(Routes.DASHBOARD) { DashboardScreen(nav, vm) }
    }
}
