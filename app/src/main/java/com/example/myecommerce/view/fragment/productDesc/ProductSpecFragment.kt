package com.example.myecommerce.view.fragment.productDesc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myecommerce.R
import com.example.myecommerce.databinding.FragmentProductSpecBinding


class ProductSpecFragment : Fragment() {

    private lateinit var binding: FragmentProductSpecBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductSpecBinding.inflate(layoutInflater)
        val mView = binding.root

        return mView
    }

}