package com.example.myecommerce.view.fragment.cartFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myecommerce.R
import com.example.myecommerce.adapter.CartAdapter
import com.example.myecommerce.databinding.FragmentAllCartBinding
import com.example.myecommerce.model.ItemCartProductModel

class AllCartFragment : Fragment() {

    private lateinit var binding: FragmentAllCartBinding
    private lateinit var itemCartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAllCartBinding.inflate(layoutInflater)
        val mView = binding.root

        itemCartAdapter = CartAdapter(getListCartItem())
        binding.rlMyCartLayout.rvMyCart.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.rlMyCartLayout.rvMyCart.adapter = itemCartAdapter

        return mView
    }

    fun getListCartItem() : MutableList<ItemCartProductModel>{
        val listItemCartProductModel = mutableListOf<ItemCartProductModel>()
        listItemCartProductModel.add(ItemCartProductModel("Thế giới di động", R.drawable.iphone_14_pro_max_purple, "Iphone 14", "Màu tìm huyền ảo", "2500$", "2180$"))
        listItemCartProductModel.add(ItemCartProductModel("Mobile city", R.drawable.iphone_14_pro_max_purple, "Iphone 14", "Màu tìm huyền ảo", "2500$", "2180$"))
        listItemCartProductModel.add(ItemCartProductModel("Siêu thị điện máy", R.drawable.iphone_14_pro_max_purple, "Iphone 14", "Màu tìm huyền ảo", "2500$", "2180$"))
        listItemCartProductModel.add(ItemCartProductModel("Thế giới di động", R.drawable.iphone_14_pro_max_purple, "Iphone 14", "Màu tìm huyền ảo", "2500$", "2180$"))
        listItemCartProductModel.add(ItemCartProductModel("Thế giới di động", R.drawable.iphone_14_pro_max_purple, "Iphone 14", "Màu tìm huyền ảo", "2500$", "2180$"))
        listItemCartProductModel.add(ItemCartProductModel("Thế giới di động", R.drawable.iphone_14_pro_max_purple, "Iphone 14", "Màu tìm huyền ảo", "2500$", "2180$"))
        return listItemCartProductModel
    }
}