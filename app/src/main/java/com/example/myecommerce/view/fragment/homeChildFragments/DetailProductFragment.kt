package com.example.myecommerce.view.fragment.homeChildFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.myecommerce.R
import com.example.myecommerce.adapter.ProductDescAdapter
import com.example.myecommerce.databinding.FragmentDetailProductBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailProductFragment : Fragment() {

    private lateinit var binding: FragmentDetailProductBinding
    private lateinit var productDescAdapter: ProductDescAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailProductBinding.inflate(layoutInflater)
        val mView = binding.root
        productDescAdapter = ProductDescAdapter(this)
        binding.layoutProductDesc.viewPagerProductDesc.adapter = productDescAdapter

        //favorite button
        binding.imgFavorite.setOnClickListener {

        }

        //tab layout for description
        TabLayoutMediator(binding.layoutProductDesc.tabLayoutProductDesc, binding.layoutProductDesc.viewPagerProductDesc){
            tab, position ->
            run {
                when (position) {
                    0 -> tab.text = "Description"
                    1 -> tab.text = "Specification"
                    2 -> tab.text = "Other"
                    else -> {
                        tab.text = "Description"
                    }
                }
            }
        }.attach()

        //set listener for rating system
        for(i in 0 until binding.layoutRatings.starContainer.childCount){
            binding.layoutRatings.starContainer.getChildAt(i).setOnClickListener {
                for(j in 0 until binding.layoutRatings.starContainer.childCount){
                    val temp: ImageView = binding.layoutRatings.starContainer.getChildAt(j) as ImageView
                    if(j <= i){
                        temp.imageTintList = ContextCompat.getColorStateList(requireContext(), R.color.ratingYellow)
                    } else{
                        temp.imageTintList = ContextCompat.getColorStateList(requireContext(), R.color.colorGrayBG)
                    }
                }
            }
        }

        return mView
    }

}