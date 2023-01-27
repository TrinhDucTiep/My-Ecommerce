package com.example.myecommerce.helper

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

    }
}