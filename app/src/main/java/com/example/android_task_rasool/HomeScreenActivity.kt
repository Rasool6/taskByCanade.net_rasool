package com.example.android_task_rasool

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_task_rasool.adapter.CategoryListAdapter
import com.example.android_task_rasool.adapter.DoctorListAdapter
import com.example.android_task_rasool.adapter.FrequentListAdapter
import com.example.android_task_rasool.adapter.StoryAdapter
import com.example.android_task_rasool.databinding.ActivityHomeScreenBinding
import com.example.android_task_rasool.model.CategoryModel
import com.example.android_task_rasool.model.FrequentDataModel
import com.example.android_task_rasool.model.Story
import com.example.android_task_rasool.ui.auth.CallerAuthActivity

class HomeScreenActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityHomeScreenBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val storiess = listOf(
            Story(1, R.drawable.pic_2, true),
            Story(2, R.drawable.pic_1, false),
            Story(3, R.drawable.pic_3, false),
            Story(4, R.drawable.pic_2, false),
            Story(2, R.drawable.pic_1, false),
            Story(3, R.drawable.pic_3, false),
         )

        val adapter = StoryAdapter(storiess)
        binding.recyclerStory.adapter = adapter

        val doctorList = listOf(
            Story(1, R.drawable.pic_2, true),

         )

        val doctorListAdapter = DoctorListAdapter(doctorList)
        binding.recDoctorCalender.adapter = doctorListAdapter


        val categoryList = listOf(
            CategoryModel("Meeting", R.drawable.bg_cat_yellow, R.drawable.bg_circle_yellow),
            CategoryModel("Hangout", R.drawable.bg_cat_pink, R.drawable.bg_circle_pink),
            CategoryModel("Cooking", R.drawable.bg_cat_red, R.drawable.bg_circle_red),
            CategoryModel("Other", R.drawable.bg_cat_black, R.drawable.bg_circle_black),
            CategoryModel("Weekend", R.drawable.bg_cat_green, R.drawable.bg_circle_green),

         )

        val categoryAdapter = CategoryListAdapter(categoryList)
        binding.recCategoryList.adapter = categoryAdapter

        val frequentList = listOf(
            FrequentDataModel("Caller\nID", R.drawable.ic_green_call, R.drawable.bg_cat_green_rect),
            FrequentDataModel("Credit\nChamp", R.drawable.ic_red_timer, R.drawable.bg_cat_red_rect),
            FrequentDataModel("Bank\nTransfer", R.drawable.ic_send, R.drawable.bg_cat_yellow_rect),
            FrequentDataModel("Request\nMoney", R.drawable.ic_timer, R.drawable.bg_cat_blue_rect),
            FrequentDataModel("Weekend", R.drawable.ic_chart, R.drawable.bg_circle_green_rect),

            )

        val frequentAdapter = FrequentListAdapter(frequentList, callBack = {

            startActivity(Intent(this, CallerAuthActivity::class.java))
        })
        binding.recFrequentList.adapter = frequentAdapter


        val financialList = listOf(
            FrequentDataModel("Transaction\nHistory", R.drawable.ic_green_call, R.drawable.bg_cat_green_rect),
            FrequentDataModel("Request\nMoney", R.drawable.ic_red_timer, R.drawable.bg_cat_red_rect),
            FrequentDataModel("Weekend", R.drawable.ic_chart, R.drawable.bg_circle_green_rect),
            FrequentDataModel("Credit\nChamp", R.drawable.ic_red_timer, R.drawable.bg_cat_red_rect),
            FrequentDataModel("Bank\nTransfer", R.drawable.ic_send, R.drawable.bg_cat_yellow_rect),
         )

        val financialAdapter = FrequentListAdapter(financialList,callBack = {

        })
        binding.recFinancialServicesList.adapter = financialAdapter

    }
}