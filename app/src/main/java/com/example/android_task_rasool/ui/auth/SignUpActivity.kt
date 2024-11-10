package com.example.android_task_rasool.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_task_rasool.R
import com.example.android_task_rasool.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        initData()
        clicksHere()
    }

    private fun clicksHere() {

        with(binding) {
            signInBtn.setOnClickListener {
                val email = emailEdt.text.toString().trim()
                val password = passwordEdt.text.toString().trim()

                if (email.isEmpty()) {
                    Toast.makeText(this@SignUpActivity, "Please enter email", Toast.LENGTH_SHORT).show()
                } else if (password.isEmpty()) {
                    Toast.makeText(this@SignUpActivity, "Please enter password", Toast.LENGTH_SHORT).show()
                } else {
                    signUpEmailPassword(email, password)
                }
            }
        }


    }

    private fun initData() {
        auth = FirebaseAuth.getInstance()
    }
    private fun signUpEmailPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Sign-Up Successful!", Toast.LENGTH_SHORT).show()
                    // Redirect to SignInActivity
                    startActivity(Intent(this, SignInActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Sign-Up Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

}