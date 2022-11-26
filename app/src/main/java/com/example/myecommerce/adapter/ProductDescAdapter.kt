package com.example.myecommerce.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myecommerce.view.fragment.productDesc.ProductDescFragment
import com.example.myecommerce.view.fragment.productDesc.ProductOtherFragment
import com.example.myecommerce.view.fragment.productDesc.ProductSpecFragment

class ProductDescAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return ProductDescFragment()
            1 -> return ProductSpecFragment()
            2 -> return  ProductOtherFragment()
        }
        return ProductDescFragment()
    }
}