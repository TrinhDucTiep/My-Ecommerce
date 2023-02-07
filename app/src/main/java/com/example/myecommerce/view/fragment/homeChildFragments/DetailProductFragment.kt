package com.example.myecommerce.view.fragment.homeChildFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myecommerce.R
import com.example.myecommerce.adapter.ImageProductAdapter
import com.example.myecommerce.adapter.ProductDescAdapter
import com.example.myecommerce.databinding.FragmentDetailProductBinding
import com.example.myecommerce.helper.AnimationHelper
import com.example.myecommerce.model.DetailProductModel
import com.example.myecommerce.viewmodel.DetailProductViewModel
import com.google.android.material.tabs.TabLayoutMediator

class DetailProductFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentDetailProductBinding
    private lateinit var controller: NavController
    private var productId = ""

    //adapter
    private lateinit var productDescAdapter: ProductDescAdapter
    private lateinit var imageProductAdapter: ImageProductAdapter

    //viewmodel
    private lateinit var detailProductViewModel: DetailProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //init
        binding = FragmentDetailProductBinding.inflate(layoutInflater)
        val mView = binding.root
        controller = findNavController()
        productId = arguments?.getString("product_id").toString()

        //back
        binding.toolBar.setNavigationOnClickListener {
            controller.popBackStack()
        }

        //animation add to cart
        setUpAnimationAddToCart()

        //swipe refresh
        initSwipeRefresh()

        //init viewmodel
        detailProductViewModel = ViewModelProvider(this).get(DetailProductViewModel::class.java)
        detailProductViewModel.loadDetailProduct(productId)


        //favorite button
        binding.imgFavorite.setOnClickListener {

        }

        //toobar
        setUpToolBar()

        //init productDesc UI
        setUpProductDescUI()

        //set listener for rating system
        setUpRating()

        //fetch data
        detailProductViewModel.detailProductModelLiveData.observe(viewLifecycleOwner) {
            fetchDataToUI(it)

            productDescAdapter = ProductDescAdapter(this, it)
            binding.layoutProductDesc.viewPagerProductDesc.adapter = productDescAdapter

            imageProductAdapter = ImageProductAdapter(it.productImage!!)
            binding.layoutProductImage.viewPagerProductImg.adapter = imageProductAdapter
            binding.layoutProductImage.indicatorProductImg.setViewPager(binding.layoutProductImage.viewPagerProductImg)
            binding.swipeRefreshLayout.isRefreshing = false
        }

        return mView
    }

    fun initSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener(this)
        binding.swipeRefreshLayout.isRefreshing = true
    }

    fun setUpToolBar() {
        binding.toolBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.cartIcon -> {
                    controller.navigate(R.id.action_detailProductFragment_to_myCartFragment)
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener false
            }
        }
    }

    fun setUpProductDescUI() {
        productDescAdapter = ProductDescAdapter(this, DetailProductModel())
        binding.layoutProductDesc.viewPagerProductDesc.adapter = productDescAdapter
        //tab layout for description
        TabLayoutMediator(binding.layoutProductDesc.tabLayoutProductDesc, binding.layoutProductDesc.viewPagerProductDesc){
                tab, position ->
            run {
                when (position) {
                    0 -> tab.text = getString(R.string.description)
                    1 -> tab.text = getString(R.string.specification)
                    2 -> tab.text = getString(R.string.other)
                    else -> {
                        tab.text = getString(R.string.description)
                    }
                }
            }
        }.attach()
    }

    fun setUpAnimationAddToCart() {
        binding.imgAddToCart.setOnClickListener {
            AnimationHelper.translateAnimation(binding.imgCartAnimation, binding.imgAddToCart, binding.toolBar.findViewById(R.id.cartIcon), object : AnimationListener {
                override fun onAnimationStart(animation: Animation?) {

                }

                override fun onAnimationEnd(animation: Animation?) {

                }

                override fun onAnimationRepeat(animation: Animation?) {

                }

            })
        }
    }

    fun setUpRating() {
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
    }

    fun fetchDataToUI(detailProductModel: DetailProductModel) {
        binding.tvProductName.text = detailProductModel.productName
        binding.tvPrice.text = detailProductModel.productPrice
        binding.tvOldPrice.text = detailProductModel.productOldPrice
        binding.tvNumberSold.text = String.format(getString(R.string.sold), detailProductModel.sumOfSold)
        binding.layoutRatings.tvNumberRating1.text = detailProductModel.star1.toString()
        binding.layoutRatings.tvNumberRating2.text = detailProductModel.star2.toString()
        binding.layoutRatings.tvNumberRating3.text = detailProductModel.star3.toString()
        binding.layoutRatings.tvNumberRating4.text = detailProductModel.star4.toString()
        binding.layoutRatings.tvNumberRating5.text = detailProductModel.star5.toString()
        val totalRating = (detailProductModel.star1!!
                + detailProductModel.star2!!
                + detailProductModel.star3!!
                + detailProductModel.star4!!
                + detailProductModel.star5!!)
        binding.layoutRatings.tvTotalRating.text = totalRating.toString()
        val tempRating = (detailProductModel.star1!!
                + detailProductModel.star2!!*2
                + detailProductModel.star3!!*3
                + detailProductModel.star4!!*4
                + detailProductModel.star5!!*5)
        if (totalRating != 0L) {
            binding.layoutRatings.tvAverageRating.text = "%.1f".format (tempRating*1.0 / totalRating)
            binding.tvRatingAvgCircle.text = "%.1f".format (tempRating*1.0 / totalRating)
        }
        binding.layoutRatings.progress1.progress = (detailProductModel.star1!!*100.0/totalRating).toInt()
        binding.layoutRatings.progress2.progress = (detailProductModel.star2!!*100.0/totalRating).toInt()
        binding.layoutRatings.progress3.progress = (detailProductModel.star3!!*100.0/totalRating).toInt()
        binding.layoutRatings.progress4.progress = (detailProductModel.star4!!*100.0/totalRating).toInt()
        binding.layoutRatings.progress5.progress = (detailProductModel.star5!!*100.0/totalRating).toInt()
    }

    override fun onRefresh() {
        detailProductViewModel.loadDetailProduct(productId)
        binding.swipeRefreshLayout.isRefreshing = true
    }

}