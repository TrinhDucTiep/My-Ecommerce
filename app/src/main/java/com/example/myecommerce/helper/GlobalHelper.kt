package com.example.myecommerce.helper

import com.example.myecommerce.R
import com.example.myecommerce.model.BannerModel
import com.example.myecommerce.model.CategoryModel

interface GlobalHelper {
    companion object{

        const val emailPattern = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}\$"

        val categoryModelList = mutableListOf<CategoryModel>(
            CategoryModel("link", "Home"),
            CategoryModel("link", "Electronics"),
            CategoryModel("link", "Appliances"),
            CategoryModel("link", "Furniture"),
            CategoryModel("link", "Fashion"),
            CategoryModel("link", "Toys"),
            CategoryModel("link", "Sports"),
            CategoryModel("link", "Wall Arts"),
            CategoryModel("link", "Books"),
            CategoryModel("link", "Shoes"),
        )

        val listBannerModel = mutableListOf<BannerModel>(
            BannerModel(R.drawable.banner_1),
            BannerModel(R.drawable.banner_2),
            BannerModel(R.drawable.banner_1),
            BannerModel(R.drawable.banner_2),
            BannerModel(R.drawable.banner_1)
        )

    }
}