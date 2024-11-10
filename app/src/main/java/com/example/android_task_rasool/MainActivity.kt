package com.example.android_task_rasool

 import android.animation.ObjectAnimator
 import android.content.Intent
 import android.os.Bundle
 import android.text.Html
 import android.view.animation.LinearInterpolator
 import android.widget.TextView
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
            delay(4000)
            val intent = Intent(this@MainActivity, SignInActivity::class.java)
            // Use ActivityOptions to add a fade-in and fade-out animation
            val options = android.app.ActivityOptions.makeCustomAnimation(
                this@MainActivity, android.R.anim.fade_in, android.R.anim.fade_out
            )
            // Start the new activity with the transition
            startActivity(intent, options.toBundle())
             finish()
        }


        animateSplash()


    }

    private fun animateSplash() {
        val text = getString(R.string.task_by_ncanada_net_nrasool)

        // Set the HTML formatted text to the TextView
        binding.txt.text = Html.fromHtml(text)

        // Start the text animation
        animateText(binding.txt)
    }

    private fun animateText(textView: TextView) {
        // First, initially make the entire text invisible
        textView.alpha = 0f

        // Animate the text to gradually fade in over 4 seconds (you can adjust this)
        val fadeIn = ObjectAnimator.ofFloat(textView, "alpha", 0f, 1f)
        fadeIn.duration = 3000 // 4 seconds
        fadeIn.interpolator = LinearInterpolator()

        // Start the fade-in animation
        fadeIn.start()
    }
}