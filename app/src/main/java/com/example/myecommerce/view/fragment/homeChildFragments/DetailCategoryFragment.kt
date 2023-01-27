package com.example.myecommerce.view.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.myecommerce.R
import com.example.myecommerce.adapter.BannerAdapter
import com.example.myecommerce.adapter.GridProductAdapter
import com.example.myecommerce.adapter.HorizontalProductAdapter
import com.example.myecommerce.databinding.FragmentDetailCategoryBinding
import com.example.myecommerce.helper.GlobalHelper
import com.example.myecommerce.model.BannerModel
import com.example.myecommerce.model.HorizontalProductModel
import com.example.myecommerce.viewmodel.BannerViewModel

class DetailCategoryFragment : Fragment(){

    private lateinit var binding: FragmentDetailCategoryBinding
    private lateinit var controller: NavController
    private lateinit var title: String
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var gridProductAdapter: GridProductAdapter
    private lateinit var horizontalProductAdapter: HorizontalProductAdapter
    private lateinit var compositePageTransformer: CompositePageTransformer

    //banner
    private var listBannerModel = mutableListOf<BannerModel>()

    //view model
    private lateinit var bannerViewModel: BannerViewModel


    private lateinit var listProductDealOfTheDay: MutableList<HorizontalProductModel>

    private lateinit var timer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailCategoryBinding.inflate(layoutInflater)
        val mView = binding.root
        controller = findNavController()

        title = arguments?.getString("content").toString()
        binding.toolBar.title = title
        binding.toolBar.setNavigationOnClickListener {
            controller.popBackStack()
        }

        binding.toolBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.searchIcon -> {
                    //todo icon in menu here

                    return@setOnMenuItemClickListener true
                }
                else -> {
                    return@setOnMenuItemClickListener false
                }
            }
        }

        //banner
        listBannerModel = mutableListOf()
        bannerViewModel = ViewModelProvider(this).get(BannerViewModel::class.java)
        bannerAdapter = BannerAdapter(listBannerModel)
        compositePageTransformer = CompositePageTransformer()
        binding.bannerViewPager.adapter = bannerAdapter
        binding.bannerViewPager.offscreenPageLimit = 4
        binding.bannerIndicator.setViewPager(binding.bannerViewPager)
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        binding.bannerViewPager.setPageTransformer(compositePageTransformer)
        bannerViewModel.loadHomePageBanners(bannerAdapter, listBannerModel) {
            binding.bannerIndicator.setViewPager(binding.bannerViewPager)
        }

        setUpAutoPager()

        //deal of the day
//        setListProductDealOfTheDay()
//        horizontalProductAdapter = HorizontalProductAdapter(listProductDealOfTheDay, controller)
//        binding.hrDealOfTheDay.rvProduct.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
//        binding.hrDealOfTheDay.rvProduct.adapter = horizontalProductAdapter
//        horizontalProductAdapter.notifyDataSetChanged()

        //grid layout
//        gridProductAdapter = GridProductAdapter(listProductDealOfTheDay)
//        binding.gridViewProduct.gridViewProduct.adapter = gridProductAdapter

        return mView
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