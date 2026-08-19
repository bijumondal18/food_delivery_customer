package com.delivery.fooddeliverycustomer.data.repository


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {

    /**
     * Current logged-in Firebase user.
     */
    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    /**
     * Check whether the user is currently logged in.
     */
    fun isLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    /**
     * Login using email and password.
     */
    suspend fun loginWithEmail(
        email: String,
        password: String
    ): Result<FirebaseUser> {

        return try {

            val result = firebaseAuth
                .signInWithEmailAndPassword(
                    email,
                    password
                )
                .await()

            val user = result.user

            user?.let {
                Result.success(it)
            } ?: Result.failure(
                Exception("User not found")
            )

        } catch (e: FirebaseAuthException) {

            when(e.errorCode){
                "ERROR_INVALID_EMAIL" ->
                    Result.failure(
                        Exception("Invalid email address")
                    )

                "ERROR_USER_NOT_FOUND" ->
                    Result.failure(
                        Exception("No account found with this email")
                    )

                "ERROR_WRONG_PASSWORD" ->
                    Result.failure(
                        Exception("Incorrect password")
                    )

                "ERROR_INVALID_CREDENTIAL" ->
                    Result.failure(
                        Exception("Invalid email or password")
                    )

                "ERROR_USER_DISABLED" ->
                    Result.failure(
                        Exception("This account has been disabled")
                    )

                else ->
                    Result.failure(
                        Exception(e.message ?: "Login failed")
                    )
            }
        }
    }

    /**
     * Create a new account using email and password.
     */
    suspend fun registerWithEmail(
        email: String,
        password: String
    ): Result<FirebaseUser> {

        return try {

            val result = firebaseAuth
                .createUserWithEmailAndPassword(
                    email,
                    password
                )
                .await()

            result.user?.let {
                Result.success(it)
            } ?: Result.failure(
                Exception("User creation failed")
            )

        } catch (e: Exception) {

            Result.failure(e)
        }
    }

    /**
     * Logout current user.
     */
    fun logout() {
        firebaseAuth.signOut()
    }
}