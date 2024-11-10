package com.example.android_task_rasool.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_task_rasool.HomeScreenActivity
import com.example.android_task_rasool.R
import com.example.android_task_rasool.databinding.ActivityPasskeySignInBinding

class PasskeySignInActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPasskeySignInBinding.inflate(layoutInflater)
    }
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


// Set up BiometricPrompt and PromptInfo
        setupBiometricPrompt()
        clickHere()
    }

    private fun clickHere() {
        with(binding) {
            fingerPrintBtn2.setOnClickListener {

            }
            fingerPrintBtn.setOnClickListener {

                authenticateUser()
            }
        }
    }

    private fun setupBiometricPrompt() {
        val executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Log.d("rasoolTag", "Authentication error: $errString")
                    Toast.makeText(
                        this@PasskeySignInActivity,
                        "Authentication error: $errString",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Log.d(
                        "rasoolTag",
                        "Authentication succeeded! Welcome back.${result.authenticationType}"
                    )
                    Toast.makeText(
                        this@PasskeySignInActivity,
                        "Authentication succeeded! Welcome back.",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.appCompatImageView2.visibility = View.VISIBLE
                    binding.appCompatImageView.visibility = View.GONE
                    startActivity(Intent(this@PasskeySignInActivity, HomeScreenActivity::class.java))
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Log.d("rasoolTag", "Authentication failed. Try again.")
                    Toast.makeText(
                        this@PasskeySignInActivity,
                        "Authentication failed. Try again.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Fingerprint Login")
            .setSubtitle("Authenticate with your fingerprint")
            .setNegativeButtonText("Cancel")
            .build()
    }

    private fun authenticateUser() {
        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                biometricPrompt.authenticate(promptInfo)
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                Toast.makeText(
                    this@PasskeySignInActivity,
                    "No fingerprint hardware found",
                    Toast.LENGTH_SHORT
                ).show()
            // binding.statusTextView.text = "No fingerprint hardware found"

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                Toast.makeText(
                    this@PasskeySignInActivity,
                    "Fingerprint hardware unavailable",
                    Toast.LENGTH_SHORT
                ).show()
            //  binding.statusTextView.text = "Fingerprint hardware unavailable"
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                Toast.makeText(
                    this@PasskeySignInActivity,
                    "No fingerprint enrolled. Please set up fingerprint in your settings.",
                    Toast.LENGTH_SHORT
                ).show()
//                binding.statusTextView.text = "No fingerprint enrolled. Please set up fingerprint in your settings."
            else -> {
                Toast.makeText(
                    this@PasskeySignInActivity,
                    "Biometric authentication is not available.",
                    Toast.LENGTH_SHORT
                ).show()
//                binding.statusTextView.text = "Biometric authentication is not available."
            }
        }
    }

}