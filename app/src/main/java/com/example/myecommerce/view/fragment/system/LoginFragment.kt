package com.example.myecommerce.view.fragment.system

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.myecommerce.R
import com.example.myecommerce.databinding.FragmentLoginBinding
import com.example.myecommerce.helper.GlobalHelper
import com.example.myecommerce.viewmodel.UserViewModel

class LoginFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var controller : NavController
    private lateinit var binding : FragmentLoginBinding
    private lateinit var email: String
    private lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(layoutInflater)
        val mView = binding.root
        controller = findNavController()
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.noAccountBtn.setOnClickListener {
            controller.navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }

        binding.loginBtn.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            email = binding.emailEdt.text.toString()
            password = binding.passwordEdt.text.toString()
            if(checkEmailAndPassword(email, password)){
                userViewModel.loginWithEmailAndPassword(requireContext(), controller , email, password)
            }
            binding.progressBar.visibility = View.INVISIBLE
        }

        binding.forgorPswBtn.setOnClickListener {
            controller.navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment())
        }

        return mView
    }

    fun checkEmailAndPassword(email: String, password: String): Boolean{
        if(email.matches(GlobalHelper.emailPattern.toRegex())){
            if(password.length >= 8){
                return true
            }else{
                binding.passwordEdt.error = requireContext().getString(R.string.require_password_8_characters)
            }
        }else{
            binding.emailEdt.error = requireContext().getString(R.string.invalid_email)
        }

        return false
    }

}