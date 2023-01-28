package com.example.myecommerce.view.fragment

import android.annotation.SuppressLint
import android.net.NetworkInfo
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.myecommerce.R
import com.example.myecommerce.adapter.BannerAdapter
import com.example.myecommerce.adapter.CategoryAdapter
import com.example.myecommerce.adapter.GridProductAdapter
import com.example.myecommerce.adapter.HorizontalProductAdapter
import com.example.myecommerce.databinding.FragmentHomeBinding
import com.example.myecommerce.helper.GlobalHelper
import com.example.myecommerce.model.BannerModel
import com.example.myecommerce.model.CategoryModel
import com.example.myecommerce.model.HorizontalProductModel
import com.example.myecommerce.viewmodel.BannerViewModel
import com.example.myecommerce.viewmodel.CategoryViewModel
import com.example.myecommerce.viewmodel.DealOfTheDayViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var controller: NavController

    //adapter and relates
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var gridProductAdapter: GridProductAdapter
    private lateinit var horizontalProductAdapter: HorizontalProductAdapter
    private lateinit var compositePageTransformer: CompositePageTransformer

    //data from fire store
    private var listCategoryModel = mutableListOf<CategoryModel>()
    private var listBannerModel = mutableListOf<BannerModel>()
    private var listProductDealOfTheDay = mutableListOf<HorizontalProductModel>()

    //view model
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var bannerViewModel: BannerViewModel
    private lateinit var dealOfTheDayViewModel: DealOfTheDayViewModel

    private lateinit var timer: CountDownTimer

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //init
        binding = FragmentHomeBinding.inflate(layoutInflater)
        val mView = binding.root
        controller = findNavController()

        // set nointernet place holder
        if (!GlobalHelper.isNetworkAvailable(requireContext())) {
            binding.phNoInternet.root.visibility = View.VISIBLE
        } else {
            binding.phNoInternet.root.visibility = View.GONE
        }

        //category
//        if (listCategoryModel.size == 0)
            setUpCategory()

        //banner
//        if (listBannerModel.size == 0)
            setUpBanner()

        //auto pager for banners
//        if (listProductDealOfTheDay.size == 0)
            setUpAutoPager()

        //deal of the day
        dealOfTheDayViewModel = ViewModelProvider(this).get(DealOfTheDayViewModel::class.java)
        listProductDealOfTheDay = mutableListOf()
        horizontalProductAdapter = HorizontalProductAdapter(listProductDealOfTheDay, controller)
        dealOfTheDayViewModel.loadHomePageDealOfTheDay(listProductDealOfTheDay, horizontalProductAdapter)
        binding.hrDealOfTheDay.rvProduct.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.hrDealOfTheDay.rvProduct.adapter = horizontalProductAdapter
        horizontalProductAdapter.notifyDataSetChanged()

        //grid layout
        gridProductAdapter = GridProductAdapter(listProductDealOfTheDay)
        binding.gridViewProduct.gridViewProduct.adapter = gridProductAdapter
        binding.gridViewProduct.gridViewProduct.setOnTouchListener { _, event ->
            event.action == MotionEvent.ACTION_MOVE
        }

        return mView
    }

    fun setUpBanner() {
        bannerViewModel = ViewModelProvider(this).get(BannerViewModel::class.java)
        listBannerModel = mutableListOf()
        bannerAdapter = BannerAdapter(listBannerModel)
        bannerViewModel.loadHomePageBanners(bannerAdapter, listBannerModel) {
            binding.bannerIndicator.setViewPager(binding.bannerViewPager)
        }
        compositePageTransformer = CompositePageTransformer()
        binding.bannerViewPager.adapter = bannerAdapter
        binding.bannerViewPager.offscreenPageLimit = 6
        binding.bannerIndicator.setViewPager(binding.bannerViewPager)
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        binding.bannerViewPager.setPageTransformer(compositePageTransformer)
    }

    fun setUpAutoPager(){
        timer = object : CountDownTimer(3000, 1000){
            override fun onTick(millisUntilFinished: Long) {

            }
            override fun onFinish() {
                var currentPage = binding.bannerViewPager.currentItem + 1
                if(currentPage == listBannerModel.size) currentPage = 0
                binding.bannerViewPager.setCurrentItem(currentPage, true)
                timer.start()
            }

        }
        timer.start()
    }

    fun setUpCategory() {
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        listCategoryModel = mutableListOf()
        categoryAdapter = CategoryAdapter(listCategoryModel, controller)
        binding.categoryRV.adapter = categoryAdapter
        binding.categoryRV.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter.notifyDataSetChanged()
        categoryViewModel.loadCategoryFromFirestore(categoryAdapter, listCategoryModel)
    }

//    fun setListProductDealOfTheDay(){
//        listProductDealOfTheDay = mutableListOf()
//        listProductDealOfTheDay.add(HorizontalProductModel(R.drawable.iphone_14_pro_max_purple, "Ihone 14 pro max purple", "Apple A14", "23.350.000 đ"))
//        listProductDealOfTheDay.add(HorizontalProductModel(R.drawable.iphone_14_pro_max_purple, "Ihone 14 pro max purple", "Apple A14", "23.350.000 đ"))
//        listProductDealOfTheDay.add(HorizontalProductModel(R.drawable.iphone_14_pro_max_purple, "Ihone 14 pro max purple", "Apple A14", "23.350.000 đ"))
//        listProductDealOfTheDay.add(HorizontalProductModel(R.drawable.iphone_14_pro_max_purple, "Ihone 14 pro max purple", "Apple A14", "23.350.000 đ"))
//        listProductDealOfTheDay.add(HorizontalProductModel(R.drawable.iphone_14_pro_max_purple, "Ihone 14 pro max purple", "Apple A14", "23.350.000 đ"))
//        listProductDealOfTheDay.add(HorizontalProductModel(R.drawable.iphone_14_pro_max_purple, "Ihone 14 pro max purple", "Apple A14", "23.350.000 đ"))
//        listProductDealOfTheDay.add(HorizontalProductModel(R.drawable.iphone_14_pro_max_purple, "Ihone 14 pro max purple", "Apple A14", "23.350.000 đ"))
//        listProductDealOfTheDay.add(HorizontalProductModel(R.drawable.iphone_14_pro_max_purple, "Ihone 14 pro max purple", "Apple A14", "23.350.000 đ"))
//        listProductDealOfTheDay.add(HorizontalProductModel(R.drawable.iphone_14_pro_max_purple, "Ihone 14 pro max purple", "Apple A14", "23.350.000 đ"))
//        listProductDealOfTheDay.add(HorizontalProductModel(R.drawable.iphone_14_pro_max_purple, "Ihone 14 pro max purple", "Apple A14", "23.350.000 đ"))
//    }

}