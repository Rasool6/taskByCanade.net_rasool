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

        // Set up the window insets listener
        setupWindowInsets()

        // Set up the adapters and bind data
        setupStoryRecyclerView()
        setupDoctorRecyclerView()
        setupCategoryRecyclerView()
        setupFrequentRecyclerView()
        setupFinancialRecyclerView()
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupStoryRecyclerView() {
        val stories = listOf(
            Story("My Story", R.drawable.pic_2, true),
            Story("Salena", R.drawable.pic_1, false),
            Story("Baber", R.drawable.pic_3, false),
            Story("Tayler", R.drawable.pic_2, false),
            Story("Dol", R.drawable.pic_1, false),
            Story("Micky", R.drawable.pic_3, false),
        )
        val adapter = StoryAdapter(stories)
        binding.recyclerStory.adapter = adapter
    }

    private fun setupDoctorRecyclerView() {
        val doctorList = listOf(
            //dummy for this list to ad item just
            Story("My Story", R.drawable.pic_2, true),
            Story("My Story", R.drawable.pic_2, true)
        )
        val doctorListAdapter = DoctorListAdapter(doctorList)
        binding.recDoctorCalender.adapter = doctorListAdapter
    }

    private fun setupCategoryRecyclerView() {
        val categoryList = listOf(
            CategoryModel("Meeting", R.drawable.bg_cat_yellow, R.drawable.bg_circle_yellow),
            CategoryModel("Hangout", R.drawable.bg_cat_pink, R.drawable.bg_circle_pink),
            CategoryModel("Cooking", R.drawable.bg_cat_red, R.drawable.bg_circle_red),
            CategoryModel("Other", R.drawable.bg_cat_black, R.drawable.bg_circle_black),
            CategoryModel("Weekend", R.drawable.bg_cat_green, R.drawable.bg_circle_green),
        )
        val categoryAdapter = CategoryListAdapter(categoryList)
        binding.recCategoryList.adapter = categoryAdapter
    }

    private fun setupFrequentRecyclerView() {
        val frequentList = listOf(
            FrequentDataModel("Caller\nID", R.drawable.ic_green_call, R.drawable.bg_cat_green_rect),
            FrequentDataModel("Credit\nChamp", R.drawable.ic_red_timer, R.drawable.bg_cat_red_rect),
            FrequentDataModel("Bank\nTransfer", R.drawable.ic_send, R.drawable.bg_cat_yellow_rect),
            FrequentDataModel("Request\nMoney", R.drawable.ic_timer, R.drawable.bg_cat_blue_rect),
            FrequentDataModel("Weekend", R.drawable.ic_chart, R.drawable.bg_circle_green_rect),
        )

        val frequentAdapter = FrequentListAdapter(frequentList) {
            navigateToCallerAuthActivity()
        }
        binding.recFrequentList.adapter = frequentAdapter
    }

    private fun setupFinancialRecyclerView() {
        val financialList = listOf(
            FrequentDataModel("Transaction\nHistory", R.drawable.ic_green_call, R.drawable.bg_cat_green_rect),
            FrequentDataModel("Request\nMoney", R.drawable.ic_red_timer, R.drawable.bg_cat_red_rect),
            FrequentDataModel("Weekend", R.drawable.ic_chart, R.drawable.bg_circle_green_rect),
            FrequentDataModel("Credit\nChamp", R.drawable.ic_red_timer, R.drawable.bg_cat_red_rect),
            FrequentDataModel("Bank\nTransfer", R.drawable.ic_send, R.drawable.bg_cat_yellow_rect),
        )

        val financialAdapter = FrequentListAdapter(financialList) {
            // Handle financial item click if needed
        }
        binding.recFinancialServicesList.adapter = financialAdapter
    }

    private fun navigateToCallerAuthActivity() {
        val intent = Intent(this@HomeScreenActivity, CallerAuthActivity::class.java)
        // Use ActivityOptions to add a fade-in and fade-out animation
        val options = android.app.ActivityOptions.makeCustomAnimation(
            this@HomeScreenActivity, android.R.anim.fade_in, android.R.anim.fade_out
        )
        // Start the new activity with the transition
        startActivity(intent, options.toBundle())
    }
}

