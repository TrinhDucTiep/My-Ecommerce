package com.example.myecommerce.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myecommerce.model.DetailProductModel
import com.example.myecommerce.view.fragment.productDesc.ProductDescFragment
import com.example.myecommerce.view.fragment.productDesc.ProductOtherFragment
import com.example.myecommerce.view.fragment.productDesc.ProductSpecFragment

class ProductDescAdapter(fragment: Fragment, var detailProductModel: DetailProductModel) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return ProductDescFragment(detailProductModel = detailProductModel)
            1 -> return ProductSpecFragment(detailProductModel = detailProductModel)
            2 -> return  ProductOtherFragment(detailProductModel = detailProductModel)
        }
        return ProductDescFragment(detailProductModel = detailProductModel)
    }
}