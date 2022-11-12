package com.example.myecommerce.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.findNavController
import com.example.myecommerce.R
import com.example.myecommerce.databinding.FragmentAccountBinding
import com.example.myecommerce.databinding.FragmentMainBinding

class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var controller: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(layoutInflater)
        val mView = binding.root
        controller = findNavController()

        binding.CCTxt.setOnClickListener {
            controller.navigate(MainFragmentDirections.actionMainFragmentToTestFragment())
        }

        return mView
    }

}