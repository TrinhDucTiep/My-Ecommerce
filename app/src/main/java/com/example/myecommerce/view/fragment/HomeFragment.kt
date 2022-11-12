package com.example.myecommerce.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.myecommerce.adapter.BannerAdapter
import com.example.myecommerce.adapter.CategoryAdapter
import com.example.myecommerce.databinding.FragmentHomeBinding
import com.example.myecommerce.helper.GlobalHelper
import me.relex.circleindicator.CircleIndicator3

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var compositePageTransformer: CompositePageTransformer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //init
        binding = FragmentHomeBinding.inflate(layoutInflater)
        val mView = binding.root
        categoryAdapter = CategoryAdapter(GlobalHelper.categoryModelList)
        bannerAdapter = BannerAdapter(GlobalHelper.listBannerModel)
        compositePageTransformer = CompositePageTransformer()


        binding.bannerViewPager.clipToPadding = false
        binding.bannerViewPager.clipChildren = false
        binding.bannerViewPager.offscreenPageLimit = 3

        binding.categoryRV.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRV.adapter = categoryAdapter
        categoryAdapter.notifyDataSetChanged()

        binding.bannerViewPager.adapter = bannerAdapter
        binding.bannerIndicator.setViewPager(binding.bannerViewPager)
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        binding.bannerViewPager.setPageTransformer(compositePageTransformer)


        return mView
    }

}