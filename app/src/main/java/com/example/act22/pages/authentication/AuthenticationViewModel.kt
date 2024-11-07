package com.example.act22.pages.authentication

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthenticationViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun signUpUser(email: String, password: String, onResult: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Log.d("SignUp", "Successfully signed up: ${user?.email}")
                    onResult(true)  // Indicate success
                } else {
                    Log.e("SignUp", "Sign-up failed: ${task.exception?.message}")
                    onResult(false) // Indicate failure
                }
            }
    }


    fun signInUser(email: String, password: String, onResult: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true)  // Indicate success
                } else {
                    onResult(false) // Indicate failure
                }
            }
    }


    fun checkIfUserIsLoggedIn(): Boolean {
        return false
    }

}