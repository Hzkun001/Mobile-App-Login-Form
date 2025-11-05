package id.antasari.uts_mp_230104040118.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.antasari.uts_mp_230104040118.data.AuthRepository

class AuthVMFactory(private val repo: AuthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown VM class")
    }
}
