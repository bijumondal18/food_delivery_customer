package com.delivery.fooddeliverycustomer.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(
    name = "app_preferences"
)

@Singleton
class AppPreferences @Inject constructor(
   @ApplicationContext private val context: Context
) {

    companion object {
        private val LOGIN_SHEET_DISMISSED =
            booleanPreferencesKey("login_sheet_dismissed")
    }

    val isLoginSheetDismissed: Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[LOGIN_SHEET_DISMISSED] ?: false
        }

    suspend fun setLoginSheetDismissed() {
        context.dataStore.edit { preferences ->
            preferences[LOGIN_SHEET_DISMISSED] = true
        }
    }
}