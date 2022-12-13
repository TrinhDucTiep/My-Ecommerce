package com.example.myecommerce.view.fragment.cartChildFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myecommerce.R
import com.example.myecommerce.adapter.BoughtCartAdapter
import com.example.myecommerce.databinding.FragmentBoughtCartBinding
import com.example.myecommerce.model.ItemBoughtCartProductModel

class BoughtCartFragment : Fragment() {

    private lateinit var binding: FragmentBoughtCartBinding
    private lateinit var boughtCartAdapter: BoughtCartAdapter
    private lateinit var controller: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBoughtCartBinding.inflate(layoutInflater)
        val mView = binding.root
        controller = findNavController()

        boughtCartAdapter = BoughtCartAdapter(getListCartItem(), controller)
        binding.rlMyCartLayout.rvMyCart.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.rlMyCartLayout.rvMyCart.adapter = boughtCartAdapter
        binding.rlMyCartLayout.lnBottom.visibility = View.GONE

        return mView
    }

    fun getListCartItem() : MutableList<ItemBoughtCartProductModel>{
        val listItemBoughtCartProductModel = mutableListOf<ItemBoughtCartProductModel>()
        listItemBoughtCartProductModel.add(ItemBoughtCartProductModel("Thế giới di động", R.drawable.iphone_14_pro_max_purple, "Iphone 14", "Đã vận chuyển vào 11-11-2001", 5))
        listItemBoughtCartProductModel.add(ItemBoughtCartProductModel("Mobile city", R.drawable.iphone_14_pro_max_purple, "Iphone 14", "Đã vận chuyển vào 11-11-2001", 5))
        listItemBoughtCartProductModel.add(ItemBoughtCartProductModel("Siêu thị điện máy", R.drawable.iphone_14_pro_max_purple, "Iphone 14", "Đã vận chuyển vào 11-11-2001", 5))
        listItemBoughtCartProductModel.add(ItemBoughtCartProductModel("Thế giới di động", R.drawable.iphone_14_pro_max_purple, "Iphone 14", "Đã vận chuyển vào 11-11-2001", 5))
        listItemBoughtCartProductModel.add(ItemBoughtCartProductModel("Thế giới di động", R.drawable.iphone_14_pro_max_purple, "Iphone 14", "Đã vận chuyển vào 11-11-2001", 5))
        listItemBoughtCartProductModel.add(ItemBoughtCartProductModel("Thế giới di động", R.drawable.iphone_14_pro_max_purple, "Iphone 14", "Đã vận chuyển vào 11-11-2001", 5))
        return listItemBoughtCartProductModel
    }

}