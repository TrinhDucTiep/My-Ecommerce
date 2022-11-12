package com.example.myecommerce.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myecommerce.view.fragment.*

class MainAdapter (fragment: Fragment) : FragmentStateAdapter(fragment){


    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return HomeFragment()
            1 -> return CartFragment()
            2 -> return ChatFragment()
            3 -> return NotificationFragment()
            4 -> return AccountFragment()
        }
        return HomeFragment()
    }

}