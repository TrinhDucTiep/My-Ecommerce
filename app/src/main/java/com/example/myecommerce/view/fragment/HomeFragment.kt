package com.example.myecommerce.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.myecommerce.adapter.BannerAdapter
import com.example.myecommerce.adapter.CategoryAdapter
import com.example.myecommerce.adapter.GridProductAdapter
import com.example.myecommerce.adapter.HorizontalProductAdapter
import com.example.myecommerce.databinding.FragmentHomeBinding
import com.example.myecommerce.helper.GlobalHelper
import com.example.myecommerce.model.BannerModel
import com.example.myecommerce.model.CategoryModel
import com.example.myecommerce.model.HorizontalProductModel
import com.example.myecommerce.viewmodel.BannerViewModel
import com.example.myecommerce.viewmodel.CategoryViewModel
import com.example.myecommerce.viewmodel.DealOfTheDayViewModel

class HomeFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var controller: NavController

    //adapter and relates
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var gridProductAdapter: GridProductAdapter
    private lateinit var horizontalProductAdapter: HorizontalProductAdapter
    private lateinit var compositePageTransformer: CompositePageTransformer

    //data from fire store
    private var listCategoryModel = mutableListOf<CategoryModel>()
    private var listBannerModel = mutableListOf<BannerModel>()
    private var listProductDealOfTheDay = mutableListOf<HorizontalProductModel>()

    //view model
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var bannerViewModel: BannerViewModel
    private lateinit var dealOfTheDayViewModel: DealOfTheDayViewModel

    private lateinit var timer: CountDownTimer

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //init
        binding = FragmentHomeBinding.inflate(layoutInflater)
        val mView = binding.root
        controller = findNavController()

        //swipe refresh
        binding.swipeRefreshLayout.setOnRefreshListener(this)
        binding.swipeRefreshLayout.isRefreshing = true

        // set nointernet place holder
        if (!GlobalHelper.isNetworkAvailable(requireContext())) {
            binding.phNoInternet.root.visibility = View.VISIBLE
        } else {
            binding.phNoInternet.root.visibility = View.GONE
        }

        //category
        setUpCategory()

        //banner
        setUpBanner()

        //auto pager for banners
        setUpAutoPager()

        //deal of the day
        setUpDealOfTheDay()

        //grid layout
        gridProductAdapter = GridProductAdapter(listProductDealOfTheDay)
        binding.gridViewProduct.gridViewProduct.adapter = gridProductAdapter
        binding.gridViewProduct.gridViewProduct.setOnTouchListener { _, event ->
            event.action == MotionEvent.ACTION_MOVE
        }

        //add product manually
//        binding.fab.setOnClickListener {
//            val firestore = Firebase.firestore
//            val detailproduct = hashMapOf(
//                "1_star" to 0,
//                "2_star" to 1,
//                "3_star" to 4,
//                "4_star" to 12,
//                "5_star" to 205,
//                "description" to "Ghế Xoay Văn Phòng Ghế Công Thái Học Ngả Lưng 135 độ, Vải Lưới Thoáng Khí, Tựa Đầu Có Điều Chỉnh Độ Cao Chống Mỏi\n" +
//                        "\n" +
//                        "✔️THÔNG TIN SẢN PHẨM\n" +
//                        "- Tên sản phẩm: Ghế gaming, ghế chơi game Công Thái Học\n" +
//                        "Trọng lượng: 13kg\n" +
//                        "Chất liệu: Khung nhựa ABS\n" +
//                        "Vải lưới thoáng khí thân thiện với môi trường \n" +
//                        "Màu sắc: Trắng , Đen\n" +
//                        "\n" +
//                        "- Hình thức ưa nhìn, đẹp mắt và độc đáo bởi thiết kế thêm gôi tựa đầu \n" +
//                        "- Thiết kế đường nét tinh tế theo đúng tư thế ngồi, tạo cảm giác thoải mái nhất khi ngồi làm việc\n" +
//                        "- Chống ê mỏi cổ, lưng, hạn chế các bênh về xương khớp\n" +
//                        "- Dễ dàng thay đổi chiều cao ghế, thay đổi chiều cao, góc nghiêng gối tựa đầu\n" +
//                        "- Cấu tạo chắc khỏe, chịu được tải trọng lên tới 250kg.\n" +
//                        "- Phù hợp vơi những người thường xuyên ngồi liên tục hàng giờ \n" +
//                        "\n" +
//                        "✔️CÔNG DỤNG:\n" +
//                        "- Có độ bền đẹp, gia tăng không gian lưu trữ \n" +
//                        "- Giúp căn nhà trở nên gọn gàng, tiện lợi, hài hòa hơn\n" +
//                        "- Món đồ nội thất trang trí mang lại sự tinh tế cho ngôi nhà của bạn\n" +
//                        "\n" +
//                        " ✔️HƯỚNG DẪN SỬ DỤNG:\n" +
//                        "- Sản phẩm tự lắp ráp, nguyên kiện bao gồm bộ dụng cụ lắp ráp, hình ảnh & Video hướng dẫn sử dụng.\n" +
//                        "- Hạn chế tiếp xúc với ánh nắng mặt trời, nơi ẩm ướt. Sử dụng các chất tẩy rửa chuyên dụng để không làm mất độ thẩm mỹ của sản phẩm\n" +
//                        "\n" +
//                        "✔️MY HOME STORE sẽ mang cho bạn một trong những trải nghiệm về tiện ích đời sống hàng ngày. Với phương châm sản xuất những sản phẩm chất lượng tốt và luôn đặt khách hàng làm trọng tâm, sứ mệnh của MY HOME là làm hài hòng người tiêu dùng toàn cầu với những sản phẩm chất lượng cao, độ tin cậy tuyệt đối và thân thiện với người dùng. Khách hàng lựa chọn các sản phẩm của MY HOME sẽ được cam kết: \n" +
//                        "\n" +
//                        "- Sản phẩm chính hãng từ nhà máy MY HOME, nguồn gốc rõ ràng và chất lượng đạt tiêu chuẩn.\n" +
//                        "- Bảo hành theo quy định của nhà sản xuất. \n" +
//                        "- Giá cả hợp lý, cạnh tranh\n" +
//                        "- Hỗ trợ đổi trả hàng cho những sản phẩm bị thiếu.",
//                "other_desc" to "#giuong #giuonggap #giuongxep #vanphong #giadinh #giuongngu #giuongnghi #giuongdon #giuonggapgon #thongminh #giuongdem #giuongsofa #giuongdannang #giuongxepdanang",
//                "product_image" to mutableListOf(
//                    "https://firebasestorage.googleapis.com/v0/b/my-ecommerce-61bc0.appspot.com/o/products%2Fchair%2Fchair_1.PNG?alt=media&token=99c60d5b-fc73-45e2-81c8-4ddfb2fbe48d",
//                    "https://firebasestorage.googleapis.com/v0/b/my-ecommerce-61bc0.appspot.com/o/products%2Fchair%2Fchair_2.PNG?alt=media&token=26fd5f59-81ad-41d7-b4a7-b8923fb91d08",
//                    "https://firebasestorage.googleapis.com/v0/b/my-ecommerce-61bc0.appspot.com/o/products%2Fchair%2Fchair_3.PNG?alt=media&token=a4802304-e5f2-4ef2-8c9a-c2a897357a8d",
//                    "https://firebasestorage.googleapis.com/v0/b/my-ecommerce-61bc0.appspot.com/o/products%2Fchair%2Fchair_4.PNG?alt=media&token=8d8a395e-5e8c-490c-9f6d-3d92966981d4",
//                    "https://firebasestorage.googleapis.com/v0/b/my-ecommerce-61bc0.appspot.com/o/products%2Fchair%2Fchair_5.PNG?alt=media&token=e70ffb6d-d017-4c8b-90ea-49493161f394",
//                    "https://firebasestorage.googleapis.com/v0/b/my-ecommerce-61bc0.appspot.com/o/products%2Fchair%2Fchair_6.PNG?alt=media&token=e1a7713c-1b27-4aad-97d9-ce888b49e505",
//                    "https://firebasestorage.googleapis.com/v0/b/my-ecommerce-61bc0.appspot.com/o/products%2Fchair%2Fchair_7.PNG?alt=media&token=9f11283a-540f-4749-b9fc-0a84e8f45527"
//                ),
//                "product_name" to "Ghế Xoay Văn Phòng Ghế Công Thái Học Ngả Lưng 135 độ, Vải Lưới Thoáng Khí, Tựa Đầu Có Điều Chỉnh Độ Cao Chống Mỏi",
//                "product_old_price" to "₫1.899.000",
//                "product_price" to "₫1.199.000",
//                "spec_title" to mutableListOf(
//                    "Danh Mục",
//                    "Hạn bảo hành",
//                    "Loại bảo hành",
//                    "Lắp ráp",
//                    "Loại ghế",
//                    "Chiều cao lưng ghế",
//                    "Chiều cao ghế đẩu",
//                    "Tay vịn ghế",
//                    "Thiết kế ghế",
//                    "Xuất xứ",
//                    "Kho hàng",
//                    "Gửi từ"
//                ),
//                "spec_value" to mutableListOf(
//                    "Nhà Cửa & Đời Sống  -> Đồ nội thất -> Nội thất văn phòng",
//                    "12 tháng",
//                    "Bảo hành nhà cung cấp",
//                    "Lắp ráp hoàn chỉnh",
//                    "Ghế gaming",
//                    "Lưng ghế vừa",
//                    "Chiều cao quầy",
//                    "Tay điều chỉnh",
//                    "Ghế tựa lưng",
//                    "Trung Quốc",
//                    "1999922",
//                    "Hà Nội"
//                ),
//                "sum_of_sold" to 514,
//            )
//
//            firestore.collection("PRODUCTS")//.document("LA")
//                //.set(detailproduct)
//                .add(detailproduct) //this is auto id of firestore
//                .addOnSuccessListener {
//                    Toast.makeText(requireContext(), "success", Toast.LENGTH_LONG).show()
//                }
//                .addOnFailureListener {
//                    Toast.makeText(requireContext(), "fail", Toast.LENGTH_LONG).show()
//                }
//        }

        return mView
    }

    fun setUpBanner() {
        bannerViewModel = ViewModelProvider(this).get(BannerViewModel::class.java)
        listBannerModel = mutableListOf()
        bannerAdapter = BannerAdapter(listBannerModel)
        bannerViewModel.loadHomePageBanners(bannerAdapter, listBannerModel) {
            binding.bannerIndicator.setViewPager(binding.bannerViewPager)
            binding.swipeRefreshLayout.isRefreshing = false
        }
        compositePageTransformer = CompositePageTransformer()
        binding.bannerViewPager.adapter = bannerAdapter
        binding.bannerViewPager.offscreenPageLimit = 6
        binding.bannerIndicator.setViewPager(binding.bannerViewPager)
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        binding.bannerViewPager.setPageTransformer(compositePageTransformer)
    }

    fun setUpAutoPager(){
        timer = object : CountDownTimer(3000, 1000){
            override fun onTick(millisUntilFinished: Long) {

            }
            override fun onFinish() {
                var currentPage = binding.bannerViewPager.currentItem + 1
                if(currentPage == listBannerModel.size) currentPage = 0
                binding.bannerViewPager.setCurrentItem(currentPage, true)
                timer.start()
            }

        }
        timer.start()
    }

    fun setUpCategory() {
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        listCategoryModel = mutableListOf()
        categoryAdapter = CategoryAdapter(listCategoryModel, controller)
        binding.categoryRV.adapter = categoryAdapter
        binding.categoryRV.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter.notifyDataSetChanged()
        categoryViewModel.loadCategoryFromFirestore(categoryAdapter, listCategoryModel)
    }

    fun setUpDealOfTheDay() {
        dealOfTheDayViewModel = ViewModelProvider(this).get(DealOfTheDayViewModel::class.java)
        listProductDealOfTheDay = mutableListOf()
        horizontalProductAdapter = HorizontalProductAdapter(listProductDealOfTheDay, controller)
        dealOfTheDayViewModel.loadHomePageDealOfTheDay(listProductDealOfTheDay, horizontalProductAdapter)
        binding.hrDealOfTheDay.rvProduct.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.hrDealOfTheDay.rvProduct.adapter = horizontalProductAdapter
        horizontalProductAdapter.notifyDataSetChanged()
    }

    override fun onRefresh() {
        setUpCategory()
        setUpBanner()
        setUpAutoPager()
        setUpDealOfTheDay()
        //todo còn thiếu grid layout
        binding.swipeRefreshLayout.isRefreshing = true
    }

}