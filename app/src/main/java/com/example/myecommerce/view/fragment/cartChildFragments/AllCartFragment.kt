package com.example.myecommerce.view.fragment.cartChildFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myecommerce.R
import com.example.myecommerce.adapter.CartAdapter
import com.example.myecommerce.databinding.FragmentAllCartBinding
import com.example.myecommerce.helper.GlobalHelper
import com.example.myecommerce.helper.PreferenceHelper
import com.example.myecommerce.model.DetailProductModel
import com.example.myecommerce.model.ItemCartProductModel
import com.example.myecommerce.model.SumOfMonneyModelLivedata
import com.example.myecommerce.viewmodel.DetailProductViewModel

class AllCartFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentAllCartBinding
    private lateinit var controller: NavController
    private var isAll: Boolean = false

    //model
    private lateinit var listDetailProductModel: MutableList<DetailProductModel>
    private var userId: String? = null

    //adapter
    private lateinit var itemCartAdapter: CartAdapter

    //init viewmodel
    private lateinit var detailProductViewModel: DetailProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //init
        binding = FragmentAllCartBinding.inflate(layoutInflater)
        val mView = binding.root
        controller = findNavController()

        //init arguments
        listDetailProductModel = mutableListOf()
        detailProductViewModel = ViewModelProvider(this).get(DetailProductViewModel::class.java)

        //set up swipe refresh
        binding.swipeRefreshLayout.setOnRefreshListener(this)

        //setup cartAdapter
        setUpCartAdapter()

        //load data
        binding.swipeRefreshLayout.isRefreshing = true
        loadData()

        //setup bottom layout
        setUpBottomLayout()

        //navigate to add address fragment
        binding.rlMyCartLayout.tvBuy.setOnClickListener {
            controller.navigate(R.id.action_mainFragment_to_listAddressFragment)
        }

        return mView
    }

    fun setUpCartAdapter() {
        itemCartAdapter = CartAdapter(listDetailProductModel, isAll, detailProductViewModel,
            inprogressUI = {
                if (binding.layoutProgressBar.lnProgressBarLayout.visibility == View.GONE)
                    binding.layoutProgressBar.lnProgressBarLayout.visibility = View.VISIBLE
            },
            updateUI = {
                if (binding.layoutProgressBar.lnProgressBarLayout.visibility == View.VISIBLE)
                    binding.layoutProgressBar.lnProgressBarLayout.visibility = View.GONE
            }
        )
        binding.rlMyCartLayout.rvMyCart.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.rlMyCartLayout.rvMyCart.adapter = itemCartAdapter
    }

    fun setUpBottomLayout() {
        itemCartAdapter.sumOfMonneyModelLivedata.observe(viewLifecycleOwner) {
            binding.rlMyCartLayout.tvNumberSumBilling.text = GlobalHelper.getStringFromMoney(it.sumOfMoney.toLong())
        }
        setUpCheckBoxAll()
    }

    fun setUpCheckBoxAll() {
        binding.rlMyCartLayout.checkboxAll.setOnClickListener {
            if (binding.rlMyCartLayout.checkboxAll.isChecked) {
                itemCartAdapter.isAll = true
                itemCartAdapter.notifyDataSetChanged()
            } else {
                itemCartAdapter.isAll = false
            }
        }
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

    fun loadData() {
        userId = PreferenceHelper.firebaseUser?.uid
        if (userId != null) {
            detailProductViewModel.loadListProductOnCart(userId!!, listDetailProductModel, itemCartAdapter) {
                if (binding.swipeRefreshLayout.isRefreshing == true) {
                    binding.swipeRefreshLayout.isRefreshing = false
                }
            }
        }
    }

    override fun onRefresh() {
        binding.swipeRefreshLayout.isRefreshing = true
        val size = listDetailProductModel.size
        listDetailProductModel.clear()
        itemCartAdapter.notifyItemRangeRemoved(0, size)
        loadData()
    }
}