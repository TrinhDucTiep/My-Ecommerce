package com.example.myecommerce.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myecommerce.R
import com.example.myecommerce.adapter.AddressAdapter
import com.example.myecommerce.databinding.FragmentListAddressBinding
import com.example.myecommerce.model.AddressModel


class ListAddressFragment : Fragment() {

    lateinit var binding : FragmentListAddressBinding
    lateinit var controller: NavController
    lateinit var addressAdapter: AddressAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListAddressBinding.inflate(layoutInflater)
        controller = findNavController()
        addressAdapter = AddressAdapter(getMyListAddress(), controller)
        val mView = binding.root

        //setup adapter
        binding.rvAddress.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.rvAddress.adapter = addressAdapter

        //icon action bar
        binding.toolBar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.add -> {
                    controller.navigate(R.id.action_listAddressFragment_to_addAddressFragment)
                    return@setOnMenuItemClickListener true
                }
                else -> {
                    return@setOnMenuItemClickListener false
                }
            }
        }

        return mView
    }

    fun getMyListAddress() : MutableList<AddressModel>{
        return mutableListOf<AddressModel>(
            AddressModel("xóm 11, Xuân Kiên, Xuân Trường, Nam Định", "Địa chỉ quê"),
            AddressModel("số 5 ngõ 237, Trần Đại Nghĩa, Hai Bà Trưng, Hà Nội", "Địa chỉ trọ")
        )
    }

}