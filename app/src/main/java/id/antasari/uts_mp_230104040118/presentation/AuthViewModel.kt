package id.antasari.uts_mp_230104040118.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.antasari.uts_mp_230104040118.data.AuthRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AuthViewModel(private val repo: AuthRepository) : ViewModel() {
    val username: StateFlow<String?> = repo.usernameFlow.stateIn(
        scope = viewModelScope, started = SharingStarted.WhileSubscribed(5000), initialValue = null
    )

    fun login(username: String, password: String, remember: Boolean,
              onSuccess: () -> Unit, onError: (String) -> Unit) = viewModelScope.launch {
        if (username == "mhs" && password == "123") {
            if (remember) repo.saveUsername(username)
            onSuccess()
        } else onError("Username atau password salah (hint: mhs / 123)")
    }

    fun logout(onDone: () -> Unit) = viewModelScope.launch {
        repo.clearUsername()
        onDone()
    }
}
