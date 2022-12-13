package com.example.myecommerce.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.myecommerce.R
import com.example.myecommerce.callback.VoidCallBack
import com.example.myecommerce.helper.AlertDialogHelper
import com.example.myecommerce.view.fragment.AccountFragment
import com.example.myecommerce.view.fragment.MainFragment
import com.example.myecommerce.view.fragment.system.LoginFragmentDirections
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class UserViewModel: ViewModel() {
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun registerWithEmailAndPassword(context: Context, controller: NavController, email: String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(OnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(context, R.string.sign_up_successfully, Toast.LENGTH_SHORT).show()
                    controller.popBackStack()
                } else{
                    Toast.makeText(context, R.string.error_happened, Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun loginWithEmailAndPassword(context: Context, controller: NavController, email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(OnCompleteListener {task ->
                if(task.isSuccessful){
                    controller.navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
                } else{
                    Toast.makeText(context, R.string.invalid_account_or_password, Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun resetPasswordBySendEmail(context: Context, email: String){
        mAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    Toast.makeText(context, R.string.send_email_successfully, Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, R.string.send_email_failed, Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun signOut(context: Context, controller: NavController){
        AlertDialogHelper.showAlertDialog(context
            , context.getString(R.string.sign_out) ,
            context.getString(R.string.want_to_sign_out),
            R.drawable.ic_alert,
            {
                mAuth.signOut()
                controller.navigate(R.id.action_mainFragment_to_loginFragment)
            })
    }
}