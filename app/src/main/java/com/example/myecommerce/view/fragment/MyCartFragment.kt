package com.example.myecommerce.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myecommerce.R
import com.example.myecommerce.adapter.CartAdapter
import com.example.myecommerce.databinding.FragmentMyCartBinding
import com.example.myecommerce.model.ItemCartProductModel
import com.example.myecommerce.view.activity.MainActivity
import com.example.myecommerce.view.fragment.cartChildFragments.AllCartFragment

class MyCartFragment : Fragment() {

    private lateinit var binding: FragmentMyCartBinding
    private lateinit var ft: FragmentTransaction
    private lateinit var allCartFragment: AllCartFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyCartBinding.inflate(layoutInflater)
        val mView = binding.root

        allCartFragment = AllCartFragment()
        ft = parentFragmentManager.beginTransaction()
        ft.replace(R.id.lnContainer, allCartFragment)
        ft.commit()

        return mView
    }

}