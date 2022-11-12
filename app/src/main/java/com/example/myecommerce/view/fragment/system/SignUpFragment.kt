package com.example.myecommerce.view.fragment.system

import android.os.Bundle
import android.text.TextUtils.isEmpty
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.myecommerce.R
import com.example.myecommerce.databinding.FragmentSignUpBinding
import com.example.myecommerce.helper.GlobalHelper
import com.example.myecommerce.viewmodel.UserViewModel

class SignUpFragment : Fragment() {

    private lateinit var controller: NavController
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var email: String
    private lateinit var password: String

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSignUpBinding.inflate(layoutInflater)
        val mView = binding.root
        controller = findNavController()
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.icClose.setOnClickListener {
            controller.popBackStack()
        }

        binding.signUpBtn.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            email = binding.emailEdt.text.toString()
            password = binding.passwordEdt.text.toString()
            val rePassword: String = binding.rePasswordEdt.text.toString()
            if(checkEmailAndPassword(email, password, rePassword)){
                userViewModel.registerWithEmailAndPassword(requireContext(), controller, email, password)
            }
            binding.progressBar.visibility = View.INVISIBLE
        }

        return mView
    }

    fun checkEmailAndPassword(email: String, password: String, rePassword: String): Boolean{
        if(email.matches(GlobalHelper.emailPattern.toRegex())){
            if(!isEmpty(binding.fullName.text.toString())){
                if(password.length >= 8){
                    if(password.equals(rePassword)){
                        return true;
                    } else{
                        binding.rePasswordEdt.error = requireContext().getString(R.string.password_not_match)
                    }
                }else{
                    binding.passwordEdt.error = requireContext().getString(R.string.require_password_8_characters)
                }
            }else{
                binding.fullName.error = requireContext().getString(R.string.require_full_name)
            }
        }else{
            binding.emailEdt.error = requireContext().getString(R.string.invalid_email)
        }

        return false
    }

}