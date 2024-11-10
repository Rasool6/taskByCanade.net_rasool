package com.example.android_task_rasool.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.android_task_rasool.R
import com.example.android_task_rasool.databinding.ActivityCallerAppIdactivityBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CallerAppIDActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCallerAppIdactivityBinding.inflate(layoutInflater)
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


        // for testing loading shammer loader
        binding.shimmerFrameLayout.startShimmer()

        lifecycleScope.launch {
            delay(3000)
            binding.shimmerFrameLayout.startShimmer()
            binding.shimmerFrameLayout.visibility = View.GONE
            binding.scrollView3.visibility= View.VISIBLE
        }
    }
}