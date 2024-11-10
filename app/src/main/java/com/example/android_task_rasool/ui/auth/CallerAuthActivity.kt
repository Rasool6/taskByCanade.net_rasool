package com.example.android_task_rasool.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.android_task_rasool.R
import com.example.android_task_rasool.databinding.ActivityCallerAuthBinding
import com.example.android_task_rasool.ui.CallerAppIDActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CallerAuthActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCallerAuthBinding.inflate(layoutInflater)
    }
    val pinBoxes = arrayListOf<EditText>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // currently applying loader for testing ...we will do accordingly
        lifecycleScope.launch {
            delay(3000)

        }

        initWorkHere()
        pinBoxWorkHere()
        clicksHere()
    }

    private fun initWorkHere() {
        pinBoxes.add(binding.pinBox1)
        pinBoxes.add( binding.pinBox2)
        pinBoxes.add( binding.pinBox3)
        pinBoxes.add( binding.pinBox4)
    }

    private fun clicksHere() {


        binding.enterPinButton.setOnClickListener {
            if (pinBoxes.isNotEmpty()) {
                navigateToNextScreen()
            }else{
                Toast.makeText(this@CallerAuthActivity, "Empty field", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun pinBoxWorkHere() {
        var currentIndex = 0

        // Function to handle adding text to the next box
        fun addDigit(digit: String) {
            if (currentIndex < pinBoxes.size) {
                // Set the digit in the current EditText
                pinBoxes[currentIndex].setText(digit)

                // Move to the next box if there is one
                currentIndex++
                if (currentIndex < pinBoxes.size) {
                    pinBoxes[currentIndex].requestFocus()
                }
            }
        }


        // Function to handle deleting the last digit
        fun deleteDigit() {
            if (currentIndex > 0) {
                currentIndex--
                pinBoxes[currentIndex].setText("")
            }
        }

        // Set click listeners for keypad buttons
        binding.button1.setOnClickListener { addDigit("1") }
        binding.button2.setOnClickListener { addDigit("2") }
        binding.button3.setOnClickListener { addDigit("3") }
        binding.button4.setOnClickListener { addDigit("4") }
        binding.button5.setOnClickListener { addDigit("5") }
        binding.button6.setOnClickListener { addDigit("6") }
        binding.button7.setOnClickListener { addDigit("7") }
        binding.button8.setOnClickListener { addDigit("8") }
        binding.button9.setOnClickListener { addDigit("9") }
        binding.button0.setOnClickListener { addDigit("0") }
       // binding.buttonDelete.setOnClickListener { deleteDigit() }
    }

    private fun navigateToNextScreen() {
        // Code to navigate to the next screen
         startActivity(Intent(this, CallerAppIDActivity::class.java))
    }

}