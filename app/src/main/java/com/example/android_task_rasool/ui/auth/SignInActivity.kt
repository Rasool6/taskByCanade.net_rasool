package com.example.android_task_rasool.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_task_rasool.HomeScreenActivity
import com.example.android_task_rasool.R
import com.example.android_task_rasool.databinding.ActivitySignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.OAuthProvider

class SignInActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySignInBinding.inflate(layoutInflater)
    }

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private val googleSignInLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    firebaseAuthWithGoogle(account)
                } catch (e: ApiException) {
                    Log.e("GoogleSignIn", "Google sign-in failed", e)
                    Toast.makeText(this, "Google sign-in failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Google sign-in canceled", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Adjust insets for edge-to-edge experience
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initData()
        clickListeners()
    }

    private fun initData() {
        auth = FirebaseAuth.getInstance()

        // Configure Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun clickListeners() {
        with(binding) {
            simpleSignUpBtn.setOnClickListener {
                startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
            }
            signInBtn.setOnClickListener {
                val email = emailEdt.text.toString().trim()
                val password = passwordEdt.text.toString().trim()

                if (email.isEmpty()) {
                    Toast.makeText(this@SignInActivity, "Please enter email", Toast.LENGTH_SHORT).show()
                } else if (password.isEmpty()) {
                    Toast.makeText(this@SignInActivity, "Please enter password", Toast.LENGTH_SHORT).show()
                } else {
                    signInEmailPassword(email, password)
                }
            }

            gitHubBtn.setOnClickListener {
                val email = emailEdt.text.toString().trim()
                if (email.isEmpty()) {
                    Toast.makeText(this@SignInActivity, "Please enter email", Toast.LENGTH_SHORT).show()
                } else {
                    signInWithGitHub(email)
                }
            }

            googleBtn.setOnClickListener {
                signInWithGoogle()
            }

            passKeyBtn.setOnClickListener {
                startActivity(Intent(this@SignInActivity, PasskeySignInActivity::class.java))
            }
        }
    }

    private fun signInEmailPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    handleAuthSuccess(user?.displayName ?: "User")
                } else {
                    handleAuthError(task.exception ?: Exception("Email sign-in failed"))
                }
            }
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    handleAuthSuccess(user?.displayName)
                } else {
                    handleAuthError(task.exception ?: Exception("Google sign-in failed"))
                }
            }
    }

    private fun signInWithGitHub(email: String) {
        val provider = OAuthProvider.newBuilder("github.com")
        provider.scopes = listOf("user:email")
        provider.addCustomParameter("login", email)

        val pendingResultTask = auth.pendingAuthResult
        if (pendingResultTask != null) {
            pendingResultTask
                .addOnSuccessListener { authResult ->
                    handleAuthSuccess(authResult.user?.displayName)
                }
                .addOnFailureListener { e ->
                    handleAuthError(e)
                }
        } else {
            auth.startActivityForSignInWithProvider(this, provider.build())
                .addOnSuccessListener { authResult ->
                    handleAuthSuccess(authResult.user?.displayName)
                }
                .addOnFailureListener { e ->
                    handleAuthError(e)
                }
        }
    }

    private fun handleAuthSuccess(displayName: String?) {
        Log.d("Auth", "Success: $displayName")
        Toast.makeText(this@SignInActivity, "Success: $displayName", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this@SignInActivity, HomeScreenActivity::class.java))
        finish()
    }

    private fun handleAuthError(exception: Exception) {
        Log.e("Auth", "Error: ${exception.message}", exception)
        Toast.makeText(this@SignInActivity, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
    }

}
