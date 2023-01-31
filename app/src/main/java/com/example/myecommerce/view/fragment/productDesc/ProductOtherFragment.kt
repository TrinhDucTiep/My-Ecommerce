package com.example.myecommerce.view.fragment.productDesc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myecommerce.R
import com.example.myecommerce.databinding.FragmentProductOtherBinding
import com.example.myecommerce.model.DetailProductModel

class ProductOtherFragment(var detailProductModel: DetailProductModel) : Fragment() {

    private lateinit var binding: FragmentProductOtherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductOtherBinding.inflate(layoutInflater)
        val mView = binding.root

        binding.tvProductOther.text = detailProductModel.otherDesc

        return mView
    }

}