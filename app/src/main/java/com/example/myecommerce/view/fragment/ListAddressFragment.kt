package com.example.myecommerce.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myecommerce.R
import com.example.myecommerce.adapter.AddressAdapter
import com.example.myecommerce.databinding.FragmentListAddressBinding
import com.example.myecommerce.model.AddressModel
import com.example.myecommerce.model.ListAddressModel
import com.example.myecommerce.viewmodel.UserInfoViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ListAddressFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding : FragmentListAddressBinding
    private lateinit var controller: NavController
    private lateinit var addressAdapter: AddressAdapter

    private lateinit var userInfoViewModel: UserInfoViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //init
        binding = FragmentListAddressBinding.inflate(layoutInflater)
        controller = findNavController()
        val mView = binding.root

        //swipe refresh
        binding.swipeRefreshLayout.setOnRefreshListener(this)

        //init viewModel
        userInfoViewModel = ViewModelProvider(this).get(UserInfoViewModel::class.java)

        //setup address adapter
        addressAdapter = AddressAdapter(mutableListOf(), controller)
        binding.rvAddress.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.rvAddress.adapter = addressAdapter

        //icon action bar
        setupToolBar()

        //load data
        binding.swipeRefreshLayout.isRefreshing = true
        loadData()

        return mView
    }

    fun loadData() {
        userInfoViewModel.loadAddress(
            updateUI = {
                if (binding.swipeRefreshLayout.isRefreshing)
                    binding.swipeRefreshLayout.isRefreshing = false
                addressAdapter.listAddressModel = Gson().fromJson(it, object : TypeToken<MutableList<AddressModel>>() {}.type)
                addressAdapter.notifyDataSetChanged()
            },
            updateFail = {
                if (binding.swipeRefreshLayout.isRefreshing)
                    binding.swipeRefreshLayout.isRefreshing = false
                Toast.makeText(requireContext(), getString(R.string.error_happened), Toast.LENGTH_SHORT).show()
            }
        )
    }

    fun setupToolBar() {
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
    }

    override fun onRefresh() {
        binding.swipeRefreshLayout.isRefreshing = true
        loadData()
    }

}