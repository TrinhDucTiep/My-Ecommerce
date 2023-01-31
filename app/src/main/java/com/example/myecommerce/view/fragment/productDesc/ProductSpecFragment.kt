package com.example.myecommerce.view.fragment.productDesc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myecommerce.R
import com.example.myecommerce.adapter.ProductSpecAdapter
import com.example.myecommerce.databinding.FragmentProductSpecBinding
import com.example.myecommerce.model.DetailProductModel


class ProductSpecFragment(var detailProductModel: DetailProductModel) : Fragment() {

    private lateinit var binding: FragmentProductSpecBinding
    private lateinit var productSpecAdapter: ProductSpecAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductSpecBinding.inflate(layoutInflater)
        val mView = binding.root

        productSpecAdapter = ProductSpecAdapter(detailProductModel.specTitle, detailProductModel.specValue)
        binding.rvSpec.adapter = productSpecAdapter
        binding.rvSpec.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return mView
    }

}