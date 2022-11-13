package com.example.myecommerce.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.node.getOrAddAdapter
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.myecommerce.R
import com.example.myecommerce.adapter.MainAdapter
import com.example.myecommerce.databinding.FragmentMainBinding
import com.example.myecommerce.view.activity.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment() {

    private lateinit var controller : NavController
    private lateinit var binding : FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        val mView = binding.root
        controller = findNavController()

        initUi()
        binding.viewPager.isUserInputEnabled = false

        return mView
    }

    private fun initUi(){
        val mainAdapter = MainAdapter(this)
        binding.viewPager.adapter = mainAdapter
        binding.bottomNav.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
            item ->  run {
                when (item.itemId) {
                    R.id.homeFragment -> {
                        binding.viewPager.currentItem = 0
                        return@OnItemSelectedListener true
                    }
                    R.id.cartFragment -> {
                        binding.viewPager.currentItem = 1
                        return@OnItemSelectedListener true
                    }
                    R.id.chatFragment -> {
                        binding.viewPager.currentItem = 2
                        return@OnItemSelectedListener true
                    }
                    R.id.notificationFragment -> {
                        binding.viewPager.currentItem = 3
                        return@OnItemSelectedListener true
                    }
                    R.id.accountFragment -> {
                        binding.viewPager.currentItem = 4
                        return@OnItemSelectedListener true
                    }
                }
                return@OnItemSelectedListener false
            }
        })

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position){
                    0 -> binding.bottomNav.menu.findItem(R.id.homeFragment).setChecked(true)
                    1 -> binding.bottomNav.menu.findItem(R.id.cartFragment).setChecked(true)
                    2 -> binding.bottomNav.menu.findItem(R.id.chatFragment).setChecked(true)
                    3 -> binding.bottomNav.menu.findItem(R.id.notificationFragment).setChecked(true)
                    4 -> binding.bottomNav.menu.findItem(R.id.accountFragment).setChecked(true)
                }
            }
        })
    }

}