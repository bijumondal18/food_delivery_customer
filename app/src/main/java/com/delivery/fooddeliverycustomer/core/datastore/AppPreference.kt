package com.delivery.fooddeliverycustomer.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
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

        private val IS_LOGGED_IN =
            booleanPreferencesKey("is_logged_in")

        private val USER_ID =
            stringPreferencesKey("user_id")

        private val USER_EMAIL =
            stringPreferencesKey("user_email")

        private val USER_NAME =
            stringPreferencesKey("user_name")
    }

    // ---------------------------------------------------------
    // Login Sheet
    // ---------------------------------------------------------

    val isLoginSheetDismissed: Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[LOGIN_SHEET_DISMISSED] ?: false
        }

    suspend fun setLoginSheetDismissed() {
        context.dataStore.edit { preferences ->
            preferences[LOGIN_SHEET_DISMISSED] = true
        }
    }

    // ---------------------------------------------------------
    // Authentication
    // ---------------------------------------------------------

    val isLoggedIn: Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[IS_LOGGED_IN] ?: false
        }

    val userId: Flow<String?> =
        context.dataStore.data.map { preferences ->
            preferences[USER_ID]
        }

    val userEmail: Flow<String?> =
        context.dataStore.data.map { preferences ->
            preferences[USER_EMAIL]
        }

    val userName: Flow<String?> =
        context.dataStore.data.map { preferences ->
            preferences[USER_NAME]
        }

    suspend fun saveUser(
        userId: String,
        email: String?,
        name: String?
    ) {
        context.dataStore.edit { preferences ->

            preferences[IS_LOGGED_IN] = true

            preferences[USER_ID] = userId

            email?.let {
                preferences[USER_EMAIL] = it
            }

            name?.let {
                preferences[USER_NAME] = it
            }
        }
    }

    suspend fun clearUser() {
        context.dataStore.edit { preferences ->

            preferences[IS_LOGGED_IN] = false

            preferences.remove(USER_ID)
            preferences.remove(USER_EMAIL)
            preferences.remove(USER_NAME)
        }
    }
}