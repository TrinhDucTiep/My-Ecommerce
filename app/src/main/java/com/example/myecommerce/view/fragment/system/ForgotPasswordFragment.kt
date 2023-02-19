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
import com.example.myecommerce.databinding.FragmentForgotPasswordBinding
import com.example.myecommerce.helper.GlobalHelper
import com.example.myecommerce.viewmodel.AccountViewModel


class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding
    private lateinit var controller: NavController
    private lateinit var userViewModel: AccountViewModel
    private lateinit var email: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentForgotPasswordBinding.inflate(layoutInflater)
        controller = findNavController()
        userViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        val mView = binding.root

        binding.icClose.setOnClickListener {
            controller.popBackStack()
        }

        binding.sendEmailBtn.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            email = binding.emailEdt.text.toString()
            if(checkEmailAndPassword(email)){
                userViewModel.resetPasswordBySendEmail(requireContext(), email)
            }
            binding.progressBar.visibility = View.GONE
        }

        return mView
    }

    fun checkEmailAndPassword(email: String): Boolean{
        if(email.matches(GlobalHelper.emailPattern.toRegex())){
            return true
        }else{
            binding.emailEdt.error = requireContext().getString(R.string.invalid_email)
        }
        return false
    }

}