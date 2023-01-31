package com.example.myecommerce.view.fragment.productDesc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myecommerce.R
import com.example.myecommerce.databinding.FragmentProductDescBinding
import com.example.myecommerce.model.DetailProductModel

class ProductDescFragment(var detailProductModel: DetailProductModel?) : Fragment() {

    private lateinit var binding: FragmentProductDescBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDescBinding.inflate(layoutInflater)
        val mView = binding.root
        binding.tvProductDesc.text = detailProductModel?.description

        return mView
    }

}