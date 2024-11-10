package com.example.android_task_rasool

 import android.content.Intent
 import android.os.Bundle
 import androidx.activity.enableEdgeToEdge
 import androidx.appcompat.app.AppCompatActivity
 import androidx.lifecycle.lifecycleScope
 import com.example.android_task_rasool.databinding.ActivityMainBinding
 import com.example.android_task_rasool.ui.auth.SignInActivity
 import kotlinx.coroutines.DelicateCoroutinesApi
 import kotlinx.coroutines.delay
 import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)


        lifecycleScope.launch {
            delay(3000)
            startActivity(Intent(this@MainActivity, SignInActivity::class.java))
            finish()
        }


    }
}