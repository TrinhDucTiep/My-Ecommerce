package com.example.myecommerce.view.fragment.homeChildFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myecommerce.R
import com.example.myecommerce.databinding.FragmentDetailProductBinding

class DetailProductFragment : Fragment() {

    private lateinit var binding: FragmentDetailProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailProductBinding.inflate(layoutInflater)
        val mView = binding.root

        return mView
    }

}