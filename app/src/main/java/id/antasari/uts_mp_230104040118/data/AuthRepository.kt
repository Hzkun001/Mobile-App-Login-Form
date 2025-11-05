package id.antasari.uts_mp_230104040118.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthRepository(private val context: Context) {
    val usernameFlow: Flow<String?> = context.dataStore.data.map { it[KEY_USERNAME] }

    suspend fun saveUsername(username: String) {
        context.dataStore.edit { it[KEY_USERNAME] = username }
    }
    suspend fun clearUsername() {
        context.dataStore.edit { it.remove(KEY_USERNAME) }
    }
}
