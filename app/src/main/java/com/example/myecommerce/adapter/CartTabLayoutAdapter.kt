package com.example.myecommerce.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myecommerce.view.fragment.cartFragments.AllCartFragment
import com.example.myecommerce.view.fragment.cartFragments.BoughtCartFragment

class CartTabLayoutAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return AllCartFragment()
            1 -> return BoughtCartFragment()
        }
        return AllCartFragment()
    }
}