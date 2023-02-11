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
import com.example.myecommerce.R
import com.example.myecommerce.databinding.FragmentAddAddressBinding
import com.example.myecommerce.model.AddressModel
import com.example.myecommerce.viewmodel.UserInfoViewModel
import com.google.gson.Gson

class AddAddressFragment : Fragment() {

    private lateinit var binding: FragmentAddAddressBinding
    private lateinit var controller: NavController
    private lateinit var addressModel: AddressModel

    private lateinit var userInfoViewModel: UserInfoViewModel

    private var str: String? = null
    private var index: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //init
        binding = FragmentAddAddressBinding.inflate(layoutInflater)
        val mView = binding.root
        controller = findNavController()
        userInfoViewModel = ViewModelProvider(this).get(UserInfoViewModel::class.java)

        //setup toolbar
        setUpToolBar()

        //init UI if from specific address
        setUpUIInit()

        //setup save data
        setUpSaveData()

        return mView
    }

    fun setUpToolBar() {
        binding.toolBar.setNavigationOnClickListener {
            controller.popBackStack()
        }
    }

    fun setUpUIInit() {
        str = arguments?.getString("address")
        index = arguments?.getInt("index")
        if (str != null) {
            addressModel = Gson().fromJson(str, AddressModel::class.java)
            binding.edtTitle.setText(addressModel.title)
            binding.edtContentAddress.setText(addressModel.content)
        }
    }

    fun setUpSaveData() {
        binding.btnSave.setOnClickListener {
            //check input
            if (binding.edtTitle.text.toString().isEmpty()) {
                binding.edtTitle.error = "Bạn chưa nhập tiêu đề"
            }
            if (binding.edtContentAddress.text.toString().isEmpty()) {
                binding.edtContentAddress.error = "Bạn chưa nhập địa chỉ"
            }

            //update
            if (str != null && index != null) {
                //update data
                userInfoViewModel.updateAddress(
                    index!!,
                    binding.edtTitle.text.toString(),
                    binding.edtContentAddress.text.toString(),
                    updateUI = {
                        controller.popBackStack()
                        Toast.makeText(requireContext(), "Cập nhật địa chỉ thành công", Toast.LENGTH_SHORT).show()
                    },
                    updateFail = {
                        Toast.makeText(requireContext(), "Cập nhật địa chỉ thất bại", Toast.LENGTH_SHORT).show()
                    }
                )
            } else {
                //add data
                userInfoViewModel.addAddress(
                    binding.edtTitle.text.toString(),
                    binding.edtContentAddress.text.toString(),
                    updateUI = {
                        controller.popBackStack()
                        Toast.makeText(requireContext(), "Thêm địa chỉ thành công", Toast.LENGTH_SHORT).show()
                    },
                    updateFail = {
                        Toast.makeText(requireContext(), "Thêm địa chỉ thất bại", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }

}