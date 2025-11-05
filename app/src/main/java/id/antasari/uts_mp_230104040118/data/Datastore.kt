package id.antasari.uts_mp_230104040118.data

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

const val DS_NAME = "auth_prefs"
val Context.dataStore by preferencesDataStore(name = DS_NAME)
val KEY_USERNAME = stringPreferencesKey("username")
