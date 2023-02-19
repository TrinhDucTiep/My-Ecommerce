package com.example.myecommerce.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.myecommerce.databinding.FragmentAccountBinding
import com.example.myecommerce.viewmodel.AccountViewModel

class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var controller: NavController
    private lateinit var userViewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(layoutInflater)
        val mView = binding.root
        controller = findNavController()
        userViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)

        binding.btnSignOut.setOnClickListener {
            userViewModel.signOut(requireContext(), controller)
        }

        return mView
    }

}