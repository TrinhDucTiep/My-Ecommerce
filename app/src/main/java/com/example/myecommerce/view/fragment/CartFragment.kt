package com.example.myecommerce.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myecommerce.R
import com.example.myecommerce.adapter.CartTabLayoutAdapter
import com.example.myecommerce.databinding.FragmentCartBinding
import com.google.android.material.tabs.TabLayoutMediator

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var cartTabLayoutAdapter: CartTabLayoutAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(layoutInflater)
        val mView = binding.root
        cartTabLayoutAdapter = CartTabLayoutAdapter(this)

        //tablayout
        binding.viewPagerCartFragment.adapter = cartTabLayoutAdapter
        TabLayoutMediator(binding.tabLayoutCartFragment, binding.viewPagerCartFragment){
            tab, position ->
            run {
                when (position) {
                    0 -> tab.text = getString(R.string.all)
                    1 -> tab.text = getString(R.string.bought)
                    else -> {
                        tab.text = getString(R.string.all)
                    }
                }
            }
        }.attach()

        return mView
    }

}