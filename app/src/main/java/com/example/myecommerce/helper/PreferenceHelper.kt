package com.example.myecommerce.helper

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class PreferenceHelper {
    companion object {
        var firebaseAuth = FirebaseAuth.getInstance()
        var firebaseUser: FirebaseUser? = firebaseAuth.currentUser
    }
}