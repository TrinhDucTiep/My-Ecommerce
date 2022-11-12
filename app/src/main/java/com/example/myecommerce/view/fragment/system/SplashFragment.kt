package com.example.myecommerce.view.fragment.system

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.myecommerce.R
import com.google.firebase.auth.FirebaseAuth


class SplashFragment : Fragment() {

    private val mAuth = FirebaseAuth.getInstance()
    private lateinit var controller: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mView = inflater.inflate(R.layout.fragment_splash, container, false)
        controller = findNavController()

        return mView
    }

    override fun onStart() {
        super.onStart()

        Handler(Looper.getMainLooper()).postDelayed({
            val currentUser = mAuth.currentUser
            if(currentUser == null){
                controller.navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
            } else{
                controller.navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }
        }, 3000)

    }

}