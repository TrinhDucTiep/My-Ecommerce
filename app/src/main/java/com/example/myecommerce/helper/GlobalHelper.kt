package com.example.myecommerce.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.myecommerce.R
import com.example.myecommerce.model.BannerModel
import com.example.myecommerce.model.CategoryModel

interface GlobalHelper {
    companion object{

        const val emailPattern = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}\$"

//        val categoryModelList = mutableListOf<CategoryModel>(
//            CategoryModel("link", "Trang chủ"),
//            CategoryModel("link", "Đồ điện tử"),
//            CategoryModel("link", "Đồ gia dụng"),
//            CategoryModel("link", "Nội thất"),
//            CategoryModel("link", "Thời trang"),
//            CategoryModel("link", "Đồ chơi"),
//            CategoryModel("link", "Thể thao"),
//            CategoryModel("link", "Đồ nghệ thuật"),
//            CategoryModel("link", "Sách"),
//            CategoryModel("link", "Giày dép"),
//        )

//        val listBannerModel = mutableListOf<BannerModel>(
//            BannerModel(R.drawable.banner_1),
//            BannerModel(R.drawable.banner_3),
//            BannerModel(R.drawable.banner_2),
//            BannerModel(R.drawable.banner_4),
//        )

        val numberProductInGridView = 4

        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val nw      = connectivityManager.activeNetwork ?: return false
                val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
                return when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    //for other device how are able to connect with Ethernet
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    //for check internet over Bluetooth
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                    else -> false
                }
            } else {
                return connectivityManager.activeNetworkInfo?.isConnected ?: false
            }
        }

    }
}