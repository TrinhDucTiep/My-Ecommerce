package com.example.myecommerce.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.myecommerce.R
import com.example.myecommerce.databinding.FragmentAddAddressBinding

class AddAddressFragment : Fragment() {

    lateinit var binding: FragmentAddAddressBinding
    lateinit var controller: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddAddressBinding.inflate(layoutInflater)
        val mView = binding.root
        controller = findNavController()

        return mView
    }

}